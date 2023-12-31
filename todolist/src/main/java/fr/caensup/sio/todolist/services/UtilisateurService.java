package fr.caensup.sio.todolist.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import fr.caensup.sio.todolist.exception.InvalidUserException;
import fr.caensup.sio.todolist.exception.UserNotFoundException;
import fr.caensup.sio.todolist.models.Utilisateur;
import fr.caensup.sio.todolist.repository.UserRepository;

@Service
public class UtilisateurService {
		
		@Autowired
		private UserRepository utilisateurRepo;
		
		
		/*public Utilisateur findByLogin(String login) {
			Optional<Utilisateur> opt= utilisateurRepo.findByLogin(login);
			if(opt.isPresent()) {
				return opt.get();			
			}
			return null;
		}*/
		
		
		@Autowired
		private UserDetailsService uDetailService;

		public Utilisateur findByLogin(String login) throws InvalidUserException {
			Optional<Utilisateur> opt = utilisateurRepo.findByLogin(login);
			if (opt.isPresent()) {
				return opt.get();
			}
			throw new InvalidUserException();
		}

		public Utilisateur getById(int id) throws UserNotFoundException {
			Optional<Utilisateur> opt = utilisateurRepo.findById(id);
			if (opt.isPresent()) {
				return opt.get();
			}
			throw new UserNotFoundException("Utilisateur d'identifiant " + id + " non trouvé !");
		}
		
		public Utilisateur createUser(String login,String password) {
			Utilisateur u=new Utilisateur();
			u.setLogin(login);
			u.setEmail(login+"@caensup.fr");
			u.setPassword(password);
			((DbUserService)uDetailService).encodePassword(u);
			return utilisateurRepo.save(u);
		}
		
			
}