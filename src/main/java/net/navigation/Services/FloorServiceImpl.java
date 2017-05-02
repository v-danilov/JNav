package net.navigation.Services;

import net.navigation.DAO.FloorDao;
import net.navigation.Models.navigation.Floor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FloorServiceImpl implements FloorService {

    private FloorDao floorDao;

    public void setFloorDao(FloorDao floorDao) {this.floorDao =floorDao;}

    @Override
    @Transactional
    public void addFloor(Floor floor){this.floorDao.addFloor(floor);};

    @Override
    @Transactional
    public int findFloorId(int floor_number, int housing_id) {
        return this.floorDao.findFloorId(floor_number, housing_id);
    }

    ;

    @Override
    @Transactional
    public void updateFloor(Floor floor){this.floorDao.updateFloor(floor);};

    @Override
    @Transactional
    public void removeFloor(int id){this.floorDao.removeFloor(id);};

    @Override
    @Transactional
    public List<Floor> listFloors(){return this.floorDao.listFloors();}
}
