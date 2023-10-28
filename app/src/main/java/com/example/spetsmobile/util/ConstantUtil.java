package com.example.spetsmobile.util;

import com.example.spetsmobile.model.response.AuthResponse;
import com.example.spetsmobile.model.response.PetResponse;
import com.example.spetsmobile.model.response.VaccineResponse;

public class ConstantUtil {

    private static AuthResponse auth = null;
    private static PetResponse petResponse = null;
    private static VaccineResponse vaccineResponse = null;
    private static String ACCESS_TOKEN = "";
    private static String REFRESH_TOKEN = "";

    public static String getAccessToken() {
        return ACCESS_TOKEN;
    }

    public static void setAccessToken(String accessToken) {
        ACCESS_TOKEN = accessToken;
    }

    public static String getRefreshToken() {
        return REFRESH_TOKEN;
    }

    public static void setRefreshToken(String refreshToken) {
        REFRESH_TOKEN = refreshToken;
    }

    public static AuthResponse getAuth() {
        return auth;
    }

    public static void setAuth(AuthResponse auth) {
        ConstantUtil.auth = auth;
    }

    public static PetResponse getPetResponse() {
        return petResponse;
    }

    public static void setPetResponse(PetResponse petResponse) {
        ConstantUtil.petResponse = petResponse;
    }

    public static VaccineResponse getVaccineResponse() {
        return vaccineResponse;
    }

    public static void setVaccineResponse(VaccineResponse vaccineResponse) {
        ConstantUtil.vaccineResponse = vaccineResponse;
    }
}
