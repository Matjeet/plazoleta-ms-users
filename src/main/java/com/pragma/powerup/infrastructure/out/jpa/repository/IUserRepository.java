package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByDocumentIdAndRoleId(String documentId, int roleId);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findById(int id);
}
