package org.wso2.carbon.identity.common.handlers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.common.ResourceConstants;
import org.wso2.carbon.identity.common.exceptions.UMAException;
import org.wso2.carbon.identity.oauth.uma.resource.endpoint.HandleErrorResponseConstants;
import org.wso2.carbon.identity.oauth.uma.resource.endpoint.dto.ErrorDTO;
import org.wso2.carbon.identity.oauth.uma.resource.endpoint.exceptions.ResourceEndpointException;
import org.wso2.carbon.identity.oauth.uma.resource.endpoint.impl.ResourceRegistrationApiServiceImpl;

import javax.ws.rs.core.Response;


public class errorHandler {

    private static final Log log = LogFactory.getLog(ResourceRegistrationApiServiceImpl.class);

    /**
     * Handle error responses.
     *
     * @param throwable exception that should pass to handle error.
     */
    public void handleErrorResponse(Throwable throwable, boolean isServerException) throws ResourceEndpointException {

        String code;
        String errorCode = null;
        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
        boolean isStatusOnly = true;
        if (throwable instanceof UMAException) {
            code = ((UMAException) throwable).getErrorCode();
        } else {
            code = ResourceConstants.ErrorMessages.ERROR_CODE_UNEXPECTED.getCode();
        }
        if (isServerException) {
            if (throwable == null) {
                log.error(status.getReasonPhrase());
            } else {
                log.error(status.getReasonPhrase(), throwable);
            }
        } else {
            HandleErrorResponseConstants handleErrorResponseConstants = new HandleErrorResponseConstants();
            if (handleErrorResponseConstants.getResponseMap().containsKey(code)) {
                String statusCode = handleErrorResponseConstants.getResponseMap().get(code)[0];
                status = Response.Status.fromStatusCode(Integer.parseInt(statusCode));
                errorCode = handleErrorResponseConstants.getResponseMap().get(code)[1];
                isStatusOnly = false;
            }
        }
        throw buildResourceEndpointException(status, errorCode, throwable == null ? "" : throwable.getMessage(),
                isStatusOnly);
    }

    public ResourceEndpointException buildResourceEndpointException(Response.Status status, String errorCode,
                                                                    String description, boolean isStatusOnly) {

        if (isStatusOnly) {
            return new ResourceEndpointException(status);
        } else {
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setCode(errorCode);
            errorDTO.setDescription(description);
            return new ResourceEndpointException(status, errorDTO);
        }
    }
}
