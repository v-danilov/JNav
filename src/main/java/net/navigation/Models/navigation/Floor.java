package net.navigation.Models.navigation;


import javax.persistence.*;

@Entity
@Table(name = "FLOORS")
public class Floor {

    @Id
    @Column(name = "IDFLOOR")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id_floor;

    @Column(name = "FLOORNUMBER")
    private int floor_number;

    @Column(name = "HOUSINGID")
    private int housing_id;

    public int getId_floor() {
        return id_floor;
    }

    public void setId_floor(int id_floor) {
        this.id_floor = id_floor;
    }

    public int getFloor_number() {
        return floor_number;
    }

    public void setFloor_number(int floor_number) {
        this.floor_number = floor_number;
    }

    public int getHousing_id() {
        return housing_id;
    }

    public void setHousing_id(int housing_id) {
        this.housing_id = housing_id;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "id_floor=" + id_floor +
                ", floor_number=" + floor_number +
                ", housing_id=" + housing_id +
                '}';
    }
}
