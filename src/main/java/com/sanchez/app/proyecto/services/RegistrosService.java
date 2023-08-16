package com.sanchez.app.proyecto.services;

import com.sanchez.app.proyecto.interfaces.IRegistrosRepository;
import com.sanchez.app.proyecto.interfaces.IRegistrosService;
import com.sanchez.app.proyecto.models.Registro;
import com.sanchez.app.proyecto.repositories.RegistrosRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RegistrosService implements IRegistrosService {

    private IRegistrosRepository iRegistrosRepository;

    public RegistrosService(Connection conexion) {

        this.iRegistrosRepository = new  RegistrosRepository(conexion);
    }

    @Override
    public Long guardaReturnId(Registro registro) throws SQLException {
        try {

            return iRegistrosRepository.guardarReturnId(registro);

        }catch (SQLException e){

            throw new RuntimeException(e.getMessage(), e.getCause());

        }
    }

    @Override
    public List<Registro> listar() {
        try {

            return iRegistrosRepository.listar();

        }catch (SQLException e){

            throw new RuntimeException(e.getMessage(), e.getCause());

        }
    }

    @Override
    public Optional<Registro> getBy(Long id) {
        try {
            return Optional.ofNullable(iRegistrosRepository.getBy(id));
        }catch (SQLException e){
            throw new RuntimeException( e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Registro registro) {

        try {

            iRegistrosRepository.guardar(registro);
        }catch (SQLException e){

            throw  new RuntimeException(e.getMessage(), e.getCause());
        }

    }

    @Override
    public void eliminar(Long id) {

        try {

            iRegistrosRepository.eliminar(id);

        }catch (SQLException e){

            throw  new RuntimeException(e.getMessage(), e.getCause());

        }

    }
}
