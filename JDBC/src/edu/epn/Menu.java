package edu.epn;

import edu.epn.modelo.*;
import java.util.*;

public class Menu {
    public static void menu(){
        String opc;
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido");
        do {
            System.out.println("Menu");
            System.out.println("1. Alumnos");
            System.out.println("2. Profesores");
            System.out.println("3. Asignaturas");
            System.out.println("4. Matriculas");
            System.out.println("0. Salir");
            opc=sc.nextLine();
            switch (opc) {
                case "1":
                    MenuAlumno.menuAlumno();
                    break;
                case "2":
                    MenuProfesor.menuProfesor();
                    break;
                case "3":
                    MenuAsignatura.menuAsignatura();
                    break;
                case "4":
                    MenuMatricula.menuMatricula();
                    break;
                case "0":
                    System.out.println("Saliendo.....\n");
                    break;
                default:
                    System.out.println("Respuesta no valida.\n");
                    break;
            }
        } while (!opc.equals("0"));
        sc.close();
    }
}
