package com.online_marketplace.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.online_marketplace.exception.UserAlreadyExistsException;
import com.online_marketplace.exception.UserNotFoundException;
import com.online_marketplace.model.LocalUser;
import com.online_marketplace.repository.RoleRepository;
import com.online_marketplace.repository.UserRespository;
import com.online_marketplace.request.RegisteRequestBody;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRespository userRespository;
    private final PasswordEncoder encryptionService;
    private final RoleRepository roleRepository;

    public LocalUser resgisterUSer(RegisteRequestBody registeRequestBody) throws UserAlreadyExistsException {
        if (userRespository.findByEmailIgnoreCase(registeRequestBody.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("user already exist with email " + registeRequestBody.getEmail());
        }

        LocalUser user = new LocalUser();
        user.setCreateAt(new Date());
        user.setEmail(registeRequestBody.getEmail());
        user.setFirstName(registeRequestBody.getFirstName());
        user.setLastName(registeRequestBody.getLastName());
        user.setPassword(encryptionService.encode(registeRequestBody.getPassword()));
        user.setRole(roleRepository.findByName(registeRequestBody.getRole()));
        return userRespository.save(user);
    }

    // public String loginUser(LoginRequestBody loginRequestBody){
    //     Optional<LocalUser> opuser = userRespository.findByEmailIgnoreCase(loginRequestBody.getEmail());
    //     if (!opuser.isPresent()) {
    //         return null;
    //     }

    //     LocalUser user = opuser.get();
    //     if (!encryptionService.matches(loginRequestBody.getPassword(), user.getPassword())) {
    //         return null;
    //     }

    //     return jwtService.generateToken(user, null);
    // }

    public List<LocalUser> findAllUsers() {
        return userRespository.findAll();
    }

    public LocalUser findUserById(Long id) {
        return userRespository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by Id " + id + " was not found"));
    }

    public LocalUser updateUser(LocalUser updatedUser) {
        Optional<LocalUser> opUser = userRespository.findById(updatedUser.getUserId());
        if (opUser.isPresent()) {
            LocalUser user = opUser.get();
            user.setFirstName(updatedUser.getFirstName() != null ? updatedUser.getFirstName() : user.getFirstName());
            user.setLastName(updatedUser.getLastName() != null ?  updatedUser.getLastName() : user.getLastName());
            user.setEmail(updatedUser.getEmail() != null ? updatedUser.getEmail() : user.getEmail());
            user.setPassword(updatedUser.getPassword() != null ? encryptionService.encode(updatedUser.getPassword()): user.getPassword());
          return userRespository.save(user);
        }
        return null;
    }

    public void deleteUserById(Long id) {
        userRespository.deleteById(id);
    }

}
