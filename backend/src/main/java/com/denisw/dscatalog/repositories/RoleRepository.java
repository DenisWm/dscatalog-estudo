package com.denisw.dscatalog.repositories;

import com.denisw.dscatalog.entities.Role;
import com.denisw.dscatalog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
