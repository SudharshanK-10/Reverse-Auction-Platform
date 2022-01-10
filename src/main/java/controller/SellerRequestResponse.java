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

import com.mysql.cj.Session;

/**
 * Servlet implementation class SellerRequestResponse
 */
@WebServlet("/seller/requestresponse")
public class SellerRequestResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerRequestResponse() {
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
			int orderID = Integer.parseInt(request.getParameter("orderid"));
			double offeringPrice;
			
			String sellerChoice = request.getParameter("choice");
			
			HttpSession session = request.getSession();
			
			SellerLogin sl = new SellerLogin();
			String orderRequestStatus = "";
			
			Seller s = (Seller)session.getAttribute("seller");
			
			if(sellerChoice.equals("accept")) {
				offeringPrice = Double.parseDouble(request.getParameter("offeringprice"));
				sl.acceptOrderRequest(orderID,offeringPrice,s);
				
				orderRequestStatus = "Response Sent! Check 'Received orders'";
				session.setAttribute("orderRequestStatus",orderRequestStatus);
			}
			else {
				sl.rejectOrderRequest(orderID,s);
				
				orderRequestStatus = "Response Sent!";
				session.setAttribute("orderRequestStatus",orderRequestStatus);
			}
			
			response.sendRedirect(request.getContextPath()+"/seller/orderrequests");
	}

}
