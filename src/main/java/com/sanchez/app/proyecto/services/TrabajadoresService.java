package com.sanchez.app.proyecto.services;

import com.sanchez.app.proyecto.interfaces.IClientesService;
import com.sanchez.app.proyecto.interfaces.ITrabajadoresRepository;
import com.sanchez.app.proyecto.interfaces.ITrabajadoresService;
import com.sanchez.app.proyecto.models.Cliente;
import com.sanchez.app.proyecto.models.Trabajador;
import com.sanchez.app.proyecto.repositories.TrabajadoresRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TrabajadoresService implements ITrabajadoresService {

    private ITrabajadoresRepository iTrabajadoresRepository;

    public TrabajadoresService(Connection conexion) {

        this.iTrabajadoresRepository = new TrabajadoresRepository(conexion);
    }

    @Override
    public List<Trabajador> listar() {

        try {

            return  iTrabajadoresRepository.listar();

        }catch (SQLException e){

            throw new RuntimeException(e.getMessage(), e.getCause());

        }
    }

    @Override
    public Optional<Trabajador> getBy(Long id) {

        try {
            return Optional.ofNullable(iTrabajadoresRepository.getBy(id));
        }catch (SQLException e){

            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Trabajador trabajador) {

        try {

            iTrabajadoresRepository.guardar(trabajador);

        }catch (SQLException e){

            throw new RuntimeException(e.getMessage(), e.getCause());

        }

    }

    @Override
    public void eliminar(Long id) {

        try {
            iTrabajadoresRepository.eliminar(id);

        }catch (SQLException e){

            throw new RuntimeException(e.getMessage(), e.getCause());

        }

    }

    @Override
    public Long guardarReturnId(Trabajador trabajador) throws SQLException {
        return null;
    }
}
