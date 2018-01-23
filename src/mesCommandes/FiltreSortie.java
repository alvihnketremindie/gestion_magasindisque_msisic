package mesCommandes;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class FiltreSortie implements Filter {
	private FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String nom = null;
		// ************************************************************
		// Ce filtre doit s'executer après la servlet 
		// Il vide le caddie du client en cours
		// ************************************************************
		chain.doFilter(request, response);
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpSession session = hrequest.getSession(); 
		nom = (String) session.getAttribute("nomClient");
		if(nom != null) {
			//Le caddy lie au client est vidé
			Depot.lesCaddy.remove(nom);
		}
	}

	public void destroy() {
		this.filterConfig = null;
	}

}
