package com.telia.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@AllArgsConstructor
@ToString
public class Address {

    /**
     * The street address
     */
    @JsonProperty("street_address")
    private String streetAddress;

    /**
     * The locality of the address
     */
    private String locality;

    /**
     * The region of the address
     */
    private String region;

    /**
     * The postal code for the address
     */
    @JsonProperty("postal_code")
    private String postalCode;

    /**
     * The country code
     */
    @JsonProperty("country")
    private String countryCode;

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getLocality() {
        return locality;
    }

    public String getRegion() {
        return region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
