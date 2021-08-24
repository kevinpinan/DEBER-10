package edu.epn.implementacionMySQL;

import java.util.List;

import edu.epn.dao.ExceptionDAO;
import edu.epn.dao.MatriculaDAO;
import edu.epn.modelo.Matricula;
import edu.epn.modelo.Matricula.IdMatricula;
import java.sql.*;
import edu.epn.conexion.Conexion;
import java.util.*;

public class MySQLMatriculaDAO implements MatriculaDAO{
    final String INSERT="INSERT INTO matriculas (alumno, asignatura, fecha, nota) VALUES (?, ?, ?, ?)";
    final String UPDATE="UPDATE matriculas SET nota = ? WHERE alumno = ? AND asignatura = ? AND fecha = ?";
    final String DELETE="DELETE FROM matriculas WHERE alumno = ? AND asignatura = ? AND fecha = ?";
    final String GETALL="SELECT alumno, asignatura, fecha, nota FROM matriculas";
    final String GETONE=GETALL+" WHERE alumno = ? AND asignatura = ? AND fecha = ?";
    final String GETALU=GETALL+" WHERE alumno = ?";

    private Conexion conexion;

    public MySQLMatriculaDAO(Conexion conexion){
        this.conexion = conexion;
    }


    private Matricula convertir(ResultSet r) throws SQLException{
        Long alumno = r.getLong("alumno");
        Long asignatura = r.getLong("asignatura");
        int anio= r.getInt("fecha");
        Integer nota = r.getInt("nota");
        if (r.wasNull()) {
            nota=null;
        }
        Matricula matricula = new Matricula (alumno,asignatura,anio);
        matricula.setNota(nota);
        return matricula;
    }

    @Override
    public List<Matricula> obtenerPorAlumno(long alumno) throws ExceptionDAO{
        conexion.establecerConexion();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        List<Matricula> matriculas = new ArrayList<>();
        try {
            preparedStatement = conexion.getConnection().prepareStatement(GETALU);  
            preparedStatement.setLong(1, alumno);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                matriculas.add(convertir(resultSet));
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
            if (resultSet!=null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new ExceptionDAO("ERROR EN SQL",e);
                }
            }
        }
        conexion.cerrarConexion();
        return matriculas;
    }

    @Override
    public void eliminar(Matricula t) throws ExceptionDAO {
        conexion.establecerConexion();
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=conexion.getConnection().prepareStatement(DELETE);
            preparedStatement.setLong(1, t.getId().getAlumno());
            preparedStatement.setLong(2, t.getId().getAsignatura());
            preparedStatement.setInt(3, t.getId().getAnio());
            if (preparedStatement.executeUpdate()==0) {
                System.out.println("NO SE HA ELIMINADO LA MATRICULA");
            } else {
                System.out.println("MATRICULA ELIMINADA CORRECTAMENTE");
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
    public void insertar(Matricula t) throws ExceptionDAO {
        conexion.establecerConexion();
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=conexion.getConnection().prepareStatement(INSERT);
            preparedStatement.setLong(1, t.getId().getAlumno());
            preparedStatement.setLong(2, t.getId().getAsignatura());
            preparedStatement.setInt(3, t.getId().getAnio());
            preparedStatement.setInt(4, t.getNota());
            if (preparedStatement.executeUpdate()==0) {
                System.out.println("NO SE HA INSERTADO LA MATRICULA");
            } else {
                System.out.println("MATRICULA INSERTADA CORRECTAMENTE");
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
    public void modificar(Matricula t) throws ExceptionDAO {
        conexion.establecerConexion();
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=conexion.getConnection().prepareStatement(UPDATE);
            preparedStatement.setInt(1, t.getNota());
            preparedStatement.setLong(2, t.getId().getAlumno());
            preparedStatement.setLong(3, t.getId().getAsignatura());
            preparedStatement.setInt(4, t.getId().getAnio());
            if (preparedStatement.executeUpdate()==0) {
                System.out.println("NO SE HA MODIFICADO CORRECTAMENTE");
            } else {
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
    public Matricula obtener(IdMatricula id) throws ExceptionDAO {
        conexion.establecerConexion();
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        Matricula matricula=null;
        try {
            preparedStatement=conexion.getConnection().prepareStatement(GETONE);
            preparedStatement.setLong(1, id.getAlumno());
            preparedStatement.setLong(2, id.getAsignatura());
            preparedStatement.setInt(3, id.getAnio());
            System.out.println("MATRICULA ENCONTRADA EXITOSAMENTE");
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                matricula= new Matricula(resultSet.getLong("alumno"), resultSet.getLong("asignatura"), resultSet.getInt("fecha"), resultSet.getInt("nota"));
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
        return matricula;
    }

    @Override
    public List<Matricula> obtenerTodos() throws ExceptionDAO {
        Statement statement=null;
        ResultSet resultSet=null;
        List<Matricula> matriculas = new ArrayList<>();
        try {
            statement = conexion.getConnection().createStatement();
            resultSet = statement.executeQuery(GETALL);
            while (resultSet.next()) {
                matriculas.add(convertir(resultSet));
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
        return matriculas;
    }
}