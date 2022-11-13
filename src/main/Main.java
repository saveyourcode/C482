package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/** Main class runs first when the program is started.
    <p>The JavaDoc is located in the JavaDoc package in the Source file.</p>
    <p>FUTURE ENHANCEMENTS:  One future enhancement to this program is changing the part deletion, so it checks
    if the part to be deleted is listed in an existing products associated parts.  In the programs current
    form a part can be deleted from the all parts list but still exist as a product's associated part which
    can cause issues when attempting to modify a product.</p>
    <p>The RUNTIME ERROR explanation is located in the MainForm controller file above the onActionModifyPart()
    method.</p>*/

public class Main extends Application {

    /**
     * Loads the main scene of the inventory program.
     * When called in the main method it gets the FXML file of the main scene and sets it on the stage
     */

    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
//        stage.setScene(new Scene(root));
//        stage.show();
        loadNextScene("/view/MainForm.fxml");

    }

    /**
     * The first method to be called when the program starts
     * runs the start method to load the first scene of the main form and loads and preliminary data
     */

    public static void main(String[] args) {

//        InHouse part1 = new InHouse(Inventory.getPartId(), "wheel", 12.50, 5, 0, 25, 475);
//        Outsourced part2 = new Outsourced(Inventory.getPartId(), "gear", 33.99, 3, 1, 45, "Big Business");
//        InHouse part3 = new InHouse(Inventory.getPartId(), "seat", 45.99, 4, 1, 99, 978);
//
//        Product product1 = new Product(Inventory.getProductId(), "bicycle", 33.50, 6, 2, 4);
//        Product product2 = new Product(Inventory.getProductId(), "helmet", 21.15, 9, 0, 99);
//
//        Inventory.addPart(part1);
//        Inventory.addPart(part2);
//        Inventory.addPart(part3);
//
//        product1.addAssociatedPart(part3);
//        product1.addAssociatedPart(part2);
//
//        Inventory.addProduct(product1);
//        Inventory.addProduct(product2);
//
//        launch(args);

        ZoneId zoneIdEST = ZoneId.of("America/New_York");

        ZonedDateTime openingTime = ZonedDateTime.of(2022, 10, 10, 8, 00, 00, 00, zoneIdEST);

        LocalDateTime localOpeningTime = LocalDateTime.ofInstant(openingTime.toInstant(), ZoneId.systemDefault());

        System.out.println(openingTime);
        System.out.println(localOpeningTime);
    }



    public void loadNextScene(String sceneLocation) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(sceneLocation));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
