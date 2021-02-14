package atm;
import java.util.Scanner;

public class AtmMachine 
{

public static void main(String[] args)
{
		Account acc = new Account();
		AtmServices cus = new AtmServices();
		int withAmt,depAmt,pin;
		System.out.println("Welcome To Pro.Dev , Please Select Any Of The Options Below:");
		System.out.println("1.Balance");
		System.out.println("2.Deposit");
		System.out.println("3.Withdrawal");
		System.out.println("4.Change ATM-Pin");
		System.out.println("5.Activate Your ATM Services");
		Scanner usrinp = new Scanner(System.in);
		short usrch;
		try
		{
			usrch = usrinp.nextShort();
			switch(usrch)
			{
				case 1 :
					System.out.println("Your Total Balance Is: "+acc.getBalance());
					break;
				case 2:
					System.out.println("Enter The Amount To Deposited:");
					depAmt=usrinp.nextInt();
					System.out.println("Your Total Balance,After Deposit Is: "+acc.setBalance(depAmt));
					break;
				case 3:
					System.out.println("Enter The Amount To Withdraw:");
					withAmt=usrinp.nextInt();
					acc.withdraw(withAmt);
					break;
				case 4:
					System.out.println("Enter The New Pin:");
					pin=usrinp.nextInt();
					acc.setMpin(pin);
					break;
					
				case 5:
					cus.validateCustomer();
					break;
						
			}
		}
		catch(Exception error)
		{
			System.out.println("Please Enter A Valid Input - From (1-4)");
		}
		finally 
		{
			usrinp.close();
		}
		
 }

}
