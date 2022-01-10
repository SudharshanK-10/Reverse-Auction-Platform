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
 * Servlet implementation class PlaceOrder
 */
@WebServlet("/buyer/placeorder")
public class PlaceOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/placeOrder.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itemName = request.getParameter("itemname");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		Item item = new Item(itemName,quantity);
		Date date = new Date();
		item.setDateOfOrder(date);
		
		BuyerLogin bl = new BuyerLogin();
		Buyer b = (Buyer)request.getSession().getAttribute("buyer");
		
		boolean res = bl.sendOrderRequest(item,b);
		
		String orderPlacementResponse = "";
		HttpSession session = request.getSession();
		
		if(res) {
			orderPlacementResponse = "Order Request Successfully Sent!";
		}
		else {
			orderPlacementResponse = "Oops!..Try again!";
		}
		
		session.setAttribute("orderPlacementResponse",orderPlacementResponse);
		response.sendRedirect(request.getContextPath()+"/buyer/placeorder");
	}

}
