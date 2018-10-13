package emailPackage;

import java.util.Random;
import java.util.Scanner;

/*
 * Email should be written as follows : firstname.lastname@department.companyname.com
 * passwords should be randomly generated
 * the mailbox capacity should be specified
 * an alternative email should be specified for password recovery
 * Department available are Administration(admin), Sales(sale), Accounting(acc)
 * */

public class Email {

	private String firstName;
	private String lastName;
	private String password;
	private String department;
	private String email;
	private static int mailboxCapacity = 500;//megabytes
	private String alternateEmail;
	private static String companySuffix = "emb-inc.com";

	public Email(String firstName, String lastName){
		this.firstName=firstName;
		this.lastName=lastName;
		String dept=this.setDepartment();
		if(dept != null){
			this.department=dept;
		}
		else if(dept == null){
			System.out.println("Cannot create email because of no valid department name specification");
		}
		this.password = setPassword();
		this.email = generateEmail( this.firstName, this.lastName, this.department );
	}

	private String setDepartment() {
		System.out.println("Department IDs map?\n1 for Administration\n2 for Sales\n3 for Accounting");
		System.out.print("Enter Department ID : ");
		Scanner deptID=new Scanner(System.in);
		int id=deptID.nextInt();
		if(id == 1){
			return "admin";
		}
		else if(id == 2){
			return "sale";
		}
		else if (id == 3){
			return "acc";
		}
		System.out.println("ID entered is not valid");
		return null;
	}

	// 6 characters random password
	//the first character is always an upper case alphabet
	//the last character is always a number
	private String setPassword(){
		String caps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String digits = "0123456789";
		String str = caps + "abcdefghijklmnopqrstuvwxyz" + digits + "!?-_%#" ;
		StringBuilder sb=new StringBuilder();
		Random random = new Random();
		sb.append(caps.charAt(random.nextInt(caps.length())));
		for(int i=0;i<4;i++){
			sb.append(str.charAt(random.nextInt(str.length())));
		}
		sb.append(digits.charAt(random.nextInt(digits.length())));

		return sb.toString();
	}
	
	private String generateEmail(String firstname, String lastname, String department){
		String fname = firstname.toLowerCase();
		String lname = lastname.toLowerCase();
		String dept = department;
		String email = fname+"."+lname+"@"+dept+"."+Email.companySuffix;
		
		return email;
	}
	
	private void setAlternateEmail(String altEmail){
		this.alternateEmail = altEmail;
	}
	
	private void setMailboxCapacity(int cap){
		Email.mailboxCapacity = cap;
	}
	
	private void changePassword(String password){
		this.password = password;
	}
	
	private String getAlternateEmail(){
		return this.alternateEmail;
	}
	
	private String getPassword(){
		return this.password;
	}
	
	public String showInfo(){
		
		return "NAME : " + firstName + " " + lastName + "\n" + 
				"Department : " + department + "\n" +
				"Email : " + email + "\n" +
				"Mailbox Capacity : " + mailboxCapacity +"mb";
	}
}
