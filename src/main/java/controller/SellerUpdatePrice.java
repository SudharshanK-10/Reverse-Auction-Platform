package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
import connector.*;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SellerUpdatePrice
 */
@WebServlet("/seller/updateofferingprice")
public class SellerUpdatePrice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerUpdatePrice() {
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
		Seller s = (Seller)request.getSession().getAttribute("seller");
		double newOfferingPrice = Double.parseDouble(request.getParameter("offeringprice"));
		int orderID = Integer.parseInt(request.getParameter("orderid"));
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("orderid") != null) {
			session.removeAttribute("orderid");
		}
		
		session.setAttribute("orderid", orderID);
		
		BuyerLogin bl = new BuyerLogin();
		
		if(bl.isOrderOver(orderID) == false) {
			SellerLogin sl = new SellerLogin();
			sl.updateOfferingPrice(orderID,newOfferingPrice,s.getEmail());
		}
		
		response.sendRedirect(request.getContextPath()+"/seller/vieworder");
	}

}
