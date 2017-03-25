package net.navigation.DAO;

import net.navigation.Models.Floor;

import java.util.List;


public interface FloorDao {

    public void addFloor(Floor floor);

    public void updateFloor(Floor floor);

    public void removeFloor(int id);

    public List<Floor> listFloors();
}
