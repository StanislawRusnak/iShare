package iShare.controller;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iShare.model.Discovery;
import iShare.service.DiscoveryService;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 saveDiscoveriesInRequest(request);
		 request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
	}

	private void saveDiscoveriesInRequest(HttpServletRequest request){
		DiscoveryService service = new DiscoveryService();
		List<Discovery> discoveries = service.getAllDiscoveries(new Comparator<Discovery>() {
			@Override
			public int compare(Discovery d1, Discovery d2) {
				int d1Votes = d1.getUpVote() - d1.getDownVote();
				int d2Votes = d2.getUpVote() - d2.getDownVote();
				if(d1Votes<d2Votes) {
					return 1;
				}else if(d1Votes>d2Votes){
					return -1;					//we are sorting from biggest to smallest votes amount, upside-down returned integer
				}
				return 0;
			}
		});
		request.setAttribute("discoveries", discoveries);
	}
}
