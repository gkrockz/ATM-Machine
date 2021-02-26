import java.util.Scanner;

public class AtmMachine 
{

	public static void main(String[] args)

	{
		UserAccount user = new UserAccount();
		System.out.println("Welcome To Pro.Dev ATM,Choose Any Of The Options Below:");
		System.out.println("1.Balance");
		System.out.println("2.Deposit");
		System.out.println("3.Withdrawal");
		System.out.println("4.Change ATM-Pin");
		try (Scanner usrinp = new Scanner(System.in)) {
			short usrch;
			usrch = usrinp.nextShort();
			switch (usrch) {
				case 1 -> acc.getBalance();
				case 2 -> acc.deposit();
				case 3 -> acc.withdraw();
				case 4 -> acc.changePin();
			}
		} catch (Exception error) {
			System.out.println("Please Enter A Valid Input - From (1-4)");
		}

	}

}
