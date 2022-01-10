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
import connector.*;
import java.util.*;

/**
 * Servlet implementation class MyOrder
 */
@WebServlet("/buyer/myorders")
public class MyOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		BuyerLogin bc = new BuyerLogin();
		
		HttpSession session = request.getSession();
		Buyer b = (Buyer)session.getAttribute("buyer");
		
		ArrayList<Item> myOrders = bc.getMyOrders(b);
		
		if(session.getAttribute("myOrders") != null) {
			session.removeAttribute("myOrders");
		}
		
		session.setAttribute("myOrders",myOrders);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/myOrders.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
