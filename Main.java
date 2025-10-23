import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Создаем массив машин для тестирования
        Car[] allCars = {
            new Car(1, "Toyota", "Camry", 2020, "Silver", 25000.0, "A123BC"),
            new Car(2, "Honda", "CR-V", 2021, "Blue", 30000.0, "D456EF"),
            new Car(3, "Toyota", "Corolla", 2019, "White", 22000.0, "G789HI"),
            new Car(4, "BMW", "X5", 2022, "Black", 60000.0, "J012KL"),
            new Car(5, "Toyota", "RAV4", 2023, "Red", 35000.0, "M345NO"),
            new Car(6, "Honda", "Civic", 2018, "Grey", 18000.0, "P678QR")
        };

        System.out.println("--- Тестирование getCarByBrand ---");
        String searchBrand1 = "Toyota";
        Car[] toyotaCars = getCarByBrand(allCars, searchBrand1);
        System.out.println("Машины марки " + searchBrand1 + ":");
        if (toyotaCars.length == 0) {
            System.out.println("  Не найдено.");
        } else {
            for (Car car : toyotaCars) {
                System.out.println("  ID: " + car.getId() + ", Модель: " + car.getModel() + ", Год: " + car.getYearOfManufacture());
            }
        }

        String searchBrand2 = "Mercedes";
        Car[] mercedesCars = getCarByBrand(allCars, searchBrand2);
        System.out.println("\nМашины марки " + searchBrand2 + ":");
        if (mercedesCars.length == 0) {
            System.out.println("  Не найдено.");
        } else {
            for (Car car : mercedesCars) {
                System.out.println("  ID: " + car.getId() + ", Модель: " + car.getModel() + ", Год: " + car.getYearOfManufacture());
            }
        }
        System.out.println("----------------------------------");

        // Тестирование для следующего метода будет здесь
    }

    public static Car[] getCarByBrand(Car[] cars, String brand) {
        ArrayList<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getBrand().equalsIgnoreCase(brand)) {
                result.add(car);
            }
        }
        return result.toArray(new Car[0]);
    }

    // Следующий метод будет добавлен здесь
}