import java.sql.*;
import java.util.Scanner;

public class Account {

//  Global Instances Required For Database Connectivity

	Connection concobj;
	PreparedStatement st, wst;
	ResultSet rs;
	Scanner usrinp = new Scanner(System.in);
	private int cusid, withAmt, depAmt, pin, count;
	private int npin1, npin2;
	private double totamt, bal;
	private boolean status;

// Method To fetch Account Balance
	public void getBalance() {
		try {
			connect();
			cusid = fetchUser();
			String Query1 = "select balance from users where id=(?)";
			st = concobj.prepareStatement(Query1);
			st.setInt(1, cusid);
			rs = st.executeQuery();
			while (rs.next()) {
				totamt = rs.getDouble(1);
				System.out.println("Total Balance: " + totamt + "Rs");
			}
		} catch (Exception error) {
			System.out.println("Error Occured During Fetching Balance : " + error);
		} finally {
			close();
		}

	}

// Method To Deposit Money Into Account 	
	public void deposit() {
		try {
			connect();
			cusid = fetchUser();
			System.out.println("Enter Deposit Amount: ");
			depAmt = usrinp.nextInt();
			String Query2 = "update users set balance=balance+(?) where id=(?)";
			st = concobj.prepareStatement(Query2);
			st.setDouble(1, depAmt);
			st.setInt(2, cusid);
			count = st.executeUpdate();
			System.out.println(depAmt + " Rs Deposited Into Your Account Sucessfully ");
		} catch (Exception error) {
			System.out.println("Error Occured During Deposit : " + error);
		} finally {
			close();
		}

	}

// Method To Withdraw Money From Account.
	public void withdraw() throws Exception {
		connect();
		cusid = fetchUser();
		totamt = checkbalance(cusid);
		System.out.println("Enter Withdrawal Amount : ");
		withAmt = usrinp.nextInt();
		try {
			if (totamt < 1000) {
				throw new MinimumBalanceException("Balance For This Account Can't Go Less Than 1000 Rs. ");
			} else {
				totamt -= withAmt;
				String Query3 = "update users set balance = (?) where id=(?)";
				wst = concobj.prepareStatement(Query3);
				wst.setDouble(1, totamt);
				wst.setInt(2, cusid);
				count = wst.executeUpdate();
				System.out.println("Total Cash Issued : " + withAmt + " Rs");
				System.out.println("Balance After Withdrawal: " + totamt + " Rs");
			}
		} catch (MinimumBalanceException ex) {
			System.out.println(ex.getMessage());
		} finally {
			close();
		}
	}

// Method That Fetches User Details.	
	public int fetchUser() {
		try {
			System.out.println("Enter Your Customer Id: ");
			cusid = usrinp.nextInt();
			System.out.println("Enter Your 4-Digit Pin:");
			pin = usrinp.nextInt();
			if (validateUser(cusid)) {
				String Query4 = "select name from users where id=(?)";
				st = concobj.prepareStatement(Query4);
				st.setInt(1, cusid);
				rs = st.executeQuery();
				while (rs.next()) {
					System.out.println("Welcome " + rs.getString(1));
					System.out.println("===============================");
				}
			} else {
				System.out.println("Incorrect User Credentials");
				System.exit(count);
			}
		} catch (Exception error) {
			System.out.println("Error Occured While Fetching User Details : " + error);
		}
		return cusid;
	}

// Parameterized Method With Cus.ID To Check Balance.	
	public double checkbalance(int cusid) {
		try {
			connect();
			String Query5 = "select balance from users where id=(?)";
			st = concobj.prepareStatement(Query5);
			st.setInt(1, cusid);
			rs = st.executeQuery();
			while (rs.next()) {
				bal = rs.getDouble(1);
			}
		} catch (Exception error) {
			System.out.println("Error Occured While Fetching Balance : " + error);
		}
		return bal;
	}

// Method That Validates User Credentials.	
	public boolean validateUser(int CusId) {
		try {
			connect();
			String Query6 = "select id,pin from users where id = (?)";
			st = concobj.prepareStatement(Query6);
			st.setInt(1, CusId);
			rs = st.executeQuery();
			while (rs.next()) {
				if (pin == Integer.parseInt(rs.getString(2)) && CusId == rs.getInt(1)) {
					status = true;
				} else {
					status = false;
				}
			}
		} catch (Exception error) {
			System.out.println("Error Occured,While Validating User : " + error);
		}
		return status;
	}

// Method That Verifies ATM Pin.	
	public void changePin() {
		try {
			connect();
			cusid = fetchUser();
			System.out.println("Enter Your Old Pin:");
			pin = usrinp.nextInt();
			String Query7 = "select pin from users where id = (?);";
			st = concobj.prepareStatement(Query7);
			st.setInt(1, cusid);
			rs = st.executeQuery();
			while (rs.next()) {
				if (pin == Integer.parseInt(rs.getString(1))) {
					System.out.println("Enter Your New 4-Digit Pin:");
					npin1 = usrinp.nextInt();
					System.out.println("Re-Enter Your New Pin");
					npin2 = usrinp.nextInt();
					if (npin1 == npin2) {
						setpin(cusid, npin1);
					} else {
						System.out.println("Yor New Pin, Doesn't Match");
					}
				} else {
					System.out.println("You Have Entered Incorrect Pin");
				}
			}
		} catch (Exception error) {
			System.out.println("Error Occured While Changing Pin: " + error);
		}
	}

// Method That Updates ATM Pin Of Users.	
	public void setpin(int cusid, int pin) {
		try {
			String Query8 = "update users set pin = (?) where id=(?)";
			st = concobj.prepareStatement(Query8);
			st.setInt(1, pin);
			st.setInt(2, cusid);
			count = st.executeUpdate();
			System.out.println("Your ATM Have Been Changed Sucessfully");

		} catch (Exception error) {
			System.out.println("Error Occured While Changing Pin: " + error);
		}
	}

// Database Connection Methods: 

	public void connect() {
		try {
			String url = "jdbc:postgresql://localhost:5432/test";
			String usrname = "postgres";
			String pass = "gkrockz";
			Class.forName("org.postgresql.Driver");
			concobj = DriverManager.getConnection(url, usrname, pass);
		} catch (Exception error) {
			System.out.println("Error Occured While Establishing Connection : " + error);
		}
	}

	public void close() {
		try {
			concobj.close();
			st.close();
		} catch (Exception error) {
			System.out.println("Error In Closing Connection : " + error);
		}
	}

}
