import javax.xml.crypto.Data;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Warehouse.java
 * <p>
 * This program runs the user interface for the warehouse.
 *
 * @author AustinWilson section 5
 * @author TannerDent section 5
 * @version 12/8/2018
 */

public class Warehouse {
    final static String FOLDER_PATH = "files/";
    final static File VEHICLE_FILE = new File(FOLDER_PATH + "VehicleList.csv");
    static ArrayList<Vehicle> vehicles = new ArrayList<>();
    final static File PACKAGE_FILE = new File(FOLDER_PATH + "PackageList.csv");
    static ArrayList<Package> packages = new ArrayList<>();
    final static File PROFIT_FILE = new File(FOLDER_PATH + "Profit.txt");
    static double profit = 0.0;
    final static File N_PACKAGES_FILE = new File(FOLDER_PATH + "NumberOfPackages.txt");
    static int numPackagesShipped = 0;
    final static File PRIME_DAY_FILE = new File(FOLDER_PATH + "PrimeDay.txt");
    static boolean primeDay = false;

    final static double PRIME_DAY_DISCOUNT = .15;

    public static void printStatisticsReport(double profits, int packagesShipped, int numberOfPackages) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        System.out.printf("==========Statistics==========\n" +
                "Profits:                %s\n" +
                "Packages Shipped:            %d\n" +
                "Packages in Warehouse:       %d\n" +
                "==============================", formatter.format(profits), packagesShipped, numberOfPackages);
    }

    /**
     * Main Method
     *
     * @param args list of command line arguements
     */

    public static void main(String[] args) {

        //1) load data (vehicle, packages, profits, packages shipped and primeday) from files using DatabaseManager

        vehicles = DatabaseManager.loadVehicles(VEHICLE_FILE);
        packages = DatabaseManager.loadPackages(PACKAGE_FILE);
        profit = DatabaseManager.loadProfit(PROFIT_FILE);
        numPackagesShipped = DatabaseManager.loadPackagesShipped(N_PACKAGES_FILE);
        primeDay = DatabaseManager.loadPrimeDay(PRIME_DAY_FILE);

//        for (int i = 0; i < packages.size(); i++) {
//            System.out.println(packages.get(i).shippingLabel());
//        }

//        vehicles.get(1).fill(packages);
//        vehicles.get(1).setZipDest(40150);
//        System.out.println(vehicles.get(1).getPackages());
//        System.out.println(vehicles.get(1).report());

        //2) Show menu and handle user inputs
        Scanner input = new Scanner(System.in);
        while (true) {
            if (!primeDay) {
                System.out.println("==========Options==========\n" +
                        "1) Add Package\n" +
                        "2) Add Vehicle\n" +
                        "3) Activate Prime Day\n" +
                        "4) Send Vehicle\n" +
                        "5) Print Statistics\n" +
                        "6) Exit\n" +
                        "===========================");
            } else {
                System.out.println("==========Options==========\n" +
                        "1) Add Package\n" +
                        "2) Add Vehicle\n" +
                        "3) Deactivate Prime Day\n" +
                        "4) Send Vehicle\n" +
                        "5) Print Statistics\n" +
                        "6) Exit\n" +
                        "===========================");
            }
            boolean menuRunning = true;
            while (menuRunning) {
                String answer = input.nextLine();
                switch (answer) {
                    case ("1"): //1) Add Package
                        System.out.println();
                        System.out.println("Enter Package ID:");
                        String packageID = input.nextLine();
                        System.out.println("Enter Product Name:");
                        String packageName = input.nextLine();
                        System.out.println("Enter Weight:");
                        double packageWeight = input.nextDouble();
                        System.out.println("Enter Price:");
                        double packagePrice = input.nextDouble();
                        input.nextLine();
                        System.out.println("Enter Buyer Name:");
                        String buyerName = input.nextLine();
                        System.out.println("Enter Address:");
                        String packageAddress = input.nextLine();
                        System.out.println("Enter City:");
                        String packageCity = input.nextLine();
                        System.out.println("Enter State:");
                        String packageState = input.nextLine();
                        System.out.println("Enter ZIP Code:");
                        int packageZip = input.nextInt();
                        input.nextLine();
                        Package pkg;
                        if (primeDay) {
                            pkg = new Package(packageID, packageName, packageWeight,
                                    packagePrice * (1 - PRIME_DAY_DISCOUNT),
                                    new ShippingAddress(buyerName, packageAddress,
                                            packageCity, packageState, packageZip));
                        } else {
                            pkg = new Package(packageID, packageName, packageWeight, packagePrice,
                                    new ShippingAddress(buyerName, packageAddress,
                                            packageCity, packageState, packageZip));
                        }
                        packages.add(pkg);
                        System.out.println();
                        System.out.println(pkg.shippingLabel());
                        System.out.println();
                        menuRunning = false;
                        break;
                    case ("2"): //2) Add Vehicle
                        boolean vehicleRunning = true;
                        while (vehicleRunning) {
                            System.out.println();
                            System.out.println("Vehicle Options:\n" +
                                    "1) Truck\n" +
                                    "2) Drone\n" +
                                    "3) Cargo Plane");
                            String answerVehicle = input.nextLine();
                            System.out.println();
                            switch (answerVehicle) {
                                case ("1"): //Truck
                                    System.out.println("Enter License Plate No.: ");
                                    String truckLicense = input.nextLine();
                                    System.out.println("Enter Maximum Carry Weight: ");
                                    double truckMaxWeight = input.nextDouble();
                                    input.nextLine();
                                    vehicles.add(new Truck(truckLicense, truckMaxWeight));
                                    vehicleRunning = false;
                                    break;
                                case ("2"): //Drone
                                    System.out.println("Enter License Plate No.: ");
                                    String droneLicense = input.nextLine();
                                    System.out.println("Enter Maximum Carry Weight: ");
                                    double droneMaxWeight = input.nextDouble();
                                    input.nextLine();
                                    vehicles.add(new Drone(droneLicense, droneMaxWeight));
                                    vehicleRunning = false;
                                    break;
                                case ("3"): //Cargo Plane
                                    System.out.println("Enter License Plate No.: ");
                                    String cargoLicense = input.nextLine();
                                    System.out.println("Enter Maximum Carry Weight: ");
                                    double cargoMaxWeight = input.nextDouble();
                                    input.nextLine();
                                    vehicles.add(new CargoPlane(cargoLicense, cargoMaxWeight));
                                    vehicleRunning = false;
                                    break;
                                default:
                                    System.out.println("Error: Option not available.");
                                    break;
                            }
                        }
                        System.out.println();
                        menuRunning = false;
                        break;
                    case ("3"): //change menu to prime day
                        primeDay = !primeDay;
                        System.out.println();
                        menuRunning = false;
                        break;
                    case ("4"):
                        System.out.println();
                        if (vehicles.size() < 1) {
                            System.out.println("Error: No vehicles available.");
                        } else if (packages.size() < 1) {
                            System.out.println("Error: No packages available.");
                        } else {
                            System.out.println("Options:\n" +
                                    "1) Send Truck\n" +
                                    "2) Send Drone\n" +
                                    "3) Send Cargo Plane\n" +
                                    "4) Send First Available");
                            answer = input.nextLine();
                            int vehicle = -1;
                            for (int i = 0; i < vehicles.size(); i++) {
                                if (answer.equals("1")) {
                                    if (vehicles.get(i) instanceof Truck) {
                                        vehicle = i;
                                    }
                                } else if (answer.equals("2")) {
                                    if (vehicles.get(i) instanceof Drone) {
                                        vehicle = i;
                                    }
                                } else if (answer.equals("3")) {
                                    if (vehicles.get(i) instanceof CargoPlane) {
                                        vehicle = i;
                                    }
                                } else if (answer.equals("4")) {
                                    vehicle = 0;
                                }
                            }
                            if (vehicle == -1) {
                                System.out.println("Error: No vehicles of selected type are available.");
                            } else {
                                System.out.println("ZIP Code Options:\n" +
                                        "1) Send to first ZIP Code\n" +
                                        "2) Send to mode of ZIP Codes");
                                answer = input.nextLine();
                                if (answer.equals("1")) {
                                    vehicles.get(vehicle).setZipDest(packages.get(0).getDestination().getZipCode());
                                } else if (answer.equals("2")) {
                                    int zip;
                                    int pack = 0;
                                    int x = 0;
                                    int y = 0;
                                    for (int i = 0; i < packages.size(); i++) {
                                        zip = packages.get(i).getDestination().getZipCode();
                                        for (int j = 0; j < packages.size(); j++) {
                                            if (zip == packages.get(j).getDestination().getZipCode()) {
                                                x++;
                                            }
                                        }
                                        if (x > y) {
                                            pack = i;
                                            y = x;
                                            x = 0;
                                        } else {
                                            x = 0;
                                        }
                                    }
                                    vehicles.get(vehicle).setZipDest(packages.get(pack).getDestination().getZipCode());
                                }
                                vehicles.get(vehicle).fill(packages);
                                System.out.println(vehicles.get(vehicle).report());
                                numPackagesShipped += vehicles.get(vehicle).getPackages().size();
                                profit += vehicles.get(vehicle).getProfit();
                                vehicles.remove(vehicle);
                            }

                        }
                        System.out.println();
                        menuRunning = false;
                        break;
                    case ("5"):
                        //need to be able to get profit, packages shipped, and packages in warehouse for this one
                        System.out.println();
                        printStatisticsReport(profit, numPackagesShipped, packages.size());
                        System.out.println();
                        System.out.println();
                        menuRunning = false;
                        break;
                    case ("6"):
                        //3) save data (vehicle, packages, profits, packages shipped and primeday)
                        // to files (overwriting them) using DatabaseManager
                        DatabaseManager.saveVehicles(VEHICLE_FILE, vehicles);
                        DatabaseManager.savePackages(PACKAGE_FILE, packages);
                        DatabaseManager.saveProfit(PROFIT_FILE, profit);
                        DatabaseManager.savePackagesShipped(N_PACKAGES_FILE, numPackagesShipped);
                        DatabaseManager.savePrimeDay(PRIME_DAY_FILE, primeDay);
                        return;
                    default:
                        System.out.println("Error: Option not available.");
                        System.out.println();
                        menuRunning = false;
                        break;
                }
            }
        }
    }
}