package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;
import java.util.*;
import connector.*;

/**
 * Servlet implementation class SellerOrderRequests
 */
@WebServlet("/seller/orderrequests")
public class SellerOrderRequests extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerOrderRequests() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		ArrayList<Item> orderRequests = new ArrayList<Item>();
		HttpSession session = request.getSession();
		
		SellerLogin sl = new SellerLogin();
		Seller s = (Seller) session.getAttribute("seller");
		
		orderRequests = sl.getOrderRequests(s);
		
		if(session.getAttribute("orderRequests") != null) {
			session.removeAttribute("orderRequests");
		}
		
		session.setAttribute("orderRequests", orderRequests);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/orderRequests.jsp");
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
