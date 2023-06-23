package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import com.lisboaworks.algafood.domain.exception.UserNotFoundException;
import com.lisboaworks.algafood.domain.model.UserGroup;
import com.lisboaworks.algafood.domain.model.User;
import com.lisboaworks.algafood.domain.model.User;
import com.lisboaworks.algafood.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserRegisterService {

    private final UserRepository userRepository;
    private final UserGroupRegisterService userGroupRegisterService;

    @Transactional
    public User save(User user) {
        userRepository.detach(user); // Remove the entity from JPA persistence context

        Optional<User> userSearchedByEmail = userRepository.findByEmail(user.getEmail());

        if (userSearchedByEmail.isPresent() && !userSearchedByEmail.get().equals(user)) {
            throw new BusinessRuleException(String.format("There is a user already registered with the email '%s'. " +
                    "Please, change the email address and try again.", user.getEmail()));
        }

        return userRepository.save(user);
    }

    public User findOrThrowException(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Transactional
    public void changePassword(Long userId, String currentPassword, String newPassword) {
        User user = this.findOrThrowException(userId);

        if (!user.passwordMatchesWith(currentPassword)) {
            throw new BusinessRuleException("Current password sent does not match with user password");
        }

        user.setPassword(newPassword);
    }

    @Transactional
    public void associateUserGroup(Long userId, Long userGroupId) {
        User user = this.findOrThrowException(userId);
        UserGroup userGroup = userGroupRegisterService.findOrThrowException(userGroupId);

        user.addUserGroup(userGroup);
    }

    @Transactional
    public void disassociateUserGroup(Long userId, Long userGroupId) {
        User user = this.findOrThrowException(userId);
        UserGroup userGroup = userGroupRegisterService.findOrThrowException(userGroupId);

        user.removeUserGroup(userGroup);
    }
    
}
