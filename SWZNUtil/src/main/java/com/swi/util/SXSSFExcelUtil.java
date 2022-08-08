package com.swi.util;

import android.content.Context;
import android.text.TextUtils;

import com.swi.config.SWZNConfig;
import com.swi.mount.SingleGasRecord;
import com.swi.mount.SingleGasRecordInfo;
import com.swi.util.logs.SWZNLog;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFFont;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class SXSSFExcelUtil {

    private static final String DEVICE_EXCEL_PATH = SWZNConfig.BaseDirectory + File.separator + SWZNConfig.sTargetPicFolder;
    private static final String GAS_DETECTOR_EXCEL_PATH = SWZNConfig.BaseDirectory + File.separator + SWZNConfig.sGasDetectorFolder;

    public static String mGasDetectReport;
    public static String mdetectTime;
    public static String mdetectArea;
    public static String maverageConcentration;
    public static String mmaxConcentration;
    public static String mminConcentration;
    public static String mconcentration;
    public static void setParam(String gasDetectReport, String detectTime, String detectArea,
                                String averageConcentration, String maxConcentration,
                                String minConcentration, String concentration){
        mGasDetectReport = gasDetectReport;
        mdetectTime = detectTime;
        mdetectArea = detectArea;
        maverageConcentration = averageConcentration;
        mmaxConcentration = maxConcentration;
        mminConcentration = minConcentration;
        mconcentration = concentration;
    }

    public static <T> void  writeGasDetectorToExcel(final List<T> objList, final String fileName, Context context, final OnOperateListener onOperateListener){
        if (objList != null && objList.size() > 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 创建一个工作簿
                    SXSSFWorkbook workbook = new SXSSFWorkbook();
                    for (int i = 0; i < objList.size(); i++) {
                        SingleGasRecordInfo singleGasRecordInfo = (SingleGasRecordInfo) objList.get(i);
                        String name =  singleGasRecordInfo.getGasName();
                        if (TextUtils.isEmpty(name)) {
                            name = "test";
                        }
                        // 创建一个工作表sheet
                        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet(name);

                        int index = 0;
                        Row row =  sheet.createRow(index);
                        CellRangeAddress region = new CellRangeAddress(index, index + 2, 0, 8);
                        sheet.addMergedRegion(region);
                        SXSSFCell cell = (SXSSFCell) row.createCell(0);
                        CellStyle style1 = workbook.createCellStyle();//单元格样式
                        style1.setAlignment(CellStyle.ALIGN_CENTER);  //居中
                        style1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);  //垂直居中
                        XSSFFont font1 = (XSSFFont) workbook.createFont();
                        font1.setFontName("Courier New");
                        font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);  //加粗
                        font1.setFontHeightInPoints((short) 14);        //字体大小
                        font1.setColor(HSSFColor.BLACK.index);          //字体颜色
                        style1.setFont(font1);
                        style1.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());  //背景色
                        style1.setFillPattern(CellStyle.SOLID_FOREGROUND);  //添加背景色
                        cell.setCellStyle(style1);
                        cell.setCellValue(mGasDetectReport + "(" +singleGasRecordInfo.getGasName() + ")");
                        index += 3;

                        String createTime = "        " + mdetectTime + "：" + DateUtil.getCurrentDateString("yyyy-MM-dd HH:mm:ss") + "\n";
                        String area = "\n        " + mdetectArea + "：" + singleGasRecordInfo.getArea() + "m²";
                        CellRangeAddress region1 = new CellRangeAddress(index, index + 3, 0, 8);
                        sheet.addMergedRegion(region1);
                        Row row1 =  sheet.createRow(index);
                        Cell cell1 = row1.createCell(0);
                        cell1.setCellValue(createTime + area);
                        index += 4;

                        CellRangeAddress region2 = new CellRangeAddress(index, index + 1, 0, 8);
                        sheet.addMergedRegion(region2);
                        Row row2 =  sheet.createRow(index);
                        Cell cell2 = row2.createCell(0);
                        cell2.setCellValue(singleGasRecordInfo.getGasName());
                        CellStyle style2 = workbook.createCellStyle();//单元格样式
                        style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
                        XSSFFont font2 = (XSSFFont) workbook.createFont();
                        font2.setFontName("Courier New");
                        font2.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
                        font2.setFontHeightInPoints((short) 14);
                        font2.setColor(HSSFColor.BLACK.index);
                        style2.setFont(font2);
                        style2.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
                        style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
                        cell2.setCellStyle(style2);
                        index += 2;

                        StringBuilder generalInformation = new StringBuilder();
                        generalInformation.append("        " + maverageConcentration + "：");
                        generalInformation.append(singleGasRecordInfo.getAverageConcentration());
                        generalInformation.append(singleGasRecordInfo.getGasUnit());
                        generalInformation.append("\n");
                        generalInformation.append("         " + mmaxConcentration + "：");
                        generalInformation.append(singleGasRecordInfo.getMaxConcentration());
                        generalInformation.append(singleGasRecordInfo.getGasUnit());
                        generalInformation.append("\n");
                        generalInformation.append("         " + mminConcentration + "：");
                        generalInformation.append(singleGasRecordInfo.getMinConcentration());
                        generalInformation.append(singleGasRecordInfo.getGasUnit());
                        generalInformation.append("\n");
                        Row row3 =  sheet.createRow(index);
                        CellRangeAddress region3 = new CellRangeAddress(index, index + 3, 0, 8);
                        sheet.addMergedRegion(region3);
                        Cell cell3 = row3.createCell(0);
                        CellStyle style3 = workbook.createCellStyle();//单元格样式
                        style3.setWrapText(true);
                        cell3.setCellStyle(style3);
                        cell3.setCellValue(new HSSFRichTextString(generalInformation.toString()));
                        index += 4;

//                row = sheet.getRow(index);
                        CellRangeAddress region4 = new CellRangeAddress(index, index + 19, 0, 8);
                        sheet.addMergedRegion(region4);
                        insertPicCost(workbook, sheet, singleGasRecordInfo.getImagePath(), index);
                        index += 19;

                        List<SingleGasRecord> singleGasRecords = singleGasRecordInfo.getGasBeans();
                        int k = 1;
                        int lastPic = -1;
                        for (int j = 0; j < singleGasRecords.size(); j++) {
                            SingleGasRecord singleGasRecord = singleGasRecords.get(j);
                            byte gasType = GasDetectorUtil.getGasTypeByName(singleGasRecord.getGasBean());
                            boolean isExceed = false;
                            if (singleGasRecord.getGasBean().isConcentration()) {
                                isExceed = true;
                            }
                            if (!isExceed || TextUtils.isEmpty(singleGasRecord.getImagePath())) {  //如果有图片，则添加marker
                                continue;
                            }
                            if (lastPic == -1) {
                                lastPic = 0;
                            } else {
                                if (j - lastPic > 5) {
                                    lastPic = j;
                                } else {
                                    continue;
                                }
                            }
                            SWZNLog.LogD("test gas isExceed " + isExceed + " " + singleGasRecord.getImagePath());
//                            if (j % 3 != 0) {
//                                continue;
//                            }
//
//                            String path = "/storage/emulated/0/gdu/flight/FlightGallery/Flight-2D/任务1-20200116174248/GDU_19700119_153858660.png";
//                            singleGasRecord.setImagePath(path);

                            StringBuilder flaw = new StringBuilder();
                            flaw.append(k++);
                            flaw.append("       " + mconcentration + "：");
                            flaw.append(singleGasRecord.getGasBean().getGasConcentration());
                            flaw.append(singleGasRecord.getGasBean().getGasUnit());
                            flaw.append("        ");
                            flaw.append("(");
                            flaw.append(singleGasRecord.getLng());
                            flaw.append(",");
                            flaw.append(singleGasRecord.getLat());
                            flaw.append(")");
                            flaw.append("\n");

                            Row row4 =  sheet.createRow(index);
                            CellRangeAddress region5 = new CellRangeAddress(index, index + 2, 0, 8);
                            sheet.addMergedRegion(region5);
                            Cell cell4 = row4.createCell(0);
                            cell4.setCellValue(flaw.toString());
                            index += 2;

                            String imagePath = singleGasRecord.getImagePath();
                            insertPicCost(workbook, sheet, imagePath, index);
                            index += 19;
                        }
                    }
                    File fileDir = new File(DEVICE_EXCEL_PATH);
                    makeDir(fileDir);
                    // 创建一个文件
                    File file = new File(fileName);
                    try {
                        file.createNewFile();
                        // 打开文件流
                        FileOutputStream outputStream = new FileOutputStream(file);
                        workbook.write(outputStream);
                        outputStream.close();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    onOperateListener.onFinished();
                }
            }).start();

            SWZNLog.LogD("test  插入完成 : ");
        }
    }




    public static void main() {
        String[] title = { "id", "name", "sex" };
        // 创建一个工作簿
        Workbook workbook = new SXSSFWorkbook();
        // 创建一个工作表sheet
        Sheet sheet = workbook.createSheet();
        // 创建第一行
        Row row = sheet.createRow(0);
        CellRangeAddress region = new CellRangeAddress(1, 2, 0, 3);
        sheet.addMergedRegion(region);
        // 创建一个单元格
        Cell cell = null;
        // 创建表头
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
        // 从第二行开始追加数据
        for (int i = 4; i <= 7; i++) {
            // 创建第i行
//            Row nextRow = sheet.createRow(i);
//            // 参数代表第几列
//            Cell cell2 = nextRow.createCell(0);
//            cell2.setCellValue("a" + i);
//            cell2 = nextRow.createCell(1);
//            cell2.setCellValue("user" + i);
//            cell2 = nextRow.createCell(2);
//            cell2.setCellValue("男");
            insertPicCost(workbook, sheet, DEVICE_EXCEL_PATH + "1.png", i);
        }
       File fileDir = new File(DEVICE_EXCEL_PATH);
        makeDir(fileDir);
        // 创建一个文件
        File file = new File(DEVICE_EXCEL_PATH + "tt1.xls");
        try {
            file.createNewFile();
            // 打开文件流
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("插入完成 : ");
    }

    public static   void makeDir(File dir) {
        if (!dir.getParentFile().exists()) {
            makeDir(dir.getParentFile());
        }
        dir.mkdir();
    }

//    private static int index = 3;
    public static void insertPicCost(Workbook workBook,Sheet topPic,String pic, int index){
        try {
            FileInputStream fs = new FileInputStream(pic);
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while (-1 != (len = fs.read(buffer))) {
                byteArrayOut.write(buffer, 0, len);
            }
            byteArrayOut.close();
            fs.close();
            // 利用HSSFPatriarch将图片写入EXCEL
            XSSFDrawing patriarch = (XSSFDrawing) topPic.createDrawingPatriarch();
            //图片一导出到单元格B2中
            XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0,
                    (short) 0, index , (short) 7, index + 19);
            // 插入图片
            patriarch.createPicture(anchor, workBook.addPicture(byteArrayOut
                    .toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
            SWZNLog.LogD("test 插入成功 " + num++);
        } catch (IOException io) {
            io.printStackTrace();
            SWZNLog.LogD("test 插入失败 : " + io.getMessage());
        } finally {

        }
    }
    private static int num = 0;

    public  interface OnOperateListener{
        void onFinished();
    }
}
