package edu.epn.modelo;

import edu.epn.conexion.*;
import edu.epn.dao.*;
import edu.epn.implementacionMySQL.*;
import edu.epn.modelo.Matricula.IdMatricula;

import java.util.*;


public class MenuMatricula {
    private static Conexion conexion = new Conexion();
    private static Scanner sc = new Scanner(System.in);
    private static String opc;
    private static MatriculaDAO daoMatricula = new MySQLMatriculaDAO(conexion);
    private static Matricula matricula = new Matricula();
    
    public static void menuMatricula(){
        int anio;
        Long asignatura, alumno;
        Integer nota;
        IdMatricula idMatricula;
        System.out.println("Apartado de matriculas");
        do {
            System.out.println("Menu");
            System.out.println("1. Insertar");
            System.out.println("2. Eliminar");
            System.out.println("3. Modificar");
            System.out.println("4. Mostrar uno");
            System.out.println("5. Mostrar todos");
            System.out.println("6. Mostrar por estudiante");
            System.out.println("0. Atras");
            opc=sc.nextLine();
            switch (opc) {
                case "1":  
                    conexion.establecerConexion();
                    System.out.println("Ingrese el ID del estudiante:");
                    alumno=sc.nextLong();
                    sc.nextLine();
                    System.out.println("Ingrese el ID de la asignatura");
                    asignatura=sc.nextLong();
                    sc.nextLine();
                    System.out.println("Ingrese el anio:");
                    anio=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Ingrese la nota:");
                    nota=sc.nextInt();
                    sc.nextLine();
                    try {
                        matricula = new Matricula(alumno, asignatura, anio, nota);
                        daoMatricula.insertar(matricula);
                    } catch (ExceptionDAO exceptionDAO) {
                        exceptionDAO.printStackTrace();
                    }finally{
                        conexion.cerrarConexion();
                    }
                    break;
                case "2":
                    conexion.establecerConexion();
                    System.out.println("Ingrese el ID del estudiante:");
                    alumno=sc.nextLong();
                    sc.nextLine();
                    System.out.println("Ingrese el ID de la asignatura");
                    asignatura=sc.nextLong();
                    sc.nextLine();
                    System.out.println("Ingrese el anio:");
                    anio=sc.nextInt();
                    sc.nextLine();
                    try {
                        matricula = new Matricula(alumno, asignatura, anio);
                        daoMatricula.eliminar(matricula);
                    } catch (ExceptionDAO exceptionDAO) {
                        exceptionDAO.printStackTrace();
                    }finally{
                        conexion.cerrarConexion();
                    }
                    break;
                case "3":
                    conexion.establecerConexion();
                    System.out.println("Ingrese el ID del estudiante:");
                    alumno=sc.nextLong();
                    sc.nextLine();
                    System.out.println("Ingrese el ID de la asignatura");
                    asignatura=sc.nextLong();
                    sc.nextLine();
                    System.out.println("Ingrese el anio:");
                    anio=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Ingrese la nueva nota:");
                    nota=sc.nextInt();
                    sc.nextLine();
                    try {
                        matricula = new Matricula(alumno, asignatura, anio, nota);
                        daoMatricula.modificar(matricula);;
                    } catch (ExceptionDAO exceptionDAO) {
                        exceptionDAO.printStackTrace();
                    }finally{
                        conexion.cerrarConexion();
                    }
                    break;
                case "4":
                    conexion.establecerConexion();
                    System.out.println("Ingrese el ID del estudiante:");
                    alumno=sc.nextLong();
                    sc.nextLine();
                    System.out.println("Ingrese el ID de la asignatura");
                    asignatura=sc.nextLong();
                    sc.nextLine();
                    System.out.println("Ingrese el anio:");
                    anio=sc.nextInt();
                    sc.nextLine();
                    idMatricula= matricula.new IdMatricula(alumno, asignatura, anio);
                    try {
                        System.out.println(daoMatricula.obtener(idMatricula));   
                    } catch (ExceptionDAO exceptionDAO) {
                        exceptionDAO.printStackTrace();
                    }finally{
                        conexion.cerrarConexion();
                    }
                    break;
                case "5":
                    conexion.establecerConexion();
                    try {
                        System.out.println(daoMatricula.obtenerTodos());
                    } catch (ExceptionDAO exceptionDAO) {
                        exceptionDAO.printStackTrace();
                    }finally{
                        conexion.cerrarConexion();
                    }
                    break;
                case "6":
                    conexion.establecerConexion();
                    System.out.println("Ingrese el ID del estudiante:");
                    alumno=sc.nextLong();
                    sc.nextLine();
                    try {
                        System.out.println(daoMatricula.obtenerPorAlumno(alumno));
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
