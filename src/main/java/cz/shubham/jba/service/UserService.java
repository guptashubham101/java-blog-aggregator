package cz.shubham.jba.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import cz.shubham.jba.entity.Blog;
import cz.shubham.jba.entity.Item;
import cz.shubham.jba.entity.Role;
import cz.shubham.jba.entity.User;
import cz.shubham.jba.repository.BlogRepository;
import cz.shubham.jba.repository.ItemRepository;
import cz.shubham.jba.repository.RoleRepository;
import cz.shubham.jba.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	public List<User> findAll(){
	return userRepository.findAll();
	}
	
	public User findOne(int id){
		return userRepository.findOne(id);
	}

	@Transactional
	public User findOneWithBlogs(int id) {
		// TODO Auto-generated method stub
		User user=findOne(id);
		List<Blog> blogs=blogRepository.findByUser(user);
		for(Blog blog:blogs)
		{
			List<Item> items=itemRepository.findByBlog(blog, new PageRequest( 0, 10, Direction.DESC, "publishedDate"));
			blog.setItems(items);
		}
		user.setBlogs(blogs);
		return user;
	}

	public void save(User user) {
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));

		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setRoles(roles);
		userRepository.save(user);
	}
	
	public User findOneWithBlogs(String name) {
		User user = userRepository.findByName(name);
		return findOneWithBlogs(user.getId());
	}
	

	
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	
}


