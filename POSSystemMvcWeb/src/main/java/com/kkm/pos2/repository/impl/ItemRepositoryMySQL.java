package com.kkm.pos2.repository.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.kkm.pos2.database.DatabaseConnection;
import com.kkm.pos2.domain.Item;
import com.kkm.pos2.exception.DatabaseConnectionException;
import com.kkm.pos2.exception.RepositoryException;
import com.kkm.pos2.repository.ItemRepository;

public class ItemRepositoryMySQL implements ItemRepository{
	
//	String userName = "root";
//	String password = "";
//	String jdbcUrl = "jdbc:mysql://localhost:3306/pos2_db";
//	
//	public void editItem(Item item) {
//		Connection conn = null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(jdbcUrl, userName, password);
//			String sql = "UPDATE item SET price=?, description=?, type=?, taxable=? WHERE item_code=?";
//	        PreparedStatement stm = conn.prepareStatement(sql);
//	        
//	            stm.setDouble(1, item.getItemPrice());
//	            stm.setString(2, item.getDescription());
//	            stm.setString(3, item.getType());
//	            stm.setBoolean(4, item.isTaxable());
//	            stm.setString(5, item.getItemCode());
//
//	            stm.executeUpdate();
//	            
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		
//	}
//	
//	public void addItem(Item item) {
//			Connection conn = null;
//
//			try {
//				Class.forName("com.mysql.jdbc.Driver");
//				conn = DriverManager.getConnection(jdbcUrl, userName, password);
//				conn.setAutoCommit(false);
//				
//				String sql = "INSERT INTO item(item_code, price, description, type, taxable) values (?,?,?,?,?)";
//				PreparedStatement stm = conn.prepareStatement(sql);
//				
//				stm.setString(1, item.getItemCode());
//				stm.setDouble(2, item.getItemPrice());
//				stm.setString(3, item.getDescription());
//				stm.setString(4, item.getType());
//				stm.setBoolean(5, item.isTaxable());
//				
//				stm.executeUpdate();
//				
//				conn.commit();
//				
//				
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//	}
//	
//	public List<Item> findAll() {	
//		List<Item> listItem = new ArrayList<Item>();
//		try {
//			//load driver
//			Class.forName("com.mysql.jdbc.Driver");
//			
//			//get db connection -- jdbc url
//			Connection conn = DriverManager.getConnection(jdbcUrl, userName, password);
//			String sqlQuery = "SELECT * FROM item";
//			
//			Statement stm = conn.createStatement();
//			ResultSet rs = stm.executeQuery(sqlQuery);
//			
//			//add item
//			while(rs.next()) {
//				boolean isTaxable;
//				if(rs.getInt("taxable") == 1) {
//					isTaxable = true;
//				} else {
//					isTaxable = false;
//				}
//				listItem.add(new Item(rs.getString("item_code"), rs.getDouble("price"), rs.getString("description"), rs.getString("type"), isTaxable));
//			}
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} return listItem;
//	}
//	@Override
//	public Item findByCode(String code) {
//		Item result = null;
//		
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection(jdbcUrl, userName, password);
//			String sqlQuery = "SELECT * FROM item WHERE item_code = ?";
//			
//			PreparedStatement stm = conn.prepareStatement(sqlQuery);
//			stm.setString(1, code);
//			
//			ResultSet rs = stm.executeQuery();
//				if(rs.next()) {
//					result = new Item(rs.getString("item_code"), rs.getDouble("price"), rs.getString("description"), rs.getString("type"), rs.getBoolean("taxable"));
//				}
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//		return result;
//	}
	
	
	
	@Override
	public Item findByCode(String code) {
		Item result = null;
		
		try (Connection conn = DatabaseConnection.conn()){

			String sqlQuery = "SELECT * FROM item WHERE item_code = ?";
			
			PreparedStatement stm = conn.prepareStatement(sqlQuery);
			stm.setString(1, code);
			
			ResultSet rs = stm.executeQuery();
				if(rs.next()) {
					result = new Item(rs.getString("item_code"), rs.getDouble("price"), rs.getString("description"), rs.getString("type"), rs.getBoolean("taxable"));
				}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		} 	
		return result;
	}
	
	public void save() {
		// TODO Auto-generated method stub
		
	}
	
	public List<Item> findAll(){
		List<Item> listItem = new ArrayList<Item>();
		try (Connection conn = DatabaseConnection.conn()){

			//Class.forName("com.mysql.jdbc.Driver");

			String sqlQuery = "SELECT * FROM item";
			
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sqlQuery);
			
			//add item
			while(rs.next()) {
				boolean isTaxable;
				if(rs.getInt("taxable") == 1) {
					isTaxable = true;
				} else {
					isTaxable = false;
				}
				listItem.add(new Item(rs.getString("item_code"), rs.getDouble("price"), rs.getString("description"), rs.getString("type"), isTaxable));
			}
		} catch (SQLException e) {
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} 
		return listItem;
	}
	
	public void editItem(Item item){
		try (Connection conn = DatabaseConnection.conn()){
			//Class.forName("com.mysql.jdbc.Driver");
			String sql = "UPDATE item SET price=?, description=?, type=?, taxable=? WHERE item_code=?";
	        PreparedStatement stm = conn.prepareStatement(sql);
	        
	            stm.setDouble(1, item.getItemPrice());
	            stm.setString(2, item.getDescription());
	            stm.setString(3, item.getType());
	            stm.setBoolean(4, item.isTaxable());
	            stm.setString(5, item.getItemCode());

	            stm.executeUpdate();
	            
		} catch (SQLException e) {
			e.printStackTrace();
		} 
//			catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} 
		
	}
	
	public void addItem(Item item) {

		try (Connection conn = DatabaseConnection.conn()){
			//Class.forName("com.mysql.jdbc.Driver");
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO item(item_code, price, description, type, taxable) values (?,?,?,?,?)";
			PreparedStatement stm = conn.prepareStatement(sql);
			
			stm.setString(1, item.getItemCode());
			stm.setDouble(2, item.getItemPrice());
			stm.setString(3, item.getDescription());
			stm.setString(4, item.getType());
			stm.setBoolean(5, item.isTaxable());
			
			stm.executeUpdate();
			
			conn.commit();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
//		catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} 
		
}

}
