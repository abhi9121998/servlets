import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class CreateTable extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String db_name="none";
        PrintWriter out=response.getWriter();
        out.println("db_name"+db_name);
        if(db_name.equals("none"))
        {
            db_name=(String)request.getAttribute("db_name");
            request.setAttribute("db_name",db_name);
            out.println("db_name"+db_name);
            //out.println("db_name"+db_name);
        }
        
        String docType ="<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";
        
        String page="<html>\n" +
                    "<title>Create Table</title>\n" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">"+
                    "<body>\n";
                page+="<div style=\"margin-left:25%\">"+
                    "<div>"+
                    "<center>"+
                    "<form method=\"GET\" action=\"CreateTable\">"+
                    "<label>Database Name : </label> <br>"+
                    "<input type=\"text\" name=\"db_name\" value="+db_name+"><br>"+
                    "<label>Table Name : </label> <br>"+
                    "<input type=\"text\" name=\"tb_name\"><br>"+
                    "<label>Number of columns : </label> <br>"+
                    "<input type=\"text\" name=\"no_col\"><br>"+
                    "<input type=\"submit\" name=\"create_tb\" value=\"create\"> "+
                    "<input type=\"submit\" name=\"cancel\" value=\"cancel\"> "+
                    "</form>"+
                    "</center>"+
                    "</div>"+
                    "</div>"+
                    "</body></html>";
                out.println(docType+page);
        if(request.getParameterMap().containsKey("create_tb"))
        {
            String db_name_temp=request.getParameter("db_name");
            String tb_name=request.getParameter("tb_name");
            String no_col=request.getParameter("no_col");
            request.setAttribute("tb_name",tb_name);
            request.setAttribute("no_col",no_col);
            request.setAttribute("db_name",db_name_temp);
            RequestDispatcher rd = request.getRequestDispatcher("TableCreated");
            rd.forward(request,response);
        }
        else if(request.getParameterMap().containsKey("cancel"))
        {
            response.sendRedirect("WelcomePage");
        }
    }
}
