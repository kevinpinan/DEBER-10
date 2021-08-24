package edu.epn;

import java.sql.*;


import edu.epn.conexion.Conexion;

public class Consulta {
    public void consulta(String apellido) throws SQLException{
        Conexion conexion = new Conexion();
        conexion.establecerConexion();
        String query = "insert into alumnos (nombre, apellido, fecha_nac) values ('Pepito', 'Pepitop', '1999')";
        //Statement statement = conexion.getConnection().createStatement();
        PreparedStatement preparedStatement = conexion.getConnection().prepareStatement(query);
        preparedStatement.setString(1,"2");
        preparedStatement.setInt(2, 2);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int idAlumno = resultSet.getInt("id_alumno");
            String nombre = resultSet.getString("nombre");
            String apellidoAux = resultSet.getString("apellidos");
            System.out.println("El id del alumno es: "+idAlumno+"\nNombre: "+nombre+"\nApellido: "+apellidoAux+"\n");
        }
        resultSet.close();
        //statement.close();
        conexion.cerrarConexion();
    }  


    public void transaccion() throws SQLException{
        Conexion conexion = new Conexion();
        final String PROFESOR="insert into profesores (id_profesor, nombre, apellidos) values (?,?,?)";
        final String ASIGNATURA="insert into asignaturas (id_asignatura, nombre, profesor) values (?,?,?)";
        PreparedStatement profesor=null, asignatura=null;
        conexion.establecerConexion();
        try {
            profesor=conexion.getConnection().prepareStatement(PROFESOR);
            profesor.setInt(1, 5);
            profesor.setString(2, "Edwin");
            profesor.setString(3, "Salvador");
            profesor.executeUpdate();

            asignatura=conexion.getConnection().prepareStatement(ASIGNATURA);
            asignatura.setInt(1, 8756);
            asignatura.setString(2, "Inteligencia artificial");
            asignatura.setInt(3, 6);
            asignatura.executeUpdate();
            conexion.getConnection().commit();
            System.out.println("Se ha ejecutado la transaccion");
        } catch (SQLException e) {
            conexion.getConnection().rollback();
            e.printStackTrace();
        }finally{
            if (profesor != null){
                profesor.close();
            }
            if(asignatura != null){
                asignatura.close();
            }
            conexion.cerrarConexion();
        }
        
    }
}