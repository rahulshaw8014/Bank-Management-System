package cheema.chowk.obc;
import java.util.InputMismatchException;
import java.util.Scanner;

class OrientalBankOfCommerce{
	private String name;
	private Date date;
	private String gender;
	private int mobNum;
	int balance;
	
	private int userId;
	private String pswd;
	
	Scanner sc=new Scanner(System.in);
	
	public void homePage() {
		System.out.println("Welcome to Oriental Bank of Commerce");
		System.out.println("We are here to help you");
		System.out.println("Let me know what you want");
		int NewOrOld;
		do{
		System.out.println("1.New User");
		System.out.println("2.Old User");
		System.out.println("Choose your option 1 or 2");
		NewOrOld=sc.nextInt();
			if(NewOrOld==1) {
				RequiredDetails rd=new RequiredDetails();
				rd.askDetailsForNewCust();
				}
			else if(NewOrOld==2) {
				RequiredDetails rd=new RequiredDetails();
				rd.askDetailsForOldCust();
				}
			else
				System.out.println("Choose appropriate ");
		}while(NewOrOld!=1&&NewOrOld!=2); 	}
	
	public void setDetailsForNewCust(String name, Date date, String gender, int mobNum){
		this.name=name;
		this.date=date;
		this.gender=gender;
		this.mobNum=mobNum;
		System.out.println("Welcome " + this.name + " " + "to Oriental Bank of Commerce");
		int accType;
		do {
		System.out.println("Choose type of bank account");
		System.out.println("1.Savings Account");
		System.out.println("2.Current Account");
		accType=sc.nextInt();
		if(accType==1) 
			new AccTypes().savingsAcc();
		else if(accType==2)
			new AccTypes().currentAcc();
		else
			System.out.println("Choose appropiate option");
		}
		while(accType!=1&&accType!=2);
	}
	
	public void setDetailForOldCust(int userId, String pswd) {
		this.userId=userId;
		this.pswd=pswd;
		System.out.println("Welcome " + userId + " " + "to Oriental Bank of Commerce");
	}
}


class RequiredDetails{
	Scanner sc=new Scanner(System.in);
	public void askDetailsForNewCust() {
		String name;
		Date date=new Date(); 
		String gender=null;
		int mobNum;
		System.out.println("First Name : ");
		String first=sc.next();
		System.out.println("Last Name : ");
		String last=sc.next();
		name=first+" "+last;
		System.out.println("Enter info of D.O.B");
		System.out.println("Enter in format dd/mmm/yyyy");
		try{
			System.out.println("day : ");
			date.day=sc.nextInt();
			System.out.println("month : ");
			date.month=sc.nextInt();
			System.out.println("year : ");
			date.year=sc.nextInt();
		}
		catch(InputMismatchException e) {
			System.out.println("please enter in valid type");
		}
		
		if(date.year>2000) {
			System.out.println("You aren't elligible for opening account in our bank");
			System.out.println("Thanku for visiting us");
			new OrientalBankOfCommerce().homePage();
		}
		int choice;
		do{
		System.out.println("Choose your Gender");
		System.out.println("1.Male");
		System.out.println("2.Female");
		System.out.println("3.Other");
		choice=sc.nextInt();
		switch(choice){
		case 1:
			gender="male";
			break;
		case 2:
			gender="female";
			break;
		case 3:
			gender="other";
			break;
			default:
				System.out.println("invalid choice");
		}
		}while(choice!=1&&choice!=2&&choice!=3) ;
		
		System.out.println("Enter mobile number : ");
		mobNum=sc.nextInt();
		new OrientalBankOfCommerce().setDetailsForNewCust(name, date, gender, mobNum);
	}
	
	public void askDetailsForOldCust() {
		int userId=0;
		String pswd;
		System.out.println("Fill below details");
		System.out.println("Enter your userId");
		try {
		userId=sc.nextInt();
		}
		catch(InputMismatchException e) {
			System.out.println("please enter valid user id");
		}
		System.out.println("Enter your password");
		pswd=sc.next();
		
		new OrientalBankOfCommerce().setDetailForOldCust(userId, pswd);
	}
}

class Date{
	int day;
	int month;
	int year;
}

class AccTypes{
	Scanner sc=new Scanner(System.in);
	OrientalBankOfCommerce obc=new OrientalBankOfCommerce();
	public void savingsAcc() {
		System.out.println("With what amount you want to start your account");
		int amt=sc.nextInt();
		obc.balance+=amt;
		System.out.println("Savings Account Created");
		new CustomerServicesForSavingAcc(obc);
	}
	
	public void currentAcc() {
		System.out.println("Please credit more than thousand rupees amount to start your account");
		int amt=sc.nextInt();
		if(amt<1000) {
			System.out.println("Please enter amount more than thousand");
			currentAcc();
		}
		else
		obc.balance+=amt;
		System.out.println("Current Account Created");
		new CustomerServicesForCurrentAcc(obc);
	}
}

interface facilities{
	public void checkBalance();
	public void creditAmount();
	public void withdrawlAmount();
	public void exit();
}

class CustomerServicesForSavingAcc implements facilities{
	CustomerServicesForSavingAcc(){}
	Scanner sc=new Scanner(System.in);
	OrientalBankOfCommerce obc;
	CustomerServicesForSavingAcc(OrientalBankOfCommerce obc){
		this.obc=obc;
		int choice=0;
		while(choice!=4) {
		do {
		System.out.println("What would you like to do next");
		System.out.println("1.checkBalance");
		System.out.println("2.creditBalance");
		System.out.println("3.withdrawlAmount");
		System.out.println("4.Exit");
		choice=sc.nextInt();
		switch(choice) {
		case 1:
			checkBalance();
			break;
		case 2:
			creditAmount();
			break;
		case 3:
			withdrawlAmount();
			break;
		case 4:
			exit();
			break;
			default:
				System.out.println("invalid choice");
		}
		}while(choice!=1&&choice!=2&&choice!=3&&choice!=4);
	
		}
		
	}
	
	public void checkBalance() {
		System.out.println("Your acc balance is : " + obc.balance);
	}
	
	public void creditAmount() {
		System.out.println("Enter amount you want to credit in your account");
		int amt=sc.nextInt();
		obc.balance+=amt;
		System.out.println("Amount of " + amt + " " + "credited to your account successfully");
	}
	
	public void withdrawlAmount() {
		int amt;
		System.out.println("How much money you want to withdrawl");
		amt=sc.nextInt();
		if(amt<=obc.balance)
		{
			obc.balance-=amt;
			System.out.println("Amount of " + amt + " debited from your account");
			System.out.println("Remaining bal : " + obc.balance);
		}
		else
			System.out.println("Insufficient Balance");
	
}
	
	public void exit() {
		obc.homePage();
	}
}

class CustomerServicesForCurrentAcc extends CustomerServicesForSavingAcc{
	CustomerServicesForCurrentAcc(OrientalBankOfCommerce obc){
		super(obc);
	}
	public void withdrawlAmount() {
		int amt;
		System.out.println("How much money you want to withdrawl");
		amt=sc.nextInt();
		if(amt<=(obc.balance-1000))
		{
			obc.balance-=amt;
			System.out.println("Amount of " + amt + " debited from your account");
			System.out.println("Remaining bal : " + obc.balance);
		}
		else
			System.out.println("Oops your account should contain minimum balance 1000");
}
}
public class BankVisitor {
	public static void main(String[] args) {
		OrientalBankOfCommerce Rahul=new OrientalBankOfCommerce();
		Rahul.homePage();
	}
}
