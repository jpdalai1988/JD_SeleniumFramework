import java.util.Scanner;

public class TestJD {
		

public static void main(String[] args) {
				// TODO Auto-generated method stub
		       Scanner sc=new Scanner(System.in);
		       int num=sc.nextInt();

		       for(int i=2;i<=num;i++) {
		           boolean isPrime=true;

		    	   for(int j=2;j<=Math.sqrt(i);j++) {
		    		   
		    		   if(i%j ==0) {
		    		   isPrime=false;
		    		   break;
		    	   }
		    		   
		       }
		    	   if(isPrime)
				   {
					   System.out.println("Prime numbers are:" +i);
				   }
				   
		       }
					
			}

		

}
