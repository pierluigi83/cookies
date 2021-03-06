package newPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * Servlet implementation class Roberto
 */
@WebServlet("/roberto")
public class Roberto extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public StringBuilder html(String name, String surname, String email)
	{
		StringBuilder html = new StringBuilder();
		html.append("<!doctype html><html>");
		html.append("<head><title>Roberto</title></head>");
		html.append("<body><h1>Dati personali</h1>");
		html.append("<p><ul><li>");
		html.append(name);
		html.append("</li>");
		html.append("<li>" + surname);
		html.append("</li>");
		html.append("<li>" + email);
		html.append("</li></ul></p>");
		html.append("</body></html>");
		return html;
	}
	
	public Map<String,String> caricaDati(HttpServletRequest request)
	{
		Cookie[] cookies = request.getCookies();
		Map<String,String> dati = new HashMap<String,String>();
		if (cookies != null)
		{
			for (Cookie bisc : cookies)
			{
				if ( bisc.getName().equals("name") )
				{
					dati.put("name", bisc.getValue());
				}
				if (bisc.getName().equals("surname"))
				{
					dati.put("surname", bisc.getValue());
				}
				if (bisc.getName().equals("email"))
				{
					dati.put("email", bisc.getValue());
				}
			}
		}
		return dati;
	}
	
	public static String checkData(String name, String name2, HttpServletResponse response,Map<String,String> dati)
	{
		if (name == null || name.trim().isEmpty())
		{
			return dati.get(name2);
		}
		else
		{
			Cookie biscotto = new Cookie(name2,name);
			response.addCookie(biscotto);
			return name;
		}
	}

	/**
	 * @see HttpServlet #doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		Map<String,String> dati = new HashMap<String,String>();
		
		dati = caricaDati(request);
		name = checkData(name,"name",response,dati);
		surname = checkData(surname,"surname",response,dati);
		email = checkData(email,"email",response,dati);
		
		PrintWriter out = response.getWriter();
		out.println(html(name,surname,email).toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
