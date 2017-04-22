package net.navigation.ExtClasses;

import java.util.List;

/**
 * Created by Vadim
 */
public class Circle {
    private String id;
    //private String cx;
    //private String cy;
    private String next_circle;
    private String prev_circle;
    private List<String> rooms;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

   /* public String getCx() {
        return cx;
    }

    public void setCx(String cx) {
        this.cx = cx;
    }

    public String getCy() {
        return cy;
    }

    public void setCy(String cy) {
        this.cy = cy;
    }*/

    public String getNext_circle() {
        return next_circle;
    }

    public void setNext_circle(String next_circle) {
        this.next_circle = next_circle;
    }

    public String getPrev_circle() {
        return prev_circle;
    }

    public void setPrev_circle(String prev_circle) {
        this.prev_circle = prev_circle;
    }

    public List<String> getRooms() {
        return rooms;
    }

    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "id='" + id + '\'' +
                ", next_circle='" + next_circle + '\'' +
                ", prev_circle='" + prev_circle + '\'' +
                ", rooms=" + rooms +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Circle circle = (Circle) o;

        if (id != null ? !id.equals(circle.id) : circle.id != null) return false;
        if (next_circle != null ? !next_circle.equals(circle.next_circle) : circle.next_circle != null) return false;
        if (prev_circle != null ? !prev_circle.equals(circle.prev_circle) : circle.prev_circle != null) return false;
        return rooms != null ? rooms.equals(circle.rooms) : circle.rooms == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (next_circle != null ? next_circle.hashCode() : 0);
        result = 31 * result + (prev_circle != null ? prev_circle.hashCode() : 0);
        result = 31 * result + (rooms != null ? rooms.hashCode() : 0);
        return result;
    }
}

