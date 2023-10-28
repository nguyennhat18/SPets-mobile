package com.example.spetsmobile.restapi;

import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.api.APIService;
import com.example.spetsmobile.itf.PetInterface;
import com.example.spetsmobile.model.request.PetRequest;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.PetResponse;
import com.example.spetsmobile.model.response.StatusEnum;
import com.example.spetsmobile.util.ConstantUtil;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetAPI {

    private PetInterface petInterface = null;

    private APIService apiService;

    public PetAPI(PetInterface petInterface) {
        this.petInterface = petInterface;
        apiService = APIClient.getAPIService();
    }

    public void findAllPetsByOwner(long ownerId) {
        String token = "Bearer " + ConstantUtil.getAccessToken();
        apiService.findAllPetsByOwner(token, ownerId).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<List<PetResponse>>> call, Response<ApiReponse<List<PetResponse>>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    petInterface.onError("PET_ERROR", null);
                } else {
                    petInterface.onSuccess("PET_LIST", apiReponse);
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<List<PetResponse>>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                petInterface.onError("PET_ERROR", error);
            }
        });
    }

    public void petSave(PetRequest request, MultipartBody.Part avatarMul) {
        String token = "Bearer " + ConstantUtil.getAccessToken();

        RequestBody idRequestBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(request.getId()));
        RequestBody ownerIdRequestBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(ConstantUtil.getAuth().getId()));
        RequestBody nameRequestBody = RequestBody.create(MediaType.parse("text/plain"), request.getName() != null ? request.getName() : "");
        RequestBody colorRequestBody = RequestBody.create(MediaType.parse("text/plain"), request.getColor() != null ? request.getColor() : "");
        RequestBody speciesRequestBody = RequestBody.create(MediaType.parse("text/plain"), request.getSpecies() != null ? request.getSpecies() : "");
        RequestBody birthDayRequestBody = RequestBody.create(MediaType.parse("text/plain"), request.getBirthDay() != null ? request.getBirthDay() : "");
        RequestBody weightRequestBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(request.getWeight()));
        RequestBody heightRequestBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(request.getHeight()));
        RequestBody healthRequestBody = RequestBody.create(MediaType.parse("text/plain"), request.getHealth() != null ? request.getHealth() : "");
        RequestBody statusRequestBody = RequestBody.create(MediaType.parse("text/plain"), request.isStatus() ? "1" : "0");


        // Tạo MultipartBody.Part từ hình ảnh (File hoặc Bitmap)
//        File imageFile = new File("path_to_your_image.jpg");
//        RequestBody imageRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
//        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("file", imageFile.getName(), imageRequestBody);

        Map<String, RequestBody> params = new HashMap<>();
        params.put("id", idRequestBody);
        params.put("ownerId", ownerIdRequestBody);
        params.put("name", nameRequestBody);
        params.put("color", colorRequestBody);
        params.put("species", speciesRequestBody);
        params.put("birthDay", birthDayRequestBody);
        params.put("weight", weightRequestBody);
        params.put("height", heightRequestBody);
        params.put("health", healthRequestBody);
        params.put("status", statusRequestBody);

        apiService.petSave(token, params).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<PetResponse>> call, Response<ApiReponse<PetResponse>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    petInterface.onError("PET_ERROR", null);
                } else {
                    if (apiReponse.getStatus().equals(StatusEnum.ERROR)) {
                        Map<String, String> error = apiReponse.getError();
                        petInterface.onError("PET_ERROR", error);
                    } else {
                        petInterface.onSuccess("PET_OBJECT", apiReponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<PetResponse>> call, Throwable t) {
                Map<String, String> error = new HashMap<>();
                error.put("ERROR", t.getMessage());

                petInterface.onError("PET_ERROR", error);
            }
        });
    }

}
