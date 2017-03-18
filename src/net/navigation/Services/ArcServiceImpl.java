package net.navigation.Services;


import net.navigation.DAO.ArcDao;
import net.navigation.Models.Arc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ArcServiceImpl implements ArcService {

    private ArcDao arcDao;

    public void setArcDao(ArcDao arcDao) {this.arcDao =arcDao;}

    @Override
    @Transactional
    public void addArc(Arc arc){this.arcDao.addArc(arc);};

    public void updateArc(Arc arc){this.arcDao.updateArc(arc);};

    public void removeArc(int id){this.arcDao.removeArc(id);};

    public Arc findArcByID(int id){return this.arcDao.findArcByID(id);};

    public Arc findArcByNodes(int start_node_id, int end_node_id){return  this.arcDao.findArcByNodes(start_node_id,end_node_id);};

    public List<Arc> listArcs(){return this.arcDao.listArcs();};


}
