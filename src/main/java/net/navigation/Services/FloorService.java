package net.navigation.Services;

import net.navigation.Models.Floor;

import java.util.List;

public interface FloorService {

    public void addFloor(Floor floor);

    public void updateFloor(Floor floor);

    public void removeFloor(int id);

    public List<Floor> listFloors();
}
