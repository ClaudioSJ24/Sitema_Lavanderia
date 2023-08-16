package com.sanchez.app.proyecto.services;

import com.sanchez.app.proyecto.interfaces.IClientesRepository;
import com.sanchez.app.proyecto.interfaces.IClientesService;
import com.sanchez.app.proyecto.models.Cliente;
import com.sanchez.app.proyecto.repositories.ClientesRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClientesService implements IClientesService {

    private IClientesRepository iClientesRepository;

    public ClientesService(Connection conexion) {

        this.iClientesRepository = new ClientesRepository(conexion);
    }

    @Override
    public Long guardarReturnId(Cliente cliente) {

        try {

            return iClientesRepository.guardarReturnId(cliente);

        }catch (SQLException e){

            throw new RuntimeException(e.getMessage(), e.getCause());

        }

    }

    @Override
    public List<Cliente> listar() {

        try {

            return iClientesRepository.listar();

        }catch (SQLException e){

            throw new RuntimeException(e.getMessage(), e.getCause());

        }
    }

    @Override
    public Optional<Cliente> getBy(Long id) {

        try {
            return Optional.ofNullable(iClientesRepository.getBy(id));
        }catch (SQLException e){
            throw new RuntimeException( e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Cliente cliente) {

        try {

            iClientesRepository.guardar(cliente);
        }catch (SQLException e){

            throw  new RuntimeException(e.getMessage(), e.getCause());
        }

    }

    @Override
    public void eliminar(Long id) {

        try {

            iClientesRepository.eliminar(id);

        }catch (SQLException e){

            throw  new RuntimeException(e.getMessage(), e.getCause());

        }

    }
}
