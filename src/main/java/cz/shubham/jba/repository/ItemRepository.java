package cz.shubham.jba.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.shubham.jba.entity.Blog;
import cz.shubham.jba.entity.Item;


public interface ItemRepository extends JpaRepository<Item, Integer> {


List<Item> findByBlog(Blog blog, Pageable pageable);
}
