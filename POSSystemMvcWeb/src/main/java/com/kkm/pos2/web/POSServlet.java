package com.kkm.pos2.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kkm.pos2.domain.Cashier;
import com.kkm.pos2.repository.impl.SaleRepositoryMySQL;

/**
 * Servlet implementation class POSServlet
 */
@WebServlet("/pos.do")
public class POSServlet extends HttpServlet {
	SaleRepositoryMySQL repo = new SaleRepositoryMySQL();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public POSServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
//		if(action == null) {
			String id = request.getParameter("cashier_id");
			String pass = request.getParameter("password");
			
			Cashier cashier = repo.cashierLogin(id, pass);
			if(cashier != null) {
				request.getSession().setAttribute("auth", cashier);
//				System.out.print("user logged in");
				response.sendRedirect("homepage.jsp");
			} else{
				out.println("there is no user");
			}
			
//		}
	}

}
