package net.navigation.DAO;

import net.navigation.Models.Arc;

import java.util.List;


public interface ArcDao {

    public void addArc(Arc arc);

    public void updateArc(Arc arc);

    public void removeArc(int id);

    public Arc findArcByID(int id);

    public Arc findArcByNodes(int start_node_id, int end_node_id);

    public List<Arc> listArcs();
}
