
package J_R_C.JOGL.BaseGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import Utility.EncryptionManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author KJW finish at 2016/ 02/ 15
 * @version 1.0.0v
 * @description this class Start The Application and Set The init State
 * @copyRight of KJW all Rights Reserved and follow the MIT license
 */
public class LoginMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		if (Settings.isDevelopingMode)
			BuildingTimeReadFile();

		// load the fxml resource
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/root.fxml"));
		Parent root = loader.load();

		// set the fxml resource to the scene
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/Css/app.css").toString());

		LoginController controller = loader.getController();
		controller.setPrimaryStage(primaryStage);
		// set the primary stage
		primaryStage.setTitle("J.R.C Game Client");
		primaryStage.setScene(scene);
		// set the user can resize the window
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#stop() if stop event is occurred then
	 * this method is called by system
	 */
	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
		// all resource wiil be return to the system
		LoginController.getClient().sendPacket(Settings._REQUEST_TERMINATE + "");
		// check the termination
		System.out.println("Terminate the Login System");
		fileSetBuildingTime();
		/*
		 * totally terminate the application. this application's resource will
		 * return to system resource manager
		 */
		Platform.exit();
		System.exit(0);
	}

	public static void main(String[] args) {

		// launch the fx application init function
		launch(args);
	}

	private void fileSetBuildingTime() {
		PrintWriter pw;
		try {
			pw = new PrintWriter(Settings.sBuildingVersionFileName);
			pw.println(EncryptionManager.encrypt64bits(Settings.nBuildingTimes + ""));
			pw.println(EncryptionManager.encrypt64bits(Settings.clientVersion));
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("serverinfo.jrc do not exist");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void BuildingTimeReadFile() {
		BufferedReader br;
		try {

			br = new BufferedReader(new FileReader(Settings.sBuildingVersionFileName));

			String temp = br.readLine();

			temp = temp == null ? "0" : temp;

			if (!temp.equals("0"))
				Settings.nBuildingTimes = Integer.parseInt(EncryptionManager.decrypt64bits(temp));
			else
				Settings.nBuildingTimes = Integer.parseInt("0");
			Settings.nBuildingTimes++;
			System.out.println("BuildTime : " + Settings.nBuildingTimes);
			br.close();
		}

		catch (FileNotFoundException e) {
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
