package com.softserve.edu.dto;

import com.softserve.edu.model.Marathon;
import lombok.Data;

@Data
public class MarathonDTO {
    private Long id;
    private String title;

    private MarathonDTO(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public MarathonDTO(String title) {
        this.title = title;
    }

    public static MarathonDTO of(Marathon marathon) {
        return new MarathonDTO(marathon.getId(), marathon.getTitle());
    }
}
