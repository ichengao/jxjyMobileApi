package cn.gc80.base.util;


public class ValidateUtils {

	/**
	 * 
	 * @param s
	 * @return boolean
	 * 
	 */
	public static boolean isNumer(String s) {
		if (isEmpty(s))
			return false;
		return s.matches("^\\d+$");
	}

	/**
	 * @param param
	 * @param replace
	 * @return
	 */
	public static String replaceWhiteSpace(String param, String replace) {
		if (null == param || "".equals(param))
			return replace;
		return param;
	}

	/**
	 * @param arg
	 * @return
	 */
	public static boolean isEmpty(String arg) {
		return null == arg ? true : "".equals(arg);
	}

	/**
	 * @param args
	 * @return
	 */
	public static boolean isEmpty(String[] args) {
		if (null == args || args.length < 1)
			return true;
		for (int i = 0; i < args.length; i++) {
			if (isEmpty(args[i]))
				return true;
		}
		return false;
	}

	/**
	 * <p>
	 * validate input whether is a email address
	 * </p>
	 * 
	 * @param inputEmail
	 * @return
	 */
	public static boolean isEmail(String inputEmail) {
		if (isEmpty(inputEmail))
			return false;
		if (!allEmailValidChars(inputEmail))
			return false;
		int emailLen = inputEmail.length();
		int index = inputEmail.indexOf("@");
		int lastIndex = inputEmail.lastIndexOf("@");
		int dotLastIndex = inputEmail.lastIndexOf(".");
		// int dotIndex = inputEmail.indexOf(".");

		if (index < 1)
			return false;
		else if (index != lastIndex)
			return false;
		else if (lastIndex == (emailLen - 1))
			return false;
		else if (dotLastIndex == (emailLen - 1))
			return false;
		return true;
	}

	/**
	 * @param email
	 * @return
	 */
	static boolean allEmailValidChars(String email) {
		boolean parsed = true;
		String validchars = "abcdefghijklmnopqrstuvwxyz0123456789@._-";
		for (int i = 0; i < email.length(); i++) {
			char letter = Character.toLowerCase(email.charAt(i));
			if (validchars.indexOf(letter) != -1) {
				continue;
			}
			parsed = false;
			break;
		}
		return parsed;
	}

	/**
	 * @param arg0
	 * @param num
	 * @return
	 */
	public static boolean isExceeds(String arg0, int num) {
		if (null == arg0)
			return false;
		if (!isEmpty(arg0) && num < 1)
			return false;

		int argLength = arg0.getBytes().length;
		return argLength <= num ? false : true;
	}
}
