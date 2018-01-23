package mesCommandes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Facturation
 */

public class Facturation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Facturation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession(); 
		String nom = (String) session.getAttribute("nomClient");
		ArrayList<String> leCaddy = (ArrayList<String>) session.getAttribute("commandeCourante");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>  votre facture </title>");
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' >");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");
		out.println("<h3>" + nom.toUpperCase()  + "  Voici la facture de votre commande" + "</h3>");
		Depot.afficherFactureCaddy(leCaddy, out, request.getContextPath());
		out.println("</body>");
		out.println("</html>");
		session.invalidate();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
