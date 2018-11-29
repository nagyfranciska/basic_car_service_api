import dao.GarageDAO;
import model.Garage;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.transaction.Transactional;
import java.util.List;

public class GarageDAOTest {

     static GarageDAO dao;
    static Garage garage01;
    static Garage garage02;
    static Garage garage03;

    @BeforeClass
    public static void setup() {

        dao = Mockito.mock(GarageDAO.class);
        garage01 = new Garage("name", "address", 5);
        garage01 = new Garage("name2", "address2", 7);
        garage01 = new Garage("name3", "address3", 2);

        dao.save(garage01);
        dao.save(garage02);
    }

    @Test
    public void testGetAll() {
        dao.save(garage01);
        dao.save(garage02);
        Assert.assertEquals(2, dao.findAll().size());
    }

    @Test
    @Transactional
    public void testAddGarage() {
        dao.save(garage03);
        Assert.assertEquals(3, dao.findAll().size());
    }

    @Test
    public void testGetGarageById() {
        Assert.assertEquals(garage02, dao.findById(1));
    }

//    ////////
//    @Test
//    public void testUpdateGarage() {
//        Garage garageToUpdate = dao.findById(garage01.getId());
//        dao.update(garageToUpdate);
//        Assert.assertEquals(garageToUpdate, dao.findById(garageToUpdate.getId()));
//
//    }

    @Test
    public void testDeleteGarage() {
        dao.delete(garage01);
        Assert.assertEquals(1, dao.findAll().size());
    }
}
