package exception.general;

import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

public class CDNotFoundException extends ResourceException {

    public CDNotFoundException(String resourceName) {

        super(
                Status.CLIENT_ERROR_NOT_FOUND.getCode(),
                "Not Found",
                String.format("Requested %s does not exist", resourceName)
        );
    }
}
