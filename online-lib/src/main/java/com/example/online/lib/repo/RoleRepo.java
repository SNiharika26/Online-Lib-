package com.example.online.lib.repo;
import java.util.Optional;

import com.example.online.lib.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RoleRepo {
    public interface RoleRepository extends JpaRepository<Role, Integer> {
        Optional<Role> findByName(String name);
    }
}
