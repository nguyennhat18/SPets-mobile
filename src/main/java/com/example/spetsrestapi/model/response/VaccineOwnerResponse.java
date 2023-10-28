package com.example.spetsrestapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VaccineOwnerResponse {

    // media
    private long id;
    private String name;
    private String date;
    private boolean status;

    // pet
    private PetResponse pet = new PetResponse();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PetResponse {

        // pet
        private long id;
        private String name;
        private String avatar;
        private String species;
        private String birthdate;
        private String color;
        private double weight;
        private double height;
        private String health;
        private boolean status;
    }

}