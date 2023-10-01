package fr.caensup.sio.todolist.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import fr.caensup.sio.todolist.models.ToDoList;
import fr.caensup.sio.todolist.models.Utilisateur;
import fr.caensup.sio.todolist.repository.ListRepository;

@Controller
public class ListController {

	@Autowired
	private ListRepository lRepository;
	
	@GetMapping("/list")
	public String index(ModelMap model) {
		model.put("list", lRepository.findAll());
	return "/list/allList";
	}
	
	
	@GetMapping("/list/create")
	public ModelAndView formAddList() {
		return new ModelAndView("/list/createList");
	}
	
	/*@PostMapping("/list/create")
	public RedirectView submitFormList(@ModelAttribute ToDoList list) {
		lRepository.save(list);
		return new RedirectView("/user");
	}*/
	
	//--------------------------------------------------------------------	
	
	/*@PostMapping("/list/create")
	public RedirectView submitFormList(@ModelAttribute ToDoList list, Principal principal) {
	    String username = principal.getName(); // Obtenez le nom d'utilisateur de l'utilisateur connecté

	    Utilisateur utilisateur = uRepository.findByUsername(username);

	    if (utilisateur == null) {
	        // Gérer le cas où l'utilisateur n'existe pas
	        // Peut-être renvoyer un message d'erreur ou rediriger vers une page d'erreur
	        return new RedirectView("/error");
	    }

	    // Assurez-vous d'associer l'utilisateur à la ToDoList
	    list.setUtilisateur(utilisateur);

	    // Maintenant, vous pouvez enregistrer la ToDoList
	    lRepository.save(list);

	    return new RedirectView("/user");
	}*/

	//-----------------------------------------------------------------//
	
	@PostMapping("/list/delete")
	public RedirectView deleteList(@RequestParam int id, @RequestParam int idUtilisateur) {
		lRepository.deleteById(id);
		Optional<ToDoList> liste = lRepository.findById(id);
		if (liste.isPresent()) {
			lRepository.delete(liste.get());
		}
		return new RedirectView("/list/"+idUtilisateur);
	}
	
	//----------------------------------------------//
	
	@GetMapping("/list/{id}")
	public String listUser(@PathVariable int id, ModelMap model) {
	    List<ToDoList> listesUtilisateur = lRepository.findByUtilisateurId(id);
	    model.put("list", listesUtilisateur);
	    model.put("idUtilisateur", id);
	    return "/list/userList";
	}
	
	
	
	
	
	

	
	
}
