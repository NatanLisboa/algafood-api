package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.exception.PermissionNotFoundException;
import com.lisboaworks.algafood.domain.model.Permission;
import com.lisboaworks.algafood.domain.repository.PermissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PermissionRegisterService {
    
    private final PermissionRepository permissionRepository;

    public Permission findOrThrowException(Long permissionId) {
        return permissionRepository.findById(permissionId)
                .orElseThrow(() -> new PermissionNotFoundException(permissionId));
    }

}
