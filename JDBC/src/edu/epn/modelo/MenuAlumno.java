package edu.epn.modelo;

import edu.epn.conexion.*;
import edu.epn.dao.*;
import edu.epn.implementacionMySQL.*;
import java.util.*;

public class MenuAlumno {
    private static Conexion conexion = new Conexion();
    private static Scanner sc = new Scanner(System.in);
    private static String opc;
    private static AlumnoDAO daoAlumno = new MySQLAlumnoDAO(conexion);
    private static Alumno alumno;
    
    public static void menuAlumno(){
        String nombre, apellido;
        int dia, mes, anio;
        Long id;
        System.out.println("Apartado de alumnos");
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
                    System.out.println("Ingrese el nombre del alumno:");
                    nombre=sc.nextLine();
                    System.out.println("Ingrese el apellido del alumno");
                    apellido=sc.nextLine();
                    System.out.println("Ingrese el dia:");
                    dia=sc.nextInt();
                    System.out.println("Ingrese el mes:");
                    mes=sc.nextInt();
                    System.out.println("Ingrese el anio");
                    anio=sc.nextInt();
                    try {
                        alumno = new Alumno(nombre, apellido, new Date(anio, (mes-1), dia));
                        daoAlumno.insertar(alumno);
                    } catch (ExceptionDAO exceptionDAO) {
                        exceptionDAO.printStackTrace();
                    }finally{
                        conexion.cerrarConexion();
                    }
                    break;
                case "2":
                    conexion.establecerConexion();
                    System.out.println("Ingrese el ID del alumno:");
                    id=sc.nextLong();
                    sc.nextLine();
                    try {
                        alumno = new Alumno(id);
                        daoAlumno.eliminar(alumno);
                    } catch (ExceptionDAO exceptionDAO) {
                        exceptionDAO.printStackTrace();
                    }finally{
                        conexion.cerrarConexion();
                    }
                    break;
                case "3":
                    conexion.establecerConexion();
                    System.out.println("Ingrese el ID del estudiante:");
                    id=sc.nextLong();
                    sc.nextLine();
                    System.out.println("Ingrese el nuevo nombre:");
                    nombre=sc.nextLine();
                    System.out.println("Ingrese el nuevo apellido:");
                    apellido=sc.nextLine();
                    try {
                        alumno = new Alumno(nombre, apellido, id);
                        daoAlumno.modificar(alumno);;
                    } catch (ExceptionDAO exceptionDAO) {
                        exceptionDAO.printStackTrace();
                    }finally{
                        conexion.cerrarConexion();
                    }
                    break;
                case "4":
                    conexion.establecerConexion();
                    System.out.println("Ingrese el ID del estudiante:");
                    id=sc.nextLong();
                    sc.nextLine();
                    try {
                        System.out.println(daoAlumno.obtener(id));   
                    } catch (ExceptionDAO exceptionDAO) {
                        exceptionDAO.printStackTrace();
                    }finally{
                        conexion.cerrarConexion();
                    }
                    break;
                case "5":
                conexion.establecerConexion();
                    try {
                        System.out.println(daoAlumno.obtenerTodos());
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
