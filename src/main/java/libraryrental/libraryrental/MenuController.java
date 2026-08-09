package libraryrental.libraryrental;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class MenuController {
    @FXML
    Label usernameLabel;
    @FXML
    Label seatnoLabel;
    @FXML
    Label libidLabel;
    @FXML
    ImageView userImage;

    static String sessionUsername;
    static String sessionSeatNo;
    static String sessionLibID;
    static String sessionUserPicture;
    public void initialize(String username, String seatNo, String libID) {
        sessionUsername = username;
        sessionSeatNo = seatNo;
        sessionLibID = libID;
        usernameLabel.setText(sessionUsername);
        seatnoLabel.setText(sessionSeatNo);
        libidLabel.setText(sessionLibID);
        String srcPath = "C:\\Khizer Projects (CPP)\\Java OOP\\LibraryRental\\src\\main\\resources\\libraryrental\\libraryrental\\";
        userImage.setImage(new Image(srcPath+sessionUserPicture));
    }

    public void changeToBookOverview(Event event) throws IOException {
        String absPath = "C:\\Khizer Projects (CPP)\\Java OOP\\LibraryRental\\src\\main\\resources\\libraryrental\\libraryrental\\";
        String btnName = ((Button)event.getSource()).getText();
        String bookDesc, bookAuthor;
        switch (btnName) {
            case "Atomic Habits":
                bookDesc = getBookDesc(btnName);
                bookAuthor = "James Clear";
                sceneSwitch(event, absPath+"atomic.jpg",btnName, bookDesc, bookAuthor, 0.5);
                break;
            case "Dog Man":
                bookDesc = getBookDesc(btnName);
                bookAuthor = "Dav Pilkey";
                sceneSwitch(event, absPath+"dogman.jpg",btnName, bookDesc, bookAuthor, 0.1);
                break;
            case "Mein Kampf":
                bookDesc = getBookDesc(btnName);
                bookAuthor = "Adolf Hitler";
                sceneSwitch(event, absPath+"meinkampf.jpg",btnName, bookDesc, bookAuthor, 2.0);
                break;
            case "Simpson V":
                bookDesc = getBookDesc(btnName);
                bookAuthor = "Sharky Finny";
                sceneSwitch(event, absPath+"simpsons.jpg",btnName, bookDesc, bookAuthor, 0.3);
                break;
            case "A Court Of Mist And Fury":
                bookDesc = getBookDesc(btnName);
                bookAuthor = "Sarah J. Maas";
                sceneSwitch(event, absPath+"acourt.jpg",btnName, bookDesc, bookAuthor, 0.5);
                break;
            case "OOP With Java":
                bookDesc = getBookDesc(btnName);
                bookAuthor = "Mukesh Kumar Rathee";
                sceneSwitch(event, absPath+"JavaOOP.jpeg",btnName, bookDesc, bookAuthor, 1.2);
                break;
        }

    }

    public void sceneSwitch(Event event, String imgSrc, String bookT, String bookD, String bookA, double bp) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("summary-view.fxml"));
        Parent root = loader.load();
        SummaryController summaryController = loader.getController();
        summaryController.initialize(imgSrc,bookT,bookD, bookA, bp);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm());
        stage.setScene(scene);
    }

    public String getBookDesc(String bookName) throws IOException {
        String filePath = "C:\\Khizer Projects (CPP)\\Java OOP\\LibraryRental\\src\\main\\resources\\libraryrental\\libraryrental\\bookDescriptions.txt";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String [] parts;
        String line = reader.readLine();
        while(line != null) {
            parts = line.split(";");
            if((parts[0].trim().equals(bookName))) {
                return parts[1].trim();
            }
            line = reader.readLine();
        }
        return "null";
    }

    public void logout(Event event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Main m = new Main();
        Scene scene = m.sceneLoader("login-view.fxml");
        stage.setScene(scene);
        Cart.totalPrice = 0;
        Cart.bookList.clear();
    }

    public void checkoutToCart(Event event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Main m = new Main();
        Scene scene = m.sceneLoader("checkout-view.fxml");
        stage.setScene(scene);
    }
}
