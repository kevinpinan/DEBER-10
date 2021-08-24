package edu.epn.modelo;

import edu.epn.conexion.*;
import edu.epn.dao.*;
import edu.epn.implementacionMySQL.*;
import java.util.*;

public class MenuAsignatura {
    private static Conexion conexion = new Conexion();
    private static Scanner sc = new Scanner(System.in);
    private static String opc;
    private static AsignaturaDAO daoAsignatura = new MySQLAsignaturaDAO(conexion);
    private static Asignatura asignatura;
    
    public static void menuAsignatura(){
        String nombre;
        Long id, idProfesor;
        System.out.println("Apartado de asignaturas");
        do {
            System.out.println("Menu");
            System.out.println("1. Insertar");
            System.out.println("2. Eliminar");
            System.out.println("3. Modificar");
            System.out.println("4. Mostrar uno");
            System.out.println("5. Mostrar todos");
            System.out.println("0. Atras");
            opc=sc.nextLine();
            switch (opc) {
                case "1":  
                    conexion.establecerConexion();
                    System.out.println("Ingrese el nombre de la asignatura:");
                    nombre=sc.nextLine();
                    System.out.println("Ingrese el ID del profesor");
                    idProfesor=sc.nextLong();
                    sc.nextLine();
                    try {
                        asignatura = new Asignatura(nombre, idProfesor);
                        daoAsignatura.insertar(asignatura);
                    } catch (ExceptionDAO exceptionDAO) {
                        exceptionDAO.printStackTrace();
                    }finally{
                        conexion.cerrarConexion();
                    }
                    break;
                case "2":
                    conexion.establecerConexion();
                    System.out.println("Ingrese el ID de la asignatura:");
                    id=sc.nextLong();
                    sc.nextLine();
                    try {
                        asignatura = new Asignatura(id);
                        daoAsignatura.eliminar(asignatura);
                    } catch (ExceptionDAO exceptionDAO) {
                        exceptionDAO.printStackTrace();
                    }finally{
                        conexion.cerrarConexion();
                    }
                    break;
                case "3":
                    conexion.establecerConexion();
                    System.out.println("Ingrese el ID de la asignatura:");
                    id=sc.nextLong();
                    sc.nextLine();
                    System.out.println("Ingrese el nuevo nombre:");
                    nombre=sc.nextLine();
                    System.out.println("Ingrese el nuevo ID del profesor:");
                    idProfesor=sc.nextLong();
                    sc.nextLine();
                    try {
                        asignatura = new Asignatura(id, nombre, idProfesor);
                        daoAsignatura.modificar(asignatura);;
                    } catch (ExceptionDAO exceptionDAO) {
                        exceptionDAO.printStackTrace();
                    }finally{
                        conexion.cerrarConexion();
                    }
                    break;
                case "4":
                    conexion.establecerConexion();
                    System.out.println("Ingrese el ID de la asignatura:");
                    id=sc.nextLong();
                    sc.nextLine();
                    try {
                        System.out.println(daoAsignatura.obtener(id));   
                    } catch (ExceptionDAO exceptionDAO) {
                        exceptionDAO.printStackTrace();
                    }finally{
                        conexion.cerrarConexion();
                    }
                    break;
                case "5":
                conexion.establecerConexion();
                    try {
                        System.out.println(daoAsignatura.obtenerTodos());
                    } catch (ExceptionDAO exceptionDAO) {
                        exceptionDAO.printStackTrace();
                    }finally{
                        conexion.cerrarConexion();
                    }
                    break;
                case "0":
                    System.out.println("..........\n");
                    break;
                default:
                    System.out.println("Respuesta no valida.\n");
                    break;
            }
        } while (!opc.equals("0"));
    }
}
