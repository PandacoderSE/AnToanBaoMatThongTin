package Caeser;
//Hệ mã Caesar:
public class Cr {
	    // Encrypts text using a shift of s
	    public static StringBuffer encrypt(String text, int s)
	    {
	        StringBuffer result= new StringBuffer();
	 
	        for (int i=0; i<text.length(); i++)
	        {
	            if (Character.isUpperCase(text.charAt(i)))
	            {
	                char ch = (char)(((int)text.charAt(i) +
	                                  s - 65) % 26 + 65);
	                result.append(ch);
	            }
	            else
	            {
	                char ch = (char)(((int)text.charAt(i) +
	                                  s - 97) % 26 + 97);
	                result.append(ch);
	            }
	        }
	        return result;
	    }
	    public static StringBuffer dencrypt(String text, int s)
	    {
	        StringBuffer result= new StringBuffer();
	 
	        for (int i=0; i<text.length(); i++)
	        {
	            if (Character.isUpperCase(text.charAt(i)))
	            {
	                char ch = (char)(((int)text.charAt(i) -
	                                  s - 65) % 26 + 65);
	                result.append(ch);
	            }
	            else
	            {
	                char ch = (char)(((int)text.charAt(i) -
	                                  s - 97) % 26 + 97);
	                result.append(ch);
	            }
	        }
	        return result;
	    }
	 
	    // Driver code
	    public static void main(String[] args)
	    {
	        String text = "DHCN HaNoi";
	        int s = 4;
	        System.out.println("Text  : " + text);
	        System.out.println("Shift : " + s);
	        System.out.println("Cipher: " + encrypt(text, s));
	        System.out.println("giải mã :"+ dencrypt("ZkrXQarl", s));
	    }
}
