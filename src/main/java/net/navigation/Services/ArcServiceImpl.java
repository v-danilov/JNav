package net.navigation.Services;


import net.navigation.DAO.ArcDao;
import net.navigation.DAO.NodeDao;
import net.navigation.DAO.NodeDaoImpl;
import net.navigation.Models.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArcServiceImpl implements ArcService {

    private ArcDao arcDao;

    public void setArcDao(ArcDao arcDao) {this.arcDao =arcDao;}

    @Override
    @Transactional
    public void addArc(Arc arc){this.arcDao.addArc(arc);};

    @Override
    @Transactional
    public void updateArc(Arc arc){this.arcDao.updateArc(arc);};

    @Override
    @Transactional
    public void removeArc(int id){this.arcDao.removeArc(id);};

    @Override
    @Transactional
    public Arc findArcByID(int id){return this.arcDao.findArcByID(id);};

    @Override
    @Transactional
    public Arc findArcByNodes(int start_node_id, int end_node_id){return  this.arcDao.findArcByNodes(start_node_id,end_node_id);}

    @Override
    @Transactional
    public List<Arc> listArcs(){return this.arcDao.listArcs();}
}



