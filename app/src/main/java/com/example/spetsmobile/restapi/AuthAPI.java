package com.example.spetsmobile.restapi;

import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.api.APIService;
import com.example.spetsmobile.itf.AuthInterface;
import com.example.spetsmobile.model.request.AccountResquest;
import com.example.spetsmobile.model.response.AuthResponse;
import com.example.spetsmobile.model.response.ApiReponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthAPI {

    private AuthInterface authInterface = null;

    private APIService apiService;

    public AuthAPI(AuthInterface authInterface) {
        this.authInterface = authInterface;
        apiService = APIClient.getAPIService();
    }

    public void login(String username, String password) {
        apiService.login(new AccountResquest(username, password)).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ApiReponse<AuthResponse>> call, Response<ApiReponse<AuthResponse>> response) {
                ApiReponse apiReponse = response.body();
                if (apiReponse == null) {
                    authInterface.onError("AUTH_ERROR", null);
                } else {
                    authInterface.onSuccess("AUTH_SUCCESS", apiReponse);
                }
            }

            @Override
            public void onFailure(Call<ApiReponse<AuthResponse>> call, Throwable t) {
                authInterface.onError("AUTH_ERROR", t.getMessage());
            }
        });
    }

}
