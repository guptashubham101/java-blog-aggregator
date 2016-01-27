package cz.shubham.jba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.shubham.jba.entity.Blog;
import cz.shubham.jba.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer>{
	List<Blog> findByUser(User user);
}
