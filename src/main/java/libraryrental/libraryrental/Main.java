package libraryrental.libraryrental;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = sceneLoader("login-view.fxml");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("DULC Library");
        stage.getIcons().add(new Image("C:\\Khizer Projects (CPP)\\Java OOP\\LibraryRental\\src\\main\\resources\\libraryrental\\libraryrental\\logo.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public Scene sceneLoader(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm());
        return scene;
    }


}
