package atm;
import java.util.Scanner;

public class AtmMachine 
{

public static void main(String[] args)
{
	Account acc = new Account();
	int withAmt,depAmt,pin;
	System.out.println("Welcome To Pro.Dev , Please Select Any Of The Options Below:");
	System.out.println("1.Balance");
	System.out.println("2.Deposit");
	System.out.println("3.Withdrawal");
	System.out.println("4.Change ATM-Pin");
	Scanner sc = new Scanner(System.in);
	short usrch;
	try
	{
	usrch = sc.nextShort();
	switch(usrch)
	{
		case 1 :
			System.out.println("Your Total Balance Is: "+acc.getBalance());
			break;
		case 2:
			System.out.println("Enter The Amount To Deposited:");
			depAmt=sc.nextInt();
			System.out.println("Your Total Balance,After Deposit Is: "+acc.setBalance(depAmt));
			break;
		case 3:
			System.out.println("Enter The Amount To Withdraw:");
			withAmt=sc.nextInt();
			acc.withdraw(withAmt);
			break;
		case 4:
			System.out.println("Enter The New Pin:");
			pin=sc.nextInt();
			acc.setMpin(pin);
			break;
	}
	}
	catch(Exception ex)
	{
		System.out.println("Please Enter A Valid Input - From (1-4)");
	}
	finally
	{
		sc.close();
	}
	
     }

}
