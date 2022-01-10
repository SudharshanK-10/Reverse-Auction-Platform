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
 * Servlet implementation class SellerAuctionList
 */
@WebServlet("/seller/auctionList")
public class SellerAuctionList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerAuctionList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session = request.getSession();
		int orderID = (int)session.getAttribute("orderid");
		
		//get the buyer info
		BuyerLogin bl = new BuyerLogin();
		Buyer b = bl.getBuyer(orderID);
		
		//check if order is over
		if(bl.isOrderOver(orderID) == true) {
			session.setAttribute("orderover", 1);
		}
		else if(session.getAttribute("orderover") != null) {
			session.removeAttribute("orderover");
		}
		
		//item for the reverse auction
		Item item = bl.getItem(orderID);
		
		if(session.getAttribute("item") != null) {
			session.removeAttribute("item");
		}
		
		session.setAttribute("item", item);
		
		if(session.getAttribute("buyer") != null) {
			session.removeAttribute("buyer");
		}
		
		session.setAttribute("buyer", b);
		
		ArrayList<Auction> auctionList = bl.getAuctionList(orderID,b);
		
		if(session.getAttribute("auctionList") != null) {
			session.removeAttribute("auctionList");
		}
		
		session.setAttribute("auctionList", auctionList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/sellerAuctionList.jsp");
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
