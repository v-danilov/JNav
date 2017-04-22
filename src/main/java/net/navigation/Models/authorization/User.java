package net.navigation.Models.authorization;

import javax.persistence.*;

/**
 * Created by Bounc on 19.04.2017.
 */

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @Column(name = "IDUSER")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id_user;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String pass;

    @Column(name = "NAME")
    private String name;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
