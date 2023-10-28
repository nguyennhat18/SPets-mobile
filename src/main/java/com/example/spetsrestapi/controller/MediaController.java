package com.example.spetsrestapi.controller;

import com.example.spetsrestapi.model.entity.Media;
import com.example.spetsrestapi.model.request.MediaSaveRequest;
import com.example.spetsrestapi.model.response.ApiResponse;
import com.example.spetsrestapi.model.response.MediaSaveResponse;
import com.example.spetsrestapi.service.MediaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    @Autowired
    private MediaService mePetService;

    @GetMapping(value = "/list/{petId}")
    public ResponseEntity<ApiResponse<List<Media>>> getAllByPet(@PathVariable long petId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(mePetService.getAllByPetOrderByUpdatedAtDesc(petId));

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ApiResponse<MediaSaveResponse>> save(@Valid @ModelAttribute MediaSaveRequest request){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(mePetService.save(request));

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}