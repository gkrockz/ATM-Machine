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
		Scanner usrinp = new Scanner(System.in);
		short usrch;
		try {
			usrch = usrinp.nextShort();
			switch (usrch) {
			case 1:
				user.getBalance();
				break;

			case 2:
				user.deposit();
				break;

			case 3:
				user.withdraw();
				break;
			case 4:
				user.changePin();
				break;
			}
		} catch (Exception error) {
			System.out.println("Please Enter A Valid Input - From (1-4)");
		} finally {
			usrinp.close();
		}

	}

}
