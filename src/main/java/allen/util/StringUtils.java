package allen.util;


import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author WangJianQiang
 * @date 2018年12月25日 上午10:21:45
 */
public final class StringUtils {

	/**
	 * 不允许外部实例化
	 */
	private StringUtils() {
	}

	/** **/
	private final static String regEx = "[\\u4e00-\\u9fa5]";

	/** **/
	private static Pattern p = Pattern.compile(regEx);

	/**
	 * 判断文字中是否包含汉字
	 *
	 * @param str
	 * @return
	 */
	public static boolean checkword(String str) {
		int count = 0;
		Matcher m = p.matcher(str);
		while (m.find()) {
			for (int i = 0; i <= m.groupCount(); i++) {
				count = count + 1;
			}
		}
		if (count == 0) { // 无汉字
			return true;
		}
		return false; // 有汉字
	}


	/**
	 * URL转码
	 *
	 * @param s
	 * @return
	 */
	public static String encode(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (Exception e) {
			return s;
		}
	}

	public static String decode(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (Exception e) {
			return s;
		}
	}

	/**
	 * MD5对字符串加密
	 *
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());

			byte[] b = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < b.length; i++) {
				int v = (int) b[i];
				v = v < 0 ? 0x100 + v : v;
				String cc = Integer.toHexString(v);
				if (cc.length() == 1)
					sb.append('0');
				sb.append(cc);
			}
			return sb.toString();
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * 按字节长度截取字符串
	 *
	 * @param str     将要截取的字符串参数
	 * @param toCount 截取的字节长度
	 * @param more    字符串末尾补上的字符串
	 * @return 返回截取后的字符串
	 */
	public static String substring(String str, int toCount, String more) {
		int reInt = 0;
		String reStr = "";
		if (str == null) {
			return "";
		}
		char[] tempChar = str.toCharArray();
		for (int kk = 0; (kk < tempChar.length && toCount > reInt); kk++) {
			String s1 = String.valueOf(tempChar[kk]);
			byte[] b = s1.getBytes();
			reInt += b.length;
			reStr += tempChar[kk];
		}
		if (toCount == reInt || (toCount == reInt - 1)) {
			reStr += more;
		}
		return reStr;
	}

	public static long getPID() {
		return System.nanoTime() + new Random().nextInt(9999);
	}

