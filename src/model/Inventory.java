package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/** Holds all the methods and variables of the Inventory class. */
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int productId = 999;
    private static int partId = 0;

    /** Adds a part to the allParts list.
        @param newPart */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /** Adds a product to the allProducts list.
        @param newProduct */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /** Searches the allParts list for a part with an id that matches the id parameter.
        @param partId*/
    public static Part lookupPart(int partId) {
        for (Part part: allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    /** Searches the allProducts list for a product with an id that matches the id parameter.
     @param productId*/
    public static Product lookupProduct(int productId) {
        for (Product product: allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    /** Searches the allParts list for part with names that contain the string parameter.
        @param name*/
    public static ObservableList<Part> lookupPart(String name) {
        ObservableList<Part> tempList = FXCollections.observableArrayList();
        for(Part part: allParts) {
            if (part.getName().contains(name)) {
                tempList.add(part);
            }
        }
        return tempList;
    }

    /** Searches the allProducts list for products with names that contain the string parameter.
     @param name*/
    public static ObservableList<Product> lookupProduct(String name) {
        ObservableList<Product> tempList = FXCollections.observableArrayList();
        for (Product product: allProducts) {
            if (product.getName().contains(name)) {
                tempList.add(product);
            }
        }
        return tempList;
    }

    /** Replaces an existing part located at the given index in the allParts list with a new part.
        @param index, newPart */
    public static void updatePart(int index, Part newPart) {
        Inventory.getAllParts().set(index, newPart);
    }

    /** Replaces an existing product located at the given index in the allProduct list with a new product.
     @param index, newProduct */
    public static void updateProduct(int index, Product newProduct) {
        Inventory.getAllProducts().set(index, newProduct);
    }

    /** Deletes a part from the allParts list.
        @param part */
    public static boolean deletePart(Part part) {
        for (Part partInList: allParts) {
            if (partInList.getId() == part.getId()) {
                allParts.remove(part);
                return true;
            }
        }
        return false;
    }

    /** Deletes a product from the allProducts list.
     @param product */
    public static boolean deleteProduct(Product product) {
        for (Product productInList: allProducts) {
            if (productInList.getId() == product.getId()) {
                if (productInList.getAllAssociatedParts().isEmpty()) {
                    allProducts.remove(product);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /** Returns an ObservableList of all parts.
        @return allParts*/
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /** Returns an observableList of all products.
        @return allProducts*/
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /** Returns a unique part id.
        Returns a unique part id by incrementing the static partId variable by 1 each time the method
        is called.
        @return partId*/
    public static int getPartId() {
        partId++;
        return partId;
    }

    /** Returns a unique product id.
     Returns a unique product id by incrementing the static productId variable by 1 each time the method
     is called.
     @return productId*/
    public static int getProductId() {
        productId++;
        return productId;
    }

    /** Returns an observableList containing all parts that match the searched text.
        @return returnedList*/
    public static ObservableList<Part> partSearch(String string) {

        ObservableList<Part> returnedList = FXCollections.observableArrayList();

        returnedList = Inventory.lookupPart(string);

        if (returnedList.isEmpty()) {
            try {
                int searchNumber = Integer.parseInt(string);
                Part tempPart = Inventory.lookupPart(searchNumber);
                if (tempPart != null) {
                    returnedList.add(tempPart);
                }

            } catch (Exception e) {

            }
        }

        return returnedList;
    }

    /** Returns an observableList containing all products that match the searched text.
     @return returnedList*/
    public static ObservableList<Product> productSearch(String string) {

        ObservableList<Product> returnedList = FXCollections.observableArrayList();

        returnedList = Inventory.lookupProduct(string);

        if (returnedList.isEmpty()) {
            try {
                int searchNumber = Integer.parseInt(string);
                Product tempProduct = Inventory.lookupProduct(searchNumber);
                if (tempProduct != null) {
                    returnedList.add(tempProduct);
                }

            } catch (Exception e) {

            }
        }

        return returnedList;

    }

}
