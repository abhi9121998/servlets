import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;

public class WelcomePage extends HttpServlet {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/my_db";
    
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
                          "<head>"+
                          "<title>Welcome Page</title>\n" +
                          "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                          "<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">"+
                          "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">"+
                          "<style>"+
                                ".dropdown {\n" +
                                "position: relative;\n" +
                                "display: inline-block;\n" +
                                "}"+
                                
                                ".dropbtn {\n" +
                                "    background-color: #4CAF50;\n" +
                                "    color: white;\n" +
                                "    padding: 16px;\n" +
                                "    font-size: 16px;\n" +
                                "    border: none;\n" +
                                "}"+
                
                                ".dropdown-content {\n" +
                                "    display: none;\n" +
                                "    position: absolute;\n" +
                                "    background-color: #f1f1f1;\n" +
                                "    min-width: 160px;\n" +
                                "    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);\n" +
                                "    z-index: 1;\n" +
                                "}"+
                                
                                ".dropdown-content a {\n" +
                                "    color: black;\n" +
                                "    padding: 12px 16px;\n" +
                                "    text-decoration: none;\n" +
                                "    display: block;\n" +
                                "}\n" +
                                "\n" +
                                ".dropdown-content a:hover {background-color: #ddd;}\n" +
                                "\n" +
                                ".dropdown:hover .dropdown-content {display: block;}\n" +
                                "\n" +
                                ".dropdown:hover .dropbtn {background-color: #3e8e41;}"+
                
                          "</style>"+
                          "</head>"+
                          "<body>\n" +
                          "<div class=\"w3-sidebar w3-light-grey w3-bar-block\" style=\"width:25%\">\n" +
                          "<h3 class=\"w3-bar-item\">Databases</h3>\n" +
                          "<a href=\"CreateDatabase\" class=\"w3-bar-item w3-button\">New +</a>\n";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            sql="SELECT Db_name FROM all_databases";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                String db_name=rs.getString("Db_name");
                left_panel+="<div class=\"dropdown\">";
                        
                left_panel+="<button class=\"dropbtn\">"+db_name+"<i class=\"fa fa-caret-down\"></i>"+"</button>"+
                            "<div class=\"dropdown-content\">";
                    stmt = conn.createStatement();
                    sql="SELECT tb_name FROM all_tables where db_name='"+db_name+"'";
                    ResultSet rs1 = stmt.executeQuery(sql);
                    while(rs1.next())
                    {
                        left_panel+="<a href=\"#\">"+rs1.getString("tb_name")+"</a>";
                    }
                left_panel+="</div></div>";
            }
        }catch(SQLException s){out.println("SQL Exception");}
        catch(ClassNotFoundException c){out.println("Class not found");}
        
        left_panel+="</div>\n";
        String page="<div style=\"margin-left:25%\">"+
                    "<div class=\"w3-bar w3-light-grey\">\n" +
                    "<a href=\"ShowDatabases\" class=\"w3-bar-item w3-button\">Databases</a>\n" +
                    "<a href=\"#\" class=\"w3-bar-item w3-button\">SQL</a>\n" +
                    "<a href=\"AddTable\" class=\"w3-bar-item w3-button\">Add Table</a>\n" +
                    "</div>"+
                    "</div></body></html>";
        out.println(docType+left_panel+page);
    }
}
