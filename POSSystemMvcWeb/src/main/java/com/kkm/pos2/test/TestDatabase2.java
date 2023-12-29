package com.kkm.pos2.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kkm.pos2.domain.Cashier;
import com.kkm.pos2.domain.Item;
import com.kkm.pos2.domain.Sale;
import com.kkm.pos2.domain.SaleItem;
import com.kkm.pos2.repository.impl.ItemRepositoryMySQL;

public class TestDatabase2 {

	
	
	public static void main(String[] args) {
	
		ItemRepositoryMySQL repo = new ItemRepositoryMySQL(); 
		//repo.addItem(new Item("008", 5000, "fanta", "minuman", false));
		
		Item test = repo.findByCode("001");
		System.out.println(test.getItemPrice());
	}	
	

}
