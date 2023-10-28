package com.example.spetsrestapi.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotEmpty(message = "Username is required!")
    private String username;
    @NotEmpty(message = "AccessToken is required!")
    private String accessToken;

}