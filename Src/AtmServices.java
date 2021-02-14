package atm;

import java.util.Scanner;

public class AtmServices 
{
   	private int otp;
   	private int nmpin1,nmpin2;
   	private short attempt=3;
	public void validateCustomer()
	{
		Scanner cusinp = new Scanner(System.in); 
		try
		{
			System.out.println("Enter The 6-Digit OTP Recieved On Your Registered Mobile Number :");
			otp = cusinp.nextInt();
			int length = String.valueOf(otp).length();
			
			if(length==6)
			{
				System.out.println("Enter Any 4-Digit Number To Set Your Mpin :");
				nmpin1=cusinp.nextInt();
				System.out.println("ReEnter Your Mpin :");
				nmpin2=cusinp.nextInt();
				if(nmpin1==nmpin1)
				{
				Account newcus = new Account();
				newcus.setMpin(nmpin1,"Your New ATM Pin Have Been Set Sucessfully.");
				}
				else
				{
					System.out.println("The MPIN Entered Does't Match.");
				}
			}
			else
			{
				System.out.println("You Have Entered Wrong OTP.Please Try Again");
				attempt--;
				System.out.println(attempt+" More Attempts Remaining For Today.");
			}
		}
		catch(Exception error)
		{
			System.out.println("Error Occured : "+error);
		}
		finally
		{
			cusinp.close();
		}
		
	}
}
