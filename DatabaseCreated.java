import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class DatabaseCreated extends HttpServlet {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/";
    
    static final String USER = "root";
    static final String PASS = "";
    
    Connection conn = null;
    Statement stmt = null;

    private String sql="";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        PrintWriter out=response.getWriter();
        if ("create_db".equals((String)request.getAttribute("create_db")))
        {
            String db_name=(String)request.getAttribute("db_name");
            sql="CREATE DATABASE `"+db_name+"`";
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                
                conn = DriverManager.getConnection(DB_URL+"my_db", USER, PASS);
                stmt = conn.createStatement();
                sql="INSERT INTO all_databases (Db_name) VALUES ('"+db_name+"')";
                stmt.executeUpdate(sql);
                
                request.setAttribute("db_name",db_name);
                RequestDispatcher rd = request.getRequestDispatcher("CreateTable");
                rd.forward(request,response);             
                
            }catch(SQLException s){out.println("SQL Exception");}
            catch(ClassNotFoundException c){out.println("Class not found");}
        }
    }
}
