package libraryrental.libraryrental;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class CheckoutController {
    @FXML
    Label usernameLabel, libidLabel, seatnoLabel, booksLabel, costLabel;

    public void initialize() {
        usernameLabel.setText(MenuController.sessionUsername);
        libidLabel.setText(MenuController.sessionLibID);
        seatnoLabel.setText(MenuController.sessionSeatNo);
        costLabel.setText(String.format("$%.2f",Cart.totalPrice));

        for(int x = 0; x<Cart.bookList.size(); x++) {
            if (booksLabel.getText().equals("")){
                booksLabel.setText((x+1) + ") " + Cart.bookList.get(x) + "\n");
            }
            else {
                booksLabel.setText(booksLabel.getText() + (x+1) + ") " + Cart.bookList.get(x) + "\n" );
            }
        }
    }

    public void goBack(Event event) throws IOException {
        SummaryController sc = new SummaryController();
        sc.goBack(event);
    }

    public void confirmAndExit(Event event) throws IOException {
        if (Cart.totalPrice > 0.0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Checkout");
            alert.setHeaderText("Are you sure you want to confirm your order?");
            alert.setContentText("The program will exit you to the logout screen");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Main m = new Main();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(m.sceneLoader("end-view.fxml"));
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cart is empty!");
            alert.setContentText("Please add items before checkout");
            alert.show();
        }
    }
}

class Cart {
    static double totalPrice = 0;
    static ArrayList<String> bookList = new ArrayList<>();
}