package com.mehmetpekdemir.jwt.repository;

import com.mehmetpekdemir.jwt.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author MEHMET PEKDEMIR
 * @since 1.0
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

}
