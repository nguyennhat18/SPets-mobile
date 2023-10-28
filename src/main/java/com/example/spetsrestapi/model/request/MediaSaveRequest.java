package com.example.spetsrestapi.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import java.io.Serializable;

@Setter
@Getter
public class MediaSaveRequest implements Serializable {

    private Long id;

    private Long petId;

    @NotBlank(message = "Title is required!")
    private String title;

    @NotBlank(message = "Record Type is required!")
    private String recordType;

    private MultipartFile pathMul;

    private String description;

    private boolean status;

}
