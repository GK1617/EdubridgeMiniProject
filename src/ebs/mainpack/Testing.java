package ebs.mainpack;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;
import ebs.entity.BillingUser;
import ebs.entity.CustomerView;
import ebs.entity.User;
import ebs.entity.BillCalc;
import ebs.repository.UserRepository;
import ebs.service.UserInterface;
import ebs.serviceimpl.UserInterfaceImpl;

public class Testing { 
    public static void main(String args[]) throws ParseException, FileNotFoundException {
        System.out.println("####### WELCOME TO ELECTRICITY BILLING SYSTEM #######");
        UserInterface userInterface = new UserInterfaceImpl();
        
        
        char ch = ' ',chh=' ';
        do {
            System.out.println("1 - User Registration  ");
            System.out.println("2 - Login By Admin");
            System.out.println("3 - Login By User");
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter the option");
            int option = scan.nextInt();
            
            switch (option) {
                case 1:
                    User user = new User();
                    System.out.println("Enter a Meter No.");
                    user.setMeterNo(scan.nextInt());
                    System.out.println("Enter a UserName");
                    user.setUserName(scan.next());
                    System.out.println("Enter a password that should be minimum 8 characters");
                    user.setPassword(scan.next());
                    System.out.println("Enter a Confirm Password");
                    user.setConfPassword(scan.next());
                    System.out.println("Enter a First Name");
                    user.setFirstName(scan.next());
                    System.out.println("Enter a Last Name");
                    user.setLastName(scan.next());
                    System.out.println("Enter a Mobile No.");
                    user.setMobileNo(scan.next());
                    System.out.println("Enter a Email Id");
                    user.setEmailId(scan.next());
                    System.out.println("Enter a Address");
                    user.setAddress(scan.next());
                    System.out.println("Enter a City");
                    user.setCity(scan.next());
                    System.out.println("Enter a State");
                    user.setState(scan.next());
                    System.out.println("Enter a Zip Code");
                    user.setZipCode(scan.next());
                    System.out.println("Enter a Connection like Home, Commercial, Education institution");
                    user.setConnectionType(scan.next());
                    System.out.println("Enter a Aadhar No");
                    user.setAadharNo(scan.next());
                    
                    
                    User user1 = userInterface.registerUser(user);
                    if (user1 != null) {
                        System.out.println("Register Successfully");
                    } else {
                        System.out.println("Register Failed");
                    }
                    break;
                case 2:
                	System.out.println("Login Here...!");
                	User login = new User();
                    System.out.println("Enter a Admin UserName.");
                    login.setAdminUsername(scan.next());
                    System.out.println("Enter a password that should be minimum 8 characters");
                    login.setAdminPassword(scan.next());
                    User login1=userInterface.loginAdmin(login);
                    
                    //System.out.println(login1);
                    if(login1 !=null) {
                    	System.out.println("Login Success");
                    	System.out.println("Enter the Bills of Consumers");
                    	int num=1;
                    	do {                
                        switch(num) {
                        case 1:
                        	BillingUser bill=new BillingUser();
                        	System.out.println("Enter Conusmer Meter No.");
                        	bill.setMeter_no(scan.nextInt());
                        	System.out.println("Enter Conusmer Units Consumed.");
                        	bill.setUnitsConsumed(scan.nextLong());
                        	System.out.println("Enter Connection Type.");
                        	bill.setConnectionType(scan.next());
                        	System.out.println("Enter like this format Date yyyy-mm-dd.");
                        	bill.setBillDate(new java.util.Date().toString());
                        	System.out.println();
//                        	String dateString=scan.next();
//                        	SimpleDateFormat dateFormat=new SimpleDateFormat("yyy-mm-dd");
//                        	java.util.Date date= dateFormat.parse(dateString);
//                        	bill.setBillDate(date);
//							bill.setBillDate(LocalDate.of(scan.nextInt(),scan.nextInt(),scan.nextInt()));
                        	
							BillingUser bill1=userInterface.insertValue(bill);
					        
                            break;
                        }
                        System.out.println("Do you Want to Enter Onemore Bill");
                        chh = scan.next().charAt(0);
                        
                        }while(chh=='y'|| chh=='Y');
    			    	  
                    }else {
                    	System.out.println("Login Failed");
                    }
                case 3:
                {
                	System.out.println("Welcome ");
                	CustomerView custoview= new CustomerView();
                	System.out.println("Enter a MeterNo");
                	custoview.setMeterNo(scan.nextInt());
                	System.out.println("Enter a UserName");
                	custoview.setUserName(scan.next());
                	System.out.println("Enter a Password");
                	custoview.setPassword(scan.next());
                	UserRepository uv=new UserRepository();
                    CustomerView cv=userInterface.loginUser(custoview);
                    
                    if(cv!=null) {
                    	
                    	System.out.println("Login Successfull");
                    	System.out.println("-----------------------------------------------------");
                    	CustomerView cv1=userInterface.ViewBill1(cv);
                    	
                    	
                    }else {
                    	System.out.println("Login Failed");
                    }
                	
                	
                }
                    	   default:
                    break;
            }
            System.out.println("Do you Want to Continue");
            ch = scan.next().charAt(0);
        } while (ch == 'y' || ch == 'Y');
    }
}
