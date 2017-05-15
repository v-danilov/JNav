package net.navigation.Models.navigation;

import javax.persistence.*;


@Entity
@Table(name = "ARCS")
public class Arc {

    @Id
    @Column(name = "IDARC")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id_arc;

    @Column(name = "START")
    private int start_node;

    @Column(name = "END")
    private int end_node;

    @Column(name = "WEIGHT")
    private int weight;

    @Column(name = "TIMEMULTIPLIER")
    private int time_multiplier;

    @Column(name = "SVGID")
    private int svg_id;

    public Arc(int start_node, int end_node, int weight, int time_multiplier, int svg_id) {
        this.start_node = start_node;
        this.end_node = end_node;
        this.weight = weight;
        this.time_multiplier = time_multiplier;
        this.svg_id = svg_id;
    }

    public Arc(){
        this.end_node = 0;
        this.start_node = 0;
        this.weight = 0;
        this.time_multiplier = 0;
        this.svg_id = 0;
    };

    public int getId_arc() {
        return id_arc;
    }

    public void setId_arc(int id_arc) {
        this.id_arc = id_arc;
    }

    public int getEnd_node() {
        return end_node;
    }

    public void setEnd_node(int end_node) {
        this.end_node = end_node;
    }

    public int getStart_node() {
        return start_node;
    }

    public void setStart_node(int start_node) {
        this.start_node = start_node;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getTime_multiplier() {
        return time_multiplier;
    }

    public void setTime_multiplier(int time_multiplier) {
        this.time_multiplier = time_multiplier;
    }

    public int getSvg_id() {
        return svg_id;
    }

    public void setSvg_id(int svg_id) {
        this.svg_id = svg_id;
    }

    @Override
    public String toString() {
        return "Arc{" +
                "id_arc=" + id_arc +
                ", end_node=" + end_node +
                ", start_node=" + start_node +
                ", weight=" + weight +
                ", time_multiplier=" + time_multiplier +
                '}';
    }
}
