/**
 * 
 */
package cz.shubham.jba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.shubham.jba.entity.Blog;
import cz.shubham.jba.entity.User;
import cz.shubham.jba.repository.BlogRepository;
import cz.shubham.jba.repository.ItemRepository;
import cz.shubham.jba.repository.UserRepository;



@Service
@Transactional
@EnableScheduling

	public class BlogService {
		
		@Autowired
		private BlogRepository blogRepository;
		
		@Autowired
		private ItemRepository itemRepository;
		
		@Autowired
		private UserRepository userRepository;
		
		public void save(Blog blog, String name) {
			User user = userRepository.findByName(name);
			System.out.println(blog);
			blog.setUser(user);
			blogRepository.save(blog);
			//saveItems(blog);
		}
		
		  @PreAuthorize("#blog.user.name == authentication.name or hasRole('ROLE_ADMIN')")
			public void delete(@P("blog")Blog blog) {
				blogRepository.delete(blog);
				
			}
		public Blog findOne(int id) {
			return blogRepository.findOne(id);
		}

}


