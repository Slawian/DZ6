// Car.java
public class Car {
    private int id;
    private String brand;
    private String model;
    private int yearOfManufacture;
    private String color;
    private double price;
    private String registrationNumber;

    public Car(int id, String brand, String model, int yearOfManufacture, String color, double price, String registrationNumber) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.color = color;
        this.price = price;
        this.registrationNumber = registrationNumber;
    }

    // Геттеры
    public int getId() { return id; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYearOfManufacture() { return yearOfManufacture; }
    public String getColor() { return color; }
    public double getPrice() { return price; }
    public String getRegistrationNumber() { return registrationNumber; }

    // Сеттеры
    public void setId(int id) { this.id = id; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setModel(String model) { this.model = model; }
    public void setYearOfManufacture(int yearOfManufacture) { this.yearOfManufacture = yearOfManufacture; }
    public void setColor(String color) { this.color = color; }
    public void setPrice(double price) { this.price = price; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }
}