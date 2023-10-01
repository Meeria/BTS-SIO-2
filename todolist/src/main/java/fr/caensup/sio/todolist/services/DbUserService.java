package fr.caensup.sio.todolist.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.caensup.sio.todolist.models.Utilisateur;
import fr.caensup.sio.todolist.repository.UserRepository;

public class DbUserService implements UserDetailsService {

	@Autowired
	private UserRepository uRepo;
	
	@Autowired 
	private PasswordEncoder pEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Utilisateur> optUser=uRepo.findByLogin(username);
		if(optUser.isPresent()) {
			Utilisateur user=optUser.get();
			return new User(username,user.getPassword(),getGrantedAuthorities());
		}
		return null;
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(){
		return new ArrayList<GrantedAuthority>();
	}
	
	public void encodePassword(Utilisateur user) {
		user.setPassword(pEncoder.encode(user.getPassword()));
	}

}
