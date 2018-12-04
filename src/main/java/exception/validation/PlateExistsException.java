package exception.validation;

import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

public class PlateExistsException extends ResourceException {

    public PlateExistsException() {

        super(
                Status.CLIENT_ERROR_CONFLICT.getCode(),
                "CONFLICT",
                "Car plate already registered"
        );
    }

}
