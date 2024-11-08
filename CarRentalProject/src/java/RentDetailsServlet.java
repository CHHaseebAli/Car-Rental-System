import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/rentDetails")
public class RentDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Get form data from rent.html
        String brandName = request.getParameter("brandName");
        String membershipType = request.getParameter("membershipType");
        double rentalPrice = Double.parseDouble(request.getParameter("rentalPrice"));
        int numberOfDays = Integer.parseInt(request.getParameter("numberOfDays"));
        
        // Apply discount based on membership type
        double discount = 0;
        switch (membershipType) {
            case "PREMIUM":
                discount = 0.35;
                break;
            case "GOLD":
                discount = 0.25;
                break;
            case "SILVER":
                discount = 0.15;
                break;
            case "NONE":
                discount = 0.0;
                break;
        }
        
        // Calculate the total invoice amount
        double total = rentalPrice * numberOfDays * (1 - discount);
        
        // Set content type and prepare the response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Generate the HTML response
        out.println("<html>");
        out.println("<head><title>Rental Details</title></head>");
        out.println("<body>");
        out.println("<h1>Car Rental Invoice</h1>");
        out.println("<table border='1'>");
        out.println("<tr><th>Brand Name</th><th>Rental Price (per day)</th><th>Number of Days</th><th>Membership Type</th><th>Total Invoice Amount</th></tr>");
        out.println("<tr><td>" + brandName + "</td><td>" + rentalPrice + "</td><td>" + numberOfDays + "</td><td>" + membershipType + "</td><td>" + total + "</td></tr>");
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
