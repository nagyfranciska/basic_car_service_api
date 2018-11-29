package resource.garage;

import model.Garage;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import service.GarageService;

public class GarageServerResource extends ServerResource {

    private GarageService garageService = GaragesServerResource.garageService;

    @Get
    public Garage getGarage() {
        Integer id = Integer.parseInt(getAttribute("garageId"));
        return garageService.getGarageById(id);
    }

    @Put
    public Garage updateGarage(Garage garage) {
        return garageService.updateGarage(garage);
    }

    @Delete
    public Garage deleteGarage(Garage garage) {
        return garageService.deleteGarage(garage);
    }
}
