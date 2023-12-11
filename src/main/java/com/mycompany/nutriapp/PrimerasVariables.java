/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.nutriapp;
//Libreriass
import Clases.CConexion;
import Entidades.Cantones;
import java.awt.Color;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JSpinner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import java.util.Date;
import javax.swing.JComboBox;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Enumeration;




/**
 *
 * @author SYSTEMarket
 */
public class PrimerasVariables extends javax.swing.JFrame {

    /**
     * Creates new form PrimerasVariables
     */
    int sector, unidades;
    int xMouse, yMouse;
    String nombrePaciente;
    String direccionPaciente;
    String generoPaciente;
    String fechaPaciente;
    String residenciaPaciente;
    String gradoPaciente;
    String paraleloPaciente;
    String unidadPaciente;
    String fechavisiPaciente;
    
    private static int ana; // Variable de clase (static)
    private static int mess; // Variable de clase (static){
    private static int anitos; // Variable de clase (static){

    
    String genero;
    float nuno, ntres, ncinco, nquince, nveinteycinco, ncincuenta, nsetentaycinco, nochentaycinco, nnoventaycinco, nnoventaysiete,nnoventaynueve;
    

    public PrimerasVariables() {
        
        initComponents();
        llenarAlausi();
        BMISL.setText(String.valueOf(SLBMI.getValue())); 
        // Obtener la fecha actual
Date fechaActual = new Date();

// Formatear la fecha en el formato deseado
SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
String fechaFormateada = formatoFecha.format(fechaActual);

// Mostrar la fecha en el JLabel
jLabel9.setText(fechaFormateada);

    }
    
