import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowTableData extends HttpServlet {
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
        String tb_name=request.getParameter("tb");
        String page="<center><table border =1>";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL+db_name, USER, PASS);
            stmt = conn.createStatement();
            sql="SELECT * FROM "+tb_name;
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rm = rs.getMetaData();
            int count=rm.getColumnCount();
            for(int i=1; i<=count; i++)
            {
                page+="<th>"+rm.getColumnName(i)+"</th>";
            }

            while(rs.next())
            {
                page+="<tr>";
                for(int i=1; i<=count; i++)
                {
                    page+="<td>"+rs.getString(i)+"</td>";
                
                }
                page+="</tr>";
            }
            
            page+="</table><form action=\"InsertData\">"+
                  "<input type=\"hidden\" name=\"db_name\" value="+db_name+">"+
                  "<input type=\"hidden\" name=\"tb_name\" value="+tb_name+"><br><br>"+
                  "<input type=\"submit\" name=\"insertData\" value=\"Insert Data\">"+
                  "</form></center>";
        }catch(SQLException s){out.println("SQL Exception");}
       catch(ClassNotFoundException c){out.println("Class not found");}
        
        out.println(page);
    }
}

