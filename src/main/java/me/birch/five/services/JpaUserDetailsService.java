package me.birch.five.services;

import lombok.RequiredArgsConstructor;
import me.birch.five.entities.CustomUserDetails;
import me.birch.five.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findUserByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        return new CustomUserDetails(user);
    }
}
