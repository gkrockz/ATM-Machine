public class Account
{
	private double minbal;
	private int mpin;
	Account()
	{
		minbal = 2000;
	}
	public void withdraw(double amount)
	{
		double t;
		t = minbal;
		t-=amount;
		try
		{
			if(t<1000)
			{
				throw new MinimumBalanceException("Balance For This Type Of Account Can't Go Less Than 1000 Rs. ");
			}
			else
			{
				minbal-=amount;
				System.out.println("Balance After Withdrawal: "+minbal+" Rupees");
			}
		}
		
		catch(MinimumBalanceException ex)
		{
			System.out.println(ex.getMessage());
		}
		
	}
	public double setBalance(double amount) 
	{
		minbal+=amount;
		return minbal;
	}
	public double getBalance() 
	{
		return minbal;
	}
	public void setMpin(int mpin) 
	{
		this.mpin = mpin;
		System.out.println("Your ATM Pin Has Been Sucessfully Changed.");
	}
	
	
}
