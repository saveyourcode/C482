package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Class that contains the methods and variables for product*/
public class Product {

    private ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** The constructor for the Product class.
     @param id is the product's id, name is the product's name, price is how much the product costs,
     stock is how many products are available, min is the minimum number of products that should be in stock,
     max is the maximum number of products that should be in stock*/
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.associatedParts = FXCollections.observableArrayList();
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;

    }

    /** Returns the product's id.
        @return id*/
    public int getId() {
        return id;
    }

    /** Sets the product's id.
        @param id*/
    public void setId(int id) {
        this.id = id;
    }

    /** Returns the product's name.
     @return name*/
    public String getName() {
        return name;
    }

    /** Sets the product's id.
     @param name*/
    public void setName(String name) {
        this.name = name;
    }

    /** Returns the product's price.
     @return price*/
    public double getPrice() {
        return price;
    }

    /** Sets the product's id.
     @param price*/
    public void setPrice(double price) {
        this.price = price;
    }

    /** Returns the product's stock.
     @return stock*/
    public int getStock() {
        return stock;
    }

    /** Sets the product's id.
     @param stock*/
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** Returns the minimum number of products that should be in stock.
     @return min*/
    public int getMin() {
        return min;
    }

    /** Sets the minimum number of products that should be in stock.
     @param min*/
    public void setMin(int min) {
        this.min = min;
    }

    /** Returns the maximum number of products that should be in stock.
     @return max*/
    public int getMax() {
        return max;
    }

    /** Sets the minimum maximum of products that should be in stock.
     @param max*/
    public void setMax(int max) {
        this.max = max;
    }

    /** Adds a part to the product's associated list.
        @param part*/
    public void addAssociatedPart(Part part) {
        this.associatedParts.add(part);
    }

    /** Deletes a part for the product's associated parts list.
        @param selectedAssociatedPart*/
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        for (Part partInList: this.associatedParts) {
            if (partInList.getId() == selectedAssociatedPart.getId()) {
                this.associatedParts.remove(partInList);
                return true;
            }
        }
        return false;
    }

    /** Returns an observable list containing the products associated parts.
        @return associatedParts observableList*/
    public ObservableList<Part> getAllAssociatedParts() {
        return this.associatedParts;
    }
}
