package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Seller;
import connector.SellerLogin;

/**
 * Servlet implementation class LoginSellerServlet
 */
@WebServlet("/seller/login")
public class LoginSellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginSellerServlet() {
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
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Seller seller = new Seller(email,password);
		SellerLogin sc = new SellerLogin();
		
		Seller result = sc.AuthenticateCredentials(seller); //on success returns object of buyer
		String invalidLoginResponse = "Invalid Email or Password!";
		
		HttpSession session = request.getSession();
		
		if(result != null) {
			//show seller dashboard
			session.setAttribute("seller", result);
			response.sendRedirect(request.getContextPath()+"/seller/dashboard");
		}
		else {
			session.setAttribute("InvalidLoginResponse",invalidLoginResponse);
			response.sendRedirect(request.getContextPath()+"/");
		}
	}

}
