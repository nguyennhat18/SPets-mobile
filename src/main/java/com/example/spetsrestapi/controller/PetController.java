package com.example.spetsrestapi.controller;

import com.example.spetsrestapi.exception.ApplicationException;
import com.example.spetsrestapi.model.dto.FileDTO;
import com.example.spetsrestapi.model.entity.Pet;
import com.example.spetsrestapi.model.mapper.PetMapper;
import com.example.spetsrestapi.model.request.PetSaveRequest;
import com.example.spetsrestapi.model.response.ApiResponse;
import com.example.spetsrestapi.model.response.PetResponse;
import com.example.spetsrestapi.service.AccountService;
import com.example.spetsrestapi.service.PetService;
import com.example.spetsrestapi.service.UploadFileService;
import com.example.spetsrestapi.util.DateUtil;
import com.example.spetsrestapi.util.ValidatorUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UploadFileService uploadFileService;

    @Autowired
    private PetMapper petMapper;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PetResponse>> findAll() {
        try {
            List<Pet> petList = petService.getAll();

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(petMapper.toResponseList(petList));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @GetMapping("/owner")
    public ResponseEntity<ApiResponse<PetResponse>> findByOwner(@RequestParam long ownerId) {
        try {
            List<Pet> petList = petService.getByOwner(ownerId);

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(petMapper.toResponseList(petList));
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException();
        }
    }

    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ApiResponse<PetResponse>> save(@Valid @ModelAttribute(value = "request") PetSaveRequest request,
            BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                ApiResponse apiResponse = new ApiResponse();
                apiResponse.error(ValidatorUtil.toErrors(bindingResult.getFieldErrors()));
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }

            Pet pet = petMapper.toEntity(request);
            pet.setId(request.getId());

            if (!StringUtils.isEmpty(request.getBirthDay())) {
                pet.setBirthdate(DateUtil.convertStringToDate(request.getBirthDay(), "dd/MM/yyyy"));
            }

            pet.setOwner(accountService.findById(request.getOwnerId()));

            // upload avatar pet
            if (request.getAvatarMul() != null &&
                    !ObjectUtils.isEmpty(request.getAvatarMul().getOriginalFilename())) {
                FileDTO fileDTOBack = uploadFileService.uploadFile(request.getAvatarMul());
                pet.setAvatar(fileDTOBack.getPath());
            }

            pet.setVersion(0);
            pet = petService.save(pet);

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.ok(pet);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

}