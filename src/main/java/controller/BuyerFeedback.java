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
@WebServlet("/buyer/feedback")
public class BuyerFeedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyerFeedback() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/buyerFeedback.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String subject = (String) request.getParameter("subject");
		String feedback = (String) request.getParameter("feedback");
		
		Buyer b = (Buyer)session.getAttribute("buyer");
		BuyerLogin bl = new BuyerLogin();
		
		bl.sendFeedback(b.getEmail(),subject,feedback);
		
		if(session.getAttribute("feedbacksent") != null) {
			session.removeAttribute("feedbacksent");
		}
		
		session.setAttribute("feedbacksent", "Feedback Successfully sent! We'll get to you soon!");
		doGet(request,response);
	}

}
