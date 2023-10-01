package fr.caensup.sio.todolist.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Utilisateur")
@Getter
@Setter

public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String email;
	private String login;
	private String password;
	
	
	@ManyToMany(mappedBy = "collaborateurs")
	private Set<ToDoList> listePartagee = new HashSet<ToDoList>();
	
	@OneToMany(mappedBy="utilisateur", fetch = FetchType.EAGER, cascade =CascadeType.ALL)
	private Set<ToDoList> listes= new HashSet<ToDoList>();

	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getUsername() {
		return login;
	}

	
	public boolean isAccountNonExpired() {
		return true;
	}

	
	public boolean isAccountNonLocked() {
		return true;
	}

	
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}
}



