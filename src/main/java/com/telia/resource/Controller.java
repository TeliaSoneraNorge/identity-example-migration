package com.telia.resource;

import com.telia.model.User;
import com.telia.model.UserResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping(value = "e164/{msisdn}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse getUser(@PathVariable String msisdn) {
        User user = getSomeUserData(msisdn);

        return UserResponse.builder()
                .user(user)
                .issuerId("issuerId")
                .build();
    }

    private User getSomeUserData(String msisdn) {
        return User.builder()
                .phoneNumber(msisdn)
                .givenName("Name")
                .familyName("Nameson")
                .externalId(String.valueOf(123))
                .build();
    }
}
