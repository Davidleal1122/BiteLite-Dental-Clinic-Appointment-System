package main;

import connectDatabase.DatabaseConnection;
import screens.FramePanelHolder;
import screens.LoginScreen;
import screens.SplashScreen;

public class Main {

	public static void main(String[] args) {

		DatabaseConnection databaseConnect = new DatabaseConnection();

//	
		SplashScreen loadingFrame = new SplashScreen();
		//LoginScreen loginFrame = new LoginScreen();
	//	FramePanelHolder dashboardFrame = new FramePanelHolder();

		loadingFrame.setVisible(true);
		//loginFrame.setVisible(true);
		//dashboardFrame.setVisible(true);

	}
}
