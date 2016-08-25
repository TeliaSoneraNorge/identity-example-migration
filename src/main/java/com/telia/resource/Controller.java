package com.telia.resource;

import com.telia.exception.UserNotFoundException;
import com.telia.model.Error;
import com.telia.model.User;
import com.telia.model.UserResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final Log LOGGER = LogFactory.getLog(Controller.class);
    static final String NOT_FOUND = "NOT_FOUND";
    static final String ERROR = "ERROR";

    @RequestMapping(value = "e164/{msisdn}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse getUser(@PathVariable String msisdn) {
        switch (msisdn) {
            case NOT_FOUND:
                throw new UserNotFoundException(msisdn);
            case ERROR:
                throw new RuntimeException("Something went terrible wrong!");
            default:
                return UserResponse.builder()
                        .user(getSomeUserData(msisdn))
                        .issuerId("issuerId")
                        .build();
        }
    }

    private User getSomeUserData(String msisdn) {
        return User.builder()
                .phoneNumber(msisdn)
                .givenName("Name")
                .familyName("Nameson")
                .externalId(String.valueOf(123))
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    private Error handleUserNotFound(Exception ex) {
        LOGGER.warn("Could not find user", ex);
        return new Error(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    private Error handleDefaultException(Exception ex) {
        LOGGER.error("Internal error", ex);
        return new Error(ex.getMessage());
    }

}
