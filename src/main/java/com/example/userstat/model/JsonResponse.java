package com.example.userstat.model;

import java.io.Serializable;

public class JsonResponse implements Serializable {
    private String description;
    private Long count;

    public JsonResponse(String description, Long count) {
        this.description = description;
        this.count = count;
    }
}
