package edu.epn.modelo;

public class Profesor {
    private Long id=null;
    private String nombre;
    private String apellidos;

    public Profesor(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    

    public Profesor(Long id, String nombre, String apellidos) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }



    public Profesor(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Profesor [apellidos=" + apellidos + ", id=" + id + ", nombre=" + nombre + "]";
    }    
}
