package com.swzn.drone;

public class FourthGStatus {
   public byte _4g_module_pdp_active;  //0:PDP上下文激活失败           1:PDP上下文激活成功
   public byte _4g_module_start_status;  //0:连接RNDIS链路失败   1:连接RNDIS链路成功
   public byte _4g_module_network_status;  //0:获取IP地址成功         1：获取IP地址失败
   public byte _4g_net_visit_qianxun;  //0:登录千寻服务器失败            1：登录千寻服务器成功
   public byte _4g_net_send_rtk_data;  //0：发送RTK数据异常  1：发送RTK数据正常
   public byte _4g_net_status;        //0:无服务   1:2G网络   2:3G网络   3:4G网络
   public short _4g_signal_quality; //4g网络信号质量,一般范围(-110,0)   5：当前信号未知或不可测    10:未知错误
}
