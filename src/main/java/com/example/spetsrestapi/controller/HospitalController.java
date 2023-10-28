package com.example.spetsrestapi.controller;

import com.example.spetsrestapi.model.response.ApiResponse;
import com.example.spetsrestapi.model.response.HospitalResponse;
import com.example.spetsrestapi.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    @Autowired
    private HospitalService service;

    @GetMapping(value = "/list")
    public ResponseEntity<ApiResponse<List<HospitalResponse>>> getHospitalList() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(service.getHospital());

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}