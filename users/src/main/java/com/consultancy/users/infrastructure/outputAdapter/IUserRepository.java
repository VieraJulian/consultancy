package com.consultancy.users.infrastructure.outputAdapter;

import com.consultancy.users.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT u FROM UserEntity u WHERE email = :email")
    public Optional<UserEntity> findUserEntityByEmail(@Param("email") String email);

    @Query(value = "SELECT u FROM UserEntity u WHERE name = :username")
    public Optional<UserEntity> findUserEntityByUsername(@Param("username") String username);
}
