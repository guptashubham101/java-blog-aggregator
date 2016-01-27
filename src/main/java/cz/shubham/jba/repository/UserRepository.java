package cz.shubham.jba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.shubham.jba.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByName(String name);
	

}
