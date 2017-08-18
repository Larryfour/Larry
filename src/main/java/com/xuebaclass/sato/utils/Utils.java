package com.xuebaclass.sato.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
	private static final Logger logger = LoggerFactory.getLogger(Utils.class);
	private static final String ORDERUUID_FORMAT_STRING = "yyyyMMddHHmmsssss";

	/**
	 * 获取系统当前日期
	 * 
	 * @return
	 */
	public static String getNowDate() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		return time;
	}


	/**
	 * 处理保留两位小数
	 * 
	 * @param f
	 * @return
	 */
	public static double m1(double f) {
		BigDecimal bg = new BigDecimal(f);
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	/**
	 * DecimalFormat转换最简便
	 */
	public static String m2(double f) {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(f);
	}

	/**
	 * String.format打印最简便
	 */
	public static void m3(double f) {
		System.out.println(String.format("%.2f", f));
	}

	public static void m4(double f) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);
		System.out.println(nf.format(f));
	}

	/**
	 * 校验学吧号
	 */
	public static boolean isXuebaSequence(String content) {
		try {
			long sequence = Long.valueOf(content);
			long id = sequence / 10;
			long s = 0;
			do {
				s += id % 10;
			} while ((id = id / 10) != 0);
			if ((s * 3 + 1) % 10 == sequence % 10) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}


	/**
	 * 生成学吧号
	 * 
	 * @param uid
	 * @return
	 */
	public static long getXueBaNO(String uid) {
		String str = uid;
		int sum = str.chars().map(c -> c - 48).sum();
		return Long.parseLong(str + (sum * 3 + 1) % 10);
	}

	/**
	 * 获取支付标识
	 * 
	 * @param channel
	 * @return
	 */
	public static Integer getChannelFlag(String channel) {
		int channelFlag = 0;
		if (channel != null && !"".equals(channel)) {
			if ("alipay_wap".equals(channel)) {
				channelFlag = 1;
			} else if ("upacp_wap".equals(channel)) {
				channelFlag = 3;
			} else if ("wx_pub".equals(channel)) {
				channelFlag = 2;
			} else if ("upacp".equals(channel)) {
				channelFlag = 0;
			}
		}
		return channelFlag;
	}


	/**
	 * 根据本地日期获取UTC日期
	 * 
	 * @param dateStr
	 * @return
	 * @throws Exception
	 */
	public static String localDateToUTC(String dateStr) throws Exception {
		SimpleDateFormat foo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
		int dstOffset = cal.get(Calendar.DST_OFFSET);
		Date date;
		date = foo.parse(dateStr + " 08:01:00");
		cal.setTime(date);
		cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
		return foo.format(cal.getTimeInMillis());
	}

	/**
	 * Date to String
	 * 
	 * @param UTCTime
	 * @return
	 */
	public static String getDateStr(Date utcTime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(utcTime);
	}

	public static String utc2Local(String utcTime) {
		SimpleDateFormat utcFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));// 时区定义并进行时间获取
		Date gpsUTCDate = null;
		try {
			gpsUTCDate = utcFormater.parse(utcTime);
		} catch (ParseException e) {
			e.printStackTrace();
			return utcTime;
		}
		SimpleDateFormat localFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		localFormater.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		String localTime = localFormater.format(gpsUTCDate.getTime());
		return localTime;
	}

	/**
	 * 获取当前UTC时间
	 * 
	 * @return
	 */
	public static Date getUTC() {
		Calendar calendar = Calendar.getInstance();
		int offset = calendar.get(Calendar.ZONE_OFFSET);
		calendar.add(Calendar.MILLISECOND, -offset);
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * 判断两个日期的2小时内
	 * 
	 * @param end
	 * @param begin
	 * @return
	 */
	public static boolean isTwoHoursIner(Date end, Date begin) {
		boolean flag = false;
		long between = 0;
		SimpleDateFormat utcFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("end 日期"+utcFormater.format(end));
		logger.info("begin 日期"+utcFormater.format(begin));
		between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
		logger.info("两个日期之差(毫秒)：" + between);
		if (between < 7200000) {
			flag = true;
		}
		return flag;
	}
}
