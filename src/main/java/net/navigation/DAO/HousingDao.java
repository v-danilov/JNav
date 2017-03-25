package net.navigation.DAO;


import net.navigation.Models.Housing;

import java.util.List;

public interface HousingDao {

    public void addHousing(Housing housing);

    public void updateHousing(Housing housing);

    public void removeHousing(int id);

    public List<Housing> listHousings();
}
