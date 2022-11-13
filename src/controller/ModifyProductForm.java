package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.AlertMessages;
import model.Inventory;
import model.Part;
import model.Product;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** Holds all the methods of the Modify Product Form controller. */
public class ModifyProductForm implements Initializable {

    Stage stage;
    Parent scene;
    private int index;
    private ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Part, Integer> modifyProductFormBottomTableInventoryCol;

    @FXML
    private TableColumn<Part, Integer> modifyProductFormBottomTablePartIDCol;

    @FXML
    private TableColumn<Part, String> modifyProductFormBottomTablePartNameCol;

    @FXML
    private TableColumn<Part, Double> modifyProductFormBottomTablePriceCostCol;

    @FXML
    private TableView<Part> modifyProductFormBottomTableView;

    @FXML
    private TextField modifyProductFormIDTxt;

    @FXML
    private TextField modifyProductFormInvTxt;

    @FXML
    private TextField modifyProductFormMaxTxt;

    @FXML
    private TextField modifyProductFormMinTxt;

    @FXML
    private TextField modifyProductFormNameTxt;

    @FXML
    private TextField modifyProductFormPrice;

    @FXML
    private TextField modifyProductFormSearch;

    @FXML
    private TableColumn<Part, Integer> modifyProductFormTopTableInventoryCol;

    @FXML
    private TableColumn<Part, Integer> modifyProductFormTopTablePartIDCol;

    @FXML
    private TableColumn<Part, String> modifyProductFormTopTablePartNameCol;

    @FXML
    private TableColumn<Part, Double> modifyProductFormTopTablePriceCostCol;

    @FXML
    private TableView<Part> modifyProductFormTopTableView;

//    public void transferProduct(Product product) {
//
////        this.selectedProduct = product;
//        this.partsList = FXCollections.observableArrayList();
//        for (Part part: product.getAllAssociatedParts()) {
//            this.partsList.add(part);
//        }
//
//        modifyProductFormIDTxt.setText(String.valueOf(product.getId()));
//        modifyProductFormNameTxt.setText(product.getName());
//        modifyProductFormInvTxt.setText(String.valueOf(product.getStock()));
//        modifyProductFormPrice.setText(String.valueOf(product.getPrice()));
//        modifyProductFormMaxTxt.setText(String.valueOf(product.getMax()));
//        modifyProductFormMinTxt.setText(String.valueOf(product.getMin()));
//
////        modifyProductFormBottomTableView.setItems(product.getAllAssociatedParts());
////        modifyProductFormBottomTablePartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
////        modifyProductFormBottomTablePartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
////        modifyProductFormBottomTableInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
////        modifyProductFormBottomTablePriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
//
//    }

    /** Transfers the index of the selected product from the main form and populates text fields with data.
        Transfers the index of the selected product from the main form and uses the index to get the corresponding
        product.  The product's getter methods are used to fill the text boxes and table that holds the
        associated parts is filled with the parts that are associated with the product.
        @param index of the selected product from the main form*/
    public void transferProduct(int index) {

        this.index = index;
        Product chosenProduct = Inventory.getAllProducts().get(index);
        for (Part part: chosenProduct.getAllAssociatedParts()) {
            this.associatedPartsList.add(part);
        }

        modifyProductFormIDTxt.setText(String.valueOf(chosenProduct.getId()));
        modifyProductFormNameTxt.setText(chosenProduct.getName());
        modifyProductFormInvTxt.setText(String.valueOf(chosenProduct.getStock()));
        modifyProductFormPrice.setText(String.valueOf(chosenProduct.getPrice()));
        modifyProductFormMaxTxt.setText(String.valueOf(chosenProduct.getMax()));
        modifyProductFormMinTxt.setText(String.valueOf(chosenProduct.getMin()));

        modifyProductFormBottomTableView.setItems(this.associatedPartsList);

    }

    /** When the scene is loaded part data is added to the part table and the product's associated parts table is created.
        Upon loading the scene the table listing all the available parts is fill with all part information and
        the product's associated parts table is created.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        modifyProductFormTopTableView.setItems(Inventory.getAllParts());
        modifyProductFormTopTablePartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductFormTopTablePartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductFormTopTableInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductFormTopTablePriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        modifyProductFormBottomTablePartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductFormBottomTablePartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductFormBottomTableInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductFormBottomTablePriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /** Adds a selected part to the product.
        Adds a selected part to the associated parts list that belongs to the product.
        @param event the add button under the part table is pressed*/
    @FXML
    void onActionAddPartToModifiedProduct(ActionEvent event) {
        if (modifyProductFormTopTableView.getSelectionModel().getSelectedItem() != null) {

            this.associatedPartsList.add(modifyProductFormTopTableView.getSelectionModel().getSelectedItem());

        } else {
            AlertMessages.warningAlert("No part was selected to add");
        }



    }

    /** Cancels product modification and returns to main form.
        Cancels part modification, any changes made to the product are lost, and returns to the main form.
        @param event the cancel button is pressed*/
    @FXML
    void onActionCancelModifiedProduct(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Removes a part from the associated parts list.
        Asks for confirmation before removing a part from the product's associated parts list and also
        tells the user if no part has been selected when the button is pressed.
        @param event the remove button is pressed*/
    @FXML
    void onActionRemovePartFromModifiedProduct(ActionEvent event) {

        if (modifyProductFormBottomTableView.getSelectionModel().getSelectedItem() != null) {

            if (AlertMessages.confirmationAlert("Do you want to remove this part?")) {

                this.associatedPartsList.remove(modifyProductFormBottomTableView.getSelectionModel().getSelectedItem());

            }

        } else {
            AlertMessages.warningAlert("No part was selected to remove");
        }


    }

    /** Saves the altered product with modified data to the list of all products.
        Creates a new product using using data from text fields and the associated products list
        to replace the old product that was being modified. Catches exceptions for parsing integer and
        double variable and checks if min is greater than max and if inv is between min and max.
        @param event the save button is pressed*/
    @FXML
    void onActionSaveModifiedProduct(ActionEvent event) throws IOException {

        if (AlertMessages.confirmationAlert("Do you want to save this product?")) {

            try {

                int id = Integer.parseInt(modifyProductFormIDTxt.getText());
                String name = modifyProductFormNameTxt.getText();

                double price = Double.parseDouble(modifyProductFormPrice.getText());
                int max = Integer.parseInt(modifyProductFormMaxTxt.getText());
                int min = Integer.parseInt(modifyProductFormMinTxt.getText());
                if (min > max) {
                    AlertMessages.errorAlert("Min should be less than max");
                    return;
                }
                int inv = Integer.parseInt(modifyProductFormInvTxt.getText());
                if (inv > max || inv < min) {
                    AlertMessages.errorAlert("Inv should be less than max and greater than min");
                    return;
                }
                Product newProduct = new Product(id, name, price, inv, min, max);
                for (Part part: this.associatedPartsList) {
                    newProduct.addAssociatedPart(part);
                }

                Inventory.updateProduct(this.index, newProduct);

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

    /** Searches the parts list for the input in the search field.
        Searches the parts list and returns either a part with a unique id or parts with names that contain the
        searched for string. A window pops up to inform the user if no results are found.
        @param event the enter button is pressed when the cursor is in the search box*/
    @FXML
    void onActionModifyProductSearch(ActionEvent event) {

        ObservableList<Part> tempList = Inventory.partSearch(modifyProductFormSearch.getText().trim());

        modifyProductFormTopTableView.setItems(tempList);

        if (tempList.isEmpty()) {

            AlertMessages.informationAlert("Part not found");
        }

        modifyProductFormSearch.setText("");
    }
}
