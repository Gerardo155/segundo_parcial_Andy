/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author GERARD
 */
public class CompuController {
    
    
      Compu[] tablaALumno;
    int indiceArray;
    private ConexionBaseDeDatos conectorBD;
    private Connection conexion;
    private PreparedStatement statement = null;
    private ResultSet result = null;
    
    public CompuController(){
        this.tablaALumno = new Compu[100];
        this.indiceArray=0;
    }
    
     public void guardarCompu(Compu compu){
        this.tablaALumno[this.indiceArray]=compu;  
        this.indiceArray=this.indiceArray+1;
        // procedimiento para almacenar en la Base de Datos
    }
    
    public Compu[] getCompu(){
        return this.tablaALumno;
    }
    
    public void abrirConexion(){
        conectorBD= new ConexionBaseDeDatos();
        conexion=conectorBD.conectar();
    }    
   
    
    public String guardarCompu2(Compu compu){        
        String sql = "INSERT INTO tienda.alumno(codigo_compu, marca, modelo, dimension, fecha) ";
             sql += " VALUES(?,?,?,?,?)";              
       try{     
            abrirConexion();
            statement = conexion.prepareStatement(sql); 
            statement.setInt(1, compu.getCodigo());
            statement.setString(2, compu.getMarca());
            statement.setString(3, compu.getModelo());
            statement.setString(4, compu.getDimension());
            statement.setString(5, compu.getFecha());
                int resultado = statement.executeUpdate(); 
                if(resultado > 0){
                    return String.valueOf(resultado);
                }else{
                    return String.valueOf(resultado);
                }
        }catch(SQLException e){ 
            return e.getMessage();
        }
    }
    
    public void getCompu2(StringBuffer respuesta){   
        String sql="select * from universidad.alumno";
        try{
        abrirConexion();
        respuesta.setLength(0); //Le agregue esto
        statement= conexion.prepareStatement(sql);                        
        result = statement.executeQuery();            
            if (result!=null){
                while (result.next()){
                respuesta.append("<tr>");
                respuesta.append("<td >").append(result.getString("codigo_compu")).append("</td>");
                respuesta.append("<td >").append(result.getString("marca")).append("</td>");
                respuesta.append("<td >").append(result.getString("modelo")).append("</td>");
                respuesta.append("<td >").append(result.getString("dimension")).append("</td>");
                respuesta.append("<td >").append(result.getString("fecha")).append("</td>");
                respuesta.append("<td id=\"").append(result.getString("numero_compu"))
                        .append("\"  onclick=\"eliminarCompu(this.id);\">") 
                         //.append("\"  onclick=\"eliminarAlumno("+result.getString("numero_compu")+");\">") 
                        .append(" <a class=\"btn btn-warning\"'><i class=\"fas fa-edit\"></i>  </a>"
                                +" <a class=\"btn btn-danger\"'> <i class=\"fas fa-trash-alt\"></i> </a>"
                                + " <td></tr>");
                }
            }else{
                respuesta.append("error al consultar");
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public String eliminarCompu(int compu){        
        String sql = "DELETE FROM compu WHERE numero_compu="+compu;              
       try{     
            abrirConexion();
            statement = conexion.prepareStatement(sql); 
            int resultado = statement.executeUpdate();
            if(resultado > 0){
                return String.valueOf(resultado);
            }else{
                return String.valueOf(resultado);
            }
        }catch(SQLException e){ 
            return e.getMessage();
        }
    }
    
    
    
    
}
