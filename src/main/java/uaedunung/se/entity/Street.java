package uaedunung.se.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "streets")
public class Street {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, length = 255)
    private String name;

    @Column(nullable = false)
    private int deliveryTimeMinutes;

    // Гетери та сетери

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeliveryTimeMinutes() {
        return deliveryTimeMinutes;
    }

    public void setDeliveryTimeMinutes(int deliveryTimeMinutes) {
        this.deliveryTimeMinutes = deliveryTimeMinutes;
    }

    @Override
    public String toString() {
        return "Street{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deliveryTimeMinutes=" + deliveryTimeMinutes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Street street = (Street) o;
        return id == street.id && deliveryTimeMinutes == street.deliveryTimeMinutes && Objects.equals(name, street.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, deliveryTimeMinutes);
    }
}
