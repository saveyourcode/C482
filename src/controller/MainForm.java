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

/** This class contains all the methods of the main form controller.*/

public class MainForm implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField mainFormPartsSearch;

    @FXML
    private TableColumn<Part, Integer> mainFormPartsTableInventoryCol;

    @FXML
    private TableColumn<Part, Integer> mainFormPartsTablePartIDCol;

    @FXML
    private TableColumn<Part, String> mainFormPartsTablePartNameCol;

    @FXML
    private TableColumn<Part, Double> mainFormPartsTablePriceCostCol;

    @FXML
    private TableView<Part> mainFormPartsTableView;

    @FXML
    private TextField mainFormProductsSearch;

    @FXML
    private TableColumn<Product, Integer> mainFormProductsTableInventoryCol;

    @FXML
    private TableColumn<Product, Double> mainFormProductsTablePriceCostCol;

    @FXML
    private TableColumn<Product, Integer> mainFormProductsTableProductIDCol;

    @FXML
    private TableColumn<Product, String> mainFormProductsTableProductNameCol;

    @FXML
    private TableView<Product> mainFormProductsTableView;

    /** Populates the parts and products tables with data.
        Loads the part table with all the existing parts and the product table with all the existing products.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        mainFormPartsTableView.setItems(Inventory.getAllParts());
        mainFormPartsTablePartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainFormPartsTablePartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainFormPartsTableInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainFormPartsTablePriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        mainFormProductsTableView.setItems(Inventory.getAllProducts());
        mainFormProductsTableProductIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainFormProductsTableProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainFormProductsTableInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainFormProductsTablePriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /** Loads the add part form when pressed.
        Sets the new scene as the add part form and sets it on the stage.
        @param event the add under the part table button is pressed
     */
    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPartForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Loads the add product form when pressed.
     Sets the new scene as the add product form and sets it on the stage.
     @param event the add button under the product table is pressed
     */
    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProductForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Deletes the selected part.
     The selected part in the part table is deleted when pressed.
     @param event the delete button under the part table is pressed
     */
    @FXML
    void onActionDeletePart(ActionEvent event) {

        if (mainFormPartsTableView.getSelectionModel().getSelectedItem() != null) {

            if (AlertMessages.confirmationAlert("Do you want to delete the selected part?")) {

                Part partToDelete = mainFormPartsTableView.getSelectionModel().getSelectedItem();

                if (Inventory.deletePart(partToDelete)) {

                    AlertMessages.informationAlert("Part was successfully deleted");

                } else {

                    AlertMessages.warningAlert("Failed to delete part");

                }

            }

        } else {

            AlertMessages.warningAlert("No Part was selected to delete");
        }
    }

    /** Deletes the selected product.
     The selected product in the product table is deleted when pressed but will fail if the part was associated parts.
     @param event the delete button under the product table is pressed
     */
    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        if (mainFormProductsTableView.getSelectionModel().getSelectedItem() != null) {

            if (AlertMessages.confirmationAlert("Do you want to delete the selected product?")) {

                Product productToDelete = mainFormProductsTableView.getSelectionModel().getSelectedItem();

                if (Inventory.deleteProduct(productToDelete)) {

                    AlertMessages.informationAlert("Product was successfully deleted");

                } else {

                    AlertMessages.warningAlert("Products with associated parts cannot be deleted");

                }

            }

        } else {

            AlertMessages.warningAlert("No product was selected to delete");

        }
    }

    /** Closes the program.
     Shows a window asking the user to confirm they want to end the program and ends if they say yes.
     @param event the exit button is pressed
     */
    @FXML
    void onActionExitMainForm(ActionEvent event) {

        if (AlertMessages.confirmationAlert("Do you want to exit the program?")) {

            System.exit(0);

        }

    }

    /** Loads the modify part form when pressed.
     * Sets the new scene as the modify part form and sets it on the stage.
     <p>RUNTIME ERROR:  This method can cause a null pointer exception if the button is pressed when a part isn't
     selected from the parts table.  This exception is caused because the object passed to the transferPart()
     method is null is not part has been selected.  To avoid this exception I used an if block that only calls
     the transferPart() method is the parameter is not null and has a window pop up telling hte user that the
     that not part has been selected if the parameter is null.</p>
     @param event the modify button under the part table is pressed
     */
    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {

        if (mainFormPartsTableView.getSelectionModel().getSelectedItem() != null) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyPartForm.fxml"));
            loader.load();

            ModifyPartForm modifyPartController = loader.getController();
            //modifyPartController.transferPart(mainFormPartsTableView.getSelectionModel().getSelectedItem());
            modifyPartController.transferPart(mainFormPartsTableView.getSelectionModel().getSelectedIndex());

            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        } else {

            AlertMessages.warningAlert("No part was selected to modify");
        }

    }

    /** Loads the modify product form when pressed.
     Sets the new scene as the modify product form and sets it on the stage.
     @param event the modify button under the product table is pressed
     */
    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {

        if (mainFormProductsTableView.getSelectionModel().getSelectedItem() != null) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyProductForm.fxml"));
            loader.load();

            ModifyProductForm modifyProductController = loader.getController();
            //modifyProductController.transferProduct(mainFormProductsTableView.getSelectionModel().getSelectedItem());
            modifyProductController.transferProduct(mainFormProductsTableView.getSelectionModel().getSelectedIndex());

            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        } else {

            AlertMessages.warningAlert("No product was selected to modify");
        }

    }

    /** Searches for the input in the part search text box.
     Searches all parts for the input in the text box and either shows the found results in the part table
     or shows a window stating that the part was not found.
     @param event the enter key is pressed while cursor is in the search part box
     */
    @FXML
    void onActionMainFormPartSearch(ActionEvent event) {

        ObservableList<Part> tempList = Inventory.partSearch(mainFormPartsSearch.getText().trim());

        mainFormPartsTableView.setItems(tempList);

        if (tempList.isEmpty()) {

            AlertMessages.informationAlert("Part not found");
        }

        mainFormPartsSearch.setText("");

    }

    /** Searches for the input in the product search text box.
        Searches all products for the input in the text box and either shows the found results in the product table
        or shows a window stating that the product was not found.
        @param event the enter key is pressed while cursor is in the search product box
     */
    @FXML
    void onActionMainFormProductSearch(ActionEvent event) {

        ObservableList<Product> tempList = Inventory.productSearch(mainFormProductsSearch.getText().trim());

        mainFormProductsTableView.setItems(tempList);

        if (tempList.isEmpty()) {
            AlertMessages.informationAlert("Product not found");
        }

        mainFormProductsSearch.setText("");

    }
}
