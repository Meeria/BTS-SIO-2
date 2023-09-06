package messagerie.models;

public class Utilisateurs {
	
	private String nom;
	private String prenom;
	private int age;
	
	/************************************
	*************************************
	************Constructeur*************
	*************************************
	*************************************
	*************************************/
	

	public Utilisateurs(String nom, String prenom, int age) { 
		this.age = age;
		this.prenom = prenom;
		this.nom = nom;
	}
	
	public Utilisateurs(String nom, String prenom) {
		this(nom, prenom, 0);
	}
	
	/************************************
	*************************************
	************* Méthodes **************
	*************************************
	*************************************
	*************************************/
	
	 public void afficher(){
		System.out.println("prenom : "+prenom+"\nnom : "+nom+"\nage : "+age);
	}
	 
	@Override
	public String toString() {
		return prenom+nom;
	}
	
	/************************************
	*************************************
	***** Accesseurs / Mutateurs ********
	*************************************
	*************************************
	*************************************/
	
	public String getPrenom() {
		return this.prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}
		
	
} 
