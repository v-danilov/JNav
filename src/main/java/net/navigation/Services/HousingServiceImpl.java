package net.navigation.Services;

import net.navigation.DAO.HousingDao;
import net.navigation.Models.Housing;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HousingServiceImpl implements HousingService {

    private HousingDao housingDao;

    public void setHousingDao(HousingDao housingDao) {this.housingDao =housingDao;}

    @Override
    @Transactional
    public void addHousing(Housing housing){this.housingDao.addHousing(housing);};

    @Override
    @Transactional
    public void updateHousing(Housing housing){this.housingDao.updateHousing(housing);};

    @Override
    @Transactional
    public void removeHousing(int id){this.housingDao.removeHousing(id);};

    @Override
    @Transactional
    public List<Housing> listHousings(){return this.housingDao.listHousings();}
}
