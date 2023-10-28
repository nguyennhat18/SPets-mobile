package com.example.spetsmobile.api;

import com.example.spetsmobile.model.request.AccountResquest;
import com.example.spetsmobile.model.request.PetRequest;
import com.example.spetsmobile.model.response.AuthResponse;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.PetResponse;
import com.example.spetsmobile.model.response.VaccineResponse;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface APIService {

    // Đăng nhập hệ thống
    //@FormUrlEncoded
    @POST("/api/auth/login")
    Call<ApiReponse<AuthResponse>> login(@Body AccountResquest accountResquest);

    // Đăng ký tài khoản
    @POST("/api/auth/register")
    Call<ApiReponse<AuthResponse>> register(@Field("fullname") String fullname, @Field("username") String username,
                                            @Field("password") String password, @Field("email") String email, @Field("roleId") long roleId);

    // Quên mật khẩu
    @FormUrlEncoded
    @POST("/api/auth/forgotpassword")
    Call<ApiReponse<AuthResponse>> forgotPassword(@Field("username") String username);

    @GET("/api/pets/owner")
    Call<ApiReponse<List<PetResponse>>> findAllPetsByOwner(@Header("Authorization") String bearerToken, @Query("ownerId") long ownerId);

    @GET("/api/vaccines/owner")
    Call<ApiReponse<List<VaccineResponse>>> findAllVaccinesByOwner(@Header("Authorization") String bearerToken, @Query("ownerId") long ownerId);

    @GET("/api/vaccines/pet")
    Call<ApiReponse<List<VaccineResponse>>> findAllVaccinesByPet(@Header("Authorization") String bearerToken, @Query("petId") long petId);


    @Multipart
    @POST("/api/pets/save")
    Call<ApiReponse<PetResponse>> petSave(@Header("Authorization") String bearerToken, @PartMap Map<String, RequestBody> params);

}
