/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.Connection;

/**
 *
 * @author SYSTEMarket
 */
public class CConexion {
    Connection conectar;
    
    String usuario="root";
    String clave="Sebastian072";
    String bd="login";
    String ip="localhost";
    String puerto="3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableceConexion(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        conectar = (Connection)DriverManager.getConnection(cadena,usuario,clave);
        //JOptionPane.showMessageDialog(null,"Se conecto a la base de datos");
    } catch (Exception e){
        JOptionPane.showMessageDialog(null,"Problemas en la conexion"+e.toString());
    }
    return conectar;
    }
}
