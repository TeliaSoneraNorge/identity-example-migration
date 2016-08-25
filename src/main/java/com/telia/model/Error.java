package com.telia.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Error {
    /**
     * The error message
     */
    private String error;

    public String getError() {
        return error;
    }
}
