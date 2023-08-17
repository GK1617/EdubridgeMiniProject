package ebs.serviceimpl;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import ebs.dbconfig.*;
import ebs.repository.UserRepository;
import ebs.repository.UserRepositoryInterface;
import ebs.entity.BillingUser;
import ebs.entity.CustomerView;
import ebs.entity.User;
import ebs.service.UserInterface;

public class UserInterfaceImpl implements UserInterface{
	private UserRepositoryInterface userRepositoryInterface;
	Connection connection=DataBaseConfig.getConnection();
	public UserInterfaceImpl() {
	
		try {
			this.userRepositoryInterface=new UserRepository();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	@Override 
	public User registerUser(User user) {
		
		if(user.getPassword().length()<8)
		{
		System.out.println("pass should be minimum 8 character");
		return null;
		}
		if(user.getPassword().equals(user.getConfPassword()))
		{
		return userRepositoryInterface.registerUser(user);
		}else
		{
		System.out.println("password and confpass doesn't match");
		return null;
		}
		
	}

	

	@Override
	public User updateUser(int userId, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> deleteUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public User loginAdmin(User login) {
	    String sqlQuery = "SELECT * FROM newuser WHERE admin_username = ? AND admin_password = ?";
	    
	    try (Connection conn = DataBaseConfig.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {
	        
	        stmt.setString(1, login.getAdminUsername());
	        stmt.setString(2, login.getAdminPassword());
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            // The result set contains at least one row, indicating successful login
	        	//String defaultValue = rs.getString("COLUMN_DEFAULT");
	        	
	        //	System.out.println("if ");
	            return login;
	        } else {
	            // The result set is empty, indicating invalid login credentials
	        	//System.out.println("else");
	            return null;
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return null;
	    }
	    
	}
	@Override
	public BillingUser validateConnection(BillingUser bill) {
			String sql="select * from newuser where meterNo=? and connectionType=?";
			try (Connection con=DataBaseConfig.getConnection();
			PreparedStatement stmt=con.prepareStatement(sql))
			{
				stmt.setInt(1, bill.getMeter_no());
				stmt.setString(2, bill.getConnectionType());
				ResultSet rs=stmt.executeQuery();
				
					if(rs.next()) {
						return bill;
					}
					else {
						return null;
					}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				return null;
			}

	}
	@Override
	public BillingUser insertValue(BillingUser bill) {
		// TODO Auto-generated method stub
		return userRepositoryInterface.insertUserBill(bill);
	}

	@Override
	public CustomerView loginUser(CustomerView cv) {
		// TODO Auto-generated method stub
		String sqlQuery1= "SELECT * FROM newuser WHERE meterNo= ? AND (UserName= ? or password=?)";
		try (Connection conn = DataBaseConfig.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sqlQuery1)) {
		        
		        stmt.setInt(1, cv.getMeterNo());
		        stmt.setString(2, cv.getUserName());
		        stmt.setString(3, cv.getPassword());
		        ResultSet rs = stmt.executeQuery();
		        			        
		        if (rs.next()) {
		            		            return cv;
		        } else {
		            // The result set is empty, indicating invalid login credentials
		        	//System.out.println("else");
		            return null;
		        }
		    } catch (SQLException e) {
		        System.out.println(e.getMessage());
		        return null;
		    }
	
	}
	@Override
	public CustomerView ViewBill1(CustomerView cv) {
		connection=DataBaseConfig.getConnection();
		String sql="select * from userbill where meterNo=? ";
		try (Connection con=DataBaseConfig.getConnection();
		PreparedStatement stmt=con.prepareStatement(sql))
		{
			  try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	                preparedStatement.setInt(1, cv.getMeterNo());
	                ResultSet rs = preparedStatement.executeQuery();
/*			Statement state=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=stmt.executeQuery();
			*/

			
			
				while(rs.next()) {
					int meterNO=rs.getInt("meterNo");
		        	int units=rs.getInt("unitconsumed");
		        	String conType=rs.getString("connectiontype");
		        	String date=rs.getString("billdate");
		        	String amount=rs.getString("billamount");
				
					System.out.println("Meter No :"+ meterNO +", Units Consumed:"+ units+", Connection Type:"+ conType+", Bill Date:"+ date+", Bill Due:"+ amount);
					System.out.println(" ");
				}
			  }
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			}


		return null;

	}
	@Override
	public CustomerView ViewBill(CustomerView cv1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CustomerView ViewBill() {
		// TODO Auto-generated method stub
		return null;
	}
	
	


}
