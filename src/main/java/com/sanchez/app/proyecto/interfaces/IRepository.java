package com.sanchez.app.proyecto.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IRepository <T>{

    List<T> listar() throws SQLException;
    T getBy(Long id) throws SQLException;
    void guardar(T t) throws SQLException;
    void eliminar(Long id) throws SQLException;


}
