package cn.gc80.base.util;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class StringTool {

    public static boolean isEmpty(String value)
    {
        return value == null || value.length() == 0;
    }
    public static String[] split(String source, String delim) {
      String[] wordLists;
      if (source == null) {
        wordLists = new String[1];
        wordLists[0] = source;
        return wordLists;
      }
      if (delim == null) {
        delim = ",";
      }
      StringTokenizer st = new StringTokenizer(source, delim);
      int total = st.countTokens();
      wordLists = new String[total];
      for (int i = 0; i < total; i++) {
        wordLists[i] = st.nextToken();
      }
      return wordLists;
    }
    
    public static String[] getTokens(String sSource, String sDelim)
    {
        StringTokenizer tokenizer = new StringTokenizer(sSource, sDelim);
        int iCount = tokenizer.countTokens();
        String sTokens[] = new String[iCount];
        for(int i = 0; i < iCount; i++)
            sTokens[i] = tokenizer.nextToken();

        return sTokens;
    }
    
    public static String formatNumber(String inputString, int maxIntegertLength, int miniFranctionLength)  {
        if(inputString== null || "".equals(inputString)) {
            return String.valueOf("");
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumIntegerDigits(maxIntegertLength);
        numberFormat.setMinimumFractionDigits(miniFranctionLength);
        Long dbleInput = Long.valueOf(inputString);
        return numberFormat.format(dbleInput);
    }
    public static final String replace( String line, String oldString, String newString ) 
    { 
        if (line == null)
   { 
    return null; 
            } 
        int i=0; 
        if ( ( i=line.indexOf( oldString, i ) ) >= 0 )
   { 
    char [] line2 = line.toCharArray(); //字符串放入数组
    char [] newString2 = newString.toCharArray(); //要替换的字符串
    int oLength = oldString.length();  //被替换的字符串的长度
    StringBuffer buf = new StringBuffer(line2.length); 
    buf.append(line2, 0, i).append(newString2); 
    i += oLength; 
    int j = i;
    while( ( i=line.indexOf( oldString, i ) ) > 0 )
     { 
      buf.append(line2, j, i-j).append(newString2); 
      i += oLength; 
      j = i; 
                    } 
    buf.append(line2, j, line2.length - j); 
    return buf.toString(); 
   } 
   return line; 
    }      
  

    public static String combineStringArray(String[] array, String delim) {
      int length = array.length - 1;
      if (delim == null) {
        delim = "";
      }
      StringBuffer result = new StringBuffer(length * 8);
      for (int i = 0; i < length; i++) {
        result.append(array[i]);
        result.append(delim);
      }
      result.append(array[length]);
      return result.toString();
    }
    /**  
     * @param str:  
     *            source string  
     * @param width:  
     *            string's byte width  
     * @param ellipsis:  
     *            a string added to abbreviate string bottom  
     * @return String Object  
     * @deprecated 这个函数是用来对输入字符的HTML代码进行过滤  
     */  
    @Deprecated
	public static String Html2Text(String inputString) {    
          String htmlStr = inputString; //含html标签的字符串    
          String textStr ="";    
          java.util.regex.Pattern p_script;    
          java.util.regex.Matcher m_script;    
          java.util.regex.Pattern p_style;    
          java.util.regex.Matcher m_style;    
          java.util.regex.Pattern p_html;    
          java.util.regex.Matcher m_html;    
             
          java.util.regex.Pattern p_html1;    
          java.util.regex.Matcher m_html1;    
          
          try {    
           String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }    
           String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }    
              String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式    
              String regEx_html1 = "<[^>]+";    
              p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);    
              m_script = p_script.matcher(htmlStr);    
              htmlStr = m_script.replaceAll(""); //过滤script标签    
  
              p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);    
              m_style = p_style.matcher(htmlStr);    
              htmlStr = m_style.replaceAll(""); //过滤style标签    
             
              p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);    
              m_html = p_html.matcher(htmlStr);    
              htmlStr = m_html.replaceAll(""); //过滤html标签    
                 
              p_html1 = Pattern.compile(regEx_html1,Pattern.CASE_INSENSITIVE);    
              m_html1 = p_html1.matcher(htmlStr);    
              htmlStr = m_html1.replaceAll(""); //过滤html标签    
              htmlStr= replace(htmlStr,"&nbsp;","");
              htmlStr.replaceAll("\\s*|\t|\r|\n","");//
              htmlStr.replaceAll("<.*?>|&.{2,5};","");//
                 
           textStr = htmlStr.trim();    
             
          }catch(Exception e) {    
                   System.err.println("Html2Text: " + e.getMessage());    
          }    
          
          return textStr;//返回文本字符串    
           }    

    public static String bSubstring(String s, int length)    
    {   
      
        byte[] bytes;
		int i;
		try {
			bytes = s.getBytes("Unicode");   
			int n = 0; // 表示当前的字节数   
			i = 2;
			for (; i < bytes.length && n < length; i++)   
			{   
			    // 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节   
			    if (i % 2 == 1)   
			    {   
			        n++; // 在UCS2第二个字节时n加1   
			    }   
			    else  
			    {   
			        // 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节   
			        if (bytes[i] != 0)   
			        {   
			            n++;   
			        }   
			    }   
			}   
			// 如果i为奇数时，处理成偶数   
			if (i % 2 == 1)   
     
			{   
			    // 该UCS2字符是汉字时，去掉这个截一半的汉字   
			    if (bytes[i - 1] != 0)   
			        i = i - 1;   
			    // 该UCS2字符是字母或数字，则保留该字符   
			    else  
			        i = i + 1;   
			}
			return new String(bytes, 0, i, "Unicode");   
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;   
      
    }  

    public static boolean sql_inj(String str) 
    {
    String inj_str = "exec|insert|select|count|*|%|chr|mid|master|truncate|char|declare| or|+";
    String inj_stra[] = split(inj_str,"|");
    for (int i=0 ; i < inj_stra.length ; i++ )
    {
    if (str.indexOf(inj_stra[i])>=0)
    {
    return true;
    }
    }
    return false;
    }


}