    private void llenarAlausi()
    {
        InstiAlausi modAlausi = new InstiAlausi();
        ArrayList <Cantones> listaAlausi = modAlausi.getCantones();
        
        SelUnidad.removeAllItems();
        
        for(int i = 0; i < listaAlausi.size(); i++)
        {
            SelUnidad.addItem(listaAlausi.get(i).getNombre_institucion());
        }
    }
    
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaci) {
        this.nombrePaciente = nombrePaci;
    }
     public String getDireccion() {
        return direccionPaciente;
    }

    public void setDireccion(String direccionPaci) {
        this.direccionPaciente = direccionPaci;
    }
     public String getGenero() {
        return generoPaciente;
    }

    public void setGenero(String generoPaci) {
        this.generoPaciente = generoPaci;
    }
     public String getFechaNaci() {
        return fechaPaciente;
    }

    public void setFechaNaci(String fechaPaci) {
        this.fechaPaciente = fechaPaci;
    }
     public String getUniEduca() {
        return unidadPaciente;
    }

    public void setUniEduca(String unidadPaci) {
        this.unidadPaciente = unidadPaci;
    }
     public String getGrado() {
        return gradoPaciente;
    }

    public void setGrado(String gradoPaci) {
        this.gradoPaciente = gradoPaci;
    }
     public String getParalelo() {
        return paraleloPaciente;
    }

    public void setParalelo(String paralPaci) {
        this.paraleloPaciente = paralPaci;
    }
     public String getResidencia() {
        return residenciaPaciente;
    }

    public void setResidencia(String residenPaci) {
        this.residenciaPaciente = residenPaci;
    }
    public String getFechaVisita() {
        return fechavisiPaciente;
    }

    public void setFechaVisita(String visitPaci) {
        this.fechavisiPaciente = visitPaci;
    }


    public void InsertarDatosPaci(JTextField nombre, JTextField direccion, String genero,String Fecha,JComboBox Unid,JComboBox Grado,JComboBox Paralelo,String Residencia,String FechaVisita){
        setNombrePaciente(INnombreape.getText());
        setDireccion(INDireccion.getText());
        String generoSeleccionado = "";
        if (Bmasc.isSelected()) {
            generoSeleccionado = Bmasc.getActionCommand();
        } else if (Bfem.isSelected()) {
            generoSeleccionado = Bfem.getActionCommand();
        }
        setGenero(generoSeleccionado);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaSeleccionada = dateFormat.format(jDateChooser2.getDate());
        setFechaNaci(fechaSeleccionada);
        String uniEducaSeleccionada = SelUnidad.getSelectedItem().toString();
        setUniEduca(uniEducaSeleccionada);
        String GradoSeleccionada = Selgrado.getSelectedItem().toString();
        setGrado(GradoSeleccionada);
        String ParaleloSeleccionada = Selparal.getSelectedItem().toString();
        setParalelo(ParaleloSeleccionada);
        String residenciaSeleccionada = "";
        if (Burbana.isSelected()) {
            residenciaSeleccionada = Burbana.getActionCommand();
        } else if (Brural.isSelected()) {
            residenciaSeleccionada = Brural.getActionCommand();
        }
        setResidencia(residenciaSeleccionada);
        Date fechaActual = new Date();

// Formatear la fecha en el formato deseado
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = formatoFecha.format(fechaActual);
        setFechaVisita(fechaFormateada);


        CConexion objetoConexion = new CConexion();

        String consulta=("insert into paciente(Nombre,Direccion,Genero,FechaNacimiento,UniEduca,Grado,Paralelo,Residencia,FechaVisita) values(?,?,?,?,?,?,?,?,?);");
        
        try{
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1,getNombrePaciente());
            cs.setString(2,getDireccion());
            cs.setString(3,getGenero());
            cs.setString(4,getFechaNaci());
            cs.setString(5,getUniEduca());
            cs.setString(6,getGrado());
            cs.setString(7,getParalelo());
            cs.setString(8,getResidencia());
            cs.setString(9,getFechaVisita());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Se ha registrado correctamente, inicie sesión");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"A ocurrido iun error"+e.toString());

        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupogen = new javax.swing.ButtonGroup();
        gruporesiden = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        txtname = new javax.swing.JLabel();
        Pinfgene = new javax.swing.JPanel();
        txtpeso = new javax.swing.JLabel();
        txtgenero = new javax.swing.JLabel();
        txtedad = new javax.swing.JLabel();
        txtnaci = new javax.swing.JLabel();
        edad2 = new javax.swing.JLabel();
        txtaltura = new javax.swing.JLabel();
        Dpeso = new javax.swing.JSpinner();
        Daltura = new javax.swing.JSpinner();
        Bmasc = new javax.swing.JRadioButton();
        Bfem = new javax.swing.JRadioButton();
        bmidat = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        fechanaci = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        Presul = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jSlider1 = new javax.swing.JSlider();
        SLBMI = new javax.swing.JSlider();
        jSlider3 = new javax.swing.JSlider();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        BMISL = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Header = new javax.swing.JPanel();
        cerrarbotn = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        txtfvisit1 = new javax.swing.JLabel();
        INDireccion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Peduca = new javax.swing.JPanel();
        txtunid = new javax.swing.JLabel();
        txtgrado = new javax.swing.JLabel();
        txtparal = new javax.swing.JLabel();
        Selgrado = new javax.swing.JComboBox<>();
        Selparal = new javax.swing.JComboBox<>();
        txtnameape = new javax.swing.JLabel();
        INnombreape = new javax.swing.JTextField();
        txtresiden = new javax.swing.JLabel();
        Burbana = new javax.swing.JRadioButton();
        Brural = new javax.swing.JRadioButton();
        txtunid1 = new javax.swing.JLabel();
        jSector = new javax.swing.JComboBox<>();
        SelUnidad = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtname.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtname.setText("Dirección Domiciliaria:");
        jPanel1.add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        Pinfgene.setBackground(new java.awt.Color(255, 255, 255));
        Pinfgene.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        Pinfgene.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtpeso.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtpeso.setText("Peso (kg):");
        Pinfgene.add(txtpeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 68, -1));

        txtgenero.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtgenero.setText("Genero:");
        Pinfgene.add(txtgenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        txtedad.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtedad.setText("Edad Calculada:");
        Pinfgene.add(txtedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        txtnaci.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtnaci.setText("Fecha de nacimiento:");
        Pinfgene.add(txtnaci, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));
        Pinfgene.add(edad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 190, 20));

        txtaltura.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtaltura.setText("Alutra (cm):");
        Pinfgene.add(txtaltura, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 85, -1));

        Dpeso.setModel(new javax.swing.SpinnerNumberModel(0.0f, null, null, 1.0f));
        Pinfgene.add(Dpeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 90, -1));

        Daltura.setModel(new javax.swing.SpinnerNumberModel(0.0f, null, null, 1.0f));
        Pinfgene.add(Daltura, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 90, -1));

        grupogen.add(Bmasc);
        Bmasc.setText("Masculino");
        Bmasc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BmascActionPerformed(evt);
            }
        });
        Pinfgene.add(Bmasc, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, -1, -1));

        grupogen.add(Bfem);
        Bfem.setText("Femenino");
        Pinfgene.add(Bfem, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, -1, -1));
        Pinfgene.add(bmidat, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 50, 20));

        jLabel14.setText("BMI");
        Pinfgene.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, -1, -1));
        Pinfgene.add(fechanaci, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 160, 20));
        Pinfgene.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 160, -1));

        jPanel1.add(Pinfgene, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 330, 180));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Resultados");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 67, -1));

        Presul.setBackground(new java.awt.Color(255, 255, 255));
        Presul.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        Presul.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setText("BMI/Edad");
        Presul.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 67, -1));

        jLabel1.setText("Talla/Edad");
        Presul.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 67, -1));

        jLabel2.setText("Peso/Edad");
        Presul.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 104, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        Presul.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 140, -1, -1));

        jSlider1.setMinorTickSpacing(25);
        jSlider1.setToolTipText("");
        Presul.add(jSlider1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, -1));

        SLBMI.setBackground(new java.awt.Color(153, 153, 153));
        SLBMI.setEnabled(false);
        Presul.add(SLBMI, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        jSlider3.setBackground(new java.awt.Color(153, 153, 153));
        Presul.add(jSlider3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, -1));

        jLabel4.setText("Percentiles");
        Presul.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        jLabel5.setText("jLabel5");
        Presul.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, -1, -1));

        BMISL.setText("jLabel5");
        Presul.add(BMISL, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        jLabel10.setText("jLabel5");
        Presul.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, -1, -1));

        jPanel1.add(Presul, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 770, 190));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        jLabel6.setText("% Masa Grasa");

        jLabel7.setText("% Masa Muscular");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap(186, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 50, 170, 240));

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                HeaderMouseDragged(evt);
            }
        });
        Header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                HeaderMousePressed(evt);
            }
        });

        cerrarbotn.setBackground(new java.awt.Color(255, 255, 255));
        cerrarbotn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        exit.setFont(new java.awt.Font("Roboto Light", 1, 14)); // NOI18N
        exit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exit.setText("Cerrar");
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitMouseExited(evt);
            }
        });

        javax.swing.GroupLayout cerrarbotnLayout = new javax.swing.GroupLayout(cerrarbotn);
        cerrarbotn.setLayout(cerrarbotnLayout);
        cerrarbotnLayout.setHorizontalGroup(
            cerrarbotnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cerrarbotnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        cerrarbotnLayout.setVerticalGroup(
            cerrarbotnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addComponent(cerrarbotn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(735, Short.MAX_VALUE))
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cerrarbotn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 30));

        txtfvisit1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtfvisit1.setText("Fecha de la visita:");
        jPanel1.add(txtfvisit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));
        jPanel1.add(INDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 194, -1));

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setToolTipText("");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 140, 20));

        Peduca.setBackground(new java.awt.Color(255, 255, 255));
        Peduca.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        txtunid.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtunid.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtunid.setText("Unidad Educativa");

        txtgrado.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtgrado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtgrado.setText("Grado");

        txtparal.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtparal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtparal.setText("Paralelo");

        Selgrado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Primero EGB", "Segundo EGB", "Tercero EGB", "Cuarto EGB", "Quinto EGB", "Sexto EGB", "Séptimo EGB", "Octavo EGB", "Noveno EGB", "Décimo EGB", "Primero de Bachillerato", "Segundo de Bachillerato", "Tercero de Bachillerato" }));

        Selparal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D", "E", "F", "G", "H" }));

        txtnameape.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtnameape.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtnameape.setText("Nombre y Apellidos");

        INnombreape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                INnombreapeActionPerformed(evt);
            }
        });

        txtresiden.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtresiden.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtresiden.setText("Residencia");

        Burbana.setText("Urbana");
        Burbana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BurbanaActionPerformed(evt);
            }
        });

        Brural.setText("Rural");

        txtunid1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtunid1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtunid1.setText("Sector");

        jSector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALAUSI", "CHAMBO", "CHUNCHI", "COLTA", "CUMANDA", "GUAMOTE", "GUANO", "PALLATANGA", "PENIPE", "RIOBAMBA" }));
        jSector.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSectorMouseClicked(evt);
            }
        });

        SelUnidad.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        SelUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelUnidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PeducaLayout = new javax.swing.GroupLayout(Peduca);
        Peduca.setLayout(PeducaLayout);
        PeducaLayout.setHorizontalGroup(
            PeducaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PeducaLayout.createSequentialGroup()
                .addGroup(PeducaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PeducaLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(PeducaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PeducaLayout.createSequentialGroup()
                                .addComponent(txtresiden)
                                .addGap(41, 41, 41)
                                .addComponent(Burbana, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Brural, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(PeducaLayout.createSequentialGroup()
                                .addGroup(PeducaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtnameape, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtgrado)
                                    .addComponent(Selgrado, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                                .addGroup(PeducaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtparal, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Selparal, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(INnombreape)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PeducaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtunid, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PeducaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtunid1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSector, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(41, 41, 41))
            .addGroup(PeducaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SelUnidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PeducaLayout.setVerticalGroup(
            PeducaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PeducaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(PeducaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtunid1)
                    .addComponent(jSector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtunid)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SelUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PeducaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtparal)
                    .addComponent(txtgrado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PeducaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Selgrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Selparal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnameape)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(INnombreape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PeducaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtresiden, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Burbana)
                    .addComponent(Brural))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel1.add(Peduca, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 430, 270));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1004, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BmascActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BmascActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BmascActionPerformed

    private void HeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeaderMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_HeaderMouseDragged

    private void HeaderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeaderMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_HeaderMousePressed

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitMouseClicked

    private void exitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseEntered
        cerrarbotn.setBackground(Color.red);
        exit.setForeground(Color.white);
    }//GEN-LAST:event_exitMouseEntered

    private void exitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseExited
        cerrarbotn.setBackground(Color.white);
        exit.setForeground(Color.black);
    }//GEN-LAST:event_exitMouseExited

    private void fechanaciPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fechanaciPropertyChange
        try {
           edad2.setText(edad(jDateChooser2.getDate()));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_fechanaciPropertyChange

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        float altura=0,peso=0;
        altura = (float) Daltura.getValue();
        peso = (float) Dpeso.getValue();
        float BMI = (peso)/((altura*altura)/10000);
       // String formattedBMI = String.format("%.2f", BMI);  // Muestra el BMI con 2 decimales
       //bmidat.setText(String.format("%.2f", BMI));
       
    anitos = (ana * 12) + mess;

    edad2.setText(edad(jDateChooser2.getDate()));

String[] registros = new String[50];
if (Bfem.isSelected()) {
    genero = "Femenino";
} else if(Bmasc.isSelected()) {
    genero = "Masculino"; // Cambia esto si necesitas otro valor para el género masculino
}

String sql = "SELECT * FROM Percentiles2 WHERE Mes LIKE '%" + anitos + "%' AND Genero LIKE '" + genero + "'";

    CConexion objetoConexion = new CConexion();
    Connection conect = objetoConexion.estableceConexion();    
    
    try{
        Statement st = (Statement) conect.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()){
        nuno = Float.parseFloat(rs.getString("uno"));
        ntres = Float.parseFloat(rs.getString("tres"));
        ncinco = Float.parseFloat(rs.getString("Cinco"));
        nquince = Float.parseFloat(rs.getString("Quince"));
        nveinteycinco = Float.parseFloat(rs.getString("VeinteYCinco"));
        ncincuenta = Float.parseFloat(rs.getString("Cincuenta"));
        nsetentaycinco = Float.parseFloat(rs.getString("SetentaYCinco"));
        nochentaycinco = Float.parseFloat(rs.getString("OchentaYCinco"));
        nnoventaycinco = Float.parseFloat(rs.getString("NoventaYCinco"));
        nnoventaysiete = Float.parseFloat(rs.getString("NoventaYCiete"));
        nnoventaynueve = Float.parseFloat(rs.getString("NoventaYNueve"));

        }
    } catch (SQLException ex){
        JOptionPane.showMessageDialog(null,ex);
    }
    float pval=nuno-BMI;
    float puno=  Math.abs(pval);
    float pval2=ntres-BMI;
    float ptres=  Math.abs(pval2);
    float pval3=ncinco-BMI;
    float pcinco=  Math.abs(pval3);
    float pval4=nquince-BMI;
    float pquince=  Math.abs(pval4);
    float pval5=nveinteycinco-BMI;
    float pveinteycinco=  Math.abs(pval5);
    float pval6=ncincuenta-BMI;
    float pcincuenta=  Math.abs(pval6);
    float pval7=nsetentaycinco-BMI;
    float psetentaycinco=  Math.abs(pval7);
    float pval8=nochentaycinco-BMI;
    float pochentaycinco=  Math.abs(pval8);
    float pval9=nnoventaycinco-BMI;
    float pnoventaycinco=  Math.abs(pval9);
    float pval10=nnoventaysiete-BMI;
    float pnoventaysiete=  Math.abs(pval10);
    float pval11=nnoventaynueve-BMI;
    float pnoventaynueve=  Math.abs(pval11);
    
    float minimo = Math.min(puno, Math.min(ptres, Math.min(pcinco, Math.min(pquince, Math.min(pveinteycinco,
        Math.min(pcincuenta, Math.min(psetentaycinco, Math.min(pochentaycinco, Math.min(pnoventaycinco,
        Math.min(pnoventaysiete, pnoventaynueve))))))))));
    String mininred = String.format("%.2f", minimo);
    String BMIR = String.format("%.1f", BMI);
    bmidat.setText(String.valueOf(BMIR));
    if (minimo==puno){
        BMISL.setText(String.valueOf(1));
        SLBMI.setValue(1);
    }
    if (minimo==ptres){
        BMISL.setText(String.valueOf(3)); 
        SLBMI.setValue(3);

    }
    if (minimo==pcinco){
        BMISL.setText(String.valueOf(5)); 
        SLBMI.setValue(5);
    }
    if (minimo==pquince){
        BMISL.setText(String.valueOf(15)); 
        SLBMI.setValue(15);
    }
    if (minimo==pveinteycinco){
        BMISL.setText(String.valueOf(25)); 
        SLBMI.setValue(25);
    }
    if (minimo==pcincuenta){
        BMISL.setText(String.valueOf(50)); 
        SLBMI.setValue(50);
    }
    if (minimo==psetentaycinco){
        BMISL.setText(String.valueOf(75)); 
        SLBMI.setValue(75);
    }
    if (minimo==pochentaycinco){
        BMISL.setText(String.valueOf(85)); 
        SLBMI.setValue(85);
    }
    if (minimo==pnoventaycinco){
        BMISL.setText(String.valueOf(95)); 
        SLBMI.setValue(95);
    }
    if (minimo==pnoventaysiete){
        BMISL.setText(String.valueOf(97)); 
        SLBMI.setValue(97);
    }
    if (minimo==pnoventaynueve){
        BMISL.setText(String.valueOf(99)); 
        SLBMI.setValue(99);
    }
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaSeleccionada = dateFormat.format(jDateChooser2.getDate());

        

        String generoSeleccionado = "";
        if (Bmasc.isSelected()) {
            generoSeleccionado = Bmasc.getActionCommand();
        } else if (Bfem.isSelected()) {
            generoSeleccionado = Bfem.getActionCommand();
        }

        String residenciaSeleccionada = "";
        if (Burbana.isSelected()) {
            residenciaSeleccionada = Burbana.getActionCommand();
        } else if (Brural.isSelected()) {
            residenciaSeleccionada = Brural.getActionCommand();
        }
        
        Date fechaActual = new Date();

// Formatear la fecha en el formato deseado
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = formatoFecha.format(fechaActual);
InsertarDatosPaci(INnombreape,INDireccion,generoSeleccionado,fechaSeleccionada,SelUnidad,Selgrado,Selparal,residenciaSeleccionada,fechaFormateada);
    }//GEN-LAST:event_jPanel2MouseClicked

    private void INnombreapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_INnombreapeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_INnombreapeActionPerformed

    private void BurbanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BurbanaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BurbanaActionPerformed

    private void jSectorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSectorMouseClicked

        sector = jSector.getSelectedIndex();

        if (sector==0){
            SelUnidad.removeAllItems();
            SelUnidad.addItem("UNIDAD EDUCATIVA NIZAG INDIGENA");
            SelUnidad.addItem("UNIDAD EDUCATIVA INTERCULTURAL BILINGUE HUANCA PALLAGUCHI");
            SelUnidad.addItem("DIEGO MENDEZ");
            SelUnidad.addItem("BENIGNO BRITO ZUÑIGA");
            SelUnidad.addItem("ANGEL ERMINIO SILVA OLIVO");
            SelUnidad.addItem("ANTONIO ELIZALDE");
            SelUnidad.addItem("UNIDAD EDUCATIVA SAN FRANCISCO DE SALES");
            SelUnidad.addItem("UNIDAD EDUCATIVA FEDERICO GONZALEZ SUAREZ");
            SelUnidad.addItem("UNIDAD EDUCATIVA CIUDAD DE ALAUSÍ");
            SelUnidad.addItem("UNIDAD EDUCATIVA ISAIAS GARZÓN LOYOLA");
            SelUnidad.addItem("ESCUELA DE EDUCACIÓN BÁSICA NUDO DEL AZUAY");
            SelUnidad.addItem("UNIDAD EDUCATIVA MARISCAL ANTONIO JOSE DE SUCRE");
            SelUnidad.addItem("UNIDAD EDUCATIVA COMUNITARIA INTERCULTURAL BILINGÜE SOCTA");
            SelUnidad.addItem("UNIDAD EDUCATIVA GUASUNTOS");
            SelUnidad.addItem("ANTONIO ANTE");
            SelUnidad.addItem("MIGUEL DE LETAMENDI");
            SelUnidad.addItem("UNIDAD EDUCATIVA ELOY ALFARO");
            SelUnidad.addItem("UNIDAD EDUCATIVA MULTITUD");
            SelUnidad.addItem("UNIDAD EDUCATIVA SIBAMBE");
            SelUnidad.addItem("JUAN FRANCISCO YEROVI");
            SelUnidad.addItem("UNIDAD EDUCATIVA PCEI JAIME ROLDÓS AGUILERA");
        }

        if (sector==1){
            SelUnidad.removeAllItems();
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA LEOPOLDO FREIRE");
            SelUnidad.addItem("COLEGIO DE BACHILLERATO CHAMBO");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA CACIQUE ACHAMBA");
        }

        if (sector==2){
            SelUnidad.removeAllItems();
            SelUnidad.addItem("UNIDAD EDUCATIVA CHUNCH");
            SelUnidad.addItem("UNIDAD EDUCATIVA FISCOMISIONAL MARIA AUXILIADORA FE Y ALEGR");
            SelUnidad.addItem("ESCUELA DE EDUCACIÓN BÁSICA MONTEVIDEO");
            SelUnidad.addItem("UNIDAD EDUCATIVA COMPUD");
            SelUnidad.addItem("UNIDAD EDUCATIVA GONZOL");
            SelUnidad.addItem("UNIDAD EDUCATIVA QUITUMBE");
        }

        if (sector==3){
            SelUnidad.removeAllItems();
            SelUnidad.addItem("UNIDAD EDUCATIVA COMUNITARIA INTERCULTURAL BILINGÜE HERMEL TAYUPANDA");
            SelUnidad.addItem("UNIDAD EDUCATIVA COMUNITARIA INTERCULTURAL BILINGÜE JAIME ROLDOS A");
            SelUnidad.addItem("CENTRO EDUCATIVO COMUNITARIO INTERCULTURAL BILINGÜE DE EDU");
            SelUnidad.addItem("UNIDAD EDUCATIVA COMUNITARIA INTERCULTURAL BILINGÜE HUALCO");
            SelUnidad.addItem("CECIBEB ESTANISLAO ZAMBRANO");
            SelUnidad.addItem("CECIBEB CARLOS ARTURO LEON");
            SelUnidad.addItem("CENTRO EDUCATIVO COMUNITARIO INTERCULTURAL BILINGÜE DE EDU");
            SelUnidad.addItem("UNIDAD EDUCATIVA COMUNITARIA INTERCULTURAL BILINGUE DR. MA");
            SelUnidad.addItem("UNIDAD EDUCATIVA COMUNITARIA INTERCULTURAL BILINGÜE SAN GU");
            SelUnidad.addItem("UNIDAD EDUCATIVA COMUNITARIA INTERCULTURAL BILINGÜE MARIA");
            SelUnidad.addItem("UNIDAD EDUCATIVA COMUNITARIA INTERCULTURAL BILINGÛE SANTIA");
            SelUnidad.addItem("UNIDAD EDUCATIVA TOMAS OLEAS");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA CHACABAMBA");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA DR JOSE MARIANO BORJA");
            SelUnidad.addItem("UNIDAD EDUCATIVA 28 DE AGOSTO");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA JUAN ADALBERTO ARAUJO");
            SelUnidad.addItem("UNIDAD EDUCATIVA GALAPAGOS");
            SelUnidad.addItem("UNIDAD EDUCATIVA 24 DE MAYO");
            SelUnidad.addItem("UNIDAD EDUCATIVA 10 DE AGOSTO");
            SelUnidad.addItem("UNIDAD EDUCATIVA CICALPA");
        }

        if (sector==4){
            SelUnidad.removeAllItems();
            SelUnidad.addItem("ESCUELA DE EDUCACIÓN BÁSICA CORNELIO DAVALOS DONOSO");
            SelUnidad.addItem("UNIDAD EDUCATIVA CHIMBORAZO PCEI");
            SelUnidad.addItem("UNIDAD EDUCATIVA CELSO AUGUSTO RODRÍGUEZ");
            SelUnidad.addItem("ESCUELA DE EDUCACIÓN BÁSICA MANUEL QUIROGA");
            SelUnidad.addItem("UNIDAD EDUCATIVA CUMANDÁ");
            SelUnidad.addItem("ESCUELA DE EDUCACIÓN BÁSICA MARISCAL SUCRE");
            SelUnidad.addItem("UNIDAD EDUCATIVA SULTANA DE LOS ANDES");
            SelUnidad.addItem("ESCUELA DE EDUCACIÓN BÁSICA ANTONIO NARIÑO");
            SelUnidad.addItem("ESCUELA DE EDUCACIÓN BÁSICA REMIGIO CRESPO TORAL");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA OFELIA REYES CAJAS");
            SelUnidad.addItem("ESCUELA DE EDUCACIÓN BÁSICA 28 DE ENERO");
            SelUnidad.addItem("ESCUELA DE EDUCACIÓN BÁSICA LUZ MARÍA DONOSO");
        }

        if (sector==5){
            SelUnidad.removeAllItems();
            SelUnidad.addItem("UNIDAD EDUCATIVA COMUNITARIA INTERCULTURAL BILINGÜE ATAHU");
            SelUnidad.addItem("CECIBEB PEDRO MONTERO");
            SelUnidad.addItem("JHON F. KENNEDY");
            SelUnidad.addItem("CENTRO EDUCATIVO COMUNITARIO INTERCULTURAL BILINGÜE DE ED");
            SelUnidad.addItem("UNIDAD EDUCATIVA COMUNITARIA INTERCULTURAL BILINGUE RUMIÑ");
            SelUnidad.addItem("MANUEL LASSO GUZÑAY");
            SelUnidad.addItem("UNIDAD EDUCATIVA COMUNITARIA INTERCULTURAL BILINGÜE ELOY A");
            SelUnidad.addItem("UNIDAD EDUCATIVA COMUNITARIA INTERCULTURAL BILINGÜE COCISA");
            SelUnidad.addItem("UNIDAD EDUCATIVA COMUNITARIA INTERCULTURAL BILINGÛE BATALLA");
            SelUnidad.addItem("SALVADOR BUSTAMANTE CELI");
            SelUnidad.addItem("UNIDAD EDUCATIVA COMUNITARIA INTERCULTURAL BILINGÜE ACHUL");
            SelUnidad.addItem("UNIDAD EDUCATIVA COMUNITARIA INTERCULTURAL BILINGÜE DR. PO");
            SelUnidad.addItem("UNIDAD EDUCATIVA COMUNITARIA INTERCULTURAL BILINGUE ÑUKAN");
            SelUnidad.addItem("INTI RAYMI");
            SelUnidad.addItem("UNIDAD EDUCATIVA COMUNITARIA INTERCULTURAL BILINGÜE MARTH");
            SelUnidad.addItem("UNIDAD EDUCATIVA COMUNITARIA INTERCULTURAL BILINGÜE 23 DE");
            SelUnidad.addItem("CECIBEB ATAPO SANTA ELENA");
            SelUnidad.addItem("UNIDAD EDUCATIVA COMUNITARIA INTERCULTURAL BILINGÜE DANIE");
            SelUnidad.addItem("UNIDAD EDUCATIVA COMUNITARIA INTERCULTURAL BILINGÜE HEROES");
            SelUnidad.addItem("CENTRO EDUCATIVO COMUNITARIO INTERCULTURAL BILINGÜE DE ED");
            SelUnidad.addItem("UNIDAD EDUCATIVA COMUNITARIA INTERCULTURAL BILINGÜE NACION");
            SelUnidad.addItem("16 DE MARZO");
            SelUnidad.addItem("UNIDAD EDUCATIVA VELASCO IBARRA");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA 29 DE JUNIO");
            SelUnidad.addItem("UNIDAD EDUCATIVA DR EMILIO UZCATEGU");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA OBISPO PLAZA");
            SelUnidad.addItem("UNIDAD EDUCATIVA DEL MILENIO GUARDIANA DE LA LENGUA 27 DE F");
            SelUnidad.addItem("UNIDAD EDUCATIVA 29 DE SEPTIEMBRE");
            SelUnidad.addItem("CENTRO EDUCATIVO COMUNITARIO INTERCULTURAL BILINGÜE DE ED");
        }

        if (sector==6){
            SelUnidad.removeAllItems();
            SelUnidad.addItem("UNIDAD EDUCATIVA DR ALFREDO PEREZ GUERRERO");
            SelUnidad.addItem("JOSE MARIA VELAZ");
            SelUnidad.addItem("UNIDAD EDUCATIVA DR GABRIEL GARCÍA MORENO");
            SelUnidad.addItem("UNIDAD EDUCATIVA DEL MILENIO GUANO");
            SelUnidad.addItem("UNIDAD EDUCATIVA DR MANUEL RODRIGUEZ OROZCO");
            SelUnidad.addItem("UNIDAD EDUCATIVA ONCE DE NOVIEMBRE");
            SelUnidad.addItem("UNIDAD EDUCATIVA SAN PABLO");
            SelUnidad.addItem("ESCUELA DE EDUCACIÓN BÁSICA BOLIVAR CHIRIBOGA BAQUERO");
            SelUnidad.addItem("UNIDAD EDUCATIVA SAN ANDRES");
            SelUnidad.addItem("UNIDAD EDUCATIVA TUNTATACTO");
            SelUnidad.addItem("UNIDAD EDUCATIVA BATZACÓN");
            SelUnidad.addItem("UNIDAD EDUCATIVA FISCOMISIONAL JOSE MARIA VELAZ, SJ – IRFEYAL –");
            SelUnidad.addItem("UNIDAD EDUCATIVA SAN GERARDO");
            SelUnidad.addItem("UNIDAD EDUCATIVA SAN ISIDRO");
            SelUnidad.addItem("UNIDAD EDUCATIVA RUMIÑAHUI");
            SelUnidad.addItem("UNIDAD EDUCATIVA JOSÉ ENRIQUE RODÓ");
            SelUnidad.addItem("ESCUELA DE EDUCACIÓN BÁSICA JOSÉ ANTONIO LIZARZABURU");
        }

        if (sector==7){
            SelUnidad.removeAllItems();
            SelUnidad.addItem("UNIDAD EDUCATIVA DR GONZALO OLEAS ZAMBRANO");
            SelUnidad.addItem("UNIDAD EDUCATIVA CARLOS MARIA DE LA CONDAMINE");
            SelUnidad.addItem("UNIDAD EDUCATIVA PROVINCIA DE CHIMBORAZO");
            SelUnidad.addItem("ESCUELA DE EDUCACIÓN BÁSICA LUZ DE AMERICA");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA EL TABERNACULO");
        }

        if (sector==8){
            SelUnidad.removeAllItems();
            SelUnidad.addItem("UNIDAD EDUCATIVA DEL MILENIO PENIPE");
            SelUnidad.addItem("ESCUELA DE EDUCACIÓN BÁSICA PRINCESA TOA");
            SelUnidad.addItem("CARLOS MONTUFAR");
            SelUnidad.addItem("UNIDAD EDUCATIVA MANUEL ALVAREZ MENDEZ");
        }

        if (sector==9){
            SelUnidad.removeAllItems();
            SelUnidad.addItem("UNIDAD EDUCATIVA INTERCULTURAL BILINGUE MONSEÑOR LEONIDA");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA INTERCULTURAL BILINGUE CACHA DU");
            SelUnidad.addItem("CECIBEB LIZARDO GARCIA");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA INTERCULTURAL BILINGUE SAN XAVI");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA INTERCULTURAL BILINGUE 13 DE JUN");
            SelUnidad.addItem("CONSOLATA 92");
            SelUnidad.addItem("UNIDAD EDUCATIVA INTERCULTURAL BILINGÜE PCEI PACHAYACHACH");
            SelUnidad.addItem("UNIDAD EDUCATIVA JEFFERSON");
            SelUnidad.addItem("UNIDAD EDUCATIVA DR NICANOR LARREA LEON");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA DR LEONIDAS GARCIA ORTIZ");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA GRAL JUAN LAVALLE");
            SelUnidad.addItem("UNIDAD EDUCATIVA NUESTRA SEÑORA DE FATIMA");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA 21 DE ABRIL");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA SAN FELIPE NERI");
            SelUnidad.addItem("UNIDAD EDUCATIVA CAMILO GALLEGOS TOLEDO");
            SelUnidad.addItem("UNIDAD EDUCATIVA PCEI CHIMBORAZO");
            SelUnidad.addItem("UNIDAD EDUCATIVA CAP EDMUNDO CHIRIBOGA");
            SelUnidad.addItem("UNIDAD EDUCATIVA PENSIONADO OLIVO");
            SelUnidad.addItem("UNIDAD EDUCATIVA RIOBAMBA");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA CAPULLITOS");
            SelUnidad.addItem("UNIDAD EDUCATIVA VICENTE ANDA AGUIRRE");
            SelUnidad.addItem("UNIDAD EDUCATIVA ONCE DE NOVIEMBRE");
            SelUnidad.addItem("COLEGIO DE BACHILLERATO PCEI LIBERTADOR");
            SelUnidad.addItem("UNIDAD EDUCATIVA LA PROVIDENCIA");
            SelUnidad.addItem("COLEGIO DE BACHILLERATO PCEI SAN PEDRO DE RIOBAMBA");
            SelUnidad.addItem("ESCUELA DE EDUCACION BÁSICA SAN MATEO");
            SelUnidad.addItem("UNIDAD EDUCATIVA CARLOS CISNEROS");
            SelUnidad.addItem("UNIDAD EDUCATIVA NUESTRO MUNDO ECO RIO");
            SelUnidad.addItem("UNIDAD EDUCATIVA JEAN PIAGET");
            SelUnidad.addItem("UNIDAD EDUCATIVA LICEO POLICIAL CHIMBORAZO");
            SelUnidad.addItem("UNIDAD EDUCATIVA SANTA MARIANA DE JESUS");
            SelUnidad.addItem("UNIDAD EDUCATIVA CRISTIANA NAZARENO");
            SelUnidad.addItem("UNIDAD EDUCATIVA SAN FELIPE NERI");
            SelUnidad.addItem("UNIDAD EDUCATIVA LEONARDO DA VINCI");
            SelUnidad.addItem("UNIDAD EDUCATIVA JUAN DE VELASCO");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA JESUS INFANTE");
            SelUnidad.addItem("UNIDAD EDUCATIVA JOSE MARIA ROMAN");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA SEMILLITAS");
            SelUnidad.addItem("UNIDAD EDUCATIVA COMBATIENTES DE TAPI");
            SelUnidad.addItem("UNIDAD EDUCATIVA MARTINIANO GUERRERO FREIRE");
            SelUnidad.addItem("UNIDAD EDUCATIVA PCEI CRUZADA SOCIAL");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA SAN FRANCISCO DE ASIS");
            SelUnidad.addItem("UNIDAD EDUCATIVA PEDRO VICENTE MALDONADO");
            SelUnidad.addItem("UNIDAD EDUCATIVA LA SALLE");
            SelUnidad.addItem("UNIDAD EDUCATIVA INTERNACIONAL SAN IGNACIO DE LOYOLA");
            SelUnidad.addItem("UNIDAD EDUCATIVA DE LAS AMERICAS");
            SelUnidad.addItem("UNIDAD EDUCATIVA MIGUEL ANGEL LEON PONTON");
            SelUnidad.addItem("UNIDAD EDUCATIVA EL DESPERTAR");
            SelUnidad.addItem("UNIDAD EDUCATIVA PENSIONADO AMERICANO INTERNATIONAL SCHO");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA DR GERMAN ABDO TOUMA");
            SelUnidad.addItem("UNIDAD EDUCATIVA AMELIA GALLEGOS DIAZ");
            SelUnidad.addItem("UNIDAD EDUCATIVA MILTON REYES");
            SelUnidad.addItem("UNIDAD EDUCATIVA SAN VICENTE DE PAUL");
            SelUnidad.addItem("UNIDAD EDUCATIVA FERNANDO DAQUILEMA");
            SelUnidad.addItem("UNIDAD EDUCATIVA MARIA AUXILIADORA");
            SelUnidad.addItem("UNIDAD EDUCATIVA THE BRITISH SCHOOL");
            SelUnidad.addItem("UNIDAD EDUCATIVA LEONTIEV VIGOTSKY");
            SelUnidad.addItem("UNIDAD EDUCATIVA FE Y ALEGRIA");
            SelUnidad.addItem("UNIDAD EDUCATIVA AMERICAN HIGH SCHOOL");
            SelUnidad.addItem("UNIDAD EDUCATIVA ISABEL DE GODIN");
            SelUnidad.addItem("UNIDAD EDUCATIVA SANTO TOMAS APOSTOL");
            SelUnidad.addItem("UNIDAD EDUCATIVA INTERNACIONAL LICEO IBEROAMERICANO");
            SelUnidad.addItem("UNIDAD EDUCATIVA MERCEDES DE JESUS MOLINA");
            SelUnidad.addItem("UNIDAD EDUCATIVA YARUQUIES");
            SelUnidad.addItem("UNIDAD EDUCATIVA ADOLFO KOLPING");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA GARCIA MORENO");
            SelUnidad.addItem("CENTRO DE EDUCACION INICIAL AMIGUITOS");
            SelUnidad.addItem("UNIDAD EDUCATIVA SIMON RODRIGUEZ");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA SHYRI I");
            SelUnidad.addItem("UNIDAD EDUCATIVA VICTOR PROAÑO CARRION");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA ANDOAS");
            SelUnidad.addItem("UNIDAD EDUCATIVA 21 DE ABRIL");
            SelUnidad.addItem("UNIDAD EDUCATIVA LICTO");
            SelUnidad.addItem("UNIDAD EDUCATIVA PCEI JOSE MARIA VELAZ - LICTO");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA CACIQUE PINTAG");
            SelUnidad.addItem("UNIDAD EDUCATIVA DANIEL LEON BORJA");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA JAVIER SAENZ");
            SelUnidad.addItem("DR. RICARDO DESCALZI");
            SelUnidad.addItem("UNIDAD EDUCATIVA CONDORAZO");
            SelUnidad.addItem("UNIDAD EDUCATIVA PURUHA");
            SelUnidad.addItem("UNIDAD EDUCATIVA RODRIGO BARRENO COBO");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA RICARDO ALFONSO DAVALOS VALDIV");
            SelUnidad.addItem("UNIDAD EDUCATIVA ESTADOS UNIDOS");
            SelUnidad.addItem("UNIDAD EDUCATIVA SAN JUAN");
            SelUnidad.addItem("UNIDAD EDUCATIVA NUEVO MUNDO");
            SelUnidad.addItem("UNIDAD EDUCATIVA BENITO JUAREZ");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA NIDIA JARAMILLO");
            SelUnidad.addItem("UNIDAD EDUCATIVA HISPANOAMERICA");
            SelUnidad.addItem("UNIDAD EDUCATIVA ANDES COLLEGE");
            SelUnidad.addItem("UNIDAD EDUCATIVA PCEI JOSE MARIA VELAZ");
            SelUnidad.addItem("CENTRO DE EDUCACIÓN INICIAL SAFARI KIDS");
            SelUnidad.addItem("ESCUELA DE EDUCACION BASICA SAN PABLO");
            SelUnidad.addItem("CENTRO DE EDUCACIÓN INICIAL EUREKA KIDS");
            SelUnidad.addItem("CENTRO DE EDUCACION INICIAL RIVER JUNIOR");
            SelUnidad.addItem("COLEGIO DE BACHILLERATO PCEI JOHANN HERBART");
            SelUnidad.addItem("UNIDAD EDUCATIVA PCEI EVEREST");
        }
    }//GEN-LAST:event_jSectorMouseClicked

    private void SelUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelUnidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SelUnidadActionPerformed
 public static Date asDate(LocalDate localDate) {
    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
  }

  public static Date asDate(LocalDateTime localDateTime) {
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

  public static LocalDate asLocalDate(Date date) {
    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
  }

  public static LocalDateTime asLocalDateTime(Date date) {
    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

  public static String edad(Date fecha_nacimiento) {
    LocalDate fecha_nacimientoLD = asLocalDate(fecha_nacimiento);
    LocalDate hoy = LocalDate.now();
    Period periodo = Period.between(fecha_nacimientoLD, hoy);
    String an = (periodo.getYears() == 1) ? "año" : "años";
    ana = periodo.getYears();
    String me = (periodo.getMonths() == 1) ? "mes" : "meses";
    mess = periodo.getMonths();

    String di = (periodo.getDays() == 1) ? "día" : "días";
    return periodo.getYears() + " " + an + ", y " + periodo.getMonths() + " " + me + ", y " + periodo.getDays() + " " + di;
     

  }
    /**
     * @param args the command line arguments
     */

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrimerasVariables.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrimerasVariables.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrimerasVariables.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrimerasVariables.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrimerasVariables().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BMISL;
    private javax.swing.JRadioButton Bfem;
    private javax.swing.JRadioButton Bmasc;
    private javax.swing.JRadioButton Brural;
    private javax.swing.JRadioButton Burbana;
    private javax.swing.JSpinner Daltura;
    private javax.swing.JSpinner Dpeso;
    private javax.swing.JPanel Header;
    private javax.swing.JTextField INDireccion;
    private javax.swing.JTextField INnombreape;
    private javax.swing.JPanel Peduca;
    private javax.swing.JPanel Pinfgene;
    private javax.swing.JPanel Presul;
    private javax.swing.JSlider SLBMI;
    private javax.swing.JComboBox<String> SelUnidad;
    private javax.swing.JComboBox<String> Selgrado;
    private javax.swing.JComboBox<String> Selparal;
    private javax.swing.JLabel bmidat;
    private javax.swing.JPanel cerrarbotn;
    private javax.swing.JLabel edad2;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel fechanaci;
    private javax.swing.ButtonGroup grupogen;
    private javax.swing.ButtonGroup gruporesiden;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JComboBox<String> jSector;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider3;
    private javax.swing.JLabel txtaltura;
    private javax.swing.JLabel txtedad;
    private javax.swing.JLabel txtfvisit1;
    private javax.swing.JLabel txtgenero;
    private javax.swing.JLabel txtgrado;
    private javax.swing.JLabel txtnaci;
    private javax.swing.JLabel txtname;
    private javax.swing.JLabel txtnameape;
    private javax.swing.JLabel txtparal;
    private javax.swing.JLabel txtpeso;
    private javax.swing.JLabel txtresiden;
    private javax.swing.JLabel txtunid;
    private javax.swing.JLabel txtunid1;
    // End of variables declaration//GEN-END:variables
}
