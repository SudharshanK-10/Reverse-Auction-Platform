package controller;

import java.io.IOException;
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
 * Servlet implementation class BuyerViewOrder
 */
@WebServlet("/seller/vieworder")
public class SellerViewOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerViewOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int orderID;
		
		if(request.getParameter("orderid") != null)
			orderID = Integer.parseInt(request.getParameter("orderid"));
		else
			orderID = (int)session.getAttribute("orderid");
		
		if(session.getAttribute("orderid") != null) {
			session.removeAttribute("orderid");
		}
		
		session.setAttribute("orderid", orderID);
		response.sendRedirect(request.getContextPath()+"/seller/auctionList");
	}
}
