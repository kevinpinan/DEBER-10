package edu.epn.dao;

import edu.epn.modelo.Matricula;

import java.util.*;

public interface MatriculaDAO extends DAO<Matricula, Matricula.IdMatricula>{
    //todas las matriculas de un alumno en particular
    List<Matricula> obtenerPorAlumno(long alumno) throws ExceptionDAO;
    
}
