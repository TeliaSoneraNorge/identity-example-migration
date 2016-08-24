package com.telia.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class Error {
    /**
     * The error message
     */
    private String error;

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return getError();
    }
}
