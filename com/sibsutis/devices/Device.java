package DZ6.com.sibsutis.devices;

import DZ6.com.sibsutis.Printable;

import java.util.Objects;

public abstract class Device implements Printable {
    private int id;
    private int price;
    private String ip;

    public Device(int id, int price, String ip) {
        this.id = id;
        this.price = price;
        this.ip = ip;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getIp() {
        return ip;
    }

    public abstract String getDeviceType();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return id == device.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
