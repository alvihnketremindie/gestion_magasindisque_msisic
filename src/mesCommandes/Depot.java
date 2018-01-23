package mesCommandes;
import java.io.PrintWriter;
import java.util.*;
import java.sql.*;

public class Depot {
	public static TreeMap<String, ArrayList<String> > lesCaddy = new TreeMap<String, ArrayList<String> >();

	static public  void afficherContenuCaddy (ArrayList<String> caddy, PrintWriter out, String repertoire)
	{
		out.println("<table border=1>");
		Disque leDisque;
		if(caddy != null) {
			Iterator <String> iter = caddy.iterator();
			while (iter.hasNext()){
				leDisque = Stock.trouveDisque((String)iter.next());
				out.println( "<tr> <td>" + leDisque.getTitre() + "  ,  " + leDisque.getNom() + " ,  "  + leDisque.getPrix() + " Euros  ,  Année :" + leDisque.getAnnee()  +"</td>");
				out.println("<td> <IMG SRC= '" + repertoire + "/images/" + leDisque.getImage() +"'  BORDER=0> </A><br> </td> "); 
			}
		}
		out.println("</tr> </table>");
	}

	static public  void afficherDisquesEnVente (Stock leStock,  PrintWriter out, String repertoire)
	{
		Disque leDisque;
		Iterator <String>  iter = leStock.getListeDisques().iterator();
		out.println("<table border=1>");
		while (iter.hasNext()){
			leDisque = Stock.trouveDisque((String)iter.next());
			out.println( "<tr> <td>" + leDisque.getTitre() + "  ,  " + leDisque.getNom() + " ,  "  + leDisque.getPrix() + " Euros  ,  Année :" + leDisque.getAnnee()  +"</td>");
			out.println("<td> <IMG SRC= '" + repertoire + "/images/" + leDisque.getImage() +"'  BORDER=0> </A><br> </td> "); 
			out.println("<td><A HREF='commande?code=" + leDisque.getReference()+ "&ordre=ajouter'>"); 
			out.println("<IMG SRC='" + repertoire + "/images/panier.gif\' BORDER=0></A><br> </td> </tr>");
		}
		out.println("</tr> </table>");
	}

	static public  void afficherDisquesDansBase ( ResultSet  rs, PrintWriter out, String repertoire)
	{
		Disque leDisque;
		try {
			out.println("<table border=1>");
			while (rs.next()) {          
				//out.println( rs.getString("article") + "<br>" );  
				leDisque = Stock.trouveDisque(rs.getString("nomarticle"));   
				out.println( "<tr> <td>" + leDisque.getTitre() + "  ,  " + leDisque.getNom() + " ,  "  + leDisque.getPrix() + " Euros  ,  Année :" + leDisque.getAnnee()  +"</td>");
				out.println("<td> <IMG SRC= '" + repertoire + "/images/" + leDisque.getImage() +"'  BORDER=0> </A><br> </td> "); 
			} 
			out.println("</tr></table>");
		}         
		catch (Exception E) {   
			out.println("erreur base");         
			System.out.println(" - probeme ajoute " + E.getClass().getName() );
			E.printStackTrace();
		}	
	}

	/*
	 * Methode d'affichage de la facture
	 */
	static public  void afficherFactureCaddy (ArrayList<String> caddy, PrintWriter out, String repertoire)
	{
		Disque leDisque;
		int prix_total = 0;
		out.println("<table border=1>");
		if(caddy != null) {
			out.println("<thead><tr>\r\n" + 
					"      <th>Titre</th>\r\n" + 
					//"      <th>Auteur</th>\r\n" + 
					//"      <th>Icone</th>\r\n" + 
					//"      <th>Année</th>\r\n" +  
					"      <th>Prix</th>\r\n" +  
					"</tr></thead>\r\n");
			out.println("<tbody>\r\n");
			Iterator <String> iter = caddy.iterator();
			while (iter.hasNext()){
				out.println("<tr>\r\n");
				leDisque = Stock.trouveDisque((String)iter.next());
				out.println("<td>" + leDisque.getTitre() +"</td>\r\n"+
						//"<td>" + leDisque.getNom() +"</td>\r\n"  +
						//"<td> <IMG SRC= '" + repertoire + "/images/" + leDisque.getImage() +"'  BORDER=0> </A><br> </td>\r\n"+
						//"<td>" + leDisque.getAnnee()  +"</td>\r\n" +
						"<td>" + leDisque.getPrix() +" Euros </td>\r\n");
				out.println("</tr>\r\n");
				prix_total += (int) leDisque.getPrix();
			}
			out.println("</tbody>");
			out.println("<tfoot><tr>\r\n" + 
					"      <td>Total</td>\r\n" + 
					"      <td>"+prix_total+" Euros </td>\r\n" + 
					"</tr></tfoot>\r\n");
		}
		out.println("</table>");
	}

}