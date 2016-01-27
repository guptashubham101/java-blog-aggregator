package cz.shubham.jba.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import cz.shubham.jba.entity.Blog;
import cz.shubham.jba.entity.Item;
import cz.shubham.jba.entity.Role;
import cz.shubham.jba.entity.User;
import cz.shubham.jba.repository.BlogRepository;
import cz.shubham.jba.repository.ItemRepository;
import cz.shubham.jba.repository.RoleRepository;
import cz.shubham.jba.repository.UserRepository;

@Transactional
@Service
public class InitDbService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@PostConstruct
	public void init()
	{
		Role roleUser=new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
		
		Role roleAdmin=new Role();
		roleUser.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		List<Role> roles=new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		
		User userAdmin=new User();
		userAdmin.setEnabled(true);
		userAdmin.setName("admin");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);
		
		Blog blogJavavids=new Blog();
		blogJavavids.setName("JavaVids");
		blogJavavids.setUrl("url");
		blogJavavids.setUser(userAdmin);
		blogRepository.save(blogJavavids);
		
		Item item1=new Item();
		item1.setBlog(blogJavavids);
		item1.setTitle("first");
		item1.setLink("");
		item1.setPublishedDate(new Date());
		
		Item item2=new Item();
		item2.setBlog(blogJavavids);
		item2.setTitle("second");
		item2.setLink("");
		item2.setPublishedDate(new Date());
		itemRepository.save(item1);
		itemRepository.save(item2);
		
		
		
	}
}

