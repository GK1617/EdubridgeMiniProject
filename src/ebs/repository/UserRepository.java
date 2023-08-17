package ebs.repository;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import ebs.dbconfig.DataBaseConfig;
import ebs.entity.BillingUser;
import ebs.entity.User;

public class UserRepository implements UserRepositoryInterface {

    private Connection connection;
    private PreparedStatement preparedStatement = null;

    public UserRepository() throws FileNotFoundException {
        connection = DataBaseConfig.getConnection();
    }

    public User registerUser(User user) {
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO newuser (meterNo, userName, password, firstName, lastName, mobileNo, emailId, address, city, state, zipCode, connectionType, aadharNo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, user.getMeterNo());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setString(6, user.getMobileNo());
            preparedStatement.setString(7, user.getEmailId());
            preparedStatement.setString(8, user.getAddress());
            preparedStatement.setString(9, user.getCity());
            preparedStatement.setString(10, user.getState());
            preparedStatement.setString(11, user.getZipCode());
            preparedStatement.setString(12, user.getConnectionType());
            preparedStatement.setString(13, user.getAadharNo());

            int res = preparedStatement.executeUpdate();
            if (res == 1) {
                System.out.println("Successfully inserted");
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources();
        }
        return null;
    }
public boolean ValidateHomeBill(int s ,String s1) {
	String sqlQuery1= "SELECT * FROM newuser WHERE meterNo= ? AND connectionType=?";
	try (Connection conn = DataBaseConfig.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sqlQuery1)) {
	        
	        stmt.setInt(1, s);
	        stmt.setString(2, s1);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {

	            return true;
	        } else {

	            return false;
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return false;
	    }

		
}
public BillingUser insertUserBill(BillingUser bill) {
    String sql = "INSERT INTO userbill (meterNo, unitConsumed, connectionType, billdate, billamount) VALUES (?, ?, ?, ?, ?)";
    
    try (Connection con = DataBaseConfig.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
        
        boolean t = ValidateHomeBill(bill.getMeter_no(), bill.getConnectionType());
        
        if (t) {
            stmt.setInt(1, bill.getMeter_no());
            stmt.setInt(2, (int) bill.getUnitsConsumed());
            stmt.setString(3, bill.getConnectionType());
            stmt.setString(4, bill.getBillDate()); // Assuming billDate is of type java.util.Date
            stmt.setDouble(5, calculateHomeBill(bill.getUnitsConsumed())); // Assuming calculateHomeBill returns a double
            
            int res = stmt.executeUpdate();
            
            if (res == 1) {
                System.out.println("Successfully Inserted");
                return bill;
            }
        } else {
            System.out.println("Invalid Connection Type");
        }
    } catch (SQLException e1) {
        e1.printStackTrace();
    }
    
    return null;
}

    private void closeResources() {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private double calculateHomeBill(long unitsConsumed) {
        Function<Long, Double> homeBillLambda = units -> {
            double cost = 0;

            if (units <= 100) {
                cost = 0;
            } else if (units <= 200) {
                cost = (units - 100) * 2.25;
            } else if (units <= 400) {
                cost = 100 * 2.25 + (units - 200) * 4.50;
            } else if (units <= 500) {
                cost = 100 * 2.25 + 200 * 4.50 + (units - 400) * 6;
            } else if (units <= 600) {
                cost = 100 * 4.50 + 200 * 4.50 + 100 * 6 + (units - 500) * 8;
            } else if (units <= 800) {
                cost = 100 * 4.50 + 200 * 4.50 + 100 * 6 + 100 * 8 + (units - 600) * 9;
            } else if (units <= 1000) {
                cost = 100 * 4.50 + 200 * 4.50 + 100 * 6 + 100 * 8 + 200 * 9 + (units - 800) * 10;
            } else {
                cost = 100 * 4.50 + 200 * 4.50 + 100 * 6 + 100 * 8 + 200 * 9 + 200 * 10 + (units - 1000) * 11;
            }

            return cost;
        };

        return homeBillLambda.apply(unitsConsumed);
    }
}
