package iShare.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iShare.service.UserService;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L; 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");				//POLSKIE ZNAKI ! 
		String username = request.getParameter("inputUsername");
		String email = request.getParameter("inputEmail");
		String password = request.getParameter("inputPassword");
		UserService userService = new UserService();
		userService.addUser(username, email, password);
		response.sendRedirect(request.getContextPath()+"/");
	}

}
