package com.telia.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@AllArgsConstructor
public class UserResponse {

    /**
     * The user, type {@link User}
     */
    private User user;

    /**
     * The id of the issuer
     */
    @JsonProperty("issuer_id")
    private String issuerId;

    public User getUser() {
        return user;
    }

    public String getIssuerId() {
        return issuerId;
    }
}
