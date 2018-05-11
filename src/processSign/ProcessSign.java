package processSign;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ProcessSign {
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	public static void main(String[] args){
		String sign = encodeByKey(args[0],args[1]);
		System.out.println(sign);
		Map<String, String> map = new HashMap<String, String>();
		map.put("abc","test01");
		map.put("acc", "test02");
		map.put("aaa", "test03");
		System.out.println(signtemp(map));
		
	}
	//SHA256
	public static String encodeByKey(String str,String key){
		return encode(str+key);
	}
	
	public static String encode(String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			//System.out.println(" file.encoding:" + System.getProperty("file.encoding"));
			messageDigest.update(str.getBytes("UTF-8"));
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}

	public static String signtemp(Map<String, String> map) {

		String signmap = "";
		/**
		 * 
		 */
		String[] str = new String[20];
		int i = 0;
		for (String s : map.keySet()) {
			str[i] = s;
			i++;
		}
		
		Arrays.sort(str, 0, i);

		
		for (int j = 0; j < i; j++) {
//			System.out.println(str[j]);
			signmap = signmap + str[j] + "=" + map.get(str[j]) + "&";
		}
		signmap = signmap.substring(0, signmap.length() - 1);
		return (signmap);
	}
	
	
}
