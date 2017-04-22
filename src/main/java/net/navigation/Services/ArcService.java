package net.navigation.Services;

import net.navigation.Models.navigation.Arc;

import java.util.List;

public interface ArcService {

    public void addArc(Arc arc);

    public void updateArc(Arc arc);

    public void removeArc(int id);

    public Arc findArcByID(int id);

    public Arc findArcByNodes(int start_node_id, int end_node_id);

    public List<Arc> listArcs();
}
