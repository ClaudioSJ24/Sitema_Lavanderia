package com.sanchez.app.proyecto.repositories;

import com.sanchez.app.proyecto.interfaces.IClientesRepository;
import com.sanchez.app.proyecto.models.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesRepository implements IClientesRepository {

    private Connection conexion;

    public ClientesRepository(Connection conexion) {

        this.conexion = conexion;

    }

    @Override
    public Long guardarReturnId(Cliente cliente) throws SQLException {

        String query;
        Long result = -1L;


        query = "insert into clientesl (ID_CLIENTE, ROPA_ID, nombre, telefono)" +
                "values(SEQUENCECLIENTE.NEXTVAL,?,?,?)";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(query, new String[]{"ID_CLIENTE"})){

            if (cliente.getIdCliente() != null && cliente.getIdCliente() > 0) {

                preparedStatement.setLong(1, cliente.getRopaId());
                preparedStatement.setString(2,cliente.getNombre());
                preparedStatement.setString(3, cliente.getTelefono());

                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {

                    result = resultSet.getLong(1);


                }

            }

        }

        return result;
    }

    @Override
    public List<Cliente> listar() throws SQLException {

        List<Cliente> clientes = new ArrayList<>();
        try (Statement statement = conexion.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM CLIENTESL")
        ){

            while (resultSet.next()){

                Cliente c = this.getCliente(resultSet);
                clientes.add(c);

            }
        }catch (SQLException e){

            throw new RuntimeException(e);
        }
        return clientes;
    }

    public Cliente getCliente(ResultSet resultSet) throws SQLException{

        /**
         *     ID_CLIENTE          NUMBER NOT NULL,
         *     ROPA_ID             NUMBER NOT NULL,
         *     NOMBRE              VARCHAR2(200),
         *     TELEFONO            VARCHAR2(45),
         */

        Cliente c = new Cliente();
        c.setIdCliente(resultSet.getLong("ID_CLIENTE"));
        c.setRopaId(resultSet.getLong("ROPA_ID"));
        c.setNombre(resultSet.getString("NOMBRE"));
        c.setTelefono(resultSet.getString("TELEFONO"));

        return c;


    }

    @Override
    public Cliente getBy(Long id) throws SQLException {

        Cliente cliente = null;

        try (PreparedStatement preparedStatement = conexion
                .prepareStatement("SELECT * FROM clientesl where ID_CLIENTE=?")){

            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()){

                if (resultSet.next()) {

                    cliente = this.getCliente(resultSet);

                }

            }
        }
        return cliente;
    }

    @Override
    public void guardar(Cliente cliente) throws SQLException {

        String query;

        if (cliente.getIdCliente() != null && cliente.getIdCliente() > 0) {

            query = "update clientesl set ropa_id=?, nombre=?, telefono=? " +
                    "where id_cliente=?";

        }else {

            query = "insert into clientel (id_cliente, ropa_id, nombre, telefono)" +
                    "values(SEQUENCECLIENTE.NEXTVAL,?,?,?)";
        }try(PreparedStatement preparedStatement = conexion.prepareStatement(query)) {

            if (cliente.getIdCliente() != null  && cliente.getIdCliente() > 0) {

                preparedStatement.setLong(1, cliente.getRopaId());
                preparedStatement.setString(2, cliente.getNombre());
                preparedStatement.setString(3, cliente.getTelefono());
                preparedStatement.setLong(4, cliente.getIdCliente());

            }else{

                preparedStatement.setLong(1, cliente.getRopaId());
                preparedStatement.setString(2, cliente.getNombre());
                preparedStatement.setString(3, cliente.getTelefono());

            }

            preparedStatement.executeUpdate();

        }

    }
    @Override
    public void eliminar(Long id) throws SQLException {

        String query = "delete from clientesl where id_cliente=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)){

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        }

    }
}
