package servlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Test extends HttpServlet{
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException
    {
        String userName = request.getParameter("userName");
        if(userName == null)
            userName = "Guest";

        String content = "Hello, " + userName.trim();
        response.setContentType("text/plain");

        OutputStream outStream = response.getOutputStream();
        outStream.write(content.getBytes(StandardCharsets.UTF_8));
        outStream.flush();
        outStream.close();
    }
}
