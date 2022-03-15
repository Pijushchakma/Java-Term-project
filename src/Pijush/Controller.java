package Pijush;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;
    @FXML


    private Main main;
    // private Button SignUp;


    public void setMain(Main main) {
        this.main = main;
    }

    public void SignUpAction(ActionEvent event) {
    }

    public void LoginAction(ActionEvent event) {
        String validUserName = "a";
        String validPassword = "a";
        String userName = userText.getText();
        String password = passwordText.getText();
        ;
        if (userName.equals(validUserName) && password.equals(validPassword)) {
            // successful login
            try {
                main.showHomePage(userName);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            // failed login
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Credentials");
            alert.setHeaderText("Incorrect Credentials");
            alert.setContentText("The username and password you provided is not correct.");
            alert.showAndWait();
        }
    }
}

