package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import connector.*;
import model.*;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BuyerFeedback
 */
@WebServlet("/seller/feedback")
public class SellerFeedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerFeedback() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/sellerFeedback.jsp");
		dispatcher.forward(request, response);
		
		System.out.println("Serving feedback form!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String subject = (String) request.getParameter("subject");
		String feedback = (String) request.getParameter("feedback");
		
		Seller s = (Seller)session.getAttribute("seller");
		SellerLogin sl = new SellerLogin();
		
		sl.sendFeedback(s.getEmail(),subject,feedback);
		
		if(session.getAttribute("feedbacksent") != null) {
			session.removeAttribute("feedbacksent");
		}
		
		session.setAttribute("feedbacksent", "Feedback Successfully sent! We'll get to you soon!");
		doGet(request,response);
	}

}
