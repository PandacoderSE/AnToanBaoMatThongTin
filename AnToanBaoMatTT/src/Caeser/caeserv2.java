package Caeser;

import java.util.Scanner;

public class caeserv2 {
	public static String mahoa(String vanban ,  int buocdich)
	{
		StringBuilder vanbanmahoa = new StringBuilder();
		for(int i = 0; i<vanban.length();i++)
		{
			char kytu = vanban.charAt(i);
			if(Character.isLetter(kytu))
			{
				char coSo = Character.isLowerCase(kytu)?'a':'A';
				char kytumahoa =(char) ( (kytu-coSo+buocdich)%26 + coSo);
				vanbanmahoa.append(kytumahoa);
			}
			else if(Character.isDigit(kytu))
			{
				char kytuchu = (char) ( (kytu -'0' +buocdich )%10 +'0' );
				vanbanmahoa.append(kytuchu);
			}
			else
			{
				vanbanmahoa.append(kytu);
			}
		}
		
		return vanbanmahoa.toString();
	}
	
	public static String giaima(String vanbanmahoa , int buocdich)
	{
		return mahoa(vanbanmahoa, 26-buocdich);
	}
	
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("nhập vào van bản cần mã hóa :");
		String vanban = sc.nextLine();
		
		System.out.println("nhập giá trị bước dịch : ");
		int buocdich = sc.nextInt();
		
		String vanbanmahoa = mahoa(vanban, buocdich);
		System.out.println("văn bản đã mã hóa là " +vanbanmahoa);
		
		String giaima = giaima(vanbanmahoa, buocdich);
		System.out.println("văn bản đã giải mã là : "+giaima);
		
	}

}
