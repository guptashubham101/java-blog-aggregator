package cz.shubham.jba.entity;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

@Entity
public class Blog {

	@Id
	@GeneratedValue
	private Integer id;
	@Size(min=1,message="enter the name")
	private String name;
	@Size(min=1,message="enter the name")
	@URL(message="enter the name")
	private String url;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	@OneToMany(mappedBy="blog" , cascade=CascadeType.REMOVE)
	private List<Item> items;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
