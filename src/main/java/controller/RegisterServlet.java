package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
import connector.*;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/register.jsp");
		dispatcher.forward(request, response);
		
		System.out.println("Serving Register page!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("received registration info!");
		
		String user = request.getParameter("user");
		System.out.println(user);
		
		HttpSession session = request.getSession();
		
		if(user.equals("buyer")) {
			Buyer buyer = new Buyer();
			buyer.setName(request.getParameter("buyerName"));
			buyer.setEmail(request.getParameter("buyerEmail"));
			buyer.setPassword(request.getParameter("buyerPassword"));
			buyer.setAddress(request.getParameter("buyerAddress"));
			long n = Long.parseLong(request.getParameter("buyerContactNo"));
			buyer.setContactNo(n);
			
			System.out.println(buyer.toString());
			
			//direct the object to the connector
			BuyerLogin b = new BuyerLogin();
			boolean result = b.Register(buyer);
			
			String RegistrationStatus;
			
			if(result)
				RegistrationStatus = "Successfully Registered!";
			else
				RegistrationStatus = "Oops! Try Again!";
			
			session.setAttribute("RegistrationStatus", RegistrationStatus);
			response.sendRedirect(request.getContextPath()+"/");
			
		}
		else {
			Seller seller = new Seller();
			seller.setEntrepriseName(request.getParameter("sellerEntrepriseName"));
			seller.setEmail(request.getParameter("sellerEmail"));
			seller.setPassword(request.getParameter("sellerPassword"));
			seller.setOwnerName(request.getParameter("sellerOwnerName"));
			seller.setAddress(request.getParameter("sellerAddress"));
			long n = Long.parseLong(request.getParameter("sellerContactNo"));
			seller.setContactNo(n);
			
			System.out.println(seller.toString());
			
			//direct the object to the connector
			SellerLogin s = new SellerLogin();
			boolean result = s.Register(seller);
			String RegistrationStatus;
			
			if(result)
				RegistrationStatus = "Successfully Registered!";
			else
				RegistrationStatus = "Oops! Try Again!";
			
			session.setAttribute("RegistrationStatus", RegistrationStatus);
			response.sendRedirect(request.getContextPath()+"/");
		}
	}

}
