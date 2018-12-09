import java.util.ArrayList;

/**
 * Drone.java
 * <p>
 * This program is the Drone version of the vehicle class.
 *
 * @author AustinWilson section 5
 * @author TannerDent section 5
 * @version 12/8/2018
 */

public class Drone extends Vehicle {

    final static private double GAS_RATE = 1.33;

    /**
     * Default Contructor
     */
    //============================================================================
    public Drone() {
        super();
    }

    //============================================================================

    /**
     * Constructor
     *
     * @param licensePlate license plate of vehicle
     * @param maxWeight    maximum weight that the vehicle can hold
     */
    //============================================================================
    public Drone(String licensePlate, double maxWeight) {
        super(licensePlate, maxWeight); //not sure
    }

    //============================================================================

    /*
     * =============================================================================
     * | Methods from Profitable Interface
     * =============================================================================
     */

    /**
     * Returns the profits generated by the packages currently in the Truck.
     * <p>
     * &sum;p<sub>price</sub> - (range<sub>max</sub> &times; 1.33)
     * </p>
     */
    @Override
    public double getProfit() {
        double profit = 0;
        if (super.getPackages() == null) {
            return profit;
        }
        for (Package pkg : super.getPackages()) {
            profit = profit + pkg.getPrice() - (getMaxRange(getPackages()) * GAS_RATE);
        }
        return profit;
    }

    /**
     * Generates a String of the Drone report. Drone report includes:
     * <ul>
     * <li>License Plate No.</li>
     * <li>Destination</li>
     * <li>Current Weight/Maximum Weight</li>
     * <li>Net Profit</li>
     * <li>Shipping labels of all packages in truck</li>
     * </ul>
     *
     * @return Truck Report
     */
    @Override
    public String report() {
        String firstPart = String.format(
                "==========Drone Report========== \n" +
                        "License Plate No.: %s \n" +
                        "Destination: %d \n" +
                        "Weight Load: %.2f/%.2f \n" +
                        "Net Profit: $%.2f \n" +
                        "=====Shipping Labels===== \n",
                getLicensePlate(), getZipDest(), getCurrentWeight(), getMaxWeight(), getProfit());
        String secondPart = "";
        if (super.getPackages() != null) {
            for (Package pkg : getPackages()) {
                secondPart += pkg.shippingLabel() + "\n";
            }
        }
        String lastPart = "==============================";
        return firstPart + secondPart + lastPart;
    }


}
