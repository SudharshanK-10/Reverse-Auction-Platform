package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connector.BuyerLogin;

/**
 * Servlet implementation class SellerDashboard
 */
@WebServlet("/seller/dashboard")
public class SellerDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerDashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//get the top sellers
		HttpSession session = request.getSession();
		BuyerLogin bl = new BuyerLogin();
				
		ArrayList<String> topSellers = bl.getTopSellers();
				
		if(session.getAttribute("topSellers") != null) {
			session.removeAttribute("topSellers");
		}
				
		session.setAttribute("topSellers", topSellers);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/sellerDashboard.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}