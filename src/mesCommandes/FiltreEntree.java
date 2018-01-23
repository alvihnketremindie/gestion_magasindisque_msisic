package mesCommandes;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class FiltreEntree implements Filter {
	private FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String nom, motPasse = null;
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		Cookie[] cookies = hrequest.getCookies();
		HttpSession session = hrequest.getSession(); 
		nom = (String)session.getAttribute("nomClient");
		Stock stockDisponible= (Stock) session.getAttribute("stockCourant");
		// ********************************************************************************************        
		// S’il n’existe pas un cookie dont le nom est celui dans la variable de session « nomClient » 
		// (vous pouvez utilisez la méthode «  rechercheCookies » de la classe Util.java)
		// et qu’il n’existe pas  la variable de session « stockCourant » : appel de la servlet "Renseigner" pour s'inscrire 
		// Autrement on continue (chain.doFilter).
		// ********************************************************************************************
		String motPasseCookie = Util.rechercheCookies(cookies, nom);
		if(nom == null || motPasseCookie == null || stockDisponible == null) {
			// Le nom n'est pas present dans le tableau de cookies
			// ou le stock est indisponible
			// ou alors l'attribut "nomClient" n'est pas reconnu au niveau de la session
			hrequest.getRequestDispatcher("/servlet/formulaire?demande=connexion&erreur=filtre").forward(hrequest, hresponse);
		} else {
			chain.doFilter(request, response); 
		}
	}

	public void destroy() {
		this.filterConfig = null;
	}

}
