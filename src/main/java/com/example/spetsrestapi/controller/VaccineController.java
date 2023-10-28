package com.example.spetsrestapi.controller;

import com.example.spetsrestapi.model.response.ApiResponse;
import com.example.spetsrestapi.model.response.VaccineOwnerResponse;
import com.example.spetsrestapi.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;

    @GetMapping(value = "/owner")
    public ResponseEntity<ApiResponse<List<VaccineOwnerResponse>>> getByOwner(@RequestParam long ownerId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(vaccineService.getByOwner(ownerId));

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/pet")
    public ResponseEntity<ApiResponse<List<VaccineOwnerResponse>>> getByPet(@RequestParam long petId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(vaccineService.getByPet(petId));

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}