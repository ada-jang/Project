package com.yedam.account;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AccountController implements Initializable {
	@FXML DatePicker selectDate;
	@FXML TextField txtPrice, selectList;
	@FXML Button btnInput, btnList, btnEnd; 
		
	Connection conn;
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "hr", "hr");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/mm/dd");
		String sql = "insert into board(exit_date date, list, price) " + "value(?,?,?)";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, selectDate.getValue().format(formatter));
			pst.setString(2, selectList.getText());
			pst.setInt(3, Integer.parseInt(txtPrice.getText()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
