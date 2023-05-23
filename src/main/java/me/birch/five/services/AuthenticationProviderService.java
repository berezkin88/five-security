package me.birch.five.services;

import lombok.RequiredArgsConstructor;
import me.birch.five.entities.CustomUserDetails;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationProviderService implements AuthenticationProvider {

    private final JpaUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @SuppressWarnings("java:S1301")
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var username = authentication.getName();
        var password = authentication.getCredentials().toString();

        var customUserDetails = userDetailsService.loadUserByUsername(username);

        switch (customUserDetails.user().getAlgorithm()) {
            case BCRYPT -> {
                return checkPassword(customUserDetails, password, "{bcrypt}");
            }
            case SCRYPT -> {
                return checkPassword(customUserDetails, password, "{scrypt}");
            }
        }

        throw new BadCredentialsException("Username or Password is wrong!");
    }

    private Authentication checkPassword(CustomUserDetails userDetails,
                                         String password,
                                         String encryptionPrefix) {

        if (passwordEncoder.matches(password, encryptionPrefix + userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities()
            );
        }

        throw new BadCredentialsException("Username or Password is wrong!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
