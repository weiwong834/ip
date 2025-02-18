package squid;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
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

    private void displayGreeting() {
        String greeting = squid.ui.showGreeting();
        dialogContainer.getChildren().add(DialogBox.getSquidDialog(greeting, SQUID_IMAGE));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing squid's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = squid.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMAGE),
                DialogBox.getSquidDialog(response, SQUID_IMAGE)
        );
        userInput.clear();
    }
}
