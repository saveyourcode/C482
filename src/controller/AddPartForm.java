package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** Holds all the methods of the Add Part Form controller. */
public class AddPartForm implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField addPartFormIDTxt;

    @FXML
    private RadioButton addPartFormInHouseRbtn;

    @FXML
    private TextField addPartFormInvTxt;

    @FXML
    private TextField addPartFormMachineCompanyTxt;

    @FXML
    private TextField addPartFormMaxTxt;

    @FXML
    private TextField addPartFormMinTxt;

    @FXML
    private TextField addPartFormNameTxt;

    @FXML
    private RadioButton addPartFormOutsourcedRbtn;

    @FXML
    private TextField addPartFormPriceCostTxt;

    @FXML
    private Label addPartFormMachineCompanyLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    /** Cancels part creation and returns to main form.
     Cancels part creation, any changes made to the part are lost, and returns to the main form.
     @param event the cancel button is pressed*/
    @FXML
    void onActionCancelAddPart(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Creates a new part and adds it to the all parts list.
     Creates either a new InHouse or OutSourced part depending on which radio button is pressed.
     Catches exceptions for parsing integer and double variable and checks if min is greater than
     max and if inv is between min and max.
     @param event the save button is pressed*/
    @FXML
    void onActionSaveAddPart(ActionEvent event) throws IOException {

        if (AlertMessages.confirmationAlert("Do you want to save this part?")){

            try {
                int id = Inventory.getPartId();
                String name = addPartFormNameTxt.getText();
                double price = Double.parseDouble(addPartFormPriceCostTxt.getText());
                int max = Integer.parseInt(addPartFormMaxTxt.getText());
                int min = Integer.parseInt(addPartFormMinTxt.getText());
                if (min > max) {
                    AlertMessages.errorAlert("Min should be less than max");
                    return;
                }
                int inv = Integer.parseInt(addPartFormInvTxt.getText());
                if (inv > max || inv < min) {
                    AlertMessages.errorAlert("Inv should be less than max and greater than min");
                    return;
                }
                int machineId;
                String companyName;
                if (addPartFormInHouseRbtn.isSelected()) {
                    machineId = Integer.parseInt(addPartFormMachineCompanyTxt.getText());
                    Inventory.addPart(new InHouse(id, name, price, inv, min, max, machineId));

                } else {
                    companyName = addPartFormMachineCompanyTxt.getText();
                    Inventory.addPart(new Outsourced(id, name, price, inv, min, max, companyName));
                }

            } catch(Exception e) {

                AlertMessages.errorAlert("Inputs in wrong format\n" +
                        "Exception: " + e + "\n" +
                        "Exception: " + e.getMessage());
                return;

            }

            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        }



    }

    /** When pressed the bottom text label is set to "Machine Id".
     @param event the InHouse radio button is pressed*/
    @FXML
    void onActionAddPartFormInHouseRbtn(ActionEvent event) {

        addPartFormMachineCompanyLabel.setText("Machine Id");

    }

    /** When pressed the bottom text label is set to "Company name".
     @param event the Outsourced radio button is pressed*/
    @FXML
    void onActionAddPartFormOutsourcedRbtn(ActionEvent event) {

        addPartFormMachineCompanyLabel.setText("Company Name");

    }
}
