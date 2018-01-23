package mesCommandes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GererComptes extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomRecu, motPasseRecu;
		String inscription, connexion;
		String nomCookie, motPasseCookie;
		Cookie[] cookies ;
		//  ********************************************************************************************        
		//  initialisation des différents paramètres possibles
		//  ********************************************************************************************  
		nomRecu = request.getParameter("nom").trim();  
		motPasseRecu = request.getParameter("motdepasse");
		inscription = request.getParameter("inscrire");
		connexion = request.getParameter("connecter");
		cookies = request.getCookies();
		//   cas 1    paramètre inscrire  présent
		//    Si les informations passées sont acceptables (nom et mot de passe > 4 caractères)
		//      un cookie est créé avec comme nom, le nom passé et comme valeur, le mot de passe passé
		//      puis appel à la servlet "Renseigner" pour la connexion avec les paramètres inscriptionFaite à true et le nom passé
		//    sinon appel à la servlet "Renseigner" pour l'inscription avec le paramètre erreur
		//  ********************************************************************************************
		if(inscription != null) {
			if(nomRecu != null && nomRecu.length() >= 4 && motPasseRecu != null && motPasseRecu.length() >= 4) {
				//Informations acceptables
				nomCookie = nomRecu;
				motPasseCookie = motPasseRecu;
				Cookie cookie = new Cookie(nomCookie, motPasseCookie);
				cookie.setPath("/");
				response.addCookie(cookie);
				request.getRequestDispatcher("/servlet/formulaire?demande=connexion&inscriptionFaite=true&nom="+nomCookie).forward(request, response);
			} else if(nomRecu == null || nomRecu.length() < 4) {
				//Erreur au niveau du nom
				request.getRequestDispatcher("/servlet/formulaire?demande=inscription&erreur=nom").forward(request, response);
			} else if(motPasseRecu == null || motPasseRecu.length() < 4) {
				//Erreur au niveau du mot de passe
				request.getRequestDispatcher("/servlet/formulaire?demande=inscription&erreur=motdepasse").forward(request, response);
			} else {
				//Erreur inconnu
				request.getRequestDispatcher("/servlet/formulaire?demande=inscription&erreur=inconnu").forward(request, response);
			}
		}
		//  Cas 2    paramètre connecter  présent
		//  si le parametre nom est absent appel à la servlet Renseigner avec demande = connexion
		//  autrement, on vérifie que le nom et le mot de passe passés se trouvent dans un cookie
		//  si oui  appel à la servlet "EntreeMagasinDisque" avec comme paramètre  le nom passé
		//  sinon appel à la servlet "Renseigner" pour la connexion avec le paramètre erreur
		//  ********************************************************************************************            
		else if(connexion != null) {
			nomCookie = nomRecu;
			motPasseCookie = Util.rechercheCookies(cookies, nomCookie);
			if(motPasseCookie == null) {
				//Le nom n'est pas present dans le tableau de cookies
				request.getRequestDispatcher("/servlet/formulaire?demande=connexion&erreur=nom").forward(request, response);
			} else if(!motPasseCookie.equals(motPasseRecu)) {
				//Le mot de passe pour cet utilisateur n'est pas le bon
				request.getRequestDispatcher("/servlet/formulaire?demande=connexion&erreur=motdepasse").forward(request, response);
			} else {
				//Pas d'erreur a signaler. On appelle la servlet "EntreeMagasinDisque"
				response.sendRedirect("http://localhost:8080/monMagasin/servlet/entre?nom="+nomCookie);
			}
		}
		//Erreur sur les parametres connexion et inscription (Cas non prevu)
		else {
			response.sendRedirect("http://localhost:8080/monMagasin/");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{ 
		doGet(request, response);
	}   

}
