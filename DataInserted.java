import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class DataInserted extends HttpServlet {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/";
    
    static final String USER = "root";
    static final String PASS = "";
    
    Connection conn = null;
    Statement stmt = null;
    
    private String sql;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String db_name=request.getParameter("db_name");
        String tb_name=request.getParameter("tb_name");
        //String col_names[]=request.getParameterValues("col");
        String col_values[];
        col_values = request.getParameterValues("col_values");
        //out.println(col_names);
        //out.println(col_values);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL+db_name, USER, PASS);
            stmt = conn.createStatement();
            sql="INSERT INTO `"+db_name+"`.`"+tb_name+"` VALUES(";
            for(int i=0; i<col_values.length; i++)
            {
                sql+="\""+col_values[i]+"\""+", ";
            }
            if (sql.endsWith(", ")) 
            {
                sql = sql.substring(0, sql.length() - 2);
            }
            sql+=")";
            stmt.executeUpdate(sql);
            response.sendRedirect("WelcomePage");
        }catch(SQLException s){out.println("SQL Exception");}
       catch(ClassNotFoundException c){out.println("Class not found");}
    }
}
