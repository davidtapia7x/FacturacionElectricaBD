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
	
	
	public ArrayList impresion(String consulta) throws SQLException{
                sentencia = conexion.createStatement();
                impresionR = sentencia.executeQuery(consulta);
		metaData = impresionR.getMetaData();
		int columnas = metaData.getColumnCount();
                
                //List<List<Object>> result = new ArrayList<>();  // List of list, one per row
                ArrayList ayuda= new ArrayList(0);
                
                //List<Object> header = new ArrayList<>(columnas);
                String[] header= new String[columnas];
                
                for(int i =1; i <=columnas;i++ ){
                    System.out.println(metaData.getColumnName(i));
                    header[i-1]=metaData.getColumnName(i);
                    //header.add(metaData.getColumnName(i));
                }
                //result.add(header);
                ayuda.add(header);
                while (impresionR.next()) {
                    //List<Object> row = new ArrayList<>(columnas); // new list per row
                    String[] fila= new String[columnas];
                    int i = 1;
                    while (i <= columnas) {  // don't skip the last column, use <=
                        //row.add(impresionR.getObject(i));
                        fila[i-1]=impresionR.getString(i);
                        if(impresionR.getString(i)!=null){
                            System.out.println(impresionR.getObject(i).toString());
                        }else{
                            System.out.println("null");
                        }
                        i++;
                    }
                    ayuda.add(fila); // add it to the result
                }
                return ayuda;
	}
	/*public static void main(String [] andres){
	conexión prueba = new conexión();
	try{
            try {
                prueba.conectar();
                ArrayList a = prueba.impresion("SELECT * FROM CALLES");
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(conexión.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
	catch(SQLException sqlExepcion){
		System.out.println(sqlExepcion);
	}
	}*/	
}
