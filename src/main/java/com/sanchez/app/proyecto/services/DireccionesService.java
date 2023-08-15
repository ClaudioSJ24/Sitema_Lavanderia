package com.sanchez.app.proyecto.services;

import com.sanchez.app.proyecto.interfaces.IDireccionesRepository;
import com.sanchez.app.proyecto.interfaces.IDireccionesService;
import com.sanchez.app.proyecto.models.Direccion;
import com.sanchez.app.proyecto.repositories.DireccionesRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DireccionesService implements IDireccionesService {

    private IDireccionesRepository iDireccionesRepository;

    public DireccionesService(Connection conexion) {

        this.iDireccionesRepository = new DireccionesRepository(conexion);

    }

    @Override
    public Long guardarReturnId(Direccion direccion) {

        try {

            return iDireccionesRepository.guardarReturnId(direccion);

        }catch (SQLException e){

            throw  new RuntimeException(e.getMessage(), e.getCause());
        }

    }

    @Override
    public List<Direccion> listar() {

        try {

            return  iDireccionesRepository.listar();

        }catch (SQLException e){

            throw new RuntimeException(e.getMessage(), e.getCause());

        }
    }

    @Override
    public Optional<Direccion> getBy(Long id) {

        try {

            return Optional.ofNullable(iDireccionesRepository.getBy(id));

        }catch (SQLException e){

            throw new RuntimeException(e.getMessage(), e.getCause());

        }
    }

    @Override
    public void guardar(Direccion direccion) {

        try {

            iDireccionesRepository.guardar(direccion);

        }catch (SQLException e){

            throw  new RuntimeException(e.getMessage(), e.getCause());

        }

    }

    @Override
    public void eliminar(Long id) {

        try {

            iDireccionesRepository.eliminar(id);

        }catch (SQLException e){

            throw  new RuntimeException(e.getMessage(), e.getCause());

        }

    }
}
