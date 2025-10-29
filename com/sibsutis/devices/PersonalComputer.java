package DZ6.com.sibsutis.devices;

import java.util.Objects;

public class PersonalComputer extends Device {
    public PersonalComputer(int id, int price, String ip) {
        super(id, price, ip);
    }

    public PersonalComputer(int id, int price) {
        super(id, price, null); // ip может быть null
    }

    @Override
    public String getDeviceType() {
        return "PersonalComputer";
    }

    @Override
    public String print() {
        return "PersonalComputer{" +
                "id=" + getId() +
                ", price=" + getPrice() +
                ", ip='" + getIp() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false; // Сначала сравниваем id родительского класса
        PersonalComputer that = (PersonalComputer) o;
        return Objects.equals(getIp(), that.getIp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getIp());
    }
}