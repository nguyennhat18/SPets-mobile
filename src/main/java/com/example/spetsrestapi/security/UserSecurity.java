package com.example.spetsrestapi.security;

import com.example.spetsrestapi.model.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserSecurity implements UserDetails {

    private Account account;

    public UserSecurity(Account account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + account.getRole().toUpperCase()));

        return authorities;
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return account.isStatus();
    }

    @Override
    public boolean isAccountNonLocked() {
        return account.isStatus();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return account.isStatus();
    }

    @Override
    public boolean isEnabled() {
        return account.isStatus();
    }

    public Account getAccount() {
        return account;
    }

}