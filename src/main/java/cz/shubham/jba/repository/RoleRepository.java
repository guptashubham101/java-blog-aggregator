package cz.shubham.jba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.shubham.jba.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>
{
	Role findByName(String name);

	}

