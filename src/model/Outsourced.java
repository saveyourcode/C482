package model;

/** Class that contains the methods and variables for Outsourced part*/
public class Outsourced extends Part{


    private String companyName;

    /** The constructor for the part class.
     @param id is the part's id, name is the part's name, price is how much the part costs,
     stock is how many parts are available, min is the minimum number of the part that should be in stock,
     max is the maximum number of the part that should be in stock, companyName is the name of the company
     that made the part*/
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /** Returns the name of the company that made the part.
        @return companyName*/
    public String getCompanyName() {
        return companyName;
    }

    /** Sets the name of the company that made the part.
        @param companyName*/
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
