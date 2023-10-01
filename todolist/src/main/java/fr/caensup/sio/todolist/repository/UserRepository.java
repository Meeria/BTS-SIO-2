package fr.caensup.sio.todolist.repository;
import fr.caensup.sio.todolist.models.Utilisateur;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<Utilisateur, Integer>{

	public List<Utilisateur> findByLoginLikeOrEmailLike(String login, String email);
	
	public Utilisateur findByLoginAndPassword(String login, String password);
	
	public Optional<Utilisateur> findByLogin(String login);
	
}
