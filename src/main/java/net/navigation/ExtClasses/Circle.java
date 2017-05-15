package net.navigation.ExtClasses;

import java.util.List;

import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte1.other;

/**
 * Created by Vadim
 */
public class Circle {
    private String id;
    private String next_circle;
    private String prev_circle;
    private List<String> rooms;

    public Circle() {
        this.id = null;
        this.next_circle = null;
        this.prev_circle = null;
        this.rooms = null;
    }

    public Circle(String id, String next_circle, String prev_circle, List<String> rooms) {
        this.id = id;
        this.next_circle = next_circle;
        this.prev_circle = prev_circle;
        this.rooms = rooms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

        return id.equals(circle.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

