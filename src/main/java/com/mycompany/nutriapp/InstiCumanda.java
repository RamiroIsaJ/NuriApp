/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nutriapp;

import Clases.CConexion;
import Entidades.Cantones;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SebaAuca
 */
public class InstiCumanda {
    
    public ArrayList<Cantones> getCantones()
    {
        CConexion objetoConexion = new CConexion();
        Connection con = objetoConexion.estableceConexion();  
        Statement stmt;
        ResultSet rs;
        ArrayList<Cantones> listaCantones = new ArrayList<>();
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Cumanda");
            
            while(rs.next())
            {
                Cantones cumanda = new Cantones();
                cumanda.setId_institucion(rs.getInt("id_institucion"));
                cumanda.setNombre_institucion(rs.getString("nombre_institucion"));
                listaCantones.add(cumanda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InstiCumanda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCantones;
    }
}
