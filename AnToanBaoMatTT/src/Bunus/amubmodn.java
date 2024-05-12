package Bunus;

import java.util.Scanner;

public class amubmodn {
	public static boolean ktntcn(int q, int p) {
		while (p!=0) {
			int temp = q%p;
			q=p;
			p=temp;
		}
		if (q==1) return true;
		return false;
	}
	public static int kq(int e , int phi_n) {
		int r[]= new int[100] ; 
		int q[]= new int[100] ; 
		int x[]= new int[100] ; 
		int y[]= new int[100] ; 
		r[0]=phi_n ; 
		r[1]=e ; 
		x[0]=0 ; 
		x[1]=1 ; 
		y[0]=1;
		y[1]=1 ; 
		
		int i=1 ; 
		while(r[i]!=1) {
			i++ ; 
			r[i]=r[i-2] % r[i-1] ; 
			q[i]=r[i-2] / r[i-1] ; 
			x[i]=x[i-2] - q[i]*x[i-1] ; 
			y[i]=r[i-2] - q[i]*y[i-1] ; 
		}
		if(x[i]<0) {
			x[i]=x[i]+phi_n ;
		}
		return x[i] ; 
	}
	public static int amub(int a , int b, int n) {
		int doinp=b ; 
		int i=0 ; 
		int mng[]=new int[1000] ; 
		while(doinp!=0) {
			mng[i]=doinp%2 ; 
			doinp/=2 ; 
			i++ ;		
		}
		int f=1 ; 
		for(int j=i-1 ;j>=0 ; j--) {
			//System.out.print(" "+ mng[j]);
			f=(f*f) % n ; 
			if(mng[j]==1) {
				f=(f*a) % n ;
			}
		}
		return f ; 
		
	}
	// ham tinh a^b mod n
	public static void main(String[] args) {
			Scanner sc = new Scanner(System.in) ; 
			System.out.println("Moi ban nhap q , p");
			int q=sc.nextInt() ; 
			int p=sc.nextInt() ; 
			if(q!=p) {


				int phi_n =(q-1)*(p-1) ; 
				int n=q*p ; 
				int e =0; 
				for(int i=2 ; i<phi_n;i++) {
					if(ktntcn(phi_n,i)) {
						e=i ; 
						break ; 
					}
				}
				//tinh khoa cong khai , khoa bi mat : d=e^-1mod phi_n ; 
				if(e==0) {
					System.out.println("Khong co e !");
				}else {
					int d=kq(e ,phi_n) ; 
					System.out.println("khoa cong khai la : ("+e+","+n+")");
					System.out.println("khoa bi mat la : ("+d+","+n+")");
					int m=32 ; 
					int c=amub(m,e,n) ;
					System.out.println("Ma hoa vs m=32 => c = "+ c);
					 if(m==amub(c,d,n)) {
					 System.out.println("Giai hoa thanh cong voi m ="+amub(c,d,n)); 
					 }else {
					 System.out.println("Giai ma khong thanh cong "); }
				}
			}
	}
		 
	
}


