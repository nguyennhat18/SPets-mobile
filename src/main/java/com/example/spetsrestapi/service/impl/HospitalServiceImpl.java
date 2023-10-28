package com.example.spetsrestapi.service.impl;

import com.example.spetsrestapi.exception.ApplicationException;
import com.example.spetsrestapi.model.entity.Account;
import com.example.spetsrestapi.model.entity.Hospital;
import com.example.spetsrestapi.model.entity.Pet;
import com.example.spetsrestapi.model.entity.Vaccination;
import com.example.spetsrestapi.model.response.HospitalResponse;
import com.example.spetsrestapi.model.response.VaccineOwnerResponse;
import com.example.spetsrestapi.repository.AccountRepository;
import com.example.spetsrestapi.repository.HospitalRepository;
import com.example.spetsrestapi.repository.PetRepository;
import com.example.spetsrestapi.repository.VaccineRepository;
import com.example.spetsrestapi.service.HospitalService;
import com.example.spetsrestapi.service.UploadFileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<HospitalResponse> getHospital() {
        try {
            List<HospitalResponse> response = new ArrayList<>();
            List<Hospital> hospitalList = hospitalRepository.findAllByStatusIsTrue();
            for (Hospital hospital : hospitalList) {
                HospitalResponse hospitalResponse = modelMapper.map(hospital, HospitalResponse.class);
                response.add(hospitalResponse);
            }

            return response;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

}
