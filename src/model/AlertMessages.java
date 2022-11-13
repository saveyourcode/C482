package model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/** Class that holds the static methods for creating alert windows. */
public class AlertMessages {

    private static Alert alert;

    /** Creates a warning window.
     @param text the text that is displayed in the window*/
    public static void warningAlert(String text) {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(text);
        alert.showAndWait();
    }

    /** Creates an error window.
     @param text the text that is displayed in the window*/
    public static void errorAlert(String text) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(text);
        alert.showAndWait();
    }

    /** Creates an information window.
        @param text the text that is displayed in the window*/
    public static void informationAlert(String text) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(text);
        alert.showAndWait();
    }

    /** Creates a confirmation window and records the users choice.
        @param text the text that is displayed in the window
        @return boolean depending on if the user chooses yes or no*/
    public static boolean confirmationAlert(String text) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(text);
        Optional<ButtonType> userChoice = alert.showAndWait();
        if (userChoice.isPresent() && userChoice.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
}
