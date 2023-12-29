package com.kkm.pos2.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kkm.pos2.database.DatabaseConnection;
import com.kkm.pos2.domain.Cashier;
import com.kkm.pos2.domain.Item;
import com.kkm.pos2.domain.Sale;
import com.kkm.pos2.domain.SaleItem;
import com.kkm.pos2.exception.DatabaseConnectionException;
import com.kkm.pos2.exception.RepositoryException;

public class TestDatabase {
	
	
	public static void main(String[] args) {
		String id = "01";
		String ps = "wafda";
		
		
			Cashier c = null;
			try (Connection conn = DatabaseConnection.conn()) {
				Class.forName("com.mysql.jdbc.Driver");
				String sqlQuery = "SELECT * FROM cashier WHERE cashier_id = ? and password = ?";
				
				PreparedStatement stm;
				stm = conn.prepareStatement(sqlQuery);
				stm.setString(1, id);
				stm.setString(2, ps);
				
				ResultSet rs = stm.executeQuery();
					if(rs.next()) {
						c = new Cashier(rs.getString("cashier_id"), rs.getString("cashier_name"), rs.getString("password"));
					}
				
					
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		System.out.println(c.getCashierID());
		System.out.println(c.getCashierName());
		System.out.println(c.getPassword());
			
	
	}
	

}

