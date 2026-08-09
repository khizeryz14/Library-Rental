package libraryrental.libraryrental;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class SummaryController {
    @FXML
    ImageView mainImage;
    @FXML
    Label bookTitle;
    @FXML
    Label bookDesc;
    @FXML
    ImageView backButton;
    @FXML
    Label authorLabel;
    double basePrice, price;
    @FXML
    DatePicker datePicker;
    @FXML
    Label invalidDateMessage;
    @FXML
    Label priceLabel;
    @FXML
    Label basePriceLabel;
    @FXML
    Button cartButton;

    public void initialize(String imgSrc, String bookT, String bookD, String bookA, double bp) {
        Image image = new Image(imgSrc);
        mainImage.setImage(image);
        bookTitle.setText(bookT);
        bookDesc.setText(bookD);
        authorLabel.setText(bookA);
        basePrice = bp;
        basePriceLabel.setText(String.format("$%.2f",basePrice));
    }

    public void goBack(Event event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
        Parent root = loader.load();
        MenuController menuController = loader.getController();
        menuController.initialize(MenuController.sessionUsername,MenuController.sessionSeatNo,
                MenuController.sessionLibID);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm());
        stage.setScene(scene);
    }

    public void setPrice(Event event) {
        long current = LocalDate.now().toEpochDay();
        long pickedDate = datePicker.getValue().toEpochDay();
        int dateDifference = (int)(pickedDate-current);
        if(dateDifference > 0) {
            price = dateDifference*basePrice;
            priceLabel.setText(String.format("$%.2f",price));
            priceLabel.setOpacity(1);
            invalidDateMessage.setVisible(false);
            cartButton.setOpacity(1);
        }
        else {
            invalidDateMessage.setVisible(true);
            cartButton.setOpacity(0.3);
            priceLabel.setText("$0.00");
            priceLabel.setOpacity(0.3);
        }
    }

    public void addToCart(Event event) {
        Button clickedBtn = (Button)(event.getSource());
        if(clickedBtn.getOpacity() == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Added to cart successfully");
            alert.show();
            Cart.totalPrice += price;
            alert.setContentText(String.format("Cart total: $%.2f",Cart.totalPrice));
            Cart.bookList.add(bookTitle.getText());
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid date");
            alert.setContentText("Please validate rental date first!");
            alert.show();
        }
    }

    public void checkout(Event event) throws IOException {
        MenuController m = new MenuController();
        m.checkoutToCart(event);
    }
}
