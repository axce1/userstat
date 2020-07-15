package com.example.userstat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class JsonResponse implements Serializable {
    private String description;
    private Long count;

    public JsonResponse(String description, Long count) {
        this.description = description;
        this.count = count;
    }
}
