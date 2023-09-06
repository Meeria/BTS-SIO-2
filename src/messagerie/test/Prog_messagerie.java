package messagerie.test;

import messagerie.models.Utilisateurs;

public class Prog_messagerie {

	public static void main(String[] args) {

		Utilisateurs valentin=new Utilisateurs("PB", "Valentin", 0);
		valentin.afficher();
		System.out.println(valentin);
		
		Utilisateurs joshua=new Utilisateurs("Dahlke", "Joshua");
		System.out.println(joshua);
	}

}
