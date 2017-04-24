package net.navigation.Models.navigation;

import javax.persistence.*;



@Entity
@Table(name = "NODES")
public class Node {

    @Id
    @Column(name = "IDNODE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_node;

    @Column(name = "NODENUMBER")
    private String node_number;

    @Column(name = "FLOORID" )
    private int floor_id;


    @Column(name = "TYPE" )
    private int type;

    @Column(name = "SVGID" )
    private int svg_id;


    @Column(name = "DESC" )
    private String desc;

    public int getId_node() {
        return id_node;
    }

    public void setId_node(int id_node) {
        this.id_node = id_node;
    }

    public String getNode_number() {
        return node_number;
    }

    public void setNode_number(String node_number) {
        this.node_number = node_number;
    }

    public int getFloor_id() {
        return floor_id;
    }

    public void setFloor_id(int floor_id) {
        this.floor_id = floor_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSvg_id() {
        return svg_id;
    }

    public void setSvg_id(int svg_id) {
        this.svg_id = svg_id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }



    @Override
    public String toString() {
        return "Node{" +
                "id_node=" + id_node +
                ", node_number=" + node_number +
                ", floor_id=" + floor_id +
                ", type=" + type +
                ", svg_id=" + svg_id +
                ", desc='" + desc + '\'' +
                '}';
    }
}
