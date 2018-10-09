import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;

public class CreateDatabase extends HttpServlet {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/";
    
    static final String USER = "root";
    static final String PASS = "";
    
    Connection conn = null;
    Statement stmt = null;
    
    private String sql;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        PrintWriter out=response.getWriter();
        
        String docType ="<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";
        String left_panel="<html>\n" +
                          "<title>Create Database</title>\n" +
                          "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                          "<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">"+
                          "<body>\n" +
                          "<div class=\"w3-sidebar w3-light-grey w3-bar-block\" style=\"width:25%\">\n" +
                          "<h3 class=\"w3-bar-item\">Databases</h3>\n" +
                          "<a href=\"CreateDatabase\" class=\"w3-bar-item w3-button\">New +</a>\n";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL+"my_db", USER, PASS);
            stmt = conn.createStatement();
            sql="SELECT Db_name FROM all_databases";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                String db_name=rs.getString("Db_name");
                left_panel+="<a href=\"\" class=\"w3-bar-item w3-button\">"+db_name+"</a>\n";
            }
        }catch(SQLException s){out.println("SQL Exception");}
        catch(ClassNotFoundException c){out.println("Class not found");}
        if(request.getParameterMap().containsKey("cancel"))
        {
            response.sendRedirect("WelcomePage");
        }   
        else if(request.getParameterMap().containsKey("create_db"))
        {
            request.setAttribute("create_db", "create_db");
            String db_name=request.getParameter("db_name");
            request.setAttribute("db_name", db_name);
            RequestDispatcher rd = request.getRequestDispatcher("DatabaseCreated");
            rd.forward(request,response);
        }
        left_panel+="</div>\n";
        String page="<div style=\"margin-left:25%\">"+
                    "<div class=\"w3-bar w3-light-grey\">\n" +
                    "<a href=\"#\" class=\"w3-bar-item w3-button\">Databases</a>\n" +
                    "<a href=\"#\" class=\"w3-bar-item w3-button\">SQL</a>\n" +
                    "</div>";
                
                page+="<div>"+
                    "<center>"+
                    "<form method=\"GET\" action=\"CreateDatabase\">"+
                    "<label>Database Name : </label> <br>"+
                    "<input type=\"text\" name=\"db_name\">"+
                    "<input type=\"submit\" name=\"create_db\" value=\"create\"> "+
                    "<input type=\"submit\" name=\"cancel\" value=\"cancel\"> "+
                    "</form>"+
                    "</center>"+
                    "</div>"+
                    "</div>"+
                    "</body></html>";
        
        out.println(docType+left_panel+page);      
    }
}
