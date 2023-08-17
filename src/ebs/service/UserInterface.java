package ebs.service;

import java.util.List;

import ebs.entity.BillingUser;
import ebs.entity.CustomerView;
import ebs.entity.User;

public interface UserInterface {
	public User registerUser(User user);
	public User updateUser(int userId,User user);
	public List<User> deleteUser(int userId);
	public User loginAdmin(User login);
	public CustomerView loginUser(CustomerView cv);
	public CustomerView  ViewBill(CustomerView cv1) ;
	//public BillingUser validateConnection(BillingUser bill);
	public BillingUser insertValue(BillingUser bill);
	BillingUser validateConnection(BillingUser bill);
	CustomerView ViewBill();
	CustomerView ViewBill1(CustomerView cv);
	
	

}
