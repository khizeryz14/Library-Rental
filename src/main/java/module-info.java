module libraryrental.libraryrental {
    requires javafx.controls;
    requires javafx.fxml;


    opens libraryrental.libraryrental to javafx.fxml;
    exports libraryrental.libraryrental;
}