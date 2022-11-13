package model;

/** Abstract class that holds the methods and variables for a part. */
public abstract class Part {

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** The constructor for the part class.
    @param id is the part's id, name is the part's name, price is how much the part costs,
    stock is how many parts are available, min is the minimum number of the part that should be in stock,
    max is the maximum number of the part that should be in stock*/
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** Returns the part's id.
        @return id*/
    public int getId() {
        return id;
    }

    /** Set the part's id.
        @param id*/
    public void setId(int id) {
        this.id = id;
    }

    /** Returns the part's name.
     @return name*/
    public String getName() {
        return name;
    }

    /** Set the part's name.
     @param name*/
    public void setName(String name) {
        this.name = name;
    }

    /** Returns the part's price.
     @return price*/
    public double getPrice() {
        return price;
    }

    /** Set the part's price.
     @param price*/
    public void setPrice(double price) {
        this.price = price;
    }

    /** Returns the number of parts in stock.
     @return stock*/
    public int getStock() {
        return stock;
    }

    /** Set the part's stock.
     @param stock*/
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** Returns the minimum number of parts that should be in stock.
     @return min*/
    public int getMin() {
        return min;
    }

    /** Set the minimum number of parts that should be in stock.
     @param min*/
    public void setMin(int min) {
        this.min = min;
    }

    /** Returns the maximum number of parts that should be in stock.
     @return max*/
    public int getMax() {
        return max;
    }

    /** Set the maximum number of parts that should be in stock.
     @param max*/
    public void setMax(int max) {
        this.max = max;
    }
}
