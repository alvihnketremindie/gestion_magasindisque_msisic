package mesCommandes;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
public class EntreeMagasinDisque extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String nomRecu;
		//  ********************************************************************************************        
		//  Créez deux variables de session : « nomClient » qui a pour valeur le nom de l’utilisateur  
		//  et « stockCourant »  qui a pour valeur une instance de la classe Stock, 
		//  et appeler la servlet  AfficherLesDisques.java
		//  ********************************************************************************************
		nomRecu = request.getParameter("nom");
		Stock stockCourant = new Stock();
		HttpSession session = request.getSession(true);    
		// Creation des variables de sessions 
		session.setAttribute("nomClient",nomRecu);
		session.setAttribute("stockCourant",stockCourant);
		//Appel de la servlet AfficherLesDisques.java
		request.getRequestDispatcher("/servlet/achat").forward(request, response);
		//  ********************************************************************************************
	}

	public void doPost(HttpServletRequest request,  HttpServletResponse response) throws IOException, ServletException
	{ 
		doGet(request, response);
	}
}
