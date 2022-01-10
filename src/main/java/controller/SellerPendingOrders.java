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

import connector.*;
import model.*;

/**
 * Servlet implementation class SellerPendingOrders
 */
@WebServlet("/seller/receivedorders")
public class SellerPendingOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerPendingOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		SellerLogin sl = new SellerLogin();
		
		HttpSession session = request.getSession();
		Seller s = (Seller)session.getAttribute("seller");
		
		ArrayList<Item> pendingOrders = sl.getPendingOrders(s);
		
		if(session.getAttribute("pendingOrders") != null) {
			session.removeAttribute("pendingOrders");
		}
		
		session.setAttribute("pendingOrders",pendingOrders);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/receivedOrders.jsp");
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
