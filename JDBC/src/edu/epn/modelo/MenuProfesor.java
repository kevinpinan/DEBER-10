package edu.epn.modelo;

import edu.epn.conexion.*;
import edu.epn.dao.*;
import edu.epn.implementacionMySQL.*;
import java.util.*;


public class MenuProfesor {
    private static Conexion conexion = new Conexion();
    private static Scanner sc = new Scanner(System.in);
    private static String opc;
    private static ProfesorDAO daoProfesor = new MySQLProfesorDAO(conexion);
    private static Profesor profesor;
    
    public static void menuProfesor(){
        String nombre, apellido;
        Long id;
        System.out.println("Apartado de profesores");
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
                    System.out.println("Ingrese el nombre del profesor:");
                    nombre=sc.nextLine();
                    System.out.println("Ingrese el apellido del profesor");
                    apellido=sc.nextLine();
                    try {
                        profesor = new Profesor(nombre, apellido);
                        daoProfesor.insertar(profesor);
                    } catch (ExceptionDAO exceptionDAO) {
                        exceptionDAO.printStackTrace();
                    }finally{
                        conexion.cerrarConexion();
                    }
                    break;
                case "2":
                    conexion.establecerConexion();
                    System.out.println("Ingrese el ID del profesor:");
                    id=sc.nextLong();
                    sc.nextLine();
                    try {
                        profesor = new Profesor(id);
                        daoProfesor.eliminar(profesor);
                    } catch (ExceptionDAO exceptionDAO) {
                        exceptionDAO.printStackTrace();
                    }finally{
                        conexion.cerrarConexion();
                    }
                    break;
                case "3":
                    conexion.establecerConexion();
                    System.out.println("Ingrese el ID del profesor:");
                    id=sc.nextLong();
                    sc.nextLine();
                    System.out.println("Ingrese el nuevo nombre:");
                    nombre=sc.nextLine();
                    System.out.println("Ingrese el nuevo apellido:");
                    apellido=sc.nextLine();
                    try {
                        profesor = new Profesor(id, nombre, apellido);
                        daoProfesor.modificar(profesor);;
                    } catch (ExceptionDAO exceptionDAO) {
                        exceptionDAO.printStackTrace();
                    }finally{
                        conexion.cerrarConexion();
                    }
                    break;
                case "4":
                    conexion.establecerConexion();
                    System.out.println("Ingrese el ID del profesor:");
                    id=sc.nextLong();
                    sc.nextLine();
                    try {
                        System.out.println(daoProfesor.obtener(id));   
                    } catch (ExceptionDAO exceptionDAO) {
                        exceptionDAO.printStackTrace();
                    }finally{
                        conexion.cerrarConexion();
                    }
                    break;
                case "5":
                conexion.establecerConexion();
                    try {
                        System.out.println(daoProfesor.obtenerTodos());
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