	/**
	 * 生成UUID
	 *
	 * @return
	 * @author ChengWenFeng
	 * @date 2014年8月20日 下午2:12:40
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 生成UUID，但会过滤"-"
	 *
	 * @return
	 * @author ChengWenFeng
	 * @date 2014年8月20日 下午2:12:48
	 */
	public static String getUUID2() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-", "");
	}

	/**
	 * 列表转换成指定格式字符串
	 *
	 * @param list
	 * @param delimiter
	 * @return
	 * @author ChengWenFeng
	 * @date 2014年11月3日 下午3:57:46
	 */
	public static String listToStr(List<?> list, String delimiter) {
		if (list == null || list.size() == 0) {
			return "";
		}
		StringBuilder sbtmp = new StringBuilder();
		int i = 0;
		for (Object t : list) {
			if (StringUtils.isNotEmpty(t.toString())) {
				if (i != 0) {
					sbtmp.append(delimiter);
				}
				sbtmp.append(t);
				i++;
			}
		}
		return sbtmp.toString();
	}

	public static String arrayToStr(String[] arr, String delimiter) {
		if (arr == null) {
			return "";
		}
		StringBuilder sbtmp = new StringBuilder();
		int i = 0;
		for (String t : arr) {
			if (StringUtils.isNotEmpty(t)) {
				if (i != 0) {
					sbtmp.append(delimiter);
				}
				sbtmp.append(t);
				i++;
			}
		}
		return sbtmp.toString();
	}

	public static boolean filterCallback(String callback) {
		if (StringUtils.isEmpty(callback)) {
			return false;
		}
		boolean flag = true;
		//过滤特殊字符
		flag = callback.matches("^[A-Za-z0-9]+$");
		if (flag && callback.toLowerCase().equals("alert")) {
			flag = false;
		}
		return flag;
	}

	public static String stripXSS(String value) {
		if (value != null) {
			// NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
			// avoid encoded attacks.
			// value = ESAPI.encoder().canonicalize(value);
			// Avoid null characters
			value = value.replaceAll("", "");
			// Avoid anything between script tags
			Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid anything in a src="http://www.yihaomen.com/article/java/..." type of e­xpression
			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			// Remove any lonesome </script> tag
			scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
			// Remove any lonesome <script ...> tag
			scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid eval(...) e­xpressions
			scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid e­xpression(...) e­xpressions
			scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid javascript:... e­xpressions
			scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid vbscript:... e­xpressions
			scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid onload= e­xpressions
			scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
		}
		return value;
	}

	public static String xssEncode(String s) {
		if (s == null || s.equals("")) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length() + 16);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
				case '>':
					sb.append('＞');// 全角大于号
					break;
				case '<':
					sb.append('＜');// 全角小于号
					break;
				case '\'':
					sb.append('\\');
					sb.append('\'');
					sb.append('\\');
					sb.append('\'');
					break;
				case '\"':
					sb.append('\\');
					sb.append('\"');// 全角双引号
					break;
				case '&':
					sb.append('＆');// 全角
					break;
				case '\\':
					sb.append('＼');// 全角斜线
					break;
				case '#':
					sb.append('＃');// 全角井号
					break;
				case ':':
					sb.append('：');// 全角冒号
					break;
				case '%':
					sb.append("\\\\%");
					break;
				default:
					sb.append(c);
					break;
			}
		}
		return sb.toString();
	}

	/**
	 * 判断字符串是否是数字
	 *
	 * @param srcStr String, 要判断的源字符串
	 * @return true, 是数字；false, 不是数字
	 * @author xieyan
	 */
	public static boolean isDigit(String srcStr) {
		if (srcStr == null) {
			return false;
		}
		int srcStrLenght = srcStr.length();
		if (srcStrLenght <= 0) {
			return false;
		}
		for (int i = 0; i < srcStrLenght; i++) {
			if (i == 0 && srcStr.charAt(i) == '-') {
				continue;
			}
			if (!Character.isDigit(srcStr.charAt(i))) {
				return false;
			}
			return true;
		}
		return false;
	}

	/***
	 * 切断字符串 （没有省略号）
	 * @param input
	 * @param length
	 * @return
	 */
	public static String formatStringNoPoint(String input, int length) {
		String temp = "<SPAN title=\"" + input + "\">";
		temp += formatString(input, length);
		temp += "</SPAN>";
		return temp;
	}

	/**
	 * 切断字符串
	 *
	 * @param msg
	 * @param fixLength
	 * @return
	 */
	public static String formatString(String msg, int fixLength) {
		if (msg == null)
			msg = "";
		if (msg.trim().equals("null") || msg.trim().equals(""))
			msg = "";
		byte data[] = msg.getBytes();
		if (data.length <= fixLength)
			return msg;
		byte tmp[] = new byte[fixLength];
		System.arraycopy(data, 0, tmp, 0, fixLength);
		data = tmp;
		int count = 0;
		for (int i = 0; i < data.length; i++) {
			if (data[data.length - 1 - i] >= 0)
				break;
			count++;
		}
		switch (count % 2) {
			case 1: // '\001'
				byte tmp2[] = new byte[data.length - 1];
				System.arraycopy(data, 0, tmp2, 0, data.length - 1);
				data = tmp2;
				break;
		}
		String reSult = new String(data);
		return reSult;
	}

	/**
	 * 在页面上显示时把空字符串或null转化成&nbsp;
	 *
	 * @param src
	 * @return
	 */
	public static String getNullToNbsp(String src) {
		String result = "";
		if (StringUtils.toTrim(src).equals("")) {
			result = "&nbsp;";
		} else {
			result = src;
		}
		return result;
	}

	public static String getZeroToLine(int num) {
		String result = "";
		if (num == 0) {
			result = "-";
		} else {
			result = num + "";
		}
		return result;
	}

	/**
	 * 将模板中的变量替换成相应的栏目数据
	 *
	 * @param templateContent
	 * @param variable
	 * @param value
	 * @return
	 */
	public static String replaceValue(String templateContent, String variable, String value) {
		variable = "${" + variable + "}$";
		templateContent = templateContent.replace(variable, value);
		return templateContent;
	}

	/**
	 * 获取来原信息
	 *
	 * @param webSiteName
	 * @param sourceName
	 * @return
	 * @Title: getSourceType
	 * @data:2012-7-20上午10:31:55
	 * @author:zxd
	 */
	public static String getSourceName(String webSiteName, String sourceName) {
		String result = "";
		if (StringUtils.toTrim(sourceName).equals("")
				|| StringUtils.toTrim(sourceName).equals("本网站")) {
			result = webSiteName;
		} else {
			result = sourceName;
		}
		return result;
	}

	/**
	 * 替换里面的{yyyy}
	 *
	 * @param theme
	 * @return
	 * @Title: createTheme
	 * @data:2012-8-17下午05:32:18
	 * @author:zxd
	 */
	public static String createTheme(String theme) {
		String result = "";
		if (theme != null && !theme.equals("")) {
			if (theme.indexOf("{yyyy}") == -1) {
				String date = DateTools.getNowDate("yy-MM-dd");
				String temp[] = date.split("-");
				theme = theme.replace("{yy}", temp[0]);
				theme = theme.replace("{MM}", temp[1]);
				theme = theme.replace("{dd}", temp[2]);
				result = theme;
			} else {
				String date = DateTools.getNowDate("yyyy-MM-dd");
				String temp[] = date.split("-");
				theme = theme.replace("{yyyy}", temp[0]);
				theme = theme.replace("{MM}", temp[1]);
				theme = theme.replace("{dd}", temp[2]);
				result = theme;
			}
		}
		return result;
	}

	public static String FileName2(String fileName) {
		String result = "";
		if (fileName != null && !fileName.equals("")) {
			if (fileName.indexOf("{yyyy}") == -1) {
				String date = DateTools.getNowDate("yy-MM-dd");
				String temp[] = date.split("-");
				fileName = fileName.replace("{yy}", temp[0]);
				fileName = fileName.replace("{dd}", temp[2]);
				if (fileName.indexOf("{ee}") == -1) {
					fileName = fileName.replace("{e}",
							DateTools.MONTH.get(temp[1]));
				} else {
					fileName = fileName.replace("{ee}",
							DateTools.MONTHALL.get(temp[1]));
				}
				result = fileName;
			} else {
				String date = DateTools.getNowDate("yyyy-MM-dd");
				String temp[] = date.split("-");
				fileName = fileName.replace("{yyyy}", temp[0]);
				fileName = fileName.replace("{MM}", temp[1]);
				fileName = fileName.replace("{dd}", temp[2]);
				result = fileName;
			}
		}
		return result;
	}

	/**
	 * 字段为空的话，写成N/A形式
	 *
	 * @param result
	 * @return
	 * @Title: isNull
	 * @data:2012-9-7上午11:55:51
	 */
	public static String isNull(String result) {
		if (result == null || result.equals("")) {
			result = "N/A";
		}
		return result;
	}

	/**
	 * @param str
	 * @return 字符串是否为空
	 * @Title: isEmpty
	 * @author zhaoxin1
	 * @Description: 判断字符串是否为空
	 * @date 2016年1月5日 上午9:59:24
	 */
	public static boolean isEmpty(Object str) {
		return (str == null || "".equals(str));
	}

	/**
	 * @param obj
	 * @return 字符串是否为空
	 * @Title: isNotEmpty
	 * @author zhaoxin1
	 * @Description: 判断字符串是否为空
	 * @date 2016年1月5日 上午9:59:24
	 */
	public static boolean isNotEmpty(Object obj) {
		if (obj == null || obj.equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * 取src里面的内容
	 *
	 * @param content
	 * @Title: matchIMGSrc
	 * @data:2012-10-12下午04:31:14
	 */
	public static String matchIMGSrc(String content) {
		String src = "";
		if (content != null && !content.equals("")) {
			Matcher ma = Pattern.compile("<IMG.*src=(.*?)[^>]*?>").matcher(
					content);
			while (ma.find()) {
				ma = Pattern.compile("src=\"(.*?)\"{1}?").matcher(ma.group());
				while (ma.find()) {
					src += ma.group(1) + ",";
				}
			}
		}
		if (src != null && !"".equals(src)) {
			src = src.substring(0, src.length() - 1);
		}
		return src;
	}

	/**
	 * 删除P标签
	 *
	 * @param content
	 * @return
	 * @Title: delLabelP
	 * @data:2013-5-24上午9:51:16
	 */
	public static String delLabelP(String content) {
		content = content.replaceAll("<BR>", "");
		content = content.replaceAll("<br/>", "");
		content = content.replaceAll("<br>", "");
		content = content.replaceAll("<P ALIGN=CENTER>", "");
		content = content.replaceAll("<P ALIGN=CENTER>", "");
		content = content.replaceAll("</P> </P>", "</P>");
		return content;
	}

	/**
	 * 删除P标签
	 *
	 * @param content
	 * @return
	 * @Title: delLabelP
	 * @data:2013-5-24上午9:51:16
	 */
	public static String delLabelP2(String content) {
		if (content != null && !content.equals("")) {
			Matcher ma = Pattern.compile("<[^>]*>([ 　]*)<[^>]*>").matcher(
					content);
			while (ma.find()) {
				content = content.replace(ma.group(1), "");
			}
			content = content.replaceAll("</P></?P>", "</P>");
			content = content.replaceAll("</p></?p>", "</P>");
			if (content.contains("</P>")) {
				content = content.replaceAll("\r", "");
				content = content.replaceAll("\n", "");
			} else {
				content = content.replaceAll("\r\n　", "</P>　");
				content = content.replaceAll("\r\n ", "</P> ");
				content = content.replaceAll("\r\n", "");
			}
			content = content.replaceAll("</P>", "\n");
			content = content.replaceAll("</p>", "\n");
			content = content.replaceAll("<[^>]*>", "");
			// content = content.replaceAll("http:*\\.jpg", "");
			content = content.replaceAll("[\n][ 　]*[\n]", "\n");
			content = content.replaceAll("。\n　", "。</P>　");
			content = content.replaceAll("。\n　", "。</p>　");
			content = content.replaceAll("，\n　", "，</P>　");
			content = content.replaceAll("，\n　", "，</p>　");
			content = content.replaceAll("\n　", "</P>　");
			content = content.replaceAll("\n　", "</p>　");
			content = content.replaceAll("\n ", "</P> ");
			content = content.replaceAll("\n ", "</p> ");
			content = content.replaceAll("\n", "");
			content = content.replaceAll("</P>", "\n");
			content = content.replaceAll("</p>", "\n");
		}
		return content;
	}

	/**
	 * 删除IMG标签
	 *
	 * @param content
	 * @return
	 * @Title: delteImg2
	 * @data:2013-5-24上午9:52:44
	 */
	public static String delteImg2(String content) {
		if (content != null && !content.equals("")) {
			Matcher ma = Pattern.compile("<IMG[^>]*>").matcher(content);
			while (ma.find()) {
				content = content.replace(ma.group(), "");
			}
			Matcher ma1 = Pattern.compile("<img[^>]*>").matcher(content);
			while (ma1.find()) {
				content = content.replace(ma1.group(), "");
			}
			content = content.replace("<P>", "");
			content = content.replace("<p>", "");
			content = content.replace("</P>", "\n");
			content = content.replace("</p>", "\n");
			content = content.replace(">", "");
		}
		return content;
	}

	/**
	 * 删除IMG标签
	 *
	 * @return
	 * @Title: delteImg
	 * @data:2012-10-12下午04:53:05
	 */
	public static String delteImg(String content) {
		if (content != null && !content.equals("")) {
			Matcher ma = Pattern.compile(
					"<P>　　<P ALIGN=CENTER><IMG.*src=(.*?)[^>]*?></P> </P>")
					.matcher(content);
			while (ma.find()) {
				content = content.replace(ma.group(), "");
			}
			content = content.replace(" ", "");
			content = content.replace("<P>", "");
			content = content.replace("</P>", "\n");
			content = content.replace(">", "");
		}
		return content;
	}

	/**
	 * 取出英文括号里面的内容
	 *
	 * @param src
	 * @return
	 * @Title: getKuoHao
	 * @data:2012-10-16下午02:29:22
	 */
	public static String getKuoHao(String src) {
		String result = "";
		if (src != null && !src.equals("")) {
			Matcher ma = Pattern.compile("\\(([^\\)]*)\\)").matcher(src);
			while (ma.find()) {
				result = ma.group(1);
			}
		}
		return result;
	}

	/**
	 * 取中文括号里面的内容
	 *
	 * @param src
	 * @return
	 * @Title: getKuoHao2
	 * @data:2012-10-16下午02:33:31
	 */
	public static String getKuoHao2(String src) {
		String result = "";
		if (src != null && !src.equals("")) {
			Matcher ma = Pattern.compile("\\（([^\\)]*)\\）").matcher(src);
			while (ma.find()) {
				result = ma.group(1);
			}
		}
		return result;
	}

	/**
	 * 使字符串==0,000,000
	 *
	 * @return
	 * @Title: getDataFormatShuZi
	 * @data:2012-10-22下午05:37:17
	 */
	public static String getDataFormatShuZi(int number) {
		String result = "0";
		if (number > 0) {
			NumberFormat nf = NumberFormat.getInstance();
			result = nf.format(number);
		}
		return result;
	}

	/**
	 * 字符串转成为00,000,000
	 *
	 * @param number
	 * @return
	 * @Title: getDataFormatShuZi
	 * @data:2012-11-9下午03:53:56
	 */
	public static String getDataFormatShuZi3(String number) {
		String result = "0";
		if (number != null && !number.equals("")) {
			int num = Integer.parseInt(number);
			if (num > 0) {
				NumberFormat nf = NumberFormat.getInstance();
				result = nf.format(num);
			}
		}
		return result;
	}

	/**
	 * long型转换00,000,000
	 *
	 * @param number
	 * @return
	 * @Title: getDataFormatShuZi2
	 * @data:2012-10-25上午10:47:15
	 */
	public static String getDataFormatShuZi2(long number) {
		String result = "0";
		if (number > 0) {
			NumberFormat nf = NumberFormat.getInstance();
			result = nf.format(number);
		}
		return result;
	}

	/**
	 * 过滤yingw
	 *
	 * @param str
	 * @return
	 * @Title: getCNString
	 * @data:2013-1-31下午05:03:06
	 * @author:zxd
	 */
	public static String getCNString(String str) {
		String result = str;
		// 取出英文
		if (result != null && !result.equals("")) {
			String regEx = "[//a-zA-Z]";
			Pattern pattern = Pattern.compile(regEx);
			Matcher matcher = pattern.matcher(result);
			while (matcher.find()) {
				result = StringUtils.toTrim(matcher.replaceAll(""));
			}
			// 取出特殊字符
			if (!result.equals("")) {
				regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~@#￥%……&*（ ）——|{}【】‘；：”“’。，？！、\"的是也了仍从以使则却又及对就并很或把是的着给而被让在还比等当与于但]";
				pattern = Pattern.compile(regEx);
				matcher = pattern.matcher(result);
				while (matcher.find()) {
					result = StringUtils.toTrim(matcher.replaceAll(""));
				}
			}
			// 取出特殊字符
			if (!result.equals("")) {
				regEx = "[//0-9]";
				pattern = Pattern.compile(regEx);
				matcher = pattern.matcher(result);
				while (matcher.find()) {
					result = StringUtils.toTrim(matcher.replaceAll(""));
				}
			}
		}
		return result;
	}

	/**
	 * 只去除标题里的标点、符号
	 *
	 * @param str
	 * @return
	 * @Title: getCNString2
	 * @data:2013-8-28上午9:48:41
	 * @author:wxy
	 */
	public static String getCNString2(String str) {
		String result = str;
		if (result != null && !result.equals("")) {
			result = result.replaceAll("入口", "");
			result = result.replaceAll("", "");
			// 取出特殊字符
			String regEx = "[`~!@#$%\\^\\&\\*\\(\\)+=\\|\\{\\}':;',//[//].<>/?~@#￥%……（）——|【】‘；：”“’。，？！、\\s\\\"]";
			Pattern pattern = Pattern.compile(regEx);
			Matcher matcher = pattern.matcher(result);
			while (matcher.find()) {
				result = StringUtils.toTrim(matcher.replaceAll(""));
			}
		}
		return result;
	}

	/**
	 * 只去除标题里的标点、符号（联合利华专用）
	 *
	 * @param str
	 * @return
	 * @Title: getCNString2
	 * @data:2013-8-28上午9:48:41
	 * @author:wxy
	 */
	public static String getCNString3(String str) {
		String result = str;
		if (result != null && !result.equals("")) {
			// result = result.replaceAll("入口", "");
			// result = result.replaceAll("", "");
			result = result.replaceAll("图文", "");
			result = result.replaceAll("图", "");
			result = result.replaceAll("的", "");
			result = result.replaceAll("或", "");
			result = result.replaceAll("也", "");
			result = result.replaceAll("-", "");
			result = result.replaceAll(" ", "");
			result = result.replaceAll("如图", "");
			// 取出特殊字符
			String regEx = "[·`~!@#$%\\^\\&\\*\\(\\)+=\\|\\{\\}':;',\\[\\].<>《》/?~@#￥%……（）——|【】‘；：”“’。，？！、\\s\\\"]";
			Pattern pattern = Pattern.compile(regEx);
			Matcher matcher = pattern.matcher(result);
			while (matcher.find()) {
				result = StringUtils.toTrim(matcher.replaceAll(""));
			}
		}
		return result;
	}

	/**
	 * 判断字符串是否是英文
	 *
	 * @param str
	 * @return
	 * @Title: isEnglish
	 * @data:2013-3-28上午11:15:12
	 */
	public static boolean isEnglish(String str) {
		// 去掉空格
		String s = str.replaceAll(" ", "");
		Pattern pattern = Pattern.compile("[a-zA-Z]+");
		Matcher m = pattern.matcher(s);
		return m.matches();
	}

	/**
	 * <> ---> &gt; &lt;
	 *
	 * @param str
	 * @return
	 * @Title: stringFilter
	 * @data:2012-12-17上午11:36:32
	 */
	public static String stringFilter(String str) {
		if (str != null) {
			str = str.replaceAll(">", "&gt;");
			str = str.replaceAll("<", "&lt;");
			str = str.replaceAll("\"", "&quot;");
			return str;
		}
		return "";
	}

	/**
	 * 过滤字符串中的特殊字符
	 *
	 * @param str
	 * @return
	 * @Title: stringFilter2
	 * @data:2013-12-10下午4:55:34
	 */
	public static String stringFilter2(String str) {
		if (str != null) {
			StringBuffer sb = new StringBuffer();
			String[] strings = str.split("");
			for (String string : strings) {
				if (Charset.forName("UTF-8").newEncoder().canEncode(string)) {
					sb.append(string);
				}
			}
			return sb.toString();
		} else {
			return "";
		}
	}

	/**
	 * @param obj
	 * @return void
	 * @Title: trimBean
	 * @author zhaoxin1
	 * @Description: 过滤对象中的String字段中的首尾空格
	 * @date 2014年7月2日 下午2:32:57
	 */
	public static void trimBean(Object obj) {
		Class cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			int modifiers = field.getModifiers();
			if ((modifiers & Modifier.FINAL) > 0
					|| (modifiers & Modifier.STATIC) > 0) {
				continue;
			} else if (field.getType().getSimpleName().equals("String")) {
				field.setAccessible(true);
				try {
					Object valueObject = field.get(obj);
					if (valueObject != null
							&& !valueObject.toString().equals("")) {
						String t = valueObject.toString().trim();
						field.set(obj, t);
					}
				} catch (IllegalArgumentException e) {
					System.out.println("trimBean:property value illegal case by " + cls.getName() + ":" + field.getType().getSimpleName());
					continue;
				} catch (IllegalAccessException e) {
					System.out.println("trimBean:property access illegal case by " + cls.getName() + ":" + field.getType().getSimpleName());
					continue;
				}
			}
		}
	}

	public static String getUTF8Result(String src) {
		String result = null;
		try {
			result = URLEncoder.encode(src, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getGBKResult(String src) {
		String result = null;
		try {
			result = URLEncoder.encode(src, "iso-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getGBKResultxy(String src) {
		String result = null;
		try {
			result = URLEncoder.encode(src, "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String toTrim(String str) {
		if (str == null) {
			return "";
		}
		if (str.trim().equalsIgnoreCase("null")) {
			return "";
		}
		return str.trim();
	}

	/**
	 * 实际替换动作
	 *
	 * @param username username
	 * @param regular  正则
	 * @return
	 */
	private static String replaceAction(String username, String regular) {
		return username.replaceAll(regular, "*");
	}

	/**
	 * 身份证号替换，保留前四位和后四位
	 * 如果身份证号为空 或者 null ,返回null ；否则，返回替换后的字符串；
	 *
	 * @param idCard 身份证号
	 * @return
	 */
	public static String idCardReplaceWithStar(String idCard, int begin, int end) {
		if (idCard.isEmpty() || idCard == null) {
			return null;
		} else {
			return replaceAction(idCard, "(?<=\\d{" + begin + "})\\d(?=\\d{" + end + "})");
		}
	}

	/**
	 * 银行卡替换，保留后四位
	 * 如果银行卡号为空 或者 null ,返回null ；否则，返回替换后的字符串；
	 *
	 * @param bankCard 银行卡号
	 * @return
	 */
	public static String bankCardReplaceWithStar(String bankCard) {
		if (bankCard.isEmpty() || bankCard == null) {
			return null;
		} else {
			return replaceAction(bankCard, "(?<=\\d{0})\\d(?=\\d{4})");
		}
	}

	/**
	 * 获取指定长度的随机文件名
	 *
	 * @param lenght 文件名长度
	 * @return
	 */
	public static String getRandomName(int lenght) {
		Random rd = new Random();
		StringBuffer sb = new StringBuffer();
		int rdGet; // 取得随机数
		char ch;
		String date = new Long(new Date().getTime()).toString();
		for (int i = 0; i < lenght; i++) {
			if (rd.nextBoolean()) {
				rdGet = Math.abs(rd.nextInt()) % 10 + 48; //产生48到57的随机数(0-9的键位值)
			} else {
				rdGet = Math.abs(rd.nextInt()) % 26 + 97; //产生97到122的随机数(a-z的键位值
				// )
			}
			ch = (char) rdGet;
			sb.append(ch);
			if (i < date.length()) {
				sb.append(date.charAt(i));
			}
		}
		return sb.toString();
	}

	public static String getRandomNum(int length) {
		int maxNum = length;
		int i;
		int count = 0;
		//去掉了小写字母L，去掉了大写字母O
		char[] str = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'
				, 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w'
				, 'x', 'y', 'z'
				, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'
				, 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W'
				, 'X', 'Y', 'Z'
				, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '_'};
		int randLength = str.length;
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < maxNum) {
			i = Math.abs(r.nextInt(randLength));
			if (i >= 0 && i < randLength) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

	/**
	 * 处理NULL字符串
	 *
	 * @param str    String
	 * @param newStr String
	 * @return String
	 */
	public static String NVL(String str, String newStr) {
		if (str == null || "".equals(str.trim())) {
			str = newStr;
		}
		return str.trim();
	}

	/**
	 * 处理NULL字符串
	 *
	 * @param str String
	 * @return String
	 */
	public static String NVL(String str) {
		if (str == null || "".equals(str.trim())) {
			str = "";
		}
		return str.trim();
	}

	public static void main(String[] args) {
		String abc = "12.3";
	}
}
