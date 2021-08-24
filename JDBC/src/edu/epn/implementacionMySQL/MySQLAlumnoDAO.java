package edu.epn.implementacionMySQL;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import edu.epn.conexion.Conexion;
import edu.epn.dao.*;
import edu.epn.modelo.Alumno;
import java.sql.*;
import java.util.ArrayList;

public class MySQLAlumnoDAO implements AlumnoDAO{
    final String INSERT="INSERT INTO alumnos (nombre, apellidos, fecha_nac) VALUES (?,?,?)";
    final String UPDATE="UPDATE alumnos SET nombre = ?, apellidos=? WHERE id_alumno= ?";
    final String DELETE="DELETE FROM alumnos WHERE id_alumno = ?";
    final String OBTENER="SELECT id_alumno, nombre, apellidos, fecha_nac FROM alumnos WHERE id_alumno = ?";
    final String OBTENERTODOS="SELECT id_alumno, nombre, apellidos, fecha_nac FROM alumnos";

    private Conexion conexion;

    public MySQLAlumnoDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public void eliminar(Alumno t) throws ExceptionDAO{
        conexion.establecerConexion();
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=conexion.getConnection().prepareStatement(DELETE);
            preparedStatement.setLong(1, t.getId());
            if(preparedStatement.executeUpdate()==0){
                System.out.println("NO SE HA ELIMINADO EL ALUMNO");
            }else{
                System.out.println("ALUMNO ELIMINADO CORRECTAMENTE");
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

    
    private Alumno convertir(ResultSet r) throws SQLException{
        String nombre = r.getString("nombre");
        String apellido = r.getString("apellidos");
        Date fechaNac= r.getDate("fecha_nac");
        Alumno alumno = new Alumno (nombre,apellido,fechaNac);
        alumno.setId(r.getLong("id_alumno"));
        return alumno;
    }


    @Override
    public void insertar(Alumno t) throws ExceptionDAO {
        conexion.establecerConexion();
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=conexion.getConnection().prepareStatement(INSERT);
            preparedStatement.setString(1, t.getNombre());
            preparedStatement.setString(2, t.getApellidos());
            preparedStatement.setDate(3, new Date(t.getFechaNacimiento().getTime()));
            if(preparedStatement.executeUpdate()==0){
                System.out.println("NO SE HA INSERTADO EL ALUMNO");
            }else{
                System.out.println("ALUMNO INSERTADO CORRECTAMENTE");
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
    public void modificar(Alumno t) throws ExceptionDAO{
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
    public Alumno obtener(Long id) throws ExceptionDAO{
        conexion.establecerConexion();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Alumno alumno=null;
        try {
            preparedStatement=conexion.getConnection().prepareStatement(OBTENER);
            preparedStatement.setLong(1, id);
            System.out.println("ALUMNO ENCONTRADO EXITOSAMENTE");
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                alumno=new Alumno(resultSet.getLong("id_alumno"), resultSet.getString("nombre"), resultSet.getString("apellidos"), resultSet.getDate("fecha_nac"));
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
        return alumno;
    }
        

    @Override
    public List<Alumno> obtenerTodos() throws ExceptionDAO{
        Statement statement=null;
        ResultSet resultSet=null;
        List<Alumno> alumnos = new ArrayList<>();
        try {
            statement = conexion.getConnection().createStatement();
            resultSet=statement.executeQuery(OBTENERTODOS);
            while(resultSet.next()){
                alumnos.add(convertir(resultSet));
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
        return alumnos;
    } 
}