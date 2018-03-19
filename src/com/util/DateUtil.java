package com.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * 鏃ユ湡宸ュ叿绫�
 * 
 */
public class DateUtil {

	/**
	 * 鏃ユ湡涓庢椂闂�
	 */
	public final static String BOTH = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 鏃ユ湡
	 */
	public final static String DATE = "yyyy-MM-dd";
	
	public final static String DATE_NO = "yyyyMMdd";
	/**
	 * 鏃堕棿
	 */
	public final static String TIME = "HH:mm:ss";
	/**
	 * 涓�棩鐨勬绉掓暟
	 */
	public static long MILLION_SECONDS_OF_DAY = 24 * 60 * 60 * 1000L;// 86400000
	/**
	 * 
	 * 涓�皬鏃剁殑姣鏁�
	 */
	public static long MILLION_SECONDS_OF_HOUR = 60 * 60 * 1000L;// 3600000;

	private DateUtil() {

	}

	/**
	 * 鏄惁鏈夋晥鏃ユ湡
	 * 
	 * @param str
	 * @param fmt
	 * @return
	 */
	public static boolean isValidDate(String str, String fmt) {
		Date date = parse(str, fmt);
		if (date == null) {
			return false;
		}
		String dateStr = format(date, fmt);
		if (dateStr.equals(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 瑙ｆ瀽鏃ユ湡瀛楃涓�
	 * 
	 * @param str
	 *            鏃ユ湡涓�
	 * @param fmt
	 *            鏃ユ湡鏍煎紡
	 * @return
	 */
	public static Date parse(String str, String fmt) {
		if (NullJudgeUtil.isNotNull(fmt) && NullJudgeUtil.isNotNull(fmt)) {
			SimpleDateFormat simDateFormat = new SimpleDateFormat(fmt);
			Date date = null;
			try {
				date = simDateFormat.parse(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return date;
		}
		return null;		
	}

	/**
	 * 鏍煎紡鍖栨棩鏈�
	 * 
	 * @param date
	 *            鏃ユ湡
	 * @param fmt
	 *            鏃ユ湡鏍煎紡
	 * @return
	 */
	public static String format(Date date, String fmt) {
		if (NullJudgeUtil.isNotNull(fmt) && NullJudgeUtil.isNotNull(fmt)) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fmt);
		return simpleDateFormat.format(date);
		}else{
			return null;
		}
	}

	/**
	 * 璁＄畻鏃ユ湡鍔犳湀
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date addMonth(Date date, int months) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, months);
		return c.getTime();
	}

	/**
	 * 璁＄畻鏃ユ湡鍔犲ぉ鏁�
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDay(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, days);
		return c.getTime();
	}

	/**
	 * 璁＄畻鏃ユ湡鍔犲ぉ鏁�
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static String addDay(String str, int days, String fmt) {
		Calendar c = Calendar.getInstance();
		c.setTime(parse(str, fmt));
		c.add(Calendar.DAY_OF_MONTH, days);
		Date date = c.getTime();
		return format(date, fmt);
	}

	/**
	 * 璁＄畻鏃ユ湡鍔犲垎閽�
	 * 
	 * @param date
	 * @param minutes
	 * @return
	 */
	public static Date addMinutes(Date date, int minutes) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, minutes);
		return c.getTime();
	}

	/**
	 * 璁＄畻澶╂暟宸�
	 * 
	 * @param sd
	 * @param ed
	 * @return
	 */
	public static int subDateDays(Date sd, Date ed) {
		Long eds = ed.getTime();
		Long sds = sd.getTime();
		return (int) ((eds - sds) / MILLION_SECONDS_OF_DAY);
	}

	/**
	 * 璁＄畻澶╂暟宸�
	 * 
	 * @param sd
	 * @param ed
	 * @return
	 */
	public static int subDateDays(String sd, String ed) {
		Long eds = parse(ed, DATE).getTime();
		Long sds = parse(sd, DATE).getTime();
		return (int) ((eds - sds) / MILLION_SECONDS_OF_DAY);
	}

	/**
	 * 鍒ゆ柇鏌愭鏃堕棿鏄惁鍦ㄦ寚瀹氱殑鏃堕棿娈靛唴(鍖呭惈寮�鏃堕棿绛変簬鎸囧畾鏃堕棿娈电殑缁撴潫鏃堕棿)
	 * 
	 * @param begin
	 *            寮�鏃堕棿
	 * @param end
	 *            缁撴潫鏃堕棿
	 * @param tBegin
	 *            寮�鏃堕棿娈�
	 * @param tEnd
	 *            缁撴潫鏃堕棿娈�
	 * @param fmt
	 * @return
	 */
	public static boolean isContain(String begin, String end, String tBegin, String tEnd, String fmt) {
		long beginTime = parse(begin, fmt).getTime();
		long endTime = parse(end, fmt).getTime();
		long bTime = parse(tBegin, fmt).getTime();
		long eTime = parse(tEnd, fmt).getTime();
		if (bTime > endTime || eTime <= beginTime) {
			return false;
		}
		return true;
	}

	/**
	 * 灏嗘棩鏈熷瓧绗︿覆,杞崲鎴怷MLGregorianCalendar瀵硅薄
	 * 
	 * @param str
	 *            鏃ユ湡瀛楃涓�
	 * @param fmt
	 *            鏃ユ湡鏍煎紡
	 * @return
	 */
	public static XMLGregorianCalendar toXMLGC(String str, String fmt) {
		GregorianCalendar cal = new GregorianCalendar();
		XMLGregorianCalendar gc = null;
		try {
			Date date = parse(str, fmt);
			cal.setTime(date);
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gc;
	}

	/**
	 * 灏哫MLGregorianCalendar鏃ユ湡瀛楃涓茶浆鎹㈡垚java.util.Date瀵硅薄
	 * 
	 * @param str
	 * @return
	 */
	public static Date toDate(String str) {
		return DatatypeConverter.parseDate(str).getTime();
	}

	public static void main(String[] args) {
		System.out.println(isValidDate("2010-03-31", DATE));
	}
}
