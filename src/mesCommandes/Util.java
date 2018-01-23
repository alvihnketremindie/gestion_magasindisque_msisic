package mesCommandes;

import javax.servlet.http.Cookie;

class Util {

	static public String rechercheCookies(Cookie[] lescookies, String nom)  {
		String reponse = null;
		//  ********************************************************************************************        
		//  recherche si dans le tableau de cookies il en existe un avec le nom donné.
		//       si oui  la valeur de ce cookie est donnée en résultat (mot de passe)
		//  Cette méthode sera appelée par d’autres classes aussi elle est « public » 
		//  et « static » pour pouvoir  l’appeler directement par la classe  "Util.rechercheCookies(..)"
		//  ********************************************************************************************
		if(lescookies != null || nom!= null) {
			for(int i=0; i<lescookies.length; i++) {
				if(lescookies[i].getName().equals(nom)) {
					reponse = lescookies[i].getValue();
					return reponse;
				}
			}
		}
		//  ********************************************************************************************
		return reponse;
	}

	static boolean identique (String recu, String cookie) {
		return ( (cookie != null) && (recu.equals(cookie) ));

	}
}