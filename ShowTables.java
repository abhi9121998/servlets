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

public class ShowTables extends HttpServlet {
    
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
        String db_name=request.getParameter("db");
        String page="<center>";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL+db_name, USER, PASS);
            stmt = conn.createStatement();
            sql="SHOW TABLES";
            ResultSet rs = stmt.executeQuery(sql);
            page+="<center><div><form action=\"ShowTableData\">"+
                  "<input type=\"hidden\" name=\"db\" value=\""+db_name+"\">"+
                  "<table>";
            String col_name;
            while(rs.next())
            {
                col_name=rs.getString("Tables_in_"+db_name);
                page+="<tr>"+
                      "<td>"+"<input type=\"submit\" name=\"tb\" value=\""+col_name+"\" >"+"</td>"+
                      "</tr>";
            }
            
            page+="</table></form></div></center>";
        }catch(SQLException s){out.println("SQL Exception");}
        catch(ClassNotFoundException c){out.println("Class not found");}
        
        out.println(page);
    }
}
            