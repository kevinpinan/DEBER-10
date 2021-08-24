package edu.epn.implementacionMySQL;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import edu.epn.conexion.Conexion;
import edu.epn.dao.*;
import edu.epn.modelo.Profesor;

import java.sql.*;
import java.util.ArrayList;


public class MySQLProfesorDAO implements ProfesorDAO{
    final String INSERT="INSERT INTO profesores (nombre, apellidos) VALUES (?,?)";
    final String UPDATE="UPDATE profesores set nombre = ?, apellidos = ? WHERE id_profesor = ?";
    final String DELETE="DELETE FROM profesores WHERE id_profesor = ?";
    final String OBTENER="SELECT id_profesor, nombre, apellidos FROM profesores WHERE id_profesor = ?";
    final String OBTENERTODOS="SELECT id_profesor, nombre, apellidos FROM profesores";

    private Conexion conexion;

    public MySQLProfesorDAO(Conexion conexion){
        this.conexion = conexion;
    }

    @Override
    public void eliminar(Profesor t) throws ExceptionDAO {
        conexion.establecerConexion();
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=conexion.getConnection().prepareStatement(DELETE);
            preparedStatement.setLong(1, t.getId());
            if(preparedStatement.executeUpdate()==0){
                System.out.println("NO SE HA ELIMINADO EL ALUMNO");
            }else{
                System.out.println("PROFESOR ELIMINADO CORRECTAMENTE");
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("ERROR EN LA SENTENCIA SQL",e);
        }finally{
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new ExceptionDAO("ERROR EN EL SQL",e);
                }
            }
        }
        conexion.cerrarConexion();
    }

    private Profesor convertir(ResultSet r) throws SQLException{
        String nombre = r.getString("nombre");
        String apellido = r.getString("apellidos");
        Profesor profesor = new Profesor(nombre,apellido);
        profesor.setId(r.getLong("id_profesor"));
        return profesor;
    }


    @Override
    public void insertar(Profesor t) throws ExceptionDAO {
        conexion.establecerConexion();
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=conexion.getConnection().prepareStatement(INSERT);
            preparedStatement.setString(1, t.getNombre());
            preparedStatement.setString(2, t.getApellidos());
            if(preparedStatement.executeUpdate()==0){
                System.out.println("NO SE HA INSERTADO EL ALUMNO");
            }else{
                System.out.println("PROFESOR INSERTADO CORRECTAMENTE");
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("ERROR EN LA SENTENCIA SQL",e);
        }finally{
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new ExceptionDAO("ERROR EN EL SQL",e);
                }
            }
        }
        conexion.cerrarConexion();
    }

    @Override
    public void modificar(Profesor t) throws ExceptionDAO {
        conexion.establecerConexion();
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=conexion.getConnection().prepareStatement(UPDATE);
            preparedStatement.setString(1, t.getNombre());
            preparedStatement.setString(2, t.getApellidos());
            preparedStatement.setLong(3, t.getId());
            if (preparedStatement.executeUpdate()==0) {
                System.out.println("NO SE A MODIFICADO CORRECTAMENTE");
            }else{
                System.out.println("MODIFICACION EXITOSA");
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("ERROR EN LA SENTENCIA SQL",e);
        }finally{
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new ExceptionDAO("ERROR EN EL SQL",e);
                }
            }
        }
        conexion.cerrarConexion();   
    }

    @Override
    public Profesor obtener(Long id) throws ExceptionDAO {
        conexion.establecerConexion();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Profesor profesor=null;
        try {
            preparedStatement=conexion.getConnection().prepareStatement(OBTENER);
            preparedStatement.setLong(1, id);
            System.out.println("PROFESOR ENCONTRADO EXITOSAMENTE");
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                profesor=new Profesor(resultSet.getLong("id_profesor"), resultSet.getString("nombre"), resultSet.getString("apellidos"));
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("ERROR EN LA SENTENCIA SQL",e);
        }finally{
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new ExceptionDAO("ERROR EN EL SQL",e);
                }
            }
        }
        conexion.cerrarConexion();
        return profesor;
    }

    @Override
    public List<Profesor> obtenerTodos() throws ExceptionDAO {
        Statement statement=null;
        ResultSet resultSet=null;
        List<Profesor> profesores = new ArrayList<>();
        try {
            statement = conexion.getConnection().createStatement();
            resultSet=statement.executeQuery(OBTENERTODOS);
            while(resultSet.next()){
                profesores.add(convertir(resultSet));
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("ERROR EN SQL",e);
        }finally{
            if (resultSet!=null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new ExceptionDAO("ERROR EN SQL",e);
                }
            }
            if (statement!=null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new ExceptionDAO("ERROR EN SQL",e);
                }
            }
        }
        conexion.cerrarConexion();
        return profesores;
    } 
}