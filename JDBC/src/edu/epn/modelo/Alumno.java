package edu.epn.modelo;

import java.util.*;


public class Alumno {
    private Long id=null;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;


    public Alumno(String nombre, String apellidos, Date fechaNacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
    }

    
    public Alumno(Long id) {
        this.id = id;
    }






    public Alumno(String nombre, String apellidos, Long id) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.id = id;
    }

    




    public Alumno(Long id, String nombre, String apellidos, Date fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    




    @Override
    public String toString() {
        return "Alumno [apellidos=" + apellidos + ", fechaNacimiento=" + fechaNacimiento + ", id=" + id + ", nombre="
                + nombre + "]";
    }

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Alumno other = (Alumno) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }

    

    
}
