package squid;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Squid squid;

    private final Image USER_IMAGE = new Image(this.getClass().getResourceAsStream("/images/whale.png"));
    private final Image SQUID_IMAGE = new Image(this.getClass().getResourceAsStream("/images/squid.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getSquidDialog(Ui.showGreeting(), SQUID_IMAGE));
    }

    /** Injects the squid instance */
    public void setSquid(Squid d) {
        squid = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing squid's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = squid.getResponse(input);
        boolean isExit = response.equals("Bye!");
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMAGE),
                DialogBox.getSquidDialog(response, SQUID_IMAGE)
        );
        userInput.clear();
        if (isExit) {
            handleExit();
        }
    }

    /**
     * Schedules the application to close after a delay, displaying the goodbye message.
     */
    private void handleExit() {
        new Thread(() -> {
            try {
                Thread.sleep(1500);
                Platform.runLater(() -> {
                    Stage stage = (Stage) scrollPane.getScene().getWindow();
                    stage.close();
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
