import java.io.File;
import java.util.Scanner;

/**
 * <h1>Warehouse</h1>
 */

public class Warehouse {
	final static String folderPath = "files/";
    final static File VEHICLE_FILE = new File(folderPath + "VehicleList.csv");
    final static File PACKAGE_FILE = new File(folderPath + "PackageList.csv");
    final static File PROFIT_FILE = new File(folderPath + "Profit.txt");
    final static File N_PACKAGES_FILE = new File(folderPath + "NumberOfPackages.txt");
    final static File PRIME_DAY_FILE = new File(folderPath + "PrimeDay.txt");
    final static double PRIME_DAY_DISCOUNT = .15;

    /**
     * Main Method
     * 
     * @param args list of command line arguements
     */
    public static void main(String[] args) {

    	
    	//1) load data (vehicle, packages, profits, packages shipped and primeday) from files using DatabaseManager
    	
    	
    	
    	//2) Show menu and handle user inputs
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("==========Options==========\n" +
                    "1) Add Package\n" +
                    "2) Add Vehicle\n" +
                    "3) Activate Prime Day\n" +
                    "4) Send Vehicle\n" +
                    "5) Print Statistics\n" +
                    "6) Exit\n" +
                    "===========================");
            boolean menuRunning = true;
            while (menuRunning) {
                String answer = input.nextLine();
                switch (answer) {
                    case("1"): //1) Add Package
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
                        Package pkg = new Package(packageID, packageName, packageWeight, packagePrice,
                                new ShippingAddress(buyerName, packageAddress, packageCity, packageState, packageZip));
                        System.out.println();
                        System.out.println(pkg.shippingLabel());
                        System.out.println();
                        menuRunning = false;
                        break;
                    case("2"): //2) Add Vehicle
                        System.out.println();
                        System.out.println("Vehicle Options:\n" +
                                "1) Truck\n" +
                                "2) Drone\n" +
                                "3) Cargo Plane");
                        boolean vehicleRunning = true;
                        while (vehicleRunning) {
                            String answerVehicle = input.nextLine();
                            System.out.println();
                            switch (answerVehicle) {
                                case ("1"): //Truck
                                    System.out.println("Enter License Plate No.: ");
                                    String truckLicense = input.nextLine();
                                    System.out.println("Enter Maximum Carry Weight: ");
                                    double truckMaxWeight = input.nextDouble();
                                    input.nextLine();
                                    new Truck(truckLicense, truckMaxWeight);
                                    vehicleRunning = false;
                                    break;
                                case ("2"): //Drone
                                    System.out.println("Enter License Plate No.: ");
                                    String droneLicense = input.nextLine();
                                    System.out.println("Enter Maximum Carry Weight: ");
                                    double droneMaxWeight = input.nextDouble();
                                    input.nextLine();
                                    new Truck(droneLicense, droneMaxWeight);
                                    vehicleRunning = false;
                                    break;
                                case ("3"): //Cargo Plane
                                    System.out.println("Enter License Plate No.: ");
                                    String cargoLicense = input.nextLine();
                                    System.out.println("Enter Maximum Carry Weight: ");
                                    double cargoMaxWeight = input.nextDouble();
                                    input.nextLine();
                                    new Truck(cargoLicense, cargoMaxWeight);
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
                    case("3"):
                        //change of format
                        System.out.println();
                        System.out.println("Case 3");
                        System.out.println();
                        menuRunning = false;
                        break;
                    case("4"):
                        //I think we have to use file reading for this to know if we have any vehicles.
                        System.out.println();
                        System.out.println("Case 4");
                        System.out.println();
                        menuRunning = false;
                        break;
                    case("5"):
                        //need to be able to get profit, packages shipped, and packages in warehouse for this one
                        System.out.println();
                        System.out.printf("==========Statistics==========\n" +
                                            "Profits:                 $9,874.08\n" +
                                            "Packages Shipped:                3\n" +
                                            "Packages in Warehouse:           1\n" +
                                            "==============================");
                        System.out.println();
                        System.out.println();
                        menuRunning = false;
                        break;
                    case("6"):
                        //make sure to save all data during our operations
                        return;
                    default:
                        System.out.println("Error: Option not available.");
                        System.out.println();
                        menuRunning = false;
                        break;
                }
            }
        }



    	
    	//3) save data (vehicle, packages, profits, packages shipped and primeday) to files (overwriting them) using DatabaseManager
    	
    
    }


}