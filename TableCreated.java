import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class TableCreated extends HttpServlet {
    private String sql, db_name="none";
    private String tb_name="none";
    private String no_col="none";
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/";
    
    static final String USER = "root";
    static final String PASS = "";
    
    Connection conn = null;
    Statement stmt = null;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        PrintWriter out=response.getWriter();
        
        if(db_name.equals("none") && tb_name.equals("none") && no_col.equals("none"))
        {
            db_name=(String)request.getAttribute("db_name");
            tb_name=(String)request.getAttribute("tb_name");
            no_col=(String)request.getAttribute("no_col");
            
            out.println("db_name : "+db_name+"----"+"tb_name : "+tb_name+"----"+"no_col : "+no_col);
        }
        int colm=0;
        try
        {
            if(!request.getParameterMap().containsKey("create"))
            {    
                colm = Integer.parseInt((String)request.getAttribute("no_col"));
                out.println("int colm : "+colm);
            }
            else
            {
                colm = Integer.parseInt(request.getParameter("no_col"));
                out.println("int colm : "+colm);
            }
        }catch(NumberFormatException n){out.println("NFE"+n);}
        
        String docType ="<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";

        String page="<html>\n" +
                    "<title>Table Created</title>\n" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "<body>\n"+
                    "<div>"+
                    "<center>"+
                    "<form method='GET' action=\"TableCreated\">"+
                    "<label>Table Name : </label><br>"+
                    "<input type=\"text\" name=\"tb_name\" value="+tb_name+"><br>"+
                    "<label>Number of columns : </label><br>"+
                    "<input type=\"text\" name=\"no_col\" value="+no_col+"><br>"+
                    "<table>"+
                    "<tr>"+
                        "<th>Name</th>"+
                        "<th>Type</th>"+
                        "<th>Size</th>"+
                        "<th>Default</th>"+
                    "</tr>";

                    for(int i=0; i<colm; i++) 
                    {
                        page+="<tr>"+
                                "<td><input type=\"text\" name='na'></td>"+
                                "<td><input type=\"text\" name='ty'></td>"+
                                "<td><input type=\"text\" name='size'></td>"+
                                "<td><input type=\"text\" name='def'></td>"+
                            "</tr><br>";
                    }
                page+="<tr>"+
                    "<td><input type=\"submit\" name='create' value='create'></td>"+
                    "<td><input type=\"submit\" name='cancel' value='cancel'></td>"+
                    "</tr>"+
                    "</table>"+
                    "</form>"+
                    "</center>"+
                    "</div>"+
                    "</body></html>";
                out.println(docType+page);
        
        if(request.getParameterMap().containsKey("create"))
        {
            String na[]=request.getParameterValues("na");
            String ty[]=request.getParameterValues("ty");
            String size[]=request.getParameterValues("size");
            String def[]=request.getParameterValues("def");
            sql="CREATE TABLE IF NOT EXISTS `"+db_name+"`.`"+tb_name+"` (";
            for(int i=0; i<colm; i++)
            {
                String a=na[i], b=ty[i];
                int c=Integer.parseInt(size[i]);
                sql+=a+" ";
                sql+=b+"(";
                sql+=c+"), ";
            }
            if (sql.endsWith(", ")) 
            {
                sql = sql.substring(0, sql.length() - 2);
            }
            sql+=")";
            out.println("sql : "+sql);
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(DB_URL+db_name, USER, PASS);
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                //out.println("table created");
                conn = DriverManager.getConnection(DB_URL+"my_db", USER, PASS);
                stmt = conn.createStatement();
                sql="INSERT INTO all_tables (db_name, tb_name) VALUES('"+db_name+"', '"+tb_name+"')";
                stmt.executeUpdate(sql);
                
                response.sendRedirect("WelcomePage");
            }catch(ClassNotFoundException c){out.println("Class not found");}
            catch(SQLException s){out.println("SQL Exception");}
        }
        else if(request.getParameterMap().containsKey("cancel"))
        {
            response.sendRedirect("WelcomePage");
        }
    }
}
