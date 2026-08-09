package libraryrental.libraryrental;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class LoginController {
    @FXML
    TextField usernameField = new TextField();
    @FXML
    TextField seatnoField = new TextField();
    @FXML
    TextField libidField = new TextField();
    @FXML
    PasswordField passwordField = new PasswordField();
    @FXML
    Label messageLabel = new Label();
    public void authorize(ActionEvent event) throws IOException {
        boolean isValid = false;
        try {
             isValid = LoginModel.validate(usernameField.getText(), seatnoField.getText(),
                    libidField.getText(), passwordField.getText());
        }
        catch(FileNotFoundException e){
            System.out.println("FileNotFoundException: "+ e);
        } catch (IOException e) {
            System.out.println("IOException: "+ e);
        }
        if (isValid) {
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Parent root = loader.load();
            MenuController menuController = loader.getController();
            menuController.initialize(usernameField.getText(),seatnoField.getText(),
                libidField.getText());
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm());
            stage.setScene(scene);
        }
        else {
            messageLabel.setText("INVALID CREDENTIALS!");
            messageLabel.setVisible(true);
        }

    }
}