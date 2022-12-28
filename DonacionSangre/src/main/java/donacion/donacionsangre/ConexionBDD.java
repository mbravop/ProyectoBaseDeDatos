/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package donacion.donacionsangre;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author mbravop
 */
public class ConexionBDD {
    public Connection databaseLink;
    
    public Connection getConnection(){
        String databaseName = "proyectobdd";
        String databaseUser = "root";
        String databasePassword = "root1234";
        
        String url = "jdbc:mysql://localhost/" + databaseName;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
            System.out.println("Conectado...");
            
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return databaseLink;
    }
}
