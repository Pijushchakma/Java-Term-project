package Pijush;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        showLoginPage();
    }

    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Mursheda.fxml"));
        Parent root = loader.load();

        // Loading the controller
        Controller controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 697, 400));
        stage.show();
    }

    public void showHomePage(String userName) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HomePage.fxml"));
        Parent root = loader.load();

        // Loading the controller
        HomePageController controller = loader.getController();
        controller.init(userName);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Home");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
public void showGamePage()throws Exception{
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("Gamepic.fxml"));
    Parent root = loader.load();

    // Loading the controller
    GameController controller = loader.getController();
    controller.setMain(this);
    controller.createPlayer();

    // Set the primary stage
    stage.setTitle("Snake Ludo Game");
    stage.setScene(new Scene(root, 749, 952));
    stage.show();

}
    public void WinningPage()throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Winning.fxml"));
        Parent root = loader.load();

        // Loading the controller
        GameController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Winner");
        stage.setScene(new Scene(root, 749, 952));
        stage.show();
    }
    public static void main(String[] args) {
        // This will launch the JavaFX application
        launch(args);
    }
}