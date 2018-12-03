package exception.validation;

import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

public class PlateFormatException extends ResourceException {

    public PlateFormatException() {

        super(
                Status.CLIENT_ERROR_BAD_REQUEST.getCode(),
                "Bad Request",
                "Car plate format invalid. Please use the following format: 'ABC-123'"
        );
    }

}
