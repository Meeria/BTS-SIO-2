package fr.caensup.sio.todolist.models;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ToDoList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Include
	private String name;
	
	@ManyToOne
	private Utilisateur utilisateur; 
	
	@ManyToMany
	private Set<Utilisateur> collaborateurs;
	
	private String[] element;

	@Override
	public String toString() {
		return name;
	}
}


