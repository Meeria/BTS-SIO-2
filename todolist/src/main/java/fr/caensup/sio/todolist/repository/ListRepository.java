package fr.caensup.sio.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.caensup.sio.todolist.models.ToDoList;
import fr.caensup.sio.todolist.models.Utilisateur;

public interface ListRepository extends JpaRepository<ToDoList, Integer> {

	List<ToDoList> findByUtilisateurId(int idUtilisateur);
	
	
	List<ToDoList> findByUtilisateurLogin(String loginUtilisateur);
}
