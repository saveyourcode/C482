package model;

/** Class that contains the methods and variables for InHouse part*/
public class InHouse extends Part {

    private int machineId;

    /** The constructor for the InHouse part class.
    @param id is the part's id, name is the part's name, price is how much the part costs,
    stock is how many parts are available, min is the minimum number of the part that should be in stock,
    max is the maximum number of the part that should be in stock, machineId is the part's machine id*/
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /** Returns the part's machine id.
        @return machineId*/
    public int getMachineId() {
        return machineId;
    }

    /** Sets the part's machine Id.
        @param machineId*/
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
