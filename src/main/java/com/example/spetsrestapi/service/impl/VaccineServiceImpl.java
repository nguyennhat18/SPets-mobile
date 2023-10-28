package com.example.spetsrestapi.service.impl;

import com.example.spetsrestapi.exception.ApplicationException;
import com.example.spetsrestapi.model.entity.Account;
import com.example.spetsrestapi.model.entity.Pet;
import com.example.spetsrestapi.model.entity.Vaccination;
import com.example.spetsrestapi.model.response.VaccineOwnerResponse;
import com.example.spetsrestapi.repository.AccountRepository;
import com.example.spetsrestapi.repository.PetRepository;
import com.example.spetsrestapi.repository.VaccineRepository;
import com.example.spetsrestapi.service.UploadFileService;
import com.example.spetsrestapi.service.VaccineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class VaccineServiceImpl implements VaccineService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<VaccineOwnerResponse> getByOwner(long ownerId) {
        try {
            List<VaccineOwnerResponse> response = new ArrayList<>();
            Account accountOwner = accountRepository.findById(ownerId).orElse(null);
            if (!ObjectUtils.isEmpty(accountOwner)) {
                List<Pet> petList = petRepository.findByOwnerAndStatusIsTrue(accountOwner);
                for (Pet pet : petList) {
                    List<Vaccination> vaccinationList = vaccineRepository.findAllByPetAndStatusIsFalse(pet);
                    if (!vaccinationList.isEmpty()) {
                        VaccineOwnerResponse vaccineOwnerResponse = new VaccineOwnerResponse();
                        VaccineOwnerResponse.PetResponse petResponse = modelMapper.map(pet, VaccineOwnerResponse.PetResponse.class);
                        vaccineOwnerResponse.setPet(petResponse);

                        for (Vaccination vaccination : vaccinationList) {
                            vaccineOwnerResponse = modelMapper.map(vaccination, VaccineOwnerResponse.class);
                        }
                        response.add(vaccineOwnerResponse);
                    }
                }
            }

            return response;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

    @Override
    public List<VaccineOwnerResponse> getByPet(long petId) {
        try {
            Pet pet = new Pet();
            pet.setId(petId);

            List<VaccineOwnerResponse> response = new ArrayList<>();

            List<Vaccination> vaccinationList = vaccineRepository.findAllByPetAndStatusIsFalse(pet);
            if (!vaccinationList.isEmpty()) {
                VaccineOwnerResponse vaccineOwnerResponse = new VaccineOwnerResponse();
                VaccineOwnerResponse.PetResponse petResponse = modelMapper.map(pet, VaccineOwnerResponse.PetResponse.class);
                vaccineOwnerResponse.setPet(petResponse);

                for (Vaccination vaccination : vaccinationList) {
                    vaccineOwnerResponse = modelMapper.map(vaccination, VaccineOwnerResponse.class);
                }
                response.add(vaccineOwnerResponse);
            }

            return response;
        } catch (Exception ex) {
            throw new ApplicationException();
        }
    }

}
