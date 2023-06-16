package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import com.lisboaworks.algafood.domain.exception.UserNotFoundException;
import com.lisboaworks.algafood.domain.model.User;
import com.lisboaworks.algafood.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserRegisterService {

    private final UserRepository userRepository;

    @Transactional
    public User save(User user) {
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
}
