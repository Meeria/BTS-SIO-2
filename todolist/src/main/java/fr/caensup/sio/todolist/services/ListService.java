package fr.caensup.sio.todolist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.caensup.sio.todolist.models.ToDoList;
import fr.caensup.sio.todolist.repository.ListRepository;

@Service
public class ListService {

	@Autowired
	private ListRepository todoRepo;

	public List<ToDoList> getUserLists(String login) {
		return todoRepo.findByUtilisateurLogin(login);
	}
}
