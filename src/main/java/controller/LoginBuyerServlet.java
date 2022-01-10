package controller;

import model.Buyer;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import connector.BuyerLogin;

/**
 * Servlet implementation class LoginBuyerServlet
 */
@WebServlet("/buyer/login")
public class LoginBuyerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginBuyerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Buyer buyer = new Buyer(email,password);
		BuyerLogin bc = new BuyerLogin();
		
		//System.out.println(buyer.toString());
		
		Buyer result = bc.AuthenticateCredentials(buyer); //on success returns object of buyer
		String invalidLoginResponse = "Invalid Email or Password!";
		
		HttpSession session = request.getSession();
		
		if(result != null) {
			//show buyer dashboard
			session.setAttribute("buyer", result);
			response.sendRedirect(request.getContextPath()+"/buyer/dashboard");
		}
		else {
			session.setAttribute("InvalidLoginResponse",invalidLoginResponse);
			response.sendRedirect(request.getContextPath()+"/");
		}
	}

}
