package com.example.spetsrestapi.model.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class AccountResponse implements Serializable {

    private int userId;
    private String name;
    private String username;
    private String role;
    private boolean status;

}
