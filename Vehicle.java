import java.util.ArrayList;

/**
 * Vehicle.java
 * <p>
 * This program creates the vehicle object.
 *
 * @author AustinWilson section 5
 * @author TannerDent section 5
 * @version 12/8/2018
 */

public class Vehicle implements Profitable {
    private String licensePlate;
    private double maxWeight;
    private double currentWeight;
    private int zipDest;
    private int maxRange;
    private ArrayList<Package> packages = new ArrayList<>();

    /**
     * Default Constructor
     */
    //============================================================================
    public Vehicle() {
        this.licensePlate = "";
        this.maxWeight = 0;
        this.currentWeight = 0;
        this.zipDest = 0;
        this.packages = new ArrayList<Package>();
    }
    //============================================================================

    /**
     * Constructor
     *
     * @param licensePlate license plate of vehicle
     * @param maxWeight    maximum weight of vehicle
     */
    //============================================================================
    public Vehicle(String licensePlate, double maxWeight) {
        this.licensePlate = licensePlate;
        this.maxWeight = maxWeight;
    }
    //============================================================================

    /**
     * Returns the license plate of this vehicle
     *
     * @return license plate of this vehicle
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * Updates the license plate of vehicle
     *
     * @param licensePlate license plate to be updated to
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    /**
     * Returns the maximum weight this vehicle can carry
     *
     * @return the maximum weight that this vehicle can carry
     */
    public double getMaxWeight() {
        return maxWeight;
    }

    /**
     * Updates the maximum weight of this vehicle
     *
     * @param maxWeight max weight to be updated to
     */
    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    /**
     * Returns the current weight of all packages inside vehicle
     *
     * @return current weight of all packages inside vehicle
     */
    public double getCurrentWeight() {
        return currentWeight;
    }

    /**
     * Returns the current ZIP code desitnation of the vehicle
     *
     * @return current ZIP code destination of vehicle
     */
    public int getZipDest() {
        return zipDest;
    }

    /**
     * Updates the ZIP code destination of vehicle
     *
     * @param zipDest ZIP code destination to be updated to
     */
    public void setZipDest(int zipDest) {
        this.zipDest = zipDest;
    }

    /**
     * Returns ArrayList of packages currently in Vehicle
     *
     * @return ArrayList of packages in vehicle
     */
    public ArrayList<Package> getPackages() {
        return packages;
    }

    /**
     * Adds Package to the vehicle only if has room to carry it (adding it would not
     * cause it to go over its maximum carry weight).
     *
     * @param pkg Package to add
     * @return whether or not it was successful in adding the package
     */
    public boolean addPackage(Package pkg) {
        if (currentWeight + pkg.getWeight() <= maxWeight) {
            packages.add(pkg);
            currentWeight += pkg.getWeight();
            return true;
        }
        return false;
    }

    /**
     * Clears vehicle of packages and resets its weight to zero
     */
    public void empty() {
        packages.clear();
        currentWeight = 0;
    }

    /**
     * Returns true if the Vehicle has reached its maximum weight load, false
     * otherwise.
     *
     * @return whether or not Vehicle is full
     */
    public boolean isFull() {
        return currentWeight > maxWeight;
    }

    /**
     * Fills vehicle with packages with preference of date added and range of its
     * destination zip code. It will iterate over the packages intially at a range
     * of zero and fill it with as many as it can within its range without going
     * over its maximum weight. The amount the range increases is dependent on the
     * vehicle that is using this. This range it increases by after each iteration
     * is by default one.
     *
     * @param warehousePackages List of packages to add from
     */

    public void fill(ArrayList<Package> warehousePackages) {
        for (int i = 0; !isFull(); i++) { //i is our distance
            if (i > getMaxRange(warehousePackages)) {
                return;
            }
            for (int j = 0; j < warehousePackages.size(); j++) {
                int distance = Math.abs(warehousePackages.get(j).getDestination().getZipCode() - getZipDest());
                if (distance == i) {
                    addPackage(warehousePackages.get(j));
                }
            }
        }
    }


    //this will be over ridden so not sure what to do with it as well
    public double getProfit() {
        double profit = 0;
        for (Package pkg : packages) {
            profit += pkg.getPrice();
        }
        return profit;
    }

    //not sure exactly what they want here
    public String report() {
        return "";
    }

    //my own method to help with the fill method as well the profit methods for all vehicles
    public int getMaxRange(ArrayList<Package> packagesRange) {
        maxRange = 0;
        for (Package pkg : packagesRange) {
            if (Math.abs(pkg.getDestination().getZipCode() - zipDest) > maxRange) {
                maxRange = Math.abs(pkg.getDestination().getZipCode() - zipDest);
            }
        }
        return maxRange;
    }
}