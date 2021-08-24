package edu.epn.dao;

import java.util.*;


public interface DAO<T, K> {
    void insertar(T t) throws ExceptionDAO;
    void modificar(T t) throws ExceptionDAO;
    void eliminar(T t) throws ExceptionDAO;
    T obtener(K id) throws ExceptionDAO;
    List<T> obtenerTodos() throws ExceptionDAO;
}
