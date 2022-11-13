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

/** Holds all the methods of the Add Product Form controller. */
public class AddProductForm implements Initializable {

    Stage stage;
    Parent scene;
    private ObservableList<Part> partsList;

    @FXML
    private TableColumn<Part, Integer> addProductFormBottomTableInventoryCol;

    @FXML
    private TableColumn<Part, Integer> addProductFormBottomTablePartIDCol;

    @FXML
    private TableColumn<Part, String> addProductFormBottomTablePartNameCol;

    @FXML
    private TableColumn<Part, Double> addProductFormBottomTablePriceCostCol;

    @FXML
    private TableView<Part> addProductFormBottomTableView;

    @FXML
    private TextField addProductFormIDtxt;

    @FXML
    private TextField addProductFormInvTxt;

    @FXML
    private TextField addProductFormMaxTxt;

    @FXML
    private TextField addProductFormMinTxt;

    @FXML
    private TextField addProductFormNameTxt;

    @FXML
    private TextField addProductFormPriceTxt;

    @FXML
    private TextField addProductFormSearch;

    @FXML
    private TableColumn<Part, Integer> addProductFormTopTableInventoryCol;

    @FXML
    private TableColumn<Part, Integer> addProductFormTopTablePartIDCol;

    @FXML
    private TableColumn<Part, String> addProductFormTopTablePartNameCol;

    @FXML
    private TableColumn<Part, Double> addProductFormTopTablePriceCostCol;

    @FXML
    private TableView<Part> addProductFormTopTableView;

    /** When the scene is loaded the all parts and associated parts table are created and loaded with data.
        When the scene is loaded the all parts and associated parts table are created and loaded with data
        from the all parts list in Inventory and associated parts list that is created in this class.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.partsList = FXCollections.observableArrayList();

        addProductFormTopTableView.setItems(Inventory.getAllParts());
        addProductFormTopTablePartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductFormTopTablePartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductFormTopTableInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductFormTopTablePriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        addProductFormBottomTableView.setItems(this.partsList);
        addProductFormBottomTablePartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductFormBottomTablePartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductFormBottomTableInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductFormBottomTablePriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /** Adds a selected part to the product.
     Adds a selected part to the associated parts list that belongs to the product.
     @param event the add button under the part table is pressed*/
    @FXML
    void onActionAddPartToProduct(ActionEvent event) {

        if (addProductFormTopTableView.getSelectionModel().getSelectedItem() != null) {

            this.partsList.add(addProductFormTopTableView.getSelectionModel().getSelectedItem());

        } else {
            AlertMessages.warningAlert("No part was selected to add");
        }



    }

    /** Cancels product creation and returns to main form.
     Cancels product creation, any changes made to the product are lost, and returns to the main form.
     @param event the cancel button is pressed*/
    @FXML
    void onActionCancelProductAdd(ActionEvent event) throws IOException {

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
    void onActionRemovePartFromProduct(ActionEvent event) {

        if (addProductFormBottomTableView.getSelectionModel().getSelectedItem() != null) {

            if (AlertMessages.confirmationAlert("Do you want to remove this part?")) {

                Part removedPart = addProductFormBottomTableView.getSelectionModel().getSelectedItem();
                this.partsList.remove(removedPart);

            }

        } else {
            AlertMessages.warningAlert("No part was selected to remove");
        }

    }

    /** Adds the new product to the list of all products.
     Creates a new product using the text fields and the associated products list that is added to
     the list of all products. Catches exceptions for parsing integer and double variable and checks
     if min is greater than max and if inv is between min and max.
     @param event the save button is pressed*/
    @FXML
    void onActionSaveProductAdd(ActionEvent event) throws IOException {

        if (AlertMessages.confirmationAlert("Do you want to save this product?")) {

            try {

                int id = Inventory.getProductId();
                String name = addProductFormNameTxt.getText();
                double price = Double.parseDouble(addProductFormPriceTxt.getText());
                int max = Integer.parseInt(addProductFormMaxTxt.getText());
                int min = Integer.parseInt(addProductFormMinTxt.getText());
                if (min > max) {
                    AlertMessages.errorAlert("Min should be less than max");
                    return;
                }
                int inv = Integer.parseInt(addProductFormInvTxt.getText());
                if (inv > max || inv < min) {
                    AlertMessages.errorAlert("Inv should be less than max and greater than min");
                    return;
                }
                Product newProduct = new Product(id, name, price, inv, min, max);
                for (Part part: this.partsList) {
                    newProduct.addAssociatedPart(part);
                }

                Inventory.addProduct(newProduct);

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
    void onActionAddProductFormSearch(ActionEvent event) {

        ObservableList<Part> tempList = Inventory.partSearch(addProductFormSearch.getText().trim());

        addProductFormTopTableView.setItems(tempList);

        if (tempList.isEmpty()) {

            AlertMessages.informationAlert("Part not found");
        }

        addProductFormSearch.setText("");

    }
}
