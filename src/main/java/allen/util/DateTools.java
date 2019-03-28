package allen.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类
 *
 * @date 2016年8月17日 下午2:31:50
 */
public class DateTools {

	private String minDate;
	private String maxDate;
	public final static String FMT_DATE_YYYY = "yyyy";
	public final static String FMT_DATE_YYMMDD = "yyMMdd";
	public final static String FMT_DATE_YYYYMMDD = "yyyyMMdd";
	public final static String FMT_DATE_YYYY_MM_DD = "yyyy-MM-dd";
	public final static String FMT_DATE_YYYY_MM_DD2 = "yyyy/MM/dd";
	public final static String FMT_DATE_DATETIME = "yyyy-MM-dd HH:mm:ss";
	public final static String FMT_DATE_DATETIME3 = "yyyy-MM-dd HH:mm:sss";
	public final static String FMT_DATE_DATETIME2 = "yyyy-MM-dd HH:mm";
	public final static String FMT_DATE_DATETIME4 = "yyyy/MM/dd HH:mm";
	public final static String FMT_DATE_DATETIME_TIGHT = "yyyyMMddHHmmss";
	public final static String FMT_DATE_YY_MM = "yy-MM";
	public final static String FMT_DATE_YYYY_MM = "yyyy-MM";
	public final static String FMT_DATA_YY_MM_DD = "yy-MM-dd";
	public final static String FMT_DATE_MM = "MM";
	public final static String FMT_DATE_DD = "dd";
	public final static String FMT_DATE_TIME = "HH:mm:ss";
	public final static String FMT_DATE_HH = "HH";
	public final static String FMT_DATE_YYYY_N_MM_Y_DD_R = "yyyy年MM月dd日";
	public final static String FMT_DATE_YYYY_D_MM = "yyyy.MM";
	public final static String FMT_DATE_YYYY_MM_DD_00 = "yyyy-MM-dd 00:00:00";
	public final static String FMT_DATE_2099_01_01_00_00_00 = "2099-01-01 00:00:00";

	public static Map<String, String> MONTH = new HashMap<String, String>();    //月份英文缩写形式转换
	public static Map<String, String> MONTHALL = new HashMap<String, String>();    //月份全英文缩写形式转换
	private static Map<String, String> MONTH2 = new HashMap<String, String>();    //NOV-11

	static {
		//月份英文缩写形式转换
		MONTH.put("01", "Jan");
		MONTH.put("02", "Feb");
		MONTH.put("03", "Mar");
		MONTH.put("04", "Apr");
		MONTH.put("05", "May");
		MONTH.put("06", "Jun");
		MONTH.put("07", "Jul");
		MONTH.put("08", "Aug");
		MONTH.put("09", "Sep");
		MONTH.put("10", "Oct");
		MONTH.put("11", "Nov");
		MONTH.put("12", "Dec");

		//月份全英文缩写形式转换
		MONTHALL.put("01", "January");
		MONTHALL.put("02", "February");
		MONTHALL.put("03", "March");
		MONTHALL.put("04", "April");
		MONTHALL.put("05", "May");
		MONTHALL.put("06", "June");
		MONTHALL.put("07", "July");
		MONTHALL.put("08", "August");
		MONTHALL.put("09", "September");
		MONTHALL.put("10", "October");
		MONTHALL.put("11", "November");
		MONTHALL.put("12", "December");

		//NOV-11
		MONTH2.put("Jan", "01");
		MONTH2.put("Feb", "02");
		MONTH2.put("Mar", "03");
		MONTH2.put("Apr", "04");
		MONTH2.put("May", "05");
		MONTH2.put("June", "06");
		MONTH2.put("July", "07");
		MONTH2.put("Aug", "08");
		MONTH2.put("Sep", "09");
		MONTH2.put("Oct", "10");
		MONTH2.put("Nov", "11");
		MONTH2.put("Dec", "12");
		MONTH2.put("Jul", "07");
		MONTH2.put("Jun", "06");
	}

