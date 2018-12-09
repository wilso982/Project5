import java.io.*;
import java.util.ArrayList;

/**
 * DatabaseManager.java
 * <p>
 * This program deals with reading and writing to files.
 *
 * @author AustinWilson section 5
 * @author TannerDent section 5
 * @version 12/8/2018
 */

public class DatabaseManager {
    public static FileReader fr;
    public static BufferedReader br;

    /**
     * Creates an ArrayList of Vehicles from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     * If filePath does not exist, a blank ArrayList will be returned.
     *
     * @param file CSV File
     * @return ArrayList of vehicles
     */
    public static ArrayList<Vehicle> loadVehicles(File file) {
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String tempLine;
            while ((tempLine = br.readLine()) != null) {
                String[] tempLines = tempLine.split(",");
                String type = tempLines[0];
                switch (type) {
                    case ("Truck"):
                        vehicles.add(new Truck(tempLines[1], Double.parseDouble(tempLines[2])));
                        break;
                    case ("Drone"):
                        vehicles.add(new Drone(tempLines[1], Double.parseDouble(tempLines[2])));
                        break;
                    case ("Cargo Plane"):
                        vehicles.add(new CargoPlane(tempLines[1], Double.parseDouble(tempLines[2])));
                        break;
                    default:
                        break;
                }
            }
            br.close();
        } catch (IOException e) {
            return vehicles;
        }
        return vehicles;
    }


    /**
     * Creates an ArrayList of Packages from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     * <p>
     * If filePath does not exist, a blank ArrayList will be returned.
     *
     * @param file CSV File
     * @return ArrayList of packages
     */
    public static ArrayList<Package> loadPackages(File file) {
        ArrayList<Package> packages = new ArrayList<Package>();
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String tempLine;
            while ((tempLine = br.readLine()) != null) {
                String[] tempLines = tempLine.split(",");
                packages.add(new Package(tempLines[0], tempLines[1], Double.parseDouble(tempLines[2]),
                        Double.parseDouble(tempLines[3]),
                        new ShippingAddress(tempLines[4], tempLines[5], tempLines[6],
                                tempLines[7], Integer.parseInt(tempLines[8]))));
            }
            br.close();
        } catch (IOException e) {
            return packages;
        }
        return packages;
    }


    /**
     * Returns the total Profits from passed text file. If the file does not exist 0
     * will be returned.
     *
     * @param file file where profits are stored
     * @return profits from file
     */
    public static double loadProfit(File file) {
        double profit = 0;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String tempLine;
            while ((tempLine = br.readLine()) != null) {
                profit = Double.parseDouble(tempLine);
            }
            br.close();
        } catch (IOException e) {
            return profit;
        }
        return profit;
    }


    /**
     * Returns the total number of packages shipped stored in the text file. If the
     * file does not exist 0 will be returned.
     *
     * @param file file where number of packages shipped are stored
     * @return number of packages shipped from file
     */
    public static int loadPackagesShipped(File file) {
        int numPackages = 0;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String tempLine;
            while ((tempLine = br.readLine()) != null) {
                numPackages = Integer.parseInt(tempLine);
            }
            br.close();
        } catch (IOException e) {
            return numPackages;
        }
        return numPackages;
    }


    /**
     * Returns whether or not it was Prime Day in the previous session. If file does
     * not exist, returns false.
     *
     * @param file file where prime day is stored
     * @return whether or not it is prime day
     */
    public static boolean loadPrimeDay(File file) {
        int primeNumber = 0;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String tempLine;
            while ((tempLine = br.readLine()) != null) {
                primeNumber = Integer.parseInt(tempLine);
            }
            if (primeNumber == 1) {
                return true;
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return false;
    }


    /**
     * Saves (writes) vehicles from ArrayList of vehicles to file in CSV format one vehicle per line.
     * Each line (vehicle) has following fields separated by comma in the same order.
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     *
     * @param file     File to write vehicles to
     * @param vehicles ArrayList of vehicles to save to file
     */
    public static void saveVehicles(File file, ArrayList<Vehicle> vehicles) {
        String type = "";
        File f = new File(String.valueOf(file));
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            for (int i = 0; i < vehicles.size(); i++) {
                if (vehicles.get(i) instanceof Truck) {
                    type = "Truck";
                } else if (vehicles.get(i) instanceof Drone) {
                    type = "Drone";
                } else if (vehicles.get(i) instanceof CargoPlane) {
                    type = "Cargo Plane";
                }
                bw.append(type + "," + vehicles.get(i).getLicensePlate() + "," + vehicles.get(i).getMaxWeight() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Saves (writes) packages from ArrayList of package to file in CSV format one package per line.
     * Each line (package) has following fields separated by comma in the same order.
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     *
     * @param file     File to write packages to
     * @param packages ArrayList of packages to save to file
     */
    public static void savePackages(File file, ArrayList<Package> packages) {
        File f = new File(String.valueOf(file));
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            for (int i = 0; i < packages.size(); i++) {
                bw.append(packages.get(i).getID() + "," + packages.get(i).getProduct() + "," +
                        packages.get(i).getWeight() + "," + packages.get(i).getPrice() + "," +
                        packages.get(i).getDestination().getName() + "," +
                        packages.get(i).getDestination().getAddress() + "," +
                        packages.get(i).getDestination().getCity() + "," +
                        packages.get(i).getDestination().getState() + "," +
                        packages.get(i).getDestination().getZipCode() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Saves profit to text file.
     *
     * @param file   File to write profits to
     * @param profit Total profits
     */

    public static void saveProfit(File file, double profit) {
        File f = new File(String.valueOf(file));
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(f));
            pw.printf("%.2f" + "\n", profit);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Saves number of packages shipped to text file.
     *
     * @param file      File to write profits to
     * @param nPackages Number of packages shipped
     */

    public static void savePackagesShipped(File file, int nPackages) {
        File f = new File(String.valueOf(file));
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(f));
            pw.printf("%d" + "\n", nPackages);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Saves status of prime day to text file. If it is primeDay "1" will be
     * writtern, otherwise "0" will be written.
     *
     * @param file     File to write profits to
     * @param primeDay Whether or not it is Prime Day
     */

    public static void savePrimeDay(File file, boolean primeDay) {
        int primeNumber = 0;
        if (primeDay) {
            primeNumber = 1;
        }
        File f = new File(String.valueOf(file));
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(f));
            pw.printf("%d" + "\n", primeNumber);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}