package Control;

import java.io.IOException;
import java.util.List;
import java.lang.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;

//import mytools.DBUtil;

/**
 * Servlet implementation class getCustomers
 */
@WebServlet("/getProduct")
public class getProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getProduct() {
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
		
		System.out.println("1324");
		
		String tableInfo = "";
		
		List<Product> product = displayAllProduct();
		
		try
		{
			
			tableInfo += tableInfo += "<tr><th>Product ID</th><th>Product Name</th><th>Price</th></tr>";
			for(int i = 0; i < product.size(); i++)
			{
				
				tableInfo += "<tr><td>" + product.get(i).getProductId()
						+ "</th><th>" + "<a href='login.html?productID="
						+ product.get(i).getProductId() + "'>"
						+ product.get(i).getProduct() + "</th><th>"
						+ product.get(i).getListPrice() + "</td></tr>";

			}
			request.setAttribute("tableInfo", tableInfo);
		}
		catch(Exception e)
		{
			request.setAttribute("message", "<div class='alert alert-danger' role='alert'>Error! Danger Will Robinson Danger! " + e + "</div>");
		}
		
		getServletContext()
		.getRequestDispatcher("/displayProduct.jsp")
		.forward(request, response);
		
	}
	
	protected static List<Product> displayAllProduct()
	{
		
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT p FROM Product p";
		TypedQuery<Product> q = em.createQuery(qString, Product.class);
		
		List<Product> i = null;
		try
		{
		
			i = q.getResultList();
			if(i == null || i.isEmpty())
			{
				i = null;
			}
		}
		catch(NoResultException e)
		{
			System.out.println(e);
		}
		
		finally 
		{
			em.close();
		}
		
		return i;
	}

}