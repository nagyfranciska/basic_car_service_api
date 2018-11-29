package resource.garage;

import model.Garage;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import service.GarageService;

import java.util.List;

public class GaragesServerResource extends ServerResource {

    public static GarageService garageService = new GarageService();

    @Get
    public List getGarages() {
        return garageService.getGarages();
    }

    @Post
    public Garage saveGarage(Garage garage) {
        System.out.println("GARAGE: " + garage.toString());
        return garageService.saveGarage(garage);
    }
}