	/**
	 * 获取格式化的当前日期值[yyyy-MM-dd HH:mm:ss]
	 *
	 * @return String
	 * @author hubaoting
	 * @date 2016年6月28日 下午2:58:23
	 */
	public static String getFormatNowDate() {
		String strTime = null;
		try {
			SimpleDateFormat simpledateformat = new SimpleDateFormat(FMT_DATE_DATETIME);
			strTime = simpledateformat.format(new Date());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strTime;
	}

	/**
	 * 获取指定格式化的当前日期值
	 *
	 * @param format 格式
	 * @return String
	 * @author hubaoting
	 * @date 2018年1月23日 下午4:11:18
	 */
	public static String getFormatNowDate(String format) {
		String strTime = null;
		try {
			SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
			strTime = simpledateformat.format(new Date());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strTime;
	}

	/**
	 * string转换成long时间
	 *
	 * @param dateString
	 * @param format
	 * @return
	 */
	public static long formatStringToLong(String dateString, String format) {
		long result = 0l;
		SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
		try {
			result = simpledateformat.parse(dateString).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 改变日志格式
	 *
	 * @param datetime
	 * @param newFormat
	 * @param flag      是否可以为空[true：做参数验证，false:为空时默认返回“至今”，反之返回格式化时间字符串]
	 * @return
	 */
	public static String formatDateStr(String datetime, String newFormat, boolean flag) {
		Date date = null;
		try {
			if (datetime == null || datetime.equals("")) {
				if (flag) {
					return "";
				}
				return "至今";
			}

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = formatter.parse(datetime);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return getSpecifiedDate(date.getTime(), newFormat);
	}

	/**
	 * 将时间戳转换成指定格式的字符串时间[yyyy-MM-dd HH:mm:ss]
	 *
	 * @param date 时间戳
	 * @return String
	 * @date 2018年1月23日 下午4:16:24
	 */
	public static String formatDateToStr(int date) {
		SimpleDateFormat simpleDateFormat4Time = new SimpleDateFormat(FMT_DATE_DATETIME);
		return simpleDateFormat4Time.format(date * 1000);
	}

	/**
	 * 改变日志格式
	 *
	 * @param datetime
	 * @param oldFormat
	 * @param newFormat
	 * @return
	 */
	public static String formatDateString(String datetime, String oldFormat, String newFormat) {
		Date date = null;
		try {
			if (datetime == null || datetime.equals("")) {
				return "";
			}
			SimpleDateFormat formatter = new SimpleDateFormat(oldFormat);
			date = formatter.parse(datetime);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return getSpecifiedDate(date.getTime(), newFormat);
	}

	/**
	 * 比较字符串时间的大小
	 *
	 * @param beginTime 开始时间
	 * @param endTime   结束时间[为空或null时直接返回true]
	 * @param format    时间格式
	 * @return boolean 是否在结束时间之前[true表示开始时间小于结束时间，false表示开始时间大于结束时间]
	 * @Description
	 * @Author hubaoting
	 * @Date 2015年8月19日 下午4:04:16
	 */
	public static boolean compareDate(String beginTime, String endTime, String format) {
		SimpleDateFormat sdf = null;
		boolean result = false;
		try {
			if ("".equals(beginTime) || null == beginTime) {
				return false;
			}
			if ("".equals(endTime) || null == endTime) {
				return true;
			}
			sdf = new SimpleDateFormat(format);
			Date d1 = sdf.parse(beginTime);
			Date d2 = sdf.parse(endTime);
			String localTime = sdf.format(new Date());
			Date d3 = sdf.parse(localTime);
			if (localTime.equals(endTime) || !d2.before(d3)) {
				if (beginTime.equals(endTime) || d1.before(d2)) {
					//表示d1在d2之前
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 结束时间与当前时间字符串比较大小
	 *
	 * @param endTime 结束时间[为空或null时直接返回true]
	 * @param format  时间格式
	 * @return boolean 是否在结束时间之前[true结束时间大于当前时间，false表示小于当前时间]
	 * @Description
	 * @Author hubaoting
	 * @Date 2015年8月19日 下午4:04:16
	 */
	public static boolean compareDate(String endTime, String format) {
		SimpleDateFormat sdf = null;
		boolean result = false;
		try {
			if ("".equals(endTime) || null == endTime) {
				return true;
			}
			sdf = new SimpleDateFormat(format);
			Date d2 = sdf.parse(endTime);
			String localTime = sdf.format(new Date());
			Date d3 = sdf.parse(localTime);
			if (localTime.equals(endTime) || !d2.before(d3)) {
				//表示d2在d3之前
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	/**
	 *  * 判断两个日期的大小
	 *  * @author xieqihong
	 *  * @date 2016年5月5日 下午4:40:18
	 *  * @param range1Start
	 *  * @param range1End
	 *  * @param range2Start
	 *  * @param range2End
	 *  * @return int
	 *  
	 */
	public static boolean compareDateLong(String range1Start, String range1End) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long range1StartTime = df.parse(range1Start).getTime();
			long range1EndTime = df.parse(range1End).getTime();
			return range1StartTime > range1EndTime;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 *  * 判断两个日期的大小
	 *  * @author xieqihong
	 *  * @date 2016年5月5日 下午4:40:18
	 *  * @param range1Start
	 *  * @param range1End
	 *  * @param range2Start
	 *  * @param range2End
	 *  * @return int
	 *  
	 */
	public static boolean compareDates(String range1Start, String range1End) {
		if (null == range1End || "".equals(range1End.trim())) {
			return true;
		}
		DateFormat df = new SimpleDateFormat(FMT_DATE_DATETIME);
		try {
			long range1StartTime = df.parse(range1Start).getTime();
			long range1EndTime = df.parse(range1End).getTime();
			return range1StartTime > range1EndTime;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 判断两个日期的大小
	 *
	 * @param endTime 结束时间
	 * @return boolean
	 * @author: hubaoting
	 * @date: 2016年8月4日 上午10:53:43
	 */
	public static boolean compareDates(String endTime) {
		if (null == endTime || "".equals(endTime.trim())) {
			return true;
		}
		DateFormat df = new SimpleDateFormat(FMT_DATE_DATETIME);
		try {
			long startTime = df.parse(endTime).getTime();
			long currentTime = df.parse(df.format(new Date())).getTime();
			return startTime > currentTime;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void setDateType(int type) {
		Date date = new Date();
		Date today = new Date(System.currentTimeMillis());
		String mixDate = "";
		String maxDate = "";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		maxDate = simpleDateFormat.format(today).toString();
		if (type == 1) {
			//3天内
			date = new Date(System.currentTimeMillis() - (0x5265c00L * (3 - 1)));
			mixDate = simpleDateFormat.format(date).toString();
		} else if (type == 2) {
			//一周内
			date = new Date(System.currentTimeMillis() - (0x5265c00L * (7 - 1)));
			mixDate = simpleDateFormat.format(date).toString();
		} else if (type == 3) {
			//一月内
			date = new Date(System.currentTimeMillis() - (0x5265c00L * (30 - 1)));
			mixDate = simpleDateFormat.format(date).toString();
		} else if (type == 4) {
			//三月内
			date = new Date(System.currentTimeMillis() - (0x5265c00L * (90 - 1)));
			mixDate = simpleDateFormat.format(date).toString();
		}
		this.maxDate = maxDate;
		this.minDate = mixDate;
		//return simpleDateFormat.format(date);
	}

	/**
	 * 3天内，一周内，3个月内
	 *
	 * @param type
	 * @param datetype
	 * @Title: setDateType
	 * @data:2012-9-17下午05:58:30
	 * @author:zxd
	 */
	public void setDateType(int type, String datetype) {
		Date date = new Date();
		Date today = new Date(System.currentTimeMillis());
		String mixDate = "";
		String maxDate = "";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datetype);
		maxDate = simpleDateFormat.format(today).toString();
		if (type == 1) {
			//3天内
			date = new Date(System.currentTimeMillis() - (0x5265c00L * (3 - 1)));
			mixDate = simpleDateFormat.format(date).toString();
		} else if (type == 2) {
			//一周内
			date = new Date(System.currentTimeMillis() - (0x5265c00L * (7 - 1)));
			mixDate = simpleDateFormat.format(date).toString();
		} else if (type == 3) {
			//一月内
			date = new Date(System.currentTimeMillis() - (0x5265c00L * (30 - 1)));
			mixDate = simpleDateFormat.format(date).toString();
		} else if (type == 4) {
			//三月内
			date = new Date(System.currentTimeMillis() - (0x5265c00L * (90 - 1)));
			mixDate = simpleDateFormat.format(date).toString();
		} else if (type == 0) {
			date = new Date(System.currentTimeMillis() - (0x5265c00L * (0 - 1)));
			maxDate = simpleDateFormat.format(date).toString();
			mixDate = simpleDateFormat.format(today).toString();
		}
		this.maxDate = maxDate;
		this.minDate = mixDate;
	}

	/**
	 *  * 判断两个日期的重复天数和重复最后一天的星期
	 *  * @author xieqihong
	 *  * @date 2016年5月5日 下午4:41:03
	 *  * @param range1Start
	 *  * @param range1End
	 *  * @param range2Start
	 *  * @param range2End
	 *  * @return int
	 *  
	 */
	public static int calcOverlapDays(String range1Start, String range1End, String range2Start, String range2End) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		long nowTime;
		try {
			nowTime = df.parse(df.format(new Date())).getTime();
			long range1StartTime = nowTime > df.parse(range1Start).getTime() ? nowTime : df.parse(range1Start).getTime();
			long range1EndTime = df.parse(range1End).getTime();
			long range2StartTime = nowTime > df.parse(range2Start).getTime() ? nowTime : df.parse(range2Start).getTime();
			long range2EndTime = df.parse(range2End).getTime();
			if (range2StartTime > range1EndTime || range1StartTime > range2EndTime || range1StartTime > range1EndTime || range2StartTime > range2EndTime) {
				return 0;
			}
			long overlapTime = Math.min(range1EndTime, range2EndTime) - Math.max(range1StartTime, range2StartTime);
			//计算重复的天数+1
			int coverdays = (overlapTime < 0) ? 0 : (int) (overlapTime / 24 / 60 / 60 / 1000 + 1);
			int weekdays = dayForWeek(range1EndTime < range2EndTime ? range1End : range2End);
			return coverdays == 0 ? 0 : coverdays * 10 + weekdays;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 *  * 根据日期判断星期
	 *  * @author xieqihong
	 *  * @date 2016年5月5日 下午4:42:34
	 *  * @param pTime
	 *  * @return
	 *  * @throws Exception int
	 *  
	 */
	public static int dayForWeek(String pTime) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(pTime));
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	/**
	 * 获取年龄
	 *
	 * @param birthDay
	 * @return String
	 * @author hubaoting
	 * @date 2015年9月15日 下午12:24:58
	 */
	public static String getDateAge(String birthDay) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			birthDay = sdf.format(sdf.parse(birthDay));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String[] birthItem = birthDay.split("-");
		String[] currentItem = sdf.format(new Date()).split("-");
		int age = Integer.parseInt(currentItem[0]) - Integer.parseInt(birthItem[0]);
		int birthM = Integer.parseInt(birthItem[1]);
		int currentM = Integer.parseInt(currentItem[1]);
		int birthD = Integer.parseInt(birthItem[2]);
		int currentD = Integer.parseInt(currentItem[2]);
		if (birthM >= currentM && birthD >= currentD) {
		} else {
			age -= 1;
		}
		return age + "";
	}

	/**
	 * 根据指定的格式显示时间
	 *
	 * @param time   long
	 * @param format String
	 * @return String
	 * @author xieyan
	 */
	public static String getSpecifiedDate(long time, String format) {
		if (time == 0) {
			return "-";
		}
		Date timeVal = new Date(time);
		String strTime = null;
		try {
			SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
			strTime = simpledateformat.format(timeVal);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strTime;
	}

	/**
	 * 格式 ： 输出指定格式类型的时间
	 *
	 * @param formater
	 * @return String
	 * @author xieyan
	 */
	public static String getNowDate(String formater) {
		String strTime = null;
		try {
			SimpleDateFormat simpledateformat = new SimpleDateFormat(formater);
			strTime = simpledateformat.format(new Date());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strTime;
	}

	/**
	 * 获取格式化的当前日期值[yyyy-MM-dd 00:00:00]
	 *
	 * @return String
	 * @author hubaoting
	 * @date 2016年6月28日 下午2:58:23
	 */
	public static String getNowDate() {
		String strTime = null;
		try {
			SimpleDateFormat simpledateformat = new SimpleDateFormat(FMT_DATE_YYYY_MM_DD_00);
			strTime = simpledateformat.format(new Date());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strTime;
	}

	/**
	 * 在指定字符串时间上叠加指定天数
	 *
	 * @param strDate
	 * @param days
	 * @return
	 * @throws ParseException String
	 * @author hubaoting
	 * @date 2016年10月14日 上午9:06:02
	 */
	public static String addDate(String strDate, int days) {
		String result = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat(FMT_DATE_DATETIME);
			Date cDate = df.parse(strDate);
			GregorianCalendar gcalendar = new GregorianCalendar();
			gcalendar.setTime(cDate);
			gcalendar.add(Calendar.DATE, days);
			result = df.format(gcalendar.getTime()).toString();
		} catch (ParseException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 在指定字符串时间上叠加指定月数并减去最后一天
	 *
	 * @param strDate
	 * @param month
	 * @return
	 * @throws ParseException String
	 * @author: hubaoting
	 * @date: 2016年10月14日 上午9:06:02
	 */
	public static String addDateMonth(String strDate, int month) {
		String result = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date cDate = df.parse(strDate);
			GregorianCalendar gcalendar = new GregorianCalendar();
			gcalendar.setTime(cDate);
			gcalendar.add(Calendar.MONTH, month);
			gcalendar.add(Calendar.DATE, -1);
			result = df.format(gcalendar.getTime()).toString();
		} catch (ParseException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
	}

	public static String getDayEndSecond(String dateTime) {
		//String result = "";
		if (!dateTime.equals("")) {
			try {
				dateTime = formatDateString(dateTime + " 23:59:59", FMT_DATE_DATETIME, FMT_DATE_DATETIME);
			} catch (Exception e) {
			}
		}
		return dateTime;
	}

	public static Timestamp getTimestampNowDate() {
		Timestamp timestamp = null;
		try {
			timestamp = new Timestamp(new Date().getTime());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return timestamp;
	}

	/**
	 * 英文时间字符串转换(月为双位)
	 * 2012-02-01  --->  01-Feb
	 *
	 * @param str
	 * @return
	 * @Title: getENTimeStr
	 * @data:2012-8-6上午09:36:16
	 * @author:liweidong
	 */
	public static String getENTimeStr(String str) {
		String result = "";
		if (str != null && str.length() == 10) {
			try {
				String month = str.substring(5, 7);
				result = str.substring(str.lastIndexOf("-") + 1) + "-" + MONTH.get(month);
			} catch (Exception e) {
				result = str;
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 专函月份
	 *
	 * @param Month APR-04
	 * @return
	 * @Title: getEnMonth
	 * @data:2012-9-7下午03:23:59
	 * @author:zxd
	 */
	public static String getEnMonth(String Month) {
		String result = "";
		if (Month != null && !Month.equals("")) {
			result = MONTH2.get(Month);
		}
		return result;
	}

	public static String getEnMonth1(String Month) {
		String result = "";
		if (Month != null && !Month.equals("")) {
			result = MONTH.get(Month);
		}
		return result;
	}

	public static String getEnMonthAll(String Month) {
		String result = "";
		if (Month != null && !Month.equals("")) {
			result = MONTHALL.get(Month);
		}
		return result;
	}

	/**
	 * 转换日期格式Jul.26.2011-2011-7-26
	 *
	 * @param date
	 * @return
	 * @Title: changeDate
	 * @data:2012-9-7下午03:34:23
	 * @author:zxd
	 */
	public static String changeDate(String date) {
		String result = "";
		if (date != null && !date.equals("")) {
			//月份
			String mm = date.substring(0, date.indexOf("."));
			//天
			String dd = date.substring(0, date.lastIndexOf("."));
			dd = dd.substring(dd.indexOf(".") + 1);
			//year
			String yy = date.substring(date.lastIndexOf(".") + 1, date.length());
			mm = getEnMonth(mm);
			result = yy + "-" + mm + "-" + dd;
		}
		return result;
	}

	/**
	 * 时间字符串转换
	 * 2012-08-01  --->  8月1日
	 *
	 * @param str
	 * @return
	 * @Title: getCNTimeStr
	 * @data:2012-9-3下午02:04:17
	 * @author:liweidong
	 */
	public static String getCNTimeStr(String str) {
		String result = "";
		if (str != null && str.length() == 10) {
			try {
				String month = str.substring(5, 7);
				String day = str.substring(8);
				if (month.startsWith("0")) {
					month = month.substring(1);
				}
				if (day.startsWith("0")) {
					day = day.substring(1);
				}
				result = month + "月" + day + "日";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 固定格式字符串转换
	 * 2012-08-21   ----->   Aug.21.2012
	 *
	 * @param str
	 * @return
	 * @Title: getTimeStr
	 * @data:2012-9-10下午03:10:40
	 * @author:liweidong
	 */
	public static String getTimeStr(String str) {
		String result = "";
		if (str != null && str.length() == 10) {
			try {
				String month = str.substring(5, 7);
				String day = str.substring(8);
				String year = str.substring(0, 4);
				result = MONTH.get(month) + "." + day + "." + year;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String getTimeStr2(String str) {
		String result = "";
		if (str != null && str.length() == 10) {
			try {
				String month = str.substring(5, 7);
				String day = str.substring(8);
				String year = str.substring(0, 4);
				result = day + "-" + MONTH.get(month) + "-" + year;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 获取距离当前时间 days 天的时间
	 *
	 * @param days
	 * @param datetype
	 * @Title: setDate
	 * @data:2012-12-24下午04:09:50
	 * @author:liweidong
	 */
	public void setDate(int days, String datetype) {
		Date date = new Date();
		Date today = new Date(System.currentTimeMillis());
		String mixDate = "";
		String maxDate = "";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datetype);
		maxDate = simpleDateFormat.format(today).toString();
		date = new Date(System.currentTimeMillis() - (0x5265c00L * days));
		mixDate = simpleDateFormat.format(date).toString();
		this.maxDate = maxDate;
		this.minDate = mixDate;
	}

	/**
	 * 根据字符串时间获取时间的long值
	 *
	 * @param strTime
	 * @Title: getLongByStringTime
	 * @data:2014-07-30 上下午10:09:50
	 * @author:hubaoting
	 */
	public static Integer getLongByStringTime(String strTime) {
		if (null == strTime || "".equals(strTime)) {
			return null;
		}
		Integer result = null;
		DateFormat df = new SimpleDateFormat(FMT_DATE_DATETIME);
		try {
			Date date = df.parse(strTime);
			result = Long.valueOf(date.getTime() / 1000L).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据时间获取时间的long值
	 *
	 * @return long
	 * @author hubaoting
	 * @date 2016年7月18日 上午8:32:20
	 */
	public static int getLongByDate() {
		int result = 0;
		DateFormat df = new SimpleDateFormat(FMT_DATE_DATETIME);
		try {
			String strTime = df.format(new Date());
			Date date = df.parse(strTime);
			result = Long.valueOf(date.getTime() / 1000L).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据字符串时间获取时间的long值
	 *
	 * @param strTime
	 * @param format
	 * @return long
	 * @Title: getLongByStringTime
	 * @data:2014-07-30 上下午10:09:50
	 * @author:hubaoting
	 */
	public static long getLongByStringTime(String strTime, String format) {
		long result = 0;
		DateFormat df = new SimpleDateFormat(format);
		try {
			Date date = df.parse(strTime);
			result = date.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据具体月的个数获取时间
	 *
	 * @param monthCount
	 * @param format
	 * @return String
	 * @author hubaoting
	 * @date 2016年6月28日 下午3:12:10
	 */
	public static String getDateByMonth(int monthCount, String format) {
		try {
			SimpleDateFormat f = new SimpleDateFormat(format);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, monthCount);
			return f.format(c.getTime());
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 根据具体月的个数获取时间
	 *
	 * @param monthCount
	 * @return String
	 * @author hubaoting
	 * @date 2016年6月28日 下午3:12:10
	 */
	public static String getDateByMonth(int monthCount) {
		try {
			SimpleDateFormat f = new SimpleDateFormat(FMT_DATE_YYYY_MM_DD_00);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, monthCount);
			return f.format(c.getTime());
		} catch (Exception e) {
		}
		return null;
	}

	public String getMaxDate() {
		return maxDate;
	}

	public String getMinDate() {
		return minDate;
	}

	public static void main(String args[]) {
//		/*	DateHelper dh = new DateHelper();
//			dh.setDateType(0,"yyyy-MM-dd");
//			System.out.println(dh.maxDate);
//			System.out.println(dh.minDate);*/
//			DateHelper.formatStringToLong("2010-10-07 12:31:00",DateHelper.FMT_DATE_DATETIME3);
//			System.out.println(DateHelper.getDayEndSecond("2010-10-07"));
//			System.out.println("------"+("  aa  ".trim())+"------");
//			System.out.println(DateHelper.getCNTimeStr("2010-02-17"));
		//System.out.println(DateHelper.getNowDate("yyyy-MM-dd HH:mm:ss"));
		//long teime = getLongByStringTime(null);
		//System.out.println(Integer.valueOf(String.valueOf(teime)).intValue());
		/*System.out.println(addDate("2017-02-28 10:00:00", 1));
		System.out.println(compareDate("2016-09-26 10:00:00", FMT_DATE_YYYY_MM_DD));
		System.out.println(addDate("2016-09-26 10:00:00", 365*2-1));
		System.out.println(addDateMonth("2016-09-26 10:00:00", 24));*/
		System.out.println(DateTools.compareDates("2018-10-23 09:02:50"));
	}
}
