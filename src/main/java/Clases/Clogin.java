/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import com.mycompany.nutriapp.Ingreso;
import com.mycompany.nutriapp.Principal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author SYSTEMarket
 */
public class Clogin {
    
    public void validaUsuario(JTextField usuario, JPasswordField clave){
   try{
       ResultSet rs= null;
       PreparedStatement ps=null;
       
       Clases.CConexion objetoConexion = new Clases.CConexion();
       
       String consulta="select * from Usuarios where Usuarios.ingresoUsuario=(?) and Usuarios.ingresoContra=(?);";
       ps=objetoConexion.estableceConexion().prepareStatement(consulta);
       
       
      String contra=String.valueOf(clave.getPassword());
       ps.setString(1, usuario.getText());
       ps.setString(2, contra);
       
       rs=ps.executeQuery();
       
       if(rs.next()){
           JOptionPane.showMessageDialog(null,"El usuario es correcto");
           
           Principal objetoprin= new Principal();
           objetoprin.setVisible(true);
       }
       else{
           JOptionPane.showMessageDialog(null,"El usuario es incorrecto");
           Ingreso objetoing= new Ingreso();
           objetoing.setVisible(true);
       }
       
   } catch (Exception e){
        JOptionPane.showMessageDialog(null,"Error:"+e.toString());
   }
    }

}
