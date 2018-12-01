package resource.garage;

import com.google.inject.Inject;
import model.Garage;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import service.GarageService;

public class GarageServerResource extends ServerResource {

    @Inject
    GarageService garageService;

    @Get
    public Garage getGarage() {
        return garageService.getGarageById(Integer.parseInt(getAttribute("garageId")));
    }

    @Put
    public Garage updateGarage(Garage garage) {
        return garageService.updateGarage(garage, Integer.parseInt(getAttribute("garageId")));
    }

    @Delete
    public Garage deleteGarage() {
        return garageService.deleteGarage(Integer.parseInt(getAttribute("garageId")));
    }
}
