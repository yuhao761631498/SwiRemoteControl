package com.swi.util;

import android.content.Context;
import android.text.TextUtils;


import com.swi.config.SWZNConfig;
import com.swi.drone.TargetMode;
import com.swi.mount.SingleGasRecord;
import com.swi.mount.SingleGasRecordInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/***
 * Excel操作类
 */
public class ExcelUtils {
    public static WritableFont arial14font = null;

    public static WritableCellFormat arial14format = null;
    public static WritableFont arial10font = null;
    public static WritableCellFormat arial10format = null;
    public static WritableFont arial12font = null;
    public static WritableCellFormat arial12format = null;

    public final static String UTF8_ENCODING = "UTF-8";
    public final static String GBK_ENCODING = "GBK";


    /**
     * 单元格的格式设置 字体大小 颜色 对齐方式、背景颜色等...
     */
    public static void format() {
        try {
            arial14font = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD);
            arial14font.setColour(jxl.format.Colour.LIGHT_BLUE);
            arial14format = new WritableCellFormat(arial14font);
            arial14format.setAlignment(jxl.format.Alignment.CENTRE);
            arial14format.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
            arial14format.setBackground(jxl.format.Colour.VERY_LIGHT_YELLOW);

            arial10font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            arial10format = new WritableCellFormat(arial10font);
            arial10format.setAlignment(jxl.format.Alignment.CENTRE);
            arial10format.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
            arial10format.setBackground(Colour.GRAY_25);

            arial12font = new WritableFont(WritableFont.ARIAL, 10);
            arial12format = new WritableCellFormat(arial12font);
            arial10format.setAlignment(jxl.format.Alignment.CENTRE);//对齐格式
            arial12format.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); //设置边框

        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化Excel
     * @param fileName
     * @param colName
     */
    public static void initExcel(String path, String fileName, String[] colName) {
        format();
        WritableWorkbook workbook = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            workbook = Workbook.createWorkbook(file);
            WritableSheet sheet = workbook.createSheet(fileName, 0);
            //创建标题栏
            sheet.addCell((WritableCell) new Label(0, 0, path,arial14format));
//            for (int col = 0; col < colName.length; col++) {
//                sheet.addCell(new Label(col, 0, colName[col], arial10format));
//            }
            sheet.setRowView(0,340); //设置行高

            workbook.write();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> void writeRecordLineToExcel(List<T> objList, String fileName, String imagePath, int imageWidth, int imageHeight, Context context) {
        if (objList != null && objList.size() > 0) {
            WritableWorkbook writebook = null;
            InputStream in = null;
            try {
                WorkbookSettings setEncode = new WorkbookSettings();
                setEncode.setEncoding(UTF8_ENCODING);
                in = new FileInputStream(new File(fileName));
                Workbook workbook = Workbook.getWorkbook(in);
                writebook = Workbook.createWorkbook(new File(fileName),workbook);
                WritableSheet sheet = writebook.getSheet(0);

                sheet.addCell(new Label(0, 0,"Flight path",arial12format));  //飞行路线地图标题
                sheet.mergeCells(0, 0, 3, 0);

                File file=new File(imagePath);
                WritableImage image=new WritableImage(0, 1, imageWidth, imageHeight,file);
                sheet.addImage(image);

                sheet.addCell(new Label(0, 19,"Flight data",arial12format)); //飞行路线轨迹标题
                sheet.mergeCells(0, 19, 3, 19);

                int imageRow = 19;
                for (int j = 0; j < objList.size(); j++) {
                    ArrayList<String> list = (ArrayList<String>) objList.get(j);
                    for (int i = 0; i < list.size(); i++) {
                        sheet.addCell(new Label(i, j + 1 + imageRow, list.get(i),arial12format));
                        if (list.get(i).length() <= 5){
                            sheet.setColumnView(i,list.get(i).length()+ 18); //设置列宽
                        }else {
                            sheet.setColumnView(i,list.get(i).length()+ 5); //设置列宽
                        }
                    }
                    sheet.setRowView(j+1,350); //设置行高
                }

                writebook.write();
//                Toast.makeText(c, "导出到手机存储中文件夹Record成功", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (writebook != null) {
                    try {
                        writebook.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }


    @SuppressWarnings("unchecked")
    public static <T> void writeDeviceToExcel(List<T> objList, String fileName,  Context c) {
        if (objList != null && objList.size() > 0) {
            WritableWorkbook writebook = null;
            InputStream in = null;
            try {
                WorkbookSettings setEncode = new WorkbookSettings();
                setEncode.setEncoding(UTF8_ENCODING);
                in = new FileInputStream(new File(fileName));
                Workbook workbook = Workbook.getWorkbook(in);
                writebook = Workbook.createWorkbook(new File(fileName),workbook);
                WritableSheet sheet = writebook.getSheet(0);
                int index = 0;
                WritableFont font1 = new WritableFont(WritableFont.ARIAL,14,WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
                WritableCellFormat wcTitle = new WritableCellFormat(font1);
                wcTitle.setAlignment(Alignment.CENTRE); // 设置居中
                wcTitle.setVerticalAlignment(VerticalAlignment.CENTRE);
                wcTitle.setBackground(Colour.GREY_40_PERCENT);
                sheet.mergeCells(0, index, 8, index + 2);   //合并0行 0-3列
                sheet.addCell(new Label(0, index,"电网器件识别报告",wcTitle));  //电网器件识别报告
                index += 3;

                WritableFont font12 = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false, UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
                WritableFont font14 = new WritableFont(WritableFont.ARIAL,14,WritableFont.NO_BOLD,false, UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
                WritableCellFormat wcCreateTime = new WritableCellFormat(font12);
                wcCreateTime.setAlignment(Alignment.RIGHT); // 设置居中
                wcCreateTime.setVerticalAlignment(VerticalAlignment.CENTRE);
                String createTime = "创建时间：" + DateUtil.getCurrentDateString("yyyy-MM-dd HH:mm:ss");
                sheet.mergeCells(0, index, 8, index + 1);  //合并1行 0-3列
                wcCreateTime.setAlignment(Alignment.RIGHT); // 设置居右
                sheet.addCell(new Label(0, index,createTime,wcCreateTime));  //电网器件识别报告
                index += 2;

                WritableCellFormat wcTotalInfo = new WritableCellFormat(font14);
                wcTotalInfo.setAlignment(Alignment.LEFT); // 设置居左
                wcTotalInfo.setVerticalAlignment(VerticalAlignment.CENTRE);
                wcTotalInfo.setBackground(Colour.LIGHT_BLUE);
                sheet.addCell(new Label(0, index,"一 、总体说明",wcTotalInfo));
                sheet.mergeCells(0, index, 8, index + 1);
                index += 2;

                StringBuilder generalInformation = new StringBuilder();
                generalInformation.append("        缺陷总数：");
                generalInformation.append(objList.size());
                generalInformation.append("\n");
                generalInformation.append("                        鸟巢：");
                generalInformation.append(objList.size());
                generalInformation.append("\n");
                generalInformation.append("                        其他：");
                generalInformation.append(0);
                generalInformation.append("\n");
                WritableCellFormat wcTotalDetail = new WritableCellFormat(font12);
                wcTotalDetail.setAlignment(Alignment.LEFT); // 设置居
                wcTotalDetail.setVerticalAlignment(VerticalAlignment.CENTRE);
                wcTotalDetail.setWrap(true);//是否自动换行
                sheet.addCell(new Label(0, index,generalInformation.toString(),wcTotalDetail));
                sheet.mergeCells(0, index, 8, index + 3);
                index += 4;

                WritableCellFormat wcRecord = new WritableCellFormat(font14);
                wcRecord.setAlignment(Alignment.LEFT); // 设置居左
                wcRecord.setVerticalAlignment(VerticalAlignment.CENTRE);
                wcRecord.setBackground(Colour.LIGHT_ORANGE);
                sheet.addCell(new Label(0, index,"二 、缺陷记录",wcRecord));
                sheet.mergeCells(0, index, 8, index + 1);
                index += 2;

//                sheet.mergeCells(0, index, 8, 3 * 20 + 2);
                for (int j = 0; j < objList.size(); j++) {
                    WritableCellFormat flawDetail = new WritableCellFormat(font12);
                    TargetMode targetMode = (TargetMode) objList.get(j);
                    String imagePath = targetMode.getImgPath();
                    StringBuilder flaw = new StringBuilder();
                    flaw.append("       缺陷");
                    flaw.append(j+1);
                    flaw.append("\n");
                    flaw.append("       缺陷类型：");
                    String targetType = "其他";
                    if (targetMode.getTargetType() == 0) {
                        targetType = "鸟巢";
                    } else if (targetMode.getTargetType() == 1) {
                        targetType = "绝缘子";
                    } else if(targetMode.getTargetType() == 2){
                        targetType = "防震锤";
                    }
                    flaw.append(targetType);
                    flaw.append("                 置信度：");
                    flaw.append(targetMode.getTargetConfidence());
                    flaw.append("\n");
                    flaw.append("       坐标：");
                    flaw.append(targetMode.getLng() * 1.0 / 10000000);
                    flaw.append(",      ");
                    flaw.append(targetMode.getLat() * 1.0 / 10000000);
                    flaw.append(",      ");
                    flaw.append(targetMode.getElevation() * 1.0 / 100);
                    flaw.append("\n");
                    flawDetail.setWrap(true);//是否自动换行
                    flawDetail.setVerticalAlignment(VerticalAlignment.CENTRE);
                    sheet.addCell(new Label(0, index , flaw.toString(), flawDetail));  //缺陷信息
                    sheet.mergeCells(0, index, 8, index + 3);
                    index += 4;
                    File file = new File(imagePath);
                    WritableImage image = new WritableImage(1, index, 7, 20, file);
                    sheet.addImage(image);
                    sheet.mergeCells(0, index, 8, index + 19);
                    index += 20;
                }
                writebook.write();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (writebook != null) {
                    try {
                        writebook.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public static String mdetectTime;
    public static String mdetectArea;
    public static String maverageConcentration;
    public static String mmaxConcentration;
    public static String mminConcentration;
    public static String mconcentration;
    public static void setParam(String detectTime, String detectArea,
                                String averageConcentration, String maxConcentration,
                                String minConcentration, String concentration){
        mdetectTime = detectTime;
        mdetectArea = detectArea;
        maverageConcentration = averageConcentration;
        mmaxConcentration = maxConcentration;
        mminConcentration = minConcentration;
        mconcentration = concentration;
    }

    @SuppressWarnings("unchecked")
    public static <T> void writeGasDetectorToExcel(List<T> objList, String fileName,  Context context) {
        if (objList != null && objList.size() > 0) {
            WritableWorkbook writebook = null;
            InputStream in = null;
            try {
                WorkbookSettings wbSetting  = new WorkbookSettings();
                wbSetting .setEncoding(UTF8_ENCODING);
                wbSetting .setUseTemporaryFileDuringWrite(true);
                String localTempFile = SWZNConfig.BaseDirectory + File.separator + SWZNConfig.sGasDetectorFolder;
                //临时文件夹的位置
                wbSetting.setTemporaryFileDuringWriteDirectory(new File(localTempFile));
                in = new FileInputStream(new File(fileName));
                Workbook workbook = Workbook.getWorkbook(in);
                writebook = Workbook.createWorkbook(new File(fileName),workbook, wbSetting);
                writeGasDetectorToSheet(writebook, objList, context);
                writebook.write();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (writebook != null) {
                    try {
                        writebook.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    /**
     * 写入单个气体记录到sheet
     * @param writebook
     * @param objList
     * @param <T>
     * @throws WriteException
     */
    public  static <T> void writeGasDetectorToSheet(WritableWorkbook writebook, List<T> objList, Context context) throws WriteException {
        for (int i = 0; i < objList.size(); i++) {
            SingleGasRecordInfo singleGasRecordInfo = (SingleGasRecordInfo) objList.get(i);
//            WritableSheet sheet = writebook.getSheet(i);
            String name = singleGasRecordInfo.getGasName();
            if (TextUtils.isEmpty(name)) {
                name = "test";
            }
            WritableSheet sheet = writebook.createSheet(name ,i);
            int index = 0;
            WritableFont font1 = new WritableFont(WritableFont.ARIAL,14,WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
            WritableCellFormat wcTitle = new WritableCellFormat(font1);
            wcTitle.setAlignment(Alignment.CENTRE); // 设置居中
            wcTitle.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcTitle.setBackground(Colour.GREY_40_PERCENT);
            sheet.mergeCells(0, index, 8, index + 2);   //合并0行 0-3列
            sheet.addCell(new Label(0, index,singleGasRecordInfo.getGasName(),wcTitle));  //电网器件识别报告
            index += 3;

            WritableFont font12 = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false, UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
            WritableFont font14 = new WritableFont(WritableFont.ARIAL,14,WritableFont.NO_BOLD,false, UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
            WritableCellFormat wcCreateTime = new WritableCellFormat(font12);
            wcCreateTime.setVerticalAlignment(VerticalAlignment.CENTRE);

            String createTime = "        " + mdetectTime + "：" + DateUtil.getCurrentDateString("yyyy-MM-dd HH:mm:ss") + "\n";
            String area = "\n        " + mdetectArea + "：" + singleGasRecordInfo.getArea() + "m²";
            sheet.mergeCells(0, index, 8, index + 3);  //合并1行 0-3列
            wcCreateTime.setAlignment(Alignment.LEFT); // 设置居左
            sheet.addCell(new Label(0, index,createTime + area,wcCreateTime));  //电网器件识别报告
            index += 4;

            WritableCellFormat wcTotalInfo = new WritableCellFormat(font14);
            wcTotalInfo.setAlignment(Alignment.LEFT); // 设置居左
            wcTotalInfo.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcTotalInfo.setBackground(Colour.LIGHT_BLUE);
            sheet.addCell(new Label(0, index,singleGasRecordInfo.getGasName(),wcTotalInfo));
            sheet.mergeCells(0, index, 8, index + 1);
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
            WritableCellFormat wcTotalDetail = new WritableCellFormat(font12);
            wcTotalDetail.setAlignment(Alignment.LEFT); // 设置居
            wcTotalDetail.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcTotalDetail.setWrap(true);//是否自动换行
            sheet.addCell(new Label(0, index,generalInformation.toString(),wcTotalDetail));
            sheet.mergeCells(0, index, 8, index + 3);
            index += 4;

            File file = new File(singleGasRecordInfo.getImagePath());
            WritableImage image = new WritableImage(1, index, 7, 20, file);
            sheet.addImage(image);
            sheet.mergeCells(0, index, 8, index + 19);
            index += 20;

            List<SingleGasRecord> singleGasRecords = singleGasRecordInfo.getGasBeans();
            int k = 1;
            for (int j = 0; j < singleGasRecords.size(); j++) {
                SingleGasRecord singleGasRecord = singleGasRecords.get(j);
                byte gasType = GasDetectorUtil.getGasTypeByName(singleGasRecord.getGasBean());
                boolean isExceed = false;
                if ((singleGasRecord.getGasExceedType() >> (gasType - 1)) == 1) {
                    isExceed = true;
                }
                if (!isExceed || TextUtils.isEmpty(singleGasRecord.getImagePath())) {  //如果有图片，则添加marker
                    continue;
                }
                WritableCellFormat flawDetail = new WritableCellFormat(font12);
                String imagePath = singleGasRecord.getImagePath();
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
                flawDetail.setWrap(true);//是否自动换行
                flawDetail.setVerticalAlignment(VerticalAlignment.CENTRE);
                sheet.addCell(new Label(0, index , flaw.toString(), flawDetail));  //缺陷信息
                sheet.mergeCells(0, index, 8, index + 3);
                index += 4;

                File singleGasRecordImageFile = new File(imagePath);
                if (singleGasRecordImageFile.exists()) {
                    WritableImage singleGasRecordImage = new WritableImage(1, index, 7, 20, singleGasRecordImageFile);
                    sheet.addImage(singleGasRecordImage);
                    sheet.mergeCells(0, index, 8, index + 19);
                    index += 20;
                }
            }
        }
    }

    public static void write()throws Exception{
        WritableWorkbook wwb=Workbook.createWorkbook(new File("c:/1.xls"));
        WritableSheet ws=wwb.createSheet("Test Sheet 1",0);
        File file=new File("C:\\jbproject\\PVS\\WebRoot\\weekhit\\1109496996281.png");
        WritableImage image=new WritableImage(1, 4, 6, 18,file);
        ws.addImage(image);
        wwb.write();
        wwb.close();
    }
}
