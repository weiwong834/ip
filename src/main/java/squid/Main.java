package squid;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Squid using FXML.
 */
public class Main extends Application {

    private Squid squid = new Squid("data/squid.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Squid");
            Image logo = new Image("images/logo.png");
            stage.getIcons().add(logo);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setMaxWidth(800);
            fxmlLoader.<MainWindow>getController().setSquid(squid);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
