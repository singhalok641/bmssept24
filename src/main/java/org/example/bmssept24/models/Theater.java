package org.example.bmssept24.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Theater extends BaseModel {
    private String name;
    private String city;
    private List<Screen> screens;
}
