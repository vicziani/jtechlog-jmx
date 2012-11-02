package jtechlog.jmx;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/index.html")
public class CounterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Counter counter = (Counter) getServletContext().getAttribute("counter");
        counter.incrementCounter();
        request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    }

}
