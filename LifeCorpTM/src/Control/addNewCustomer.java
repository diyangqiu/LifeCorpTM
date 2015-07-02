package Control;

import java.io.IOException;
import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addCustomers
 */
@WebServlet("/addNewCustomer")
public class addNewCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addNewCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String CustomerUserName = request.getParameter("email");
		String CustomerEmailID = request.getParameter("username");
		String CustomerPassword = request.getParameter("password");
		
		//System.out.println(CustomerUserName);
		
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		
		
		try
		{
			//System.out.println("121212");
			
			model.Customer cust = new model.Customer();
			cust.setUserName(CustomerUserName);
			
			cust.setPassword(CustomerPassword);
			
			em.persist(cust);
			
			trans.commit();
		}
		catch(Exception e)
		{
			System.out.println(e);
			trans.rollback();
		}
		finally
		{
			em.close();
		}
		
		getServletContext()
		.getRequestDispatcher("/postaddcustomer.jsp")
		.forward(request, response);
		
	}

}
