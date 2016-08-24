package com.telia.model;

import com.telia.model.util.CustomMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.fail;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

public class ModelTest {

    @Test
    public void mapUserResponse() {
        UserResponse userResponse = UserResponse.builder().user(createUserWithAddress()).issuerId("some id").build();
        assertEqual(userResponse, UserResponse.class);
    }

    @Test
    public void mapUser() {
        User user = createUserWithAddress();
        assertEqual(user, User.class);
    }

    @Test
    public void mapAddress() {
        Address address = createAddress();
        assertEqual(address, Address.class);
    }

    @Test
    public void mapError() {
        Error error = Error.builder().error("error").build();
        assertEqual(error, Error.class);
    }

    private <T> void assertEqual(T object, Class<T> clazz) {
        CustomMapper<T> customMapper = new CustomMapper<>(clazz);

        try {
            String serialized = customMapper.serialize(object);
            T deserialized = customMapper.deserialize(serialized);
            assertReflectionEquals(object, deserialized);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    private User createUserWithAddress() {
        return createUser(createAddress());
    }

    private User createUser(Address address) {
        return User.builder()
                .givenName("given name")
                .familyName("family name")
                .middleName("middle name")
                .nickname("nickname")
                .preferredUsername("preferred username")
                .profile("profile")
                .picture("picture")
                .website("website")
                .email("email")
                .gender("gender")
                .birthdate("birthdate")
                .phoneNumber("phone number")
                .address(address)
                .externalId("external id")
                .preferredLocale("preferred locale")
                .build();
    }

    private Address createAddress() {
        return Address.builder().streetAddress("street address").locality("locality").region("region").postalCode("postal code").countryCode("country code").build();
    }
}