package edu.epn.implementacionMySQL;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import edu.epn.conexion.Conexion;
import edu.epn.dao.*;
import edu.epn.modelo.Asignatura;

import java.sql.*;
import java.util.ArrayList;

public class MySQLAsignaturaDAO implements AsignaturaDAO{
    final String INSERT="INSERT INTO asignaturas (nombre, profesor) VALUES (?,?)";
    final String UPDATE="UPDATE asignaturas set nombre = ?, profesor = ? WHERE id_asignatura= ?";
    final String DELETE="DELETE FROM asignaturas WHERE id_asignatura = ?";
    final String OBTENER="SELECT id_asignatura, nombre, profesor FROM asignaturas WHERE id_asignatura = ?";
    final String OBTENERTODOS="SELECT id_asignatura, nombre, profesor FROM asignaturas";

    private Conexion conexion;

    public MySQLAsignaturaDAO(Conexion conexion){
        this.conexion = conexion;
    }

    @Override
    public void eliminar(Asignatura t) throws ExceptionDAO {
        conexion.establecerConexion();
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=conexion.getConnection().prepareStatement(DELETE);
            preparedStatement.setLong(1, t.getId());
            if(preparedStatement.executeUpdate()==0){
                System.out.println("NO SE HA ELIMINADO LA ASIGNATURA");
            }else{
                System.out.println("ASIGNATURA ELIMINADA CORRECTAMENTE");
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

    
    private Asignatura convertir(ResultSet r) throws SQLException{
        String nombre = r.getString("nombre");
        Long profesor = r.getLong("profesor");
        Asignatura asignatura = new Asignatura(nombre,profesor);
        asignatura.setId(r.getLong("id_asignatura"));
        return asignatura;
    }

    @Override
    public void insertar(Asignatura t) throws ExceptionDAO {
        conexion.establecerConexion();
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=conexion.getConnection().prepareStatement(INSERT);
            preparedStatement.setString(1, t.getNombre());
            preparedStatement.setLong(2, t.getIdProfesor());
            if(preparedStatement.executeUpdate()==0){
                System.out.println("NO SE HA INSERTADO LA ASIGNATURA");
            }else{
                System.out.println("ASIGNATURA INSERTADO CORRECTAMENTE");
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
    public void modificar(Asignatura t) throws ExceptionDAO {
        conexion.establecerConexion();
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=conexion.getConnection().prepareStatement(UPDATE);
            preparedStatement.setString(1, t.getNombre());
            preparedStatement.setLong(2, t.getIdProfesor());
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
    public Asignatura obtener(Long id) throws ExceptionDAO {
        conexion.establecerConexion();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Asignatura asignatura=null;
        try {
            preparedStatement=conexion.getConnection().prepareStatement(OBTENER);
            preparedStatement.setLong(1, id);
            System.out.println("ASIGNATURA ENCONTRADO EXITOSAMENTE");
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                asignatura=new Asignatura(resultSet.getLong("id_asignatura"), resultSet.getString("nombre"), resultSet.getLong("profesor"));
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
        return asignatura;
    }

    @Override
    public List<Asignatura> obtenerTodos() throws ExceptionDAO {
        Statement statement=null;
        ResultSet resultSet=null;
        List<Asignatura> asignaturas = new ArrayList<>();
        try {
            statement = conexion.getConnection().createStatement();
            resultSet=statement.executeQuery(OBTENERTODOS);
            while(resultSet.next()){
                asignaturas.add(convertir(resultSet));
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
        return asignaturas;
    }
}
