package com.yedam.account;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Button btnSignup;
	@FXML
	private Button btnSignin;

	Connection con = null;
	
	PreparedStatement preparedStatement= null;
	ResultSet resultSet = null; 
	
	public LoginController() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.95:1521:xe", "hr", "hr");
//			 con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.3:1521:xe", "hr", "hr");
		
			System.out.println("연결");
		} catch (ClassNotFoundException | SQLException ex) {
			System.err.println("ConnectionUtil : " + ex.getMessage());

		}

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO

	}

	@FXML
	public void handleButtonAction(MouseEvent event) {

		System.out.println(event.getSource());

		if (event.getSource() == btnSignin) {
			// login here
			if (logIn().equals("Success")) {
				try {

					// add you loading or delays - ;-)
					Node node = (Node) event.getSource();
					Stage stage = (Stage) node.getScene().getWindow();
					// stage.setMaximized(true);
					stage.close();
					Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MainPageControl.fxml")));
					stage.setScene(scene);
					stage.show();

				} catch (IOException ex) {
					System.err.println(ex.getMessage());
				}

			}

		} else if (event.getSource() == btnSignup) {
			System.out.println("signup");
			if (signUp().equals("Success")) {

			} else {

			}
		}
	}

	private String signUp() {
		String status = "Success";
		String email = txtUsername.getText();
		String password = txtPassword.getText();
		if (email.isEmpty() || password.isEmpty()) {
			status = "Error";
		} else {
			// query
			String sql = "insert into accbook_admins values(?, ?)";
			try {
				preparedStatement = con.prepareStatement(sql);
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, password);
				int r = preparedStatement.executeUpdate();
				if (r == 0) {
					status = "Error";
				} else {
					
				}
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
				status = "Exception";
			}
		}
		
		return status;
	}

	// we gonna use string to check for status
	private String logIn() {
		String status = "Success";
		String email = txtUsername.getText();
		String password = txtPassword.getText();
		if (email.isEmpty() || password.isEmpty()) {
			status = "Error";
		} else {
			// query
			String sql = "SELECT * FROM accbook_admins Where user_name = ? and password = ?";
			try {
				preparedStatement = con.prepareStatement(sql);
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, password);
				resultSet = preparedStatement.executeQuery();
				if (!resultSet.next()) {
					status = "Error";
				} else {
				}
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
				status = "Exception";
			}
		}

		return status;
	}

}
