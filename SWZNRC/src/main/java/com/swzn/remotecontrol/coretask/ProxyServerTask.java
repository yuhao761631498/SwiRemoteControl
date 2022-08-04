package com.swzn.remotecontrol.coretask;

import android.text.TextUtils;

import com.swzn.util.logs.SWZNLog;
import com.swzn.util.SaveFileUtils;
import com.swzn.remotecontrol.ZOConstants;
import com.swzn.GlobalVariableTest;
import com.swzn.event.DownloadListenerEvent;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/** USB代理服务器管理类，负责接收client端数据，并发送到USB，接收USB数据，回复给client
 * Created by liangzi on 2016/11/15.
 *  此对象，有多个地方，容易出现空指针，一定要做非空处理。要不然就会出现，通信不畅的问题----xx
 */

public class ProxyServerTask extends ZeroTask {
    private Map<Integer, CacheSocket> mSocketMap;
    private ByteBuffer mUsbByteBuffer;
    private Selector mSelector;
    private DatagramChannel mDatagramChannel;

    public DisposeTask.OnGetUsbDataCallBack onGetUsbDataCallBack = new DisposeTask.
            OnGetUsbDataCallBack()
    {
        @Override
        public void onCallBack(byte[] data)
        {
            try{
                //连接类型：TCP(0)、UDP(1)
                if(data[0] == ZOConstants.TCP)
                {
                    sendUsbDataToTcp(data);
                }
                else if(data[0] == ZOConstants.UDP)
                {
                    sendUsbDataToUdp(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    /******************
     * 监听本地发送的9000端口 ---xx
     */
    private DatagramChannel mDatagramChannel_update;

    ServerSocketChannel mServerChannelUsb;

    public ProxyServerTask() {
        mSocketMap = new ConcurrentHashMap<>();
        mUsbByteBuffer = ByteBuffer.allocate(ZOConstants.BUFFER_SIZE_4104);
        receiverCacheData = new byte[2048 + 8];
        EventBus.getDefault().register(this);
    }

    @Override
    public void run() {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_AUDIO);
        mSelector = createSelecter();
        run(mSelector);
    }

    /** 创建selecter，并注册server */
    private Selector createSelecter(){
        Selector selector  = null;
        if(mSocketMap.size() > 0 )
        {
            SWZNLog.LogE("createSelecter=======mSocketSize>0");
        }
        try {
            //用于接收 app HTTP 请求的server
            try {
                selector = Selector.open( );
                ServerSocketChannel serverChannel = ServerSocketChannel.open( );
                ServerSocket serverSocket = serverChannel.socket( );
                serverSocket.setReuseAddress(true);
                serverSocket.bind (new InetSocketAddress(InetAddress.getLocalHost(), ZOConstants.LOCAL_PORT_TCP));
                serverChannel.configureBlocking (false);
                serverChannel.register (selector, SelectionKey.OP_ACCEPT, ZOConstants.LOCAL_PORT_TCP );
                mSocketMap.put(ZOConstants.LOCAL_PORT_TCP,new CacheSocket(serverChannel,System.currentTimeMillis()));
                SWZNLog.LogE("创建HTTP请求的Server================");
            } catch (IOException e) {
                e.printStackTrace();
            }


            //目前主要用途是 5.8G o2 和 遥控器进行 HTTP通信的服务类------xx
            try {
                ServerSocketChannel serverChannel = ServerSocketChannel.open( );
                ServerSocket serverSocket = serverChannel.socket( );
                serverSocket.setReuseAddress(true);
                serverSocket.bind (new InetSocketAddress(InetAddress.getLocalHost(), ZOConstants.LOCAL_PORT_RC_USB));
                serverChannel.configureBlocking (false);
                serverChannel.register (selector, SelectionKey.OP_ACCEPT, ZOConstants.LOCAL_PORT_RC_USB );
                mSocketMap.put(ZOConstants.LOCAL_PORT_RC_USB,new CacheSocket(serverChannel,System.currentTimeMillis()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //用于接收 app UDP数据的server,接受 app发送出来的UDP的数据(不包括升级指令) ---xx
            try {
                mDatagramChannel = DatagramChannel.open();
                DatagramSocket datagramSocket = mDatagramChannel.socket();
                datagramSocket.bind (new InetSocketAddress(InetAddress.getLocalHost(), ZOConstants.LOCAL_PORT_UDP));
                mDatagramChannel.configureBlocking (false);
                mDatagramChannel.register (selector, SelectionKey.OP_READ , ZOConstants.LOCAL_PORT_UDP);
                mSocketMap.put(ZOConstants.LOCAL_PORT_UDP,new CacheSocket(mDatagramChannel,System.currentTimeMillis()));
                SWZNLog.LogE("创建接受App UDP数据的Server");
            } catch (IOException e) {
                e.printStackTrace();
            }

            //用于接受 app发送 到 9000端口的数据-----xx
            try {
                mDatagramChannel_update = DatagramChannel.open();
                DatagramSocket datagramSocket = mDatagramChannel_update.socket( );
                datagramSocket.bind (new InetSocketAddress(InetAddress.getLocalHost(), ZOConstants.REMOTE_PORT_UDP_UPGRADE));
                mDatagramChannel_update.configureBlocking (false);
                mDatagramChannel_update.register (selector, SelectionKey.OP_READ , ZOConstants.REMOTE_PORT_UDP_UPGRADE);
                mSocketMap.put(ZOConstants.REMOTE_PORT_UDP_UPGRADE,new CacheSocket(mDatagramChannel_update,System.currentTimeMillis()));
                SWZNLog.LogE("创建接受App 9000端口 UDP数据的Server");
            } catch (IOException e) {
                e.printStackTrace();
            }

            //目前usb的数据，没有通过socket转发，而是通过队列来获取数据,故 先屏蔽掉下面的代码---xx
            //用于接收USB发送过来的数据，----xx
            try {
                mServerChannelUsb = ServerSocketChannel.open( );
                ServerSocket serverSocketUsb = mServerChannelUsb.socket( );
                serverSocketUsb.bind (new InetSocketAddress(InetAddress.getLocalHost(), ZOConstants.LOCAL_PORT_PROXY_SERVER));
                mServerChannelUsb.configureBlocking (false);
                mServerChannelUsb.register (selector, SelectionKey.OP_ACCEPT, ZOConstants.LOCAL_PORT_PROXY_SERVER);
//                mSocketMap.put(ZOConstants.LOCAL_PORT_PROXY_SERVER,mServerChannelUsb);
                SWZNLog.LogE("用于接收USB发送过来的数据============");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                ServerSocketChannel serverChannelFtpCmd = ServerSocketChannel.open( );
                ServerSocket serverSocketFtpCmd = serverChannelFtpCmd.socket( );
                serverSocketFtpCmd.bind (new InetSocketAddress(InetAddress.getLocalHost(), ZOConstants.LOCAL_PORT_FTP_CMD));
                serverChannelFtpCmd.configureBlocking (false);
                serverChannelFtpCmd.register (selector, SelectionKey.OP_ACCEPT, ZOConstants.LOCAL_PORT_FTP_CMD );
//                mSocketMap.put(ZOConstants.LOCAL_PORT_FTP_CMD,serverChannelFtpCmd);
                mSocketMap.put(ZOConstants.LOCAL_PORT_FTP_CMD,new CacheSocket(serverChannelFtpCmd,0));
                SWZNLog.LogE("接收FTP命令的server======================");
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            try {
                ServerSocketChannel serverChannelFtpData = ServerSocketChannel.open( );
                ServerSocket serverSocketFtpData = serverChannelFtpData.socket( );
                serverSocketFtpData.bind (new InetSocketAddress(InetAddress.getLocalHost(), ZOConstants.LOCAL_PORT_FTP_DATA));
                serverChannelFtpData.configureBlocking (false);
                serverChannelFtpData.register (selector, SelectionKey.OP_ACCEPT, ZOConstants.LOCAL_PORT_FTP_DATA );
//                mSocketMap.put(ZOConstants.LOCAL_PORT_FTP_DATA,serverChannelFtpData);
                mSocketMap.put(ZOConstants.LOCAL_PORT_FTP_DATA,new CacheSocket(serverChannelFtpData,0));
                SWZNLog.LogE("接收FTP数据的server =============");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return selector;
    }

    /******************************
     *  关闭SocketChannel
     */
    private void closeSocketChannel()
    {
        if(mSelector == null) return;
        Iterator it = mSelector.selectedKeys().iterator( );
        while (it.hasNext())
        {
            SelectionKey key = (SelectionKey) it.next( );
            try {
                key.channel().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    boolean isBeginCheck;
    private Runnable clearCacheSocket = new Runnable() {
        @Override
        public void run()
        {
           while ( isBeginCheck )
           {
                Iterator<Integer> it = mSocketMap.keySet().iterator();
                while (it.hasNext())
                {
                    Integer key = it.next();
                    CacheSocket cacheSocket = mSocketMap.get(key);
                    if(cacheSocket.canBreak)
                    {
                        if( System.currentTimeMillis() -  cacheSocket.lastUpdateTime > 3000 )
                        {
                            byte[]  data = getTcpBytes(2,key, ZOConstants.REMOTE_PORT_TCP , 0);
                            try {
                                sendDataToUsb(data);
                                if(cacheSocket.abstractSelectableChannel != null && cacheSocket.abstractSelectableChannel.isOpen())
                                {
                                    cacheSocket.abstractSelectableChannel.close();
                                    mSocketMap.remove(key);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
           }
        }
    };

    /** 运行selecter */
    private void run(Selector selector){
        try {
            while (!isClose) {
                int n = selector.select( );
                if (n == 0) {
                    continue; // nothing to do
                }
                if(!selector.isOpen()){
                    return;
                }
                Iterator it = selector.selectedKeys().iterator( );
                while (it.hasNext( ) && !isClose) {
                    SelectionKey key = (SelectionKey) it.next( );
                    int  att = (Integer)key.attachment();
                    try
                    {
                        if (key.isAcceptable( ))
                        {
                            registerChannel(selector, key, att);
                        }
                        if(key.isReadable( ))
                        {
                            readData(key, att);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        key.cancel();
                    }
                    it.remove( );
                }
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**可读后，读取数据*/
    private void readData(SelectionKey key, int att)  {
        try {
            switch (att){
                case ZOConstants.LOCAL_PORT_TCP:
                    readTcpData(key);
                    break;
                case ZOConstants.LOCAL_PORT_RC_USB:
                    readTcpDataWifi(key);
                    break;
                case ZOConstants.LOCAL_PORT_UDP:
                    readUdpData(key);
                    break;
                case ZOConstants.REMOTE_PORT_UDP_UPGRADE:
                    if(GlobalVariableTest.csIsLastVersion == 2)
                        readUdpDataUpdate(key);
                    break;

                //USB的获取，目前改为从回调中获取---xx
                case ZOConstants.LOCAL_PORT_PROXY_SERVER:
                        readDataFromUsb2(key);
                    break;

                case ZOConstants.LOCAL_PORT_FTP_CMD:
                    readCmdFromFtp(key);
                    break;
                case ZOConstants.LOCAL_PORT_FTP_DATA:
                    readDataFromFtp(key);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            SWZNLog.LogE("=========:"+att);
        }
    }

    /**server socket 接收到client 连接后将该client注册到selected 中*/
    private void registerChannel(Selector selector, SelectionKey key, int netType){
        try {

            ServerSocketChannel server = (ServerSocketChannel) key.channel( );
            SocketChannel channel = server.accept( );
            if (channel != null) {
                channel.configureBlocking (false);
                channel.register (selector, SelectionKey.OP_READ  , netType );
                //给usb发送 已接受连接的消息
                sendConnecting(netType, channel);
                //HTTP连接后发生的
                //保存端口对应的channel，后面write数据的时候使用
                if(!mSocketMap.containsKey(channel.socket().getPort()))
                {
                    //如果连接的是 LOCAL_PORT_PROXY_SERVER，说明是和USB通信的中间层的Socket,
                    //如果不是，说明是HTTP了。---xx.
                    mSocketMap.put(ZOConstants.LOCAL_PORT_PROXY_SERVER == netType ?
                            ZOConstants.LOCAL_PORT_PROXY_CLIENT : channel.socket().getPort(), new CacheSocket(channel,System.currentTimeMillis(),true));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送正在连接的状态给USB。 只有TCP连接有该状态
     * @param netType   连接类型，命令，数据
     * @param channel
     */
    private void sendConnecting(int netType, SocketChannel channel) throws IOException {
        byte[] data = null;
        if(netType == ZOConstants.LOCAL_PORT_FTP_CMD ){
            data = getTcpBytes(0, channel.socket().getPort(), ZOConstants.REMOTE_PORT_FTP_CMD_23, 0);
        }else if( netType == ZOConstants.LOCAL_PORT_FTP_DATA){
            data = getTcpBytes(0, channel.socket().getPort(), ZOConstants.REMOTE_PORT_FTP_DATA_3412, 0);
        }else if(netType == ZOConstants.LOCAL_PORT_TCP){
            data = getTcpBytes(0, channel.socket().getPort(), ZOConstants.REMOTE_PORT_TCP , 0);
        } else if(netType == ZOConstants.LOCAL_PORT_RC_USB){
            data = getTcpBytes(0, channel.socket().getPort(), ZOConstants.LOCAL_PORT_RC_USB , 0);
        }
        sendDataToUsb(data);
    }

    /**接收TCP client 端发来的数据，然后放在队列中*/
    private void readTcpData(SelectionKey selKey) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(ZOConstants.BUFFER_SIZE_1024);
        SocketChannel socketChannel = (SocketChannel) selKey.channel();
        Socket socket = socketChannel.socket();
        int port = socket.getPort();
        int readLen = socketChannel.read(byteBuffer);
        if ( readLen > 0) {
            byte [] data = getTcpBytes(3, port, ZOConstants.REMOTE_PORT_TCP , readLen );
            System.arraycopy(byteBuffer.array(), 0, data, 8, ZOConstants.BUFFER_SIZE_1024);
            if(readLen< 500)
            {
                String url = new String(data,8,readLen);
                if(url.startsWith("GET"))
                {
                   int index =   url.indexOf("HTTP/1.1");
                    String  a = url.substring(0,index -1);
                    CacheSocket cacheSocket =  mSocketMap.get(port);
                    if(cacheSocket != null )
                    {
                        cacheSocket.downLoadURL = a;
                    }
                }
                SWZNLog.LogE("url:"+ url);
            }
            SWZNLog.LogE("sendDataToUsb:====="+  readLen + ","+ data.length);
            sendDataToUsb(data);
        }else{//关闭 socket
            byte[]  data = getTcpBytes(2,port, ZOConstants.REMOTE_PORT_TCP , 0);
            sendDataToUsb(data);
            closeChannel(selKey, port);
        }
    }

    /**
     * 向飞机端发送释放http链接的指令  余浩
     */
    private void sendDisConnect( int port) {
        if(mSocketMap.containsKey(port)){
            try {
                byte[] data = getTcpBytes(2, port, ZOConstants.REMOTE_PORT_TCP, 0);
                sendDataToUsb(data);
//                YhLog.LogE("channel.socket().getPort()="+channel.socket().getPort());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /********************************
     *  下载完成后的反馈---xx
     */
    @Subscribe(threadMode = ThreadMode.BackgroundThread)
    public void downLoadSuccess(DownloadListenerEvent event)
    {
        if(event.getType() == 6 )
        {
            Iterator<Integer> it = mSocketMap.keySet().iterator();
            List<Integer> needRemoveKeys = new ArrayList<>();
            while (it.hasNext())
            {
                Integer key = it.next();
                CacheSocket cacheSocket = mSocketMap.get(key);
                if(cacheSocket.canBreak)
                {
                       if(TextUtils.isEmpty(cacheSocket.downLoadURL))
                           continue;
                        if(!cacheSocket.downLoadURL.trim().endsWith(event.getFilePath().substring(event.getFilePath().length() - 20)))
                            continue;
                        needRemoveKeys.add(key);

                }
            }

            for(Integer key : needRemoveKeys)
            {
                CacheSocket cacheSocket = mSocketMap.get(key);
                if(cacheSocket == null) continue;
                byte[]  data = getTcpBytes(2,key, ZOConstants.REMOTE_PORT_TCP , 0);
                try {
                    sendDataToUsb(data);
                   /* if(cacheSocket.abstractSelectableChannel != null && cacheSocket.abstractSelectableChannel.isOpen())
                    {
                        cacheSocket.abstractSelectableChannel.close();
                        cacheSocket.abstractSelectableChannel = null;
                    }*/
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void readTcpDataWifi(SelectionKey selKey) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(ZOConstants.BUFFER_SIZE_1024);
        SocketChannel socketChannel = (SocketChannel) selKey.channel();
        Socket socket = socketChannel.socket();
        int port = socket.getPort();
        int readLen = socketChannel.read(byteBuffer);
        if ( readLen > 0) {
            byte [] data = getTcpBytes(3, port, ZOConstants.LOCAL_PORT_RC_USB , readLen );
            SWZNLog.LogE("send data 2 RC:"+ port);
            System.arraycopy(byteBuffer.array(), 0, data, 8, ZOConstants.BUFFER_SIZE_1024);
            sendDataToUsb(data);
        }else{//关闭 socket
            closeChannel(selKey, port);
            SWZNLog.LogE("ProxyServerTask:TCPdata readLen == -1");
        }
    }

    /**接收 UDP client 端发来的数据，然后放在队列中*/
    private void readUdpDataUpdate(SelectionKey selKey) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(ZOConstants.BUFFER_SIZE_1024);
        DatagramChannel mChannel = (DatagramChannel) selKey.channel();
        InetSocketAddress var2len = (InetSocketAddress) mChannel.receive(byteBuffer);
        int len = getUnsignedByte((byte)(byteBuffer.array()[1] + 4));
        SWZNLog.LogE("eeeeeeeeeee:"+var2len.getPort());
        //固件升级的端口为 9000
        byte[] data = getUdpBytes(byteBuffer, var2len.getPort(),
                ZOConstants.REMOTE_PORT_UDP_UPGRADE ,
                len);
        sendDataToUsb(data);
    }

    /**接收 UDP client 端发来的数据，然后放在队列中*/
    private void readUdpData(SelectionKey selKey) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(ZOConstants.BUFFER_SIZE_1024);
        DatagramChannel mChannel = (DatagramChannel) selKey.channel();
        InetSocketAddress var2len = (InetSocketAddress) mChannel.receive(byteBuffer);
        int len = getUnsignedByte((byte)(byteBuffer.array()[1] + 4));
        //固件升级的端口为 9000
        byte[] data = getUdpBytes(byteBuffer, var2len.getPort(),
                (byteBuffer.array()[2] == 0xFF || byteBuffer.array()[2] == 0xFE) ? ZOConstants.REMOTE_PORT_UDP_UPGRADE : ZOConstants.REMOTE_PORT_UDP,
                len);
        sendDataToUsb(data);
    }

    /***************************
     *  接受到cachedata ---xx
     */
    private byte[] receiverCacheData;

    /*******************
     * 接受cache的postion的位置---xx
     */
    private int receiverCachePosition;

    /**************************
     * 当前包的长度-----xx
     */
    private int currentPackageLength;

    /**接收USB发送来的数据，将对应的数据包发送到对应的端口。
     *  此方案适配定长512的方案
     * */
    private void readDataFromUsb(SelectionKey selKey)  throws IOException {
        mUsbByteBuffer.clear();
        SocketChannel socketChannel = (SocketChannel) selKey.channel();
        socketChannel.read(mUsbByteBuffer);
        byte[] data = mUsbByteBuffer.array();
            if( receiverCachePosition  == 0  )
            {
                currentPackageLength = getDataLength(data) + 8;
                if(currentPackageLength < 1409 && data[0] < 2 )
                {//加1层保险，防止一包出现问题
                    if (currentPackageLength < ZOConstants.BUFFER_SIZE_512 ) //一包小于512个字节
                    {
                        //连接类型：TCP(0)、UDP(1)
                        if (data[0] == ZOConstants.TCP) {
                            sendUsbDataToTcp(data);
                        } else if (data[0] == ZOConstants.UDP)
                        {
                            sendUsbDataToUdp(data);
                        }
                        currentPackageLength = 0;
                    } else {
                        System.arraycopy(data, 0, receiverCacheData, 0, data.length);
                        receiverCachePosition = data.length;
                    }
                }else
                {
                    SWZNLog.LogE("512 create package is err : " + currentPackageLength +","+ data[0]);
                }
            }else
            {
                System.arraycopy(data,0,receiverCacheData,receiverCachePosition,data.length);
                receiverCachePosition += data.length;
                if(currentPackageLength <= receiverCachePosition )
                {
                    //连接类型：TCP(0)、UDP(1)
                    if(receiverCacheData[0] == ZOConstants.TCP)
                    {
                        sendUsbDataToTcp(receiverCacheData);
                    }else if(receiverCacheData[0] == ZOConstants.UDP)
                    {
                        sendUsbDataToUdp(receiverCacheData);
                    }
                    currentPackageLength = 0;
                    receiverCachePosition = 0;
                }
            }
    }

    /**
     * 接收USB发送来的数据，将对应的数据包发送到对应的端口。
     *  此方法适配 定长 4104的方案
     * */
    private void readDataFromUsb2(SelectionKey selKey)  throws IOException {
        mUsbByteBuffer.clear();
        SocketChannel socketChannel = (SocketChannel) selKey.channel();
        socketChannel.read(mUsbByteBuffer);
        byte[] data = mUsbByteBuffer.array();
        //连接类型：TCP(0)、UDP(1)
        if(data[0] == ZOConstants.TCP)
        {
            sendUsbDataToTcp(data);
        }else if(data[0] == ZOConstants.UDP)
        {
            sendUsbDataToUdp(data);
        }
    }

    private SaveFileUtils saveFileUtils;
    /**将USB返回的数据发送给 各个请求的UDP client*/
    private void sendUsbDataToUdp(byte[] data) throws IOException {
        int destPort = getDestPort(data);
        int length = getDataLength(data);
        if(length <= 0 || length > ZOConstants.BUFFER_SIZE_4104 - 8)
            return;
        if(mDatagramChannel != null )
            {
                mDatagramChannel.send(ByteBuffer.wrap(data,8,length),
                        new InetSocketAddress(InetAddress.getLocalHost(),destPort));
            }
    }

    /**将USB返回的数据发送给 各个请求的TCP client*/
    private void sendUsbDataToTcp(byte[] data) throws IOException {
        CacheSocket cacheSocket =  mSocketMap.get(getDestPort(data));
        int dataType = data[1];
        if( cacheSocket == null ) return;
            //TODO 8002过不去，正在找原因  ---xx
            if(getDestPort(data) == 8002)
            {
                return;
            }
//        if(!(cacheSocket.abstractSelectableChannel instanceof  SocketChannel))
//           return;

            SocketChannel serverChannel = (SocketChannel) cacheSocket.abstractSelectableChannel;
        switch (dataType){
            case ZOConstants.CONNECTING://连接中
                break;
            case ZOConstants.CONNECTED://已连接
                break;
            case ZOConstants.DATA://发送数据

                if(serverChannel != null && serverChannel.isOpen())
                {
                    int length = getDataLength(data);
                    if(length>0  && length <= ZOConstants.BUFFER_SIZE_4104 - 8) {
                        ByteBuffer buffer = ByteBuffer.wrap(data, 8, length);
//                        if (length<ZOConstants.BUFFER_SIZE_4104 - 8){
//                            sendDisConnect(getDestPort(data));
//                        }
                        while(buffer.hasRemaining()) {
                            serverChannel.write(buffer);
                        }
                    }
                }
                break;
            case ZOConstants.DISCONNECTED://断开连接
                mSocketMap.remove(getDestPort(data));
                if(serverChannel != null)
                {
                    byte[]  closeData = getTcpBytes(2,getDestPort(data), ZOConstants.REMOTE_PORT_TCP , 0);
                    sendDataToUsb(closeData);
                    serverChannel.close();

                }
                break;
        }
    }

    /**从FTP client 端读取命令发送给 USB*/
    private void readCmdFromFtp(SelectionKey key)
    {
        ByteBuffer byteBuffer = ByteBuffer.allocate(ZOConstants.BUFFER_SIZE_1024);
        try {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            Socket socket = socketChannel.socket();
            int port = socket.getPort();
            int readLen = socketChannel.read(byteBuffer);
            if ( readLen > 0) {
                byte[] data = getTcpBytes(3, port, ZOConstants.REMOTE_PORT_FTP_CMD_23, readLen);
                System.arraycopy(byteBuffer.array(), 0, data, 8, ZOConstants.BUFFER_SIZE_1024);
                sendDataToUsb(data);
            }else{//关闭 socket
                closeChannel(key, port);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**从FTP client 端读取数据发送给 USB*/
    private void readDataFromFtp(SelectionKey key)
    {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            Socket socket = socketChannel.socket();
            int port = socket.getPort();
            int readLen = socketChannel.read(byteBuffer);
            if ( readLen > 0)
            {
                byte[] data = getTcpBytes(3, port, ZOConstants.REMOTE_PORT_FTP_DATA_3412, readLen);
                System.arraycopy(byteBuffer.array(), 0, data, 8, ZOConstants.BUFFER_SIZE_1024);
                sendDataToUsb(data);
            }
            else
            {//关闭 socket
                byte[] data = getTcpBytes(2, port, ZOConstants.REMOTE_PORT_FTP_DATA_3412, 0);
                sendDataToUsb(data);
                closeChannel(key, port);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**发送*/
    private void sendDataToUsb(byte[] data) throws IOException
    {
        CacheSocket cacheSocket = mSocketMap.get(ZOConstants.LOCAL_PORT_PROXY_CLIENT);
        if(cacheSocket != null && data != null)
        {
            SocketChannel socketProxy = (SocketChannel)cacheSocket.abstractSelectableChannel;
            if(socketProxy != null)
            socketProxy.write(ByteBuffer.wrap(data));
        }
    }

    /**关闭 通道*/
    private void closeChannel(SelectionKey key, int port) throws IOException {
        if(mSocketMap.containsKey(port)){
            mSocketMap.get(port).abstractSelectableChannel.close();
            mSocketMap.remove(port);
        }
        key.cancel();
        if(key.channel() != null){
            key.channel().close();
        }
    }

    /**获取包含协议头的字节数组*/
    private byte[] getUdpBytes(ByteBuffer byteBuffer, int port, int remotePort, int len) {
        byte [] data = new byte[ZOConstants.BUFFER_SIZE_1024 + 8];
        data[0] = (byte) 1 ;//UDP
        data[1] = (byte) 3 ;//请求头
        data[2] = (byte) (port & 0xFF) ;
        data[3] = (byte) (port >>> 8 & 0xFF) ;
        data[4] = (byte) (remotePort  & 0xFF) ;
        data[5] = (byte) (remotePort >>> 8 & 0xFF) ;
        data[6] = (byte) (len & 0xFF) ;
        data[7] =  (byte) (len >>> 8 & 0xFF) ;
        System.arraycopy(byteBuffer.array(), 0, data, 8, ZOConstants.BUFFER_SIZE_1024);
        return data;
    }

    /**获取包含协议头的字节数组*/
    private byte[] getTcpBytes(int connType, int localPort, int remotePort, int len) {
        byte[] data = new byte[ZOConstants.BUFFER_SIZE_1024 + 8];
        data[0] = (byte) 0;//tcp
        data[1] = (byte) connType;//connecting
        data[2] = (byte) (localPort & 0xFF);
        data[3] = (byte) (localPort >>> 8 & 0xFF);
        data[4] = (byte) (remotePort & 0xFF);
        data[5] = (byte) (remotePort >>> 8 & 0xFF);
        data[6] = (byte) (len & 0xFF);
        data[7] = (byte) (len >>> 8 & 0xFF);
        return data;
    }

    /**获取远程端口*/
    private int getDestPort(byte[] data){
        return get2ByteToInt(data[4], data[5]);
    }

    /**获取远程端口*/
    private int getSourcePort(byte[] data){
        return get2ByteToInt(data[2], data[3]);
    }

    /**获取数据长度*/
    private int getDataLength(byte[] data){
        return  get2ByteToInt(data[6], data[7]);
    }

    public static int getUnsignedByte(byte var0) {
        int var1 = var0;
        if (var0 < 0) {
            var1 = var0 + 256;
        }

        return var1;
    }

    public static int get2ByteToInt(byte var0, byte var1) {
        int var = var1 << 8 | getUnsignedByte(var0);
        if(var < 0){
            var += 65536;
        }
        return var;
    }

    /**关闭，清除数据*/
    @Override
    public void close() throws IOException {
        isClose = true;
        SWZNLog.LogE("调用关闭端口的连接");
        if(mServerChannelUsb != null)
        {
            if(mServerChannelUsb.socket().isBound() && !mServerChannelUsb.socket().isClosed())
            {
                mServerChannelUsb.socket().close();
                mServerChannelUsb = null;
            }
        }
        try {
            if(mSocketMap != null){
                for(CacheSocket channel : mSocketMap.values()){
                    if(channel != null && channel.abstractSelectableChannel != null){
                        channel.abstractSelectableChannel.close();
                    }
                }
                mSocketMap.clear();
                mSocketMap = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if(mDatagramChannel != null){
                mDatagramChannel.close();
                mDatagramChannel = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            if(mDatagramChannel_update != null){
                mDatagramChannel_update.close();
                mDatagramChannel_update = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            if(mSelector != null){
                closeSocketChannel();
                mSelector.close();
                mSelector = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        EventBus.getDefault().unregister(this);
    }

}
