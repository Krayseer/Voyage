package ru.krayseer.voyage.security;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.krayseer.voyage.domain.responses.AuthResponse;

import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
@AllArgsConstructor
public class VoyageUserDetails implements UserDetails {

    private AuthResponse authResponse;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(authResponse.getRole());
    }

    @Override
    public String getPassword() {
        return authResponse.getPassword();
    }

    @Override
    public String getUsername() {
        return authResponse.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
