package com.example.spetsrestapi.service;

import com.example.spetsrestapi.model.entity.Media;
import com.example.spetsrestapi.model.request.MediaSaveRequest;
import com.example.spetsrestapi.model.response.MediaSaveResponse;

import java.util.List;

public interface MediaService {

    List<Media> getAllByPetOrderByUpdatedAtDesc(long petId);

    MediaSaveResponse save(MediaSaveRequest request);

}
