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

/** Holds all the methods of the Modify Part Form controller. */
public class ModifyPartForm implements Initializable {

    Stage stage;
    Parent scene;
    private int index;

    @FXML
    private TextField modifyPartFormIDTxt;

    @FXML
    private RadioButton modifyPartFormInHouseRbtn;

    @FXML
    private TextField modifyPartFormInvTxt;

    @FXML
    private TextField modifyPartFormMachineCompanyTxt;

    @FXML
    private TextField modifyPartFormMaxTxt;

    @FXML
    private TextField modifyPartFormMinTxt;

    @FXML
    private TextField modifyPartFormNameTxt;

    @FXML
    private RadioButton modifyPartFormOutsourcedRbtn;

    @FXML
    private TextField modifyPartFormPriceCostTxt;

    @FXML
    private Label modifyPartFormMachineCompanyLabel;

//    public void transferPart(Part part) {
//
//        modifyPartFormIDTxt.setText(String.valueOf(part.getId()));
//        modifyPartFormNameTxt.setText(part.getName());
//        modifyPartFormInvTxt.setText(String.valueOf(part.getStock()));
//        modifyPartFormPriceCostTxt.setText(String.valueOf(part.getPrice()));
//        modifyPartFormMaxTxt.setText(String.valueOf(part.getMax()));
//        modifyPartFormMinTxt.setText(String.valueOf(part.getMin()));
//        if (part instanceof InHouse) {
//            modifyPartFormMachineCompanyTxt.setText(String.valueOf(((InHouse) part).getMachineId()));
//            modifyPartFormInHouseRbtn.setSelected(true);
//
//        } else if (part instanceof Outsourced)  {
//            modifyPartFormMachineCompanyTxt.setText(((Outsourced) part).getCompanyName());
//            modifyPartFormOutsourcedRbtn.setSelected(true);
//        }
//
//    }

    /** Transfers the index of the selected part from the main form and populates text fields with data.
     Transfers the index of the selected part from the main form and uses the index to get the corresponding
     part from the all parts list. The part's getter methods are used to fill the text boxes and the radio button
     is selected to match the parts type.
     @param index of the selected part from the main form*/
    public void transferPart(int index) {
        this.index = index;
        Part chosenPart = Inventory.getAllParts().get(index);
        modifyPartFormIDTxt.setText(String.valueOf(chosenPart.getId()));
        modifyPartFormNameTxt.setText(chosenPart.getName());
        modifyPartFormInvTxt.setText(String.valueOf(chosenPart.getStock()));
        modifyPartFormPriceCostTxt.setText(String.valueOf(chosenPart.getPrice()));
        modifyPartFormMaxTxt.setText(String.valueOf(chosenPart.getMax()));
        modifyPartFormMinTxt.setText(String.valueOf(chosenPart.getMin()));
        if (chosenPart instanceof InHouse) {
            modifyPartFormMachineCompanyTxt.setText(String.valueOf(((InHouse) chosenPart).getMachineId()));
            modifyPartFormInHouseRbtn.setSelected(true);

        } else if (chosenPart instanceof Outsourced)  {
            modifyPartFormMachineCompanyTxt.setText(((Outsourced) chosenPart).getCompanyName());
            modifyPartFormOutsourcedRbtn.setSelected(true);
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /** Cancels part modification and returns to main form.
     Cancels part modification, any changes made to the part are lost, and returns to the main form.
     @param event the cancel button is pressed*/
    @FXML
    void onActionCancelModifyPart(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Modifies a part and adds it to the all parts list.
        Creates either a new InHouse or OutSourced part to replace the existing part depending
        on which radio button is pressed. Catches exceptions for parsing integer and double variable
        and checks if min is greater than max and if inv is between min and max.
        @param event the save button is pressed*/
    @FXML
    void onActionSaveModifyPart(ActionEvent event) throws IOException {

        if (AlertMessages.confirmationAlert("Do you want to save this part?")) {

            try{
                int id = Integer.parseInt(modifyPartFormIDTxt.getText());
                String name = modifyPartFormNameTxt.getText();
                double price = Double.parseDouble(modifyPartFormPriceCostTxt.getText());;
                int min = Integer.parseInt(modifyPartFormMinTxt.getText());
                int max = Integer.parseInt(modifyPartFormMaxTxt.getText());
                if (min > max) {
                    AlertMessages.errorAlert("Min should be less than max");
                    return;
                }
                int inv = Integer.parseInt(modifyPartFormInvTxt.getText());
                if (inv > max || inv < min) {
                    AlertMessages.errorAlert("Inv should be less than max and greater than min");
                    return;
                }
                int machineId;
                String companyName;
                if (modifyPartFormInHouseRbtn.isSelected()) {
                    machineId = Integer.parseInt(modifyPartFormMachineCompanyTxt.getText());
                    Inventory.updatePart(this.index, new InHouse(id, name, price, inv, min, max, machineId));

                } else {
                    companyName = modifyPartFormMachineCompanyTxt.getText();
                    Inventory.updatePart(this.index, new Outsourced(id, name, price, inv, min, max, companyName));
                }

            } catch (NumberFormatException e) {
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
    void onActionModifyPartFormInHouseRbtn(ActionEvent event) {

        modifyPartFormMachineCompanyLabel.setText("Machine Id");

    }

    /** When pressed the bottom text label is set to "Company name".
        @param event the Outsourced radio button is pressed*/
    @FXML
    void onActionModifyPartFormOutsourcedRbtn(ActionEvent event) {

        modifyPartFormMachineCompanyLabel.setText("Company Name");

    }
}
