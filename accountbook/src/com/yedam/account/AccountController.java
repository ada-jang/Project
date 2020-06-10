package com.yedam.account;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AccountController implements Initializable {
	@FXML
	DatePicker selectDate;
	@FXML
	TextField txtPrice, txtList;
	@FXML
	Button btnInput, btnList, btnEnd;

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
	}

	public void handleBtnInputAction(ActionEvent e) {
		if (selectDate.getValue() == null || selectDate.getValue().equals("")) {

		} else if (txtList.getText() == null || txtList.getText().equals("")) {

		} else if (txtPrice.getText() == null || txtPrice.getText().equals("")) {

		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			System.out.println(selectDate.getValue().format(formatter));
			String sql = "insert into accountbook(exit_date, list, price) " + " values(?,?,?)";
			try {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, selectDate.getValue().format(formatter));
				pst.setString(2, txtList.getText());
				pst.setInt(3, Integer.parseInt(txtPrice.getText()));
			
				int r = pst.executeUpdate();
				System.out.println(r + "입력 되었습니다.");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} // end of insert
	}

	public void handleBtnListAction(ActionEvent e) {
		Stage stageList = new Stage(StageStyle.DECORATED);
		stageList.initModality(Modality.WINDOW_MODAL);
		stageList.initOwner(btnList.getScene().getWindow());
			
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("EditControl.fxml"));
			Scene scene = new Scene(parent);
			stageList.setScene(scene);
			stageList.setResizable(false);
			stageList.show();
			
			
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	} //end of list
	
	public void handleBtnChartAction(ActionEvent e) {
		
	} //end of Chart

	public void handleBtnEndAction(ActionEvent e) {
		Platform.exit();
	}// end of exit
}
