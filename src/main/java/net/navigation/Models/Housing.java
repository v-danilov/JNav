package net.navigation.Models;


import javax.persistence.*;

@Entity
@Table(name = "HOUSING")
public class Housing {

    @Id
    @Column(name = "IDHOUSING")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id_housing;

    @Column(name = "DESC")
    private String desc;

    public int getId_housing() {
        return id_housing;
    }

    public void setId_housing(int id_housing) {
        this.id_housing = id_housing;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Housing{" +
                "id_housing=" + id_housing +
                ", desc='" + desc + '\'' +
                '}';
    }
}
