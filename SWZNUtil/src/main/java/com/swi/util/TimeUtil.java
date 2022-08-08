package com.swi.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by xx on 2016/7/22.
 */
public class TimeUtil {
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
    public static SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");

    /**
     * <p>获取时间的string</p>
     * <p>时间戳</p>
     *
     * @param time 时间为0的时间戳
     * @return
     */
    public static String getTimeStr(int time) {
        Date date = new Date(time * 1000L);
        return simpleDateFormat.format(date);
    }

    public static String getTimeStrCount(short seconds) {
        String standardTime;
        if (seconds <= 0){
            standardTime = "00:00";
        } else if (seconds < 60) {
            standardTime = String.format(Locale.getDefault(), "00:%02d", seconds % 60);
        } else if (seconds < 3600) {
            standardTime = String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60);
        } else {
            standardTime = String.format(Locale.getDefault(), "%02d:%02d:%02d", seconds / 3600, seconds % 3600 / 60, seconds % 60);
        }
        return standardTime;
    }


//    /**
//     * @param time
//     * @return
//     */
//    public static String getTimeFormate(String time) {
//        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINA);
//        Date date;
//        StringBuilder stringBuilder = new StringBuilder();
//        try {
//            date = sdr.parse(time);
//            long l = date.getTime();
//            String stf = String.valueOf(l);
//            String[] split = stf.split("-");
//            stringBuilder.append(split[0]);
//            stringBuilder.append(split[1]);
//            stringBuilder.append(split[2]);
//            stringBuilder.append(" ");
//            stringBuilder.append(split[3]);
//            stringBuilder.append(":");
//            stringBuilder.append(split[4]);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return stringBuilder.toString();
//    }


    /**
     * <p>获取当前时间的string</p>
     * <p>时间戳</p>
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    public static String getCurrentTimeStr() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }


    /**
     * 获取东八区的中国时间
     *
     * @param time 时间戳
     * @return
     */
    public static String getTime(long time) {
        return getTime(time, "yyyyMMdd_HHmmssSSS");
    }

    /**
     * 根据时间戳获取小时和分钟  HH:MM
     *
     * @param time 毫秒
     * @return
     */
    public static String getHourAndMinute(long time) {
        long timeS = time / 1000;
        int minute = (int) (timeS / 60);
        int minuteR = minute % 60;
        int hour = (int) (timeS / 3600);
        return hour + ":" + minuteR;
    }

    /**
     * 根据时间戳和时间类型获取时间string
     *
     * @param time
     * @param format
     * @return
     */
    public static String getTime(long time, String format) {
        SimpleDateFormat sdr = new SimpleDateFormat(format, Locale.CHINA);
        sdr.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String times = sdr.format(new Date(time));
        return times;
    }

    public static int[] getDateByTimeStamp(long timeStamp) {
        int[] timeArray = new int[7];
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        timeArray[0] = calendar.get(Calendar.YEAR);
        timeArray[1] = calendar.get(Calendar.MONTH);
        timeArray[2] = calendar.get(Calendar.DAY_OF_MONTH);
        timeArray[3] = calendar.get(Calendar.HOUR_OF_DAY);
        timeArray[4] = calendar.get(Calendar.MINUTE);
        timeArray[5] = calendar.get(Calendar.SECOND);
        timeArray[6] = calendar.get(Calendar.MILLISECOND);
        return timeArray;
    }


    /**
     * 国际化时间 带时区和夏时令
     *
     * @return
     */
    public static long getTimeForTimeZone() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            Date date = dateFormat.parse("2000-01-01 00:00:00");
            Date curDate = new Date();
            Calendar baseCalendar = Calendar.getInstance();

            baseCalendar.setTime(date);

            int baseDstOffest = baseCalendar.get(Calendar.DST_OFFSET);

            Calendar curCalendar = Calendar.getInstance();

            curCalendar.setTime(dateFormat.parse(dateFormat.format(curDate)));

            int curDstOffest = curCalendar.get(Calendar.DST_OFFSET);
            //baseCalendar.getTimeInMillis()-baseDstOffest 因为起始时间如俄罗斯2000-01-01 00:00:00，我们把这个时间定为0时，但实际上没有考虑夏令时实际时间是baseDstOffest ，因此我们要定为0时必须在当时的时间减掉dst偏移量

            long curtime = curCalendar.getTimeInMillis() - baseCalendar.getTimeInMillis() - baseDstOffest + curDstOffest;
            //经过上面的加减最终得到了一个准确的时间
            return curtime;
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }


    /**
     * 通过HH:MM:SS获取时间
     *
     * @param time
     * @return
     */
    public static long getTimeStampByHMSString(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("HHmmssSSS", Locale.CHINA);
        Date date = null;
        try {
            date = sdr.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long timeL = date.getTime();
        return timeL;
    }

    /**
     * 根据时间字符串获取时间戳
     * @param time
     * @param format
     * @return
     */
    public static long getTimeStamp(String time, String format){
        SimpleDateFormat sdr = new SimpleDateFormat(format, Locale.CHINA);
        Date date = null;
        try {
            date = sdr.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long timeL = date.getTime();
        return timeL;
    }


    /**
     * 根据年月获取时间戳
     *
     * @param time
     * @return
     */
    public static long getTimeStampByyyMMtring(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyMM");
        Date date = null;
        try {
            date = sdr.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long timeL = date.getTime();
        return timeL;
    }

    public static String getTimeString(long timeStamp) {
        int[] times = getDateByTimeStamp(timeStamp);
        StringBuilder sb = new StringBuilder();
        if (times[3] - 8 > 0) {
            int hour = times[3] - 8;
            sb.append(hour + "H");
        }
        if (times[4] > 0) {
            sb.append(times[4] + "M");
        }
        if (times[5] > 0) {
            sb.append(times[5] + "S");
        }
        return sb.toString();
    }

    /**
     * 根据时间戳和格式,时区获取时间
     * @param time
     * @param format
     * @return
     */
    public static String getTime(long time, String format, String zone){
        SimpleDateFormat sdr = new SimpleDateFormat(format, Locale.CHINA);
        sdr.setTimeZone(TimeZone.getTimeZone(zone));
        String times = sdr.format(new Date(time));
        return times;
    }

    /**
     * 获取当前日期
     * @return
     */
    public static String getCurrentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMdd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }
}
