
import clases.conexión;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Andrés Campoverde <andrescamp_ac at hotmail.com>
 */
public class generarFactura extends javax.swing.JDialog {

    /**
     * Creates new form generarFactura
     */
    public generarFactura(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        entradaCodigo.requestFocus();
    }
    private void limpiar_datos(){
        textoDireccion.setText("");
        DefaultTableModel modelo = (DefaultTableModel)tablaServicio.getModel();//new DefaultTableModel(null, titulos);
        modelo.setRowCount(0);
        tablaServicio.setModel(modelo);
        entradaCedula.setText("");
        entradaCodigo.setText("");
        entradaFecha.setText("");
        entradaNFactura.setText("");
        entradaNombre.setText("");
        entradaTarifa.setText("");
        entradaTelefono.setText("");
        entradaCodigo.requestFocus();
    }
    ///ESTOS DEBERIAN IR EN CLASE DE CONEXION?
    private int obtener_num_fac(){
        int valor=1;
        conexión prueba = new conexión();
        try {
            try {
                prueba.conectar();
                ArrayList a = prueba.impresion("SELECT MAX(NUM_FAC_SERV) " +
                                                "FROM FACTURA_SERVICIO");
                String registro[] = (String[]) a.get(1);
                if(registro[0]!=null){
                    valor = Integer.parseInt(registro[0]);
                    valor++;
                }
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(conexión.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException sqlExepcion) {
            System.out.println(sqlExepcion);
        }
        
        return valor;
    }
    private float obtener_tarifa(String cod_uso){
        float valor=0;
        conexión prueba = new conexión();
        try {
            try {
                prueba.conectar();
                ArrayList a = prueba.impresion("SELECT TARIFA.TARIFA FROM TARIFA,USOS WHERE TARIFA.COD_USO =USOS.CODUSOS AND USOS.DESCRIPCIONUSO='"+cod_uso+"'");
                String registro[] = (String[]) a.get(1);
                if(registro[0]!=null){
                    valor = Float.parseFloat(registro[0]);
                }else{
                    JOptionPane.showMessageDialog(null,"No existe esa tarifa!");
                }
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(conexión.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException sqlExepcion) {
            System.out.println(sqlExepcion);
        }
        
        return valor;
    }
    public void cargarTabla2(ArrayList datos,String desc_uso) {
        float tarifa = obtener_tarifa(desc_uso);
        tarifa = Math.round(tarifa*(float)100.0)/(float)100.0;
        //String[] titulos = {"Lectura","Consumo","Valor ($)","Pagar"};
        DefaultTableModel modelo = (DefaultTableModel)tablaServicio.getModel();//new DefaultTableModel(null, titulos);
        Object registro[] = new Object[4];
        float valor_consumo = 0,cons=0;
        for (int i = 1; i < datos.size(); i++) {
            
            String Lectura[] = (String[]) datos.get(i);
            registro[0] = (String)Lectura[0];
            cons = calcular_consumo(Integer.parseInt(Lectura[0]));
            registro[1] = (String)Float.toString(cons);
            //Calcular valor
            valor_consumo = cons*tarifa;
            registro[2] = (String)Float.toString(valor_consumo);
            //Colocar booleano
            registro[3] = (boolean) false;
            modelo.addRow(registro);
        }
        tablaServicio.setModel(modelo);  //Nombre de la tabla    
    }
    private String obtener_nom_calle(String cod){
        String nombre="";
        conexión prueba = new conexión();
        try {
            try {
                prueba.conectar();
                ArrayList a = prueba.impresion("select \"CALLES\".\"NOMBRECALLE\" " +
                                                "FROM CALLES " +
                                                "WHERE CALLES.CODCALLES='"+cod+"'");
                String registro[] = (String[]) a.get(1);
                nombre = registro[0];
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(conexión.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException sqlExepcion) {
            System.out.println(sqlExepcion);
        }
        return nombre;
    } 
    private String obtener_fecha(){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return dateFormat.format(date); //2014/08/06 15:59:48
    }
    private float calcular_consumo(int codigo_lectura){
        float consumo = 0,consumo_anterior=0;
        conexión prueba = new conexión();
        //consumo
        try {
                try {
                    prueba.conectar();
                    ArrayList a = prueba.impresion("SELECT LECTURA.VALOR_LECTURA " +
                                                    "FROM LECTURA WHERE LECTURA.NUM_LECTURA="+Integer.toString(codigo_lectura));
                    String registro[] = (String[]) a.get(1);
                    if(registro[0]!=null){
                        consumo = Float.parseFloat(registro[0]);
                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(conexión.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException sqlExepcion) {
                System.out.println(sqlExepcion);
        }
        
   
        if(codigo_lectura!=1){
            try {
                try {
                    prueba.conectar();
                    ArrayList a = prueba.impresion("SELECT LECTURA.VALOR_LECTURA " +
                                                    "FROM LECTURA WHERE LECTURA.NUM_LECTURA="+Integer.toString(codigo_lectura-1));
                    String registro[] = (String[]) a.get(1);
                    if(registro[0]!=null){
                        consumo_anterior = Float.parseFloat(registro[0]);
                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(conexión.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException sqlExepcion) {
                System.out.println(sqlExepcion);
            }
        }else if(codigo_lectura==1){
            try {
                try {
                    prueba.conectar();
                    ArrayList a = prueba.impresion("SELECT MEDIDOR.CONSUMO_INICIAL " +
                                                    "FROM MEDIDOR, LECTURA WHERE MEDIDOR.COD_MEDIDOR=LECTURA.COD_MEDIDOR and LECTURA.NUM_LECTURA="+Integer.toString(codigo_lectura));
                    String registro[] = (String[]) a.get(1);
                    if(registro[0]!=null){
                        consumo_anterior = Float.parseFloat(registro[0]);
                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(conexión.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException sqlExepcion) {
                System.out.println(sqlExepcion);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Lectura inexistente");
        }
        consumo -= consumo_anterior;
        return consumo;
    }
    public void cargarDatos(ArrayList datos) {
        if(datos.size()>1){
            int numero_factura = obtener_num_fac();
            //cod medidor, desc uso, calle1, calle 2, calle 3, ced id, nombre appellido1, appleido 2, telefono
            String registro[] = (String[]) datos.get(1);
            entradaTarifa.setText(registro[1]);
            entradaCedula.setText(registro[5]);
            entradaFecha.setText(obtener_fecha());
            entradaNombre.setText(registro[6] + " " + registro[7] +" "+registro[8].charAt(0));
            textoDireccion.setText(obtener_nom_calle(registro[2])+" "+obtener_nom_calle(registro[3])+" "+obtener_nom_calle(registro[4]));
            entradaNFactura.setText(Integer.toString(numero_factura));
            entradaTelefono.setText(registro[9]);
            //CARGAR LECTURAS NO PAGADAS.
            conexión prueba = new conexión();
            try {
                try {
                    prueba.conectar();
                    ArrayList a = prueba.impresion("select LECTURA.NUM_LECTURA from LECTURA\n" +
                                                "where COD_MEDIDOR = '"+registro[0]+"' " +
                                                "minus\n" +
                                                "(select LEC_FACT_PAGADA.NUM_LECTURA \n" +
                                                "from LEC_FACT_PAGADA\n" +
                                                "where COD_MEDIDOR = '"+registro[0]+"')");
                    if(a.size()>1){
                        cargarTabla2(a,registro[1]);
                    }else{
                        JOptionPane.showMessageDialog(null,"No existen lecturas aun!");
                        limpiar_datos();
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(conexión.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException sqlExepcion) {
                System.out.println(sqlExepcion);
            }
        }else{
            JOptionPane.showMessageDialog(null,"No existe el medidor indicado");
            limpiar_datos();
        }
        
    }
    public void agregarFactServPag(String lect){
        conexión prueba = new conexión();
            try {
                try {
                    prueba.conectar();
                    //ArrayList a = prueba.impresion("select MEDIDOR.COD_MEDIDOR as \"Codigo Medidor\", 	 USOS.DESCRIPCIONUSO as \"DESCRIPCIONUSO\", 	 PREDIO.CALLE1 as \"CALLE1\", 	 PREDIO.CALLE2 as \"CALLE2\", 	 PREDIO.CALLE3 as \"CALLE3\", 	 PREDIO.CIDENTIDAD as \"CIDENTIDAD\", 	 PROPIETARIO.NOMBRE1 as \"NOMBRE1\", 	 PROPIETARIO.APELLIDO1 as \"APELLIDO1\", 	 PROPIETARIO.APELLIDO2 as \"APELLIDO2\", 	 PROPIETARIO.TELEFONO as \"TELEFONO\"   from	 USOS, 	 PROPIETARIO, 	 PREDIO, 	 MEDIDOR where   MEDIDOR.CLAVECATASTRAL=PREDIO.CLAVECATASTRAL and	 PREDIO.CIDENTIDAD=PROPIETARIO.IDENT and	 PREDIO.CDUSO=USOS.CODUSOS and 	 MEDIDOR.COD_MEDIDOR = '400'");
                    prueba.modificar("insert into LEC_FACT_PAGADA (NUM_LECTURA,COD_MEDIDOR, NUM_FAC_SERV) VALUES ("+lect+",'"+entradaCodigo.getText()+"',"+entradaNFactura.getText()+")");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(conexión.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException sqlExepcion) {
                System.out.println(sqlExepcion);
            }
    }
    public void generarFact(float tot, int numfilas,int seleccionadas)
    {
        if(tot>0){
            conexión prueba = new conexión();
            try {
                try {
                    prueba.conectar();
                    //ArrayList a = prueba.impresion("select MEDIDOR.COD_MEDIDOR as \"Codigo Medidor\", 	 USOS.DESCRIPCIONUSO as \"DESCRIPCIONUSO\", 	 PREDIO.CALLE1 as \"CALLE1\", 	 PREDIO.CALLE2 as \"CALLE2\", 	 PREDIO.CALLE3 as \"CALLE3\", 	 PREDIO.CIDENTIDAD as \"CIDENTIDAD\", 	 PROPIETARIO.NOMBRE1 as \"NOMBRE1\", 	 PROPIETARIO.APELLIDO1 as \"APELLIDO1\", 	 PROPIETARIO.APELLIDO2 as \"APELLIDO2\", 	 PROPIETARIO.TELEFONO as \"TELEFONO\"   from	 USOS, 	 PROPIETARIO, 	 PREDIO, 	 MEDIDOR where   MEDIDOR.CLAVECATASTRAL=PREDIO.CLAVECATASTRAL and	 PREDIO.CIDENTIDAD=PROPIETARIO.IDENT and	 PREDIO.CDUSO=USOS.CODUSOS and 	 MEDIDOR.COD_MEDIDOR = '400'");
                    prueba.modificar("insert into FACTURA_SERVICIO (NUM_FAC_SERV,ID_PROPIETARIO, ID_EMPLEADO,FECHA_PAGO,TOTAL) VALUES ("+entradaNFactura.getText()+",'"+entradaCedula.getText()+"','0106625437','"+entradaFecha.getText()+"',"+txtTotal.getText()+")");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(conexión.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException sqlExepcion) {
                System.out.println(sqlExepcion);
            }
            //String lecturas_pagadas[] = new String[seleccionadas];
            for(int i=0;i<numfilas;i++){
                if((boolean)tablaServicio.getValueAt(i, 3)){
                    agregarFactServPag((String)tablaServicio.getValueAt(i, 0));
                }
            }
        }else{
            JOptionPane.showMessageDialog(null,"No hay datos a generar!");
        }
    }
     //////////////////////

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        entradaFecha = new javax.swing.JTextField();
        entradaNombre = new javax.swing.JTextField();
        etiquetaCedula = new javax.swing.JLabel();
        etiquetaNFact = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaServicio = new javax.swing.JTable();
        botonEmitir = new javax.swing.JButton();
        entradaTelefono = new javax.swing.JTextField();
        entradaNFactura = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        etiquetaDireccion = new javax.swing.JLabel();
        entradaTarifa = new javax.swing.JTextField();
        textoDireccion = new javax.swing.JTextField();
        entradaCedula = new javax.swing.JTextField();
        etiquetaEncabezado = new javax.swing.JLabel();
        etiquetaCodigo = new javax.swing.JLabel();
        etiquetaFecha = new javax.swing.JLabel();
        etiquetaNombre = new javax.swing.JLabel();
        etiquetaTelf = new javax.swing.JLabel();
        etiquetaTarifa = new javax.swing.JLabel();
        entradaCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Facturacion");

        entradaFecha.setEditable(false);

        entradaNombre.setEditable(false);

        etiquetaCedula.setText("Cédula Cliente");

        etiquetaNFact.setText("Nro Factura: ");

        tablaServicio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lectura", "Consumo", "Valor ($)", "Pagar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaServicio);

        botonEmitir.setText("Emitir");
        botonEmitir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEmitirActionPerformed(evt);
            }
        });

        entradaTelefono.setEditable(false);

        entradaNFactura.setEditable(false);

        jLabel6.setText("Teléfono:");

        etiquetaDireccion.setText("Dirección");

        entradaTarifa.setEditable(false);

        textoDireccion.setEditable(false);

        entradaCedula.setEditable(false);

        etiquetaEncabezado.setText("Empresa Electrica");

        etiquetaCodigo.setText("Código Medidor: ");

        etiquetaFecha.setText("Fecha:");

        etiquetaNombre.setText("Nombre Cliente");

        etiquetaTelf.setText("07282938");

        etiquetaTarifa.setText("Tarifa:");

        entradaCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entradaCodigoActionPerformed(evt);
            }
        });

        jLabel1.setText("Total:");

        txtTotal.setEditable(false);
        txtTotal.setColumns(9);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaNombre)
                    .addComponent(etiquetaNFact)
                    .addComponent(etiquetaCedula))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(entradaCedula)
                    .addComponent(entradaNFactura)
                    .addComponent(entradaNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(entradaTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(etiquetaCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(entradaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(etiquetaFecha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(entradaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(etiquetaDireccion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addComponent(etiquetaTarifa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(entradaTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(294, 294, 294)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(etiquetaEncabezado)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(etiquetaTelf))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botonEmitir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(etiquetaEncabezado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiquetaTelf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNFact)
                    .addComponent(entradaNFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(entradaTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaCodigo)
                    .addComponent(entradaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaCedula)
                    .addComponent(entradaCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaDireccion)
                    .addComponent(textoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaTarifa)
                    .addComponent(entradaTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNombre)
                    .addComponent(entradaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaFecha)
                    .addComponent(entradaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonEmitir)
                    .addComponent(jLabel1)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonEmitirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEmitirActionPerformed
        //REVISAR SI ES QUE HA SELECCIONADO ITEMS A PAGAR
        TableModel tm = tablaServicio.getModel();
        float total=0;
        int seleccionadas= 0;
        if(tm.getRowCount()>=1){
            for (int i = 0; i < tm.getRowCount(); i++) {
                //System.out.println(tablaServicio.getValueAt(i, 3));
                if((boolean)tablaServicio.getValueAt(i, 3)){
                    seleccionadas++;
                    total += Float.parseFloat((String)(tablaServicio.getValueAt(i, 2)));
                }
            }
            txtTotal.setText(Float.toString(total));
            generarFact(total,tm.getRowCount(),seleccionadas);
        }else{
            JOptionPane.showMessageDialog(null,"Ingrese datos primero!");
        }
    }//GEN-LAST:event_botonEmitirActionPerformed

    private void entradaCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entradaCodigoActionPerformed
        //Realizar consulta que obtenga los datos pertinentes
        //primero generar un numero de factura nuevo
        //luego con el codigo de medidor obtener el id del propietario
        String ing_cod = entradaCodigo.getText();
        limpiar_datos();
        entradaCodigo.setText(ing_cod);
        conexión prueba = new conexión();
        try {
            try {
                prueba.conectar();
                ArrayList a = prueba.impresion("select \"MEDIDOR\".\"COD_MEDIDOR\" as \"Codigo Medidor\", " +
"	 \"USOS\".\"DESCRIPCIONUSO\" as \"DESCRIPCIONUSO\", " +
"	 \"PREDIO\".\"CALLE1\" as \"CALLE1\", " +
"	 \"PREDIO\".\"CALLE2\" as \"CALLE2\", " +
"	 \"PREDIO\".\"CALLE3\" as \"CALLE3\", " +
"	 \"PREDIO\".\"CIDENTIDAD\" as \"CIDENTIDAD\", " +
"	 \"PROPIETARIO\".\"NOMBRE1\" as \"NOMBRE1\", " +
"	 \"PROPIETARIO\".\"APELLIDO1\" as \"APELLIDO1\", " +
"	 \"PROPIETARIO\".\"APELLIDO2\" as \"APELLIDO2\", " +
"	 \"PROPIETARIO\".\"TELEFONO\" as \"TELEFONO\"  " +
" from	 \"USOS\", " +
"	 \"PROPIETARIO\", " +
"	 \"PREDIO\", " +
"	 \"MEDIDOR\" " +
"where   \"MEDIDOR\".\"CLAVECATASTRAL\"=\"PREDIO\".\"CLAVECATASTRAL\" " +
"and	 \"PREDIO\".\"CIDENTIDAD\"=\"PROPIETARIO\".\"IDENT\" " +
"and	 \"PREDIO\".\"CDUSO\"=\"USOS\".\"CODUSOS\" " +
"and 	 \"MEDIDOR\".\"COD_MEDIDOR\" = "+"'"+entradaCodigo.getText()+"'");
     
                
                cargarDatos(a);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(conexión.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException sqlExepcion) {
            System.out.println(sqlExepcion);
        }
    }//GEN-LAST:event_entradaCodigoActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

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
            java.util.logging.Logger.getLogger(generarFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(generarFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(generarFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(generarFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                generarFactura dialog = new generarFactura(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEmitir;
    private javax.swing.JTextField entradaCedula;
    private javax.swing.JTextField entradaCodigo;
    private javax.swing.JTextField entradaFecha;
    private javax.swing.JTextField entradaNFactura;
    private javax.swing.JTextField entradaNombre;
    private javax.swing.JTextField entradaTarifa;
    private javax.swing.JTextField entradaTelefono;
    private javax.swing.JLabel etiquetaCedula;
    private javax.swing.JLabel etiquetaCodigo;
    private javax.swing.JLabel etiquetaDireccion;
    private javax.swing.JLabel etiquetaEncabezado;
    private javax.swing.JLabel etiquetaFecha;
    private javax.swing.JLabel etiquetaNFact;
    private javax.swing.JLabel etiquetaNombre;
    private javax.swing.JLabel etiquetaTarifa;
    private javax.swing.JLabel etiquetaTelf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaServicio;
    private javax.swing.JTextField textoDireccion;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
