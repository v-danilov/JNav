package net.navigation.Services;

import net.navigation.Models.navigation.Floor;

import java.util.List;

public interface FloorService {

    public void addFloor(Floor floor);

    public void updateFloor(Floor floor);

    public int findFloorId(int floor_number, int housing_id);

    public void removeFloor(int id);

    public List<Floor> listFloors();
}
