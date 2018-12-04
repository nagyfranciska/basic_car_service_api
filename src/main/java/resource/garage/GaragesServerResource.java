package resource.garage;

import com.google.inject.Inject;
import model.Garage;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import service.model.GarageService;

import java.util.List;

public class GaragesServerResource extends ServerResource {

    @Inject
    private GarageService garageService;

    @Get
    public List<Garage> getGarages() {
        return garageService.getGarages();
    }

    @Post
    public Garage saveGarage(Garage garage) {
        return garageService.saveGarage(garage);
    }
}
