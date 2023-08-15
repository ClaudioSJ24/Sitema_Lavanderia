package com.sanchez.app.proyecto.services;

import com.sanchez.app.proyecto.interfaces.IRepository;
import com.sanchez.app.proyecto.interfaces.IService;
import com.sanchez.app.proyecto.repositories.RopaRepository;
import com.sanchez.app.proyecto.models.Ropa;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RopaService implements IService<Ropa> {

    private IRepository<Ropa> ropaIRepository;

    public RopaService(Connection conexion) {

        ropaIRepository = new RopaRepository(conexion);
    }

    @Override
    public List<Ropa> listar() {

        try {

            return  ropaIRepository.listar();

        }catch (SQLException e){

            throw  new RuntimeException(e.getMessage(), e.getCause());

        }

    }

    @Override
    public Optional<Ropa> getBy(Long id) {

        try {
            return Optional.ofNullable(ropaIRepository.getBy(id));
        }catch (SQLException e){

            throw new RuntimeException(e.getMessage(), e.getCause());

        }

    }

    @Override
    public void guardar(Ropa ropa) {

        try {

            ropaIRepository.guardar(ropa);

        }catch (SQLException e){

            throw new RuntimeException(e.getMessage(), e.getCause());

        }

    }

    @Override
    public void eliminar(Long id) {

        try {

            ropaIRepository.eliminar(id);

        }catch (SQLException e){

            throw  new RuntimeException(e.getMessage(), e.getCause());

        }

    }
}
