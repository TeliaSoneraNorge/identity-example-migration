package com.telia.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@AllArgsConstructor
@ToString
public class User {

    /**
     * Given name(s) or first name(s) of the End-User
     */
    @JsonProperty("given_name")
    private String givenName;

    /**
     * Surname(s) or last name(s) of the End-User
     */
    @JsonProperty("family_name")
    private String familyName;

    /**
     * Middle name(s) of the End-User
     */
    @JsonProperty("middle_name")
    private String middleName;

    /**
     * Casual name of the End-User that may or may not be the same as the given_name
     */
    @JsonProperty("nickname")
    private String nickname;

    /**
     * Shorthand name by which the End-User wishes to be referred to at the RP, such as janedoe or j.doe
     */
    @JsonProperty("preferred_username")
    private String preferredUsername;

    /**
     * URL of the End-User's profile page
     */
    private String profile;

    /**
     * URL of the End-User's profile picture. This URL MUST refer to an image file (for example, a PNG, JPEG, or GIF image file), rather than to a Web page containing an image
     */
    private String picture;

    /**
     * URL of the End-User's Web page or blog
     */
    private String website;

    /**
     * End-User's email address
     */
    private String email;

    /**
     * End-User's gender. Values defined by this specification are 'female' and 'male'
     */
    private String gender;

    /**
     * End-User's birthday, represented as an ISO 8601:2004 YYYY-MM-DD format.
     */
    private String birthdate;

    /**
     * End-User's preferred telephone number. E.164
     */
    @JsonProperty("phone_number")
    private String phoneNumber;

    /**
     * End-User's address, {@link Address}
     */
    private Address address;

    /**
     * End-User id at the federated source
     */
    @JsonProperty("external_id")
    private String externalId;

    /**
     * IETF, BCP47 locale
     */
    @JsonProperty("preferred_locale")
    private String preferredLocale;

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPreferredUsername() {
        return preferredUsername;
    }

    public String getProfile() {
        return profile;
    }

    public String getPicture() {
        return picture;
    }

    public String getWebsite() {
        return website;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public String getExternalId() {
        return externalId;
    }

    public String getPreferredLocale() {
        return preferredLocale;
    }
}
