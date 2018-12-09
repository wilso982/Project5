/**
 * ShippingAddress.java
 * <p>
 * This program creates the shippingadress object.
 *
 * @author AustinWilson section 5
 * @author TannerDent section 5
 * @version 12/8/2018
 */

public class ShippingAddress {
    private String name; //e.g. (Lawson Computer Science Building)
    private String address; //e.g. Street Address (305 N University St)
    private String city; // e.g. (West Lafayette)
    private String state; // e.g. IN
    private int zipCode; //e.g. 47907

    public ShippingAddress() {
        this.name = "";
        this.address = "";
        this.city = "";
        this.state = "";
        this.zipCode = 0;
    }

    public ShippingAddress(String name, String address, String city, String state, int zipCode) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}