package mesCommandes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Renseigner extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String demande, nomRecu, erreur, inscriptionFaite;
		//  ********************************************************************************************        
		//  Initialisation des différents paramètres possibles
		//  ********************************************************************************************    
		demande = request.getParameter("demande");
		nomRecu = request.getParameter("nom");
		erreur = request.getParameter("erreur");
		inscriptionFaite = request.getParameter("inscriptionFaite");
		//  ********************************************************************************************           
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//  ********************************************************************************************            		 
		//   Cas 1   inscription       paramètre demande = "inscription"        
		//   Si le paramètre "erreur" est présent, informez que les informations transmises ne sont pas acceptables.
		//   Demandez des informations (nom, mot de passe, email, téléphone) par un formulaire
		//   et rappel de la  servlet   "GererComptes" avec ces informations en paramètre pour enregistrer ces valeurs
		//   le paramètre inscrire est envoyé avec comme valeur inscrire (bouton submit)
		//  ********************************************************************************************       
		if(demande.equals("inscription")) {
			out.println("<html>");
			//Une erreur est survenu lors de l'inscription
			if(erreur != null) {
				//out.println("<p><pre>Les informations transmises ne sont pas acceptables.</pre></p>");
				if(erreur.equals("nom")) {
					//L'erreur est en rapport avec le nom de l'utilisateur
					out.println("<p><pre>Le nom est obligatoire et doit faire plus de 4 caractères.</pre></p>");
				} else if(erreur.equals("motdepasse")) {
					//L'erreur a un lien avec le mot de passe
					out.println("<p><pre>Le mot de passe est obligatoire et doit faire plus de 4 caractères.</pre></p>");
				} else if(erreur.equals("filtre")) {
					//L'erreur a un lien avec le filtre d'entree de la servlet
					out.println("<p><pre>Il est nesessaire de se reconnecter avant de pouvoir continuer SVP.</pre></p>");
				}

			}
			//Formulaire d'inscription
			out.println("<form action='voirCookies' method='GET'>");
			out.println("<div class='container'>");
			out.println("<label for='nom'><b>Nom </b></label>");
			out.println("<input type='text' size='20' name='nom' placeholder='Enter votre nom'></br>");
			out.println("<label for='motdepasse'><b>Mot de passe </b></label>");
			out.println("<input type='password' size='20' name='motdepasse'></br>");
			out.println("<label for='email'><b>Email </b></label>");
			out.println("<input type='email' size='20' name='email' placeholder='user@example.com'></br>");
			out.println("<label for='phone'><b>Telephone </b></label>");
			out.println("<input type='tel' size='20' name='phone'></br>");
			out.println("<div class='clearfix'>");
			out.println("<input type='submit' value='inscrire' name='inscrire'></br>");
			out.println("</div>");
			out.println("</div>");
			out.println("</form>");
			out.println("</html>");
		}
		//  ********************************************************************************************            		 
		//   cas 2   connection     paramètre demande = "connexion"        
		//       Si le paramètre "erreur" est présent, informez que les informations transmises ne sont pas acceptables,
		//     et sortir la valeur de erreur
		//       Si le paramètre inscriptionFaite est présent, il vient de s'inscrire, on rajoute un message comme quoi 
		//    l'inscription  s'est bien réalisée et dans le formulaire on initialise le nom avec le nom 
		//       reçu en paramètre.
		//   Demandez des informations (nom, mot de passe) par un formulaire
		//   et rappel de la  servlet   "GererComptes" avec ces informations en paramètres pour vérifier ces valeurs
		//   le paramètre connecter est envoyé avec comme valeur connecter (bouton submit)
		//  ********************************************************************************************         
		else if(demande.equals("connexion")) {
			out.println("<html>");
			//Une erreur est survenu lors de la connexion
			if(erreur != null) {
				out.println("<p><pre>Un probleme est survenu lors de la vérification de vos informations.</pre></p>");
				if(erreur.equals("nom")) {
					out.println("<p><pre>Le nom indiqué n'est pas le bon.</pre></p>");
				} else if(erreur.equals("motdepasse")) {
					//L'erreur a un lien avec la correspondance mot de passe et nom d'utilisateur
					out.println("<p><pre>Le mot de passe fournit n'est pas le bon.</pre></p>");
				} else if(erreur.equals("filtre")) {
					//L'erreur a un lien avec le filtre d'entree de la servlet
					out.println("<p><pre>Il est nesessaire de se reconnecter avant de pouvoir continuer SVP.</pre></p>");
				}
				//En cas d'erreur la tentative d'inscription est annulée
				inscriptionFaite = null;
			}
			//Formulaire de connexion
			out.println("<form action='voirCookies' method='GET'>");
			out.println("<div class='container'>");
			if(inscriptionFaite != null && nomRecu != null) {
				//L'inscription a été faite : Le nom est pré-renseigné dans le formulaire
				out.println("<p><pre>Votre inscription a ete prise en compte. Vous pouvez vous connecter</pre></p>");
				out.println("<label for='nom'><b>Nom </b></label>");
				out.println("<input type='text' size='20' name='nom' value='"+nomRecu+"'></br>");
			} else {
				out.println("<label for='nom'><b>Nom </b></label>");
				out.println("<input type='text' size='20' name='nom' placeholder='Enter votre nom'></br>");
			}
			out.println("<label for='motdepasse'><b>Mot de passe </b></label>");
			out.println("<input type='password' size='20' name='motdepasse'></br>");
			out.println("<div class='clearfix'>");
			out.println("<input type='submit' value='connecter' name='connecter'></br>");
			out.println("</div>");
			out.println("</div>");
			out.println("</form>");
			out.println("</html>");
		}
		//Erreur sur les parametres connexion et inscription (Cas non prevu). L'utilisateur est rédirigé sur la servlet de départ
		else {
			response.sendRedirect("http://localhost:8080/monMagasin/");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{ 
		doGet(request, response);
	}
}
