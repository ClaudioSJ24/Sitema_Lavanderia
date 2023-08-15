package com.sanchez.app.proyecto.services;

import java.util.List;
import java.util.Optional;

public interface IService <T>{

    List<T> listarRopa();
    Optional<T> getByIdRopa(Long id);
    void guardarRopa(T t);
    void eliminarRopa(Long id);
}
