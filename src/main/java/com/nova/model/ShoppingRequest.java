package com.nova.model;

import lombok.Data;

@Data
public class ShoppingRequest {
    private String style;
    private String occasion;
    private String budget;
    private String mood;
    private String additionalNotes;
}
