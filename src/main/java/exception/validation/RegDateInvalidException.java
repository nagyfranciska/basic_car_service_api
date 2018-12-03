package exception.validation;

import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

import java.time.LocalDate;

public class RegDateInvalidException extends ResourceException {

    public RegDateInvalidException() {

        super(
                Status.CLIENT_ERROR_BAD_REQUEST.getCode(),
                "Bad Request",
                "Car registration date invalid. Server only accepts cars registered between " +
                        LocalDate.now().minusYears(10) + " and " + LocalDate.now().minusDays(1)
        );
    }

}
