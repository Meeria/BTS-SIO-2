package fr.caensup.sio.todolist.controller;

import java.util.Optional;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import fr.caensup.sio.todolist.models.ToDoList;
import fr.caensup.sio.todolist.models.Utilisateur;
import fr.caensup.sio.todolist.repository.UserRepository;
import fr.caensup.sio.todolist.services.UtilisateurService;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("login")
public class UserController {

	
	@Autowired
	private UserRepository uRepository;
	
	@Autowired
	private UtilisateurService userService;
	
	@GetMapping("/user")
	public String index(ModelMap model, HttpSession session) {
		model.put("user", uRepository.findAll());
	return "/users/index";
	}
	
	
	@GetMapping("/user/{id}")
	public ModelAndView showUserAction(@PathVariable int id) {
		Optional<Utilisateur> optUser = uRepository.findById(id);
		if (optUser.isPresent()) {
			return new ModelAndView("/users/show", "user", optUser.get());
		}
		return new ModelAndView("", HttpStatus.NOT_FOUND);
	}
	
	
	
	@GetMapping("/user/create")
	public ModelAndView formAddUser() {
		//return new ModelAndView("/users/formAddUser");
		return new ModelAndView("/users/formAddUser", "user", new Utilisateur());
	}
	
	@PostMapping("/user/create")
	public RedirectView submitCreateUserAction(@ModelAttribute Utilisateur user, RedirectAttributes attrs,
			@RequestParam String myLists) {
		if (myLists != "") {
			for (String listName : myLists.split(",")) {
				ToDoList list = new ToDoList();
				list.setName(listName);
				list.setUtilisateur(user);
				user.getListes().add(list);
			}
		}
		uRepository.save(user);
		attrs.addFlashAttribute("message", "Utilisateur " + user.getLogin() + " ajouté");
		return new RedirectView("/user");
	}
	
	@GetMapping("/users/update/{id}")
	public ModelAndView updateUserAction(@PathVariable int id) {
		Optional<Utilisateur> optUser = uRepository.findById(id);
		if (optUser.isPresent()) {
			return new ModelAndView("/users/formAddUser", "user", optUser.get());
		}
		return new ModelAndView("/users/index", HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/user/update/{id}")
	public RedirectView submitUserAction(@ModelAttribute Utilisateur user) {
		uRepository.save(user);
		return new RedirectView("/users");
	}
	
	@PostMapping("/user/delete")
	public RedirectView deleteConfAction(RedirectAttributes attrs, @RequestParam int id) {
		Optional<Utilisateur> optUser = uRepository.findById(id);
		if (optUser.isPresent()) {
			Utilisateur user = optUser.get();
			uRepository.delete(user);

		}
		return new RedirectView("/user");
	}
	
	@GetMapping("/user/login")
		public String connexion(ModelMap model) {
		return "/users/connexion";
	}
	
	
	@PostMapping("/user/login")
	public RedirectView submitConnectionUserAction(RedirectAttributes attrs, @RequestParam String login, @RequestParam String password, HttpSession session) {
		if (login != "" && password != "") {
			Utilisateur utilisateur = uRepository.findByLoginAndPassword(login, password);
			if (utilisateur != null) {
				session.setAttribute("login", utilisateur);
				return new RedirectView("/user");
				}
			return new RedirectView("/user/login");
			}
	
		return new RedirectView("/user/login");
	} 
	
	@GetMapping("/user/logout")
	public RedirectView logoutAction(HttpSession session, SessionStatus status) {
		
		status.setComplete();
		session.invalidate();
	
		return new RedirectView("/user");
	}
	
	
	
}
	
