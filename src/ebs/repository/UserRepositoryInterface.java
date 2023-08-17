package ebs.repository;

import ebs.entity.BillingUser;
import ebs.entity.User;

public interface UserRepositoryInterface {

	public User registerUser(User user);
	public BillingUser insertUserBill(BillingUser bill);

}
