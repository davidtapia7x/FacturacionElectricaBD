package clases;

import java.sql.*;
import java.text.SimpleDateFormat;
public class conexión{
	
	private Statement sentencia;
	private Connection conexion;
	private ResultSet impresionR;
	private ResultSetMetaData metaData;
	
	public void conectar() throws SQLException{
		conexion = null;
		
		String bd = "sistema";
		String user = "andres";
		String pass = "UD4.10";
		String url  = "jdbc:mysql://localhost/" + bd;
		
		//try{
			conexion = DriverManager.getConnection(url,user,pass);
			System.out.println("Conexion exitosa: "+ bd);
			
		//}
		//catch(SQLException sqlExepcion){
		//System.out.println(sqlExepcion);
		//}
		
	}
	
	public void modificaciones() throws SQLException{ 
		
		try{
		SimpleDateFormat formatoFecha = new SimpleDateFormat("y/MM/d");
        java.util.Date fecha = new java.util.Date();
        fecha = formatoFecha.parse("1980/5/18");
        String fechaN = formatoFecha.format(fecha).toString();
		
			
		System.out.println("Antes de la eliminacion con codigo 11");	
		System.out.println("-------------------------------------");
		sentencia = conexion.createStatement();
		impresion();	
			
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
	
		}
		catch(Exception e){
		}
	}
	
	public void impresion() throws SQLException{
		impresionR = sentencia.executeQuery("select * from sistema.alumnos");
		metaData = impresionR.getMetaData();
		int columnas = metaData.getColumnCount();
		System.out.println(metaData.getColumnName(1)+ "\t" + metaData.getColumnName(2) + "\t" +metaData.getColumnName(3) +"\t" +metaData.getColumnName(7));
		
		while(impresionR.next()){
			
			String codA = impresionR.getString("AL_codigo");
            String apeA = impresionR.getString("AL_APELLIDOS");
            String nomA = impresionR.getString("AL_NOMBRES");
            Date fechaNA = impresionR.getDate("AL_FEC_NAC");
            
            	System.out.printf("%10s",codA);
            	System.out.printf("%20s",apeA);
            	System.out.printf("%15s",nomA);
            	System.out.printf("%15s",fechaNA);
				System.out.println("");
		}
		
	}
	public static void main(String [] andres){
	conexión prueba = new conexión();
	try{
	prueba.conectar();
	prueba.modificaciones();
	}
	catch(SQLException sqlExepcion){
		System.out.println(sqlExepcion);
	}
	}	
}
