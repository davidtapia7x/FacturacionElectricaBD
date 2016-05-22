package clases;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class conexión{
	
	private Statement sentencia;
	private Connection conexion;
	private ResultSet impresionR;
	private ResultSetMetaData metaData;
	
	public void conectar() throws SQLException, ClassNotFoundException{
		conexion = null;
		Class.forName ("oracle.jdbc.driver.OracleDriver");
		String user = "FACTURACIONELECTRICA";
		String pass = "basededatos2016";
		String url  = "jdbc:oracle:thin:@localhost:1521:XE";
		conexion = DriverManager.getConnection(url,user,pass);
		System.out.println("Conexion exitosa: ");	
                
	}
	
	public void modificaciones() throws SQLException{ 
		
		try{
		/*SimpleDateFormat formatoFecha = new SimpleDateFormat("y/MM/d");
                java.util.Date fecha = new java.util.Date();
                fecha = formatoFecha.parse("1980/5/18");
                String fechaN = formatoFecha.format(fecha).toString();*/
		
			
		System.out.println("consulta calles");	
		System.out.println("---------------");
		sentencia = conexion.createStatement();
		impresion();	
			/*
		System.out.println("Despues de la eliminacion con codigo 11");	
		System.out.println("---------------------------------------");
		String eliminarC = "delete from sistema.alumnos where AL_CODIGO=11";
		sentencia = conexion.createStatement();
		sentencia.execute(eliminarC);
		impresion();
		
		
		System.out.println("Creacion con codigo 11");	
		System.out.println("----------------------");
		String insertar = "insert into sistema.alumnos (AL_CODIGO,AL_APELLIDOS, AL_NOMBRES) VALUES (11,'MESSI','LEONEL')";
		sentencia = conexion.createStatement();
		sentencia.executeUpdate(insertar);
		impresion();
	
		System.out.println("Actualizar fecha con codigo 11");	
		System.out.println("------------------------------");
		String actualizarF = "update sistema.alumnos set AL_FEC_NAC = '" + fechaN + "' WHERE AL_CODIGO = 11";
		sentencia = conexion.createStatement();
		sentencia.executeUpdate(actualizarF);
		impresion();
	*/
		}
		catch(Exception e){
		}
	}
	
        
	public void impresion() throws SQLException{
		impresionR = sentencia.executeQuery("SELECT * FROM CALLES");
		metaData = impresionR.getMetaData();
		int columnas = metaData.getColumnCount();
         
		
                
                List<List<Object>> result = new ArrayList<>();  // List of list, one per row
                
                List<Object> header = new ArrayList<>(columnas);
                for(int i =1; i <=columnas;i++ ){
                    System.out.println(metaData.getColumnName(i));
                    header.add(metaData.getColumnName(i));
                }
                result.add(header);
                while (impresionR.next()) {
                    List<Object> row = new ArrayList<>(columnas); // new list per row
                    int i = 1;
                    while (i <= columnas) {  // don't skip the last column, use <=
                        row.add(impresionR.getObject(i));
                        System.out.println(impresionR.getObject(i).toString());
                        i++;
                    }
                    result.add(row); // add it to the result
                }
            
	}
	public static void main(String [] andres){
	conexión prueba = new conexión();
	try{
            try {
                prueba.conectar();
                prueba.modificaciones();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(conexión.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
	catch(SQLException sqlExepcion){
		System.out.println(sqlExepcion);
	}
	}	
}
