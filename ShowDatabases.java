import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class ShowDatabases extends HttpServlet {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/my_db";
    
    static final String USER = "root";
    static final String PASS = "";
    
    Connection conn = null;
    Statement stmt = null;
    
    private String sql;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String page="<center>";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            sql="SELECT Db_name FROM all_databases";
            ResultSet rs = stmt.executeQuery(sql);
            
            page+="<div>";
            page+="<form action=\"ShowTables\"><table>";
            while(rs.next())
            {
                String db_name=rs.getString("Db_name");
                page+="<tr>"+
                      "<td>"+"<input type=\"submit\" name=\"db\" value=\""+db_name+"\" >"+"</td>"+
                      "</tr>";
            }
            page+="</form></table></div></center>";
        }catch(SQLException s){out.println("SQL Exception");}
        catch(ClassNotFoundException c){out.println("Class not found");}
        
        out.println(page);
    }
}
