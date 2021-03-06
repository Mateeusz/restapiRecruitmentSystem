package pl.mateuszharazin.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.mateuszharazin.restapi.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    public Role findByRoleName(String roleName);
}