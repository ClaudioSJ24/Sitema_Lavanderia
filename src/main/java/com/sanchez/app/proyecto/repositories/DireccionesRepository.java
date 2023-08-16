package com.sanchez.app.proyecto.repositories;

import com.sanchez.app.proyecto.interfaces.IDireccionesRepository;
import com.sanchez.app.proyecto.models.Direccion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DireccionesRepository implements IDireccionesRepository {

    private Connection conexion;

    public DireccionesRepository(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public Long guardarReturnId(Direccion direccion) throws SQLException {

        String query = "";
        Long result = -1L;

        query = "insert into direccionest (ID_DIRECION, calle, numero, colonia, " +
                "cuidad, estado, cp)" +
                "values (SEQUENCE3.NEXTVAL,?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(query, new String[]{"ID_DIRECCION"})){

            if (direccion.getIdDireccion() != null && direccion.getIdDireccion() > 0) {

                preparedStatement.setString(1, direccion.getCalle());
                preparedStatement.setString(2, direccion.getNumero());
                preparedStatement.setString(3, direccion.getColonia());
                preparedStatement.setString(4, direccion.getCiudad());
                preparedStatement.setString(5, direccion.getEstado());
                preparedStatement.setString(6, direccion.getCp());

                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {

                    result = resultSet.getLong(1);

                }
            }

        }
        return result;
    }

    @Override
    public List<Direccion> listar() throws SQLException {

        List<Direccion> direcciones = new ArrayList<>();

        try (Statement statement  = conexion.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM DIRECCIONEST")

        ){

            while (resultSet.next()){

                Direccion d = this.getDireccion(resultSet);
                direcciones.add(d);

            }

        }catch (SQLException e){

            throw new RuntimeException(e);

        }
        return direcciones;
    }

    @Override
    public Direccion getBy(Long id) throws SQLException {

        Direccion direccion = null;

        try (PreparedStatement preparedStatement = conexion
                .prepareStatement("SELECT * FROM direccionest where ID_DIRECCION=?")){
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()){

                if (resultSet.next()) {

                    direccion  = this.getDireccion(resultSet);

                }

            }

        }
        return direccion;
    }

    @Override
    public void guardar(Direccion direccion) throws SQLException {

        String query;

        if (direccion.getIdDireccion() != null && direccion.getIdDireccion() > 0) {

            query = "update direccionest set calle=?, numero=?, colonia=?, " +
                    "ciudad=?, estado=?, cp=? " +
                    "where id_direccion=?";

        }else {

            query = "insert into direccionest (id_direcciones, calle, numero, " +
                    "ciudad, estado, cp)" +
                    "values(SEQUENCEDIRECCION.NEXTVAL,?,?,?,?,?,?)";
        }

        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)){

            if (direccion.getIdDireccion() != null && direccion.getIdDireccion() > 0 ) {

                /**
                 * ID_DIRECCION    NUMBER NOT NULL,
                 *     CALLE           VARCHAR2 (45),
                 *     NUMERO          VARCHAR2 (45),
                 *     COLONIA         VARCHAR2 (45),
                 *     CIUDAD          VARCHAR2 (45),
                 *     ESTADO          VARCHAR (45),
                 *     CP              VARCHAR2 (45),
                 */

                preparedStatement.setString(1, direccion.getCalle());
                preparedStatement.setString(2, direccion.getNumero());
                preparedStatement.setString(3, direccion.getColonia());
                preparedStatement.setString(4, direccion.getCiudad());
                preparedStatement.setString(5, direccion.getEstado());
                preparedStatement.setString(6, direccion.getCp());
                preparedStatement.setLong(7, direccion.getIdDireccion());


            }else{

                preparedStatement.setString(1, direccion.getCalle());
                preparedStatement.setString(2, direccion.getNumero());
                preparedStatement.setString(3, direccion.getColonia());
                preparedStatement.setString(4, direccion.getCiudad());
                preparedStatement.setString(5, direccion.getEstado());
                preparedStatement.setString(6, direccion.getCp());
            }

            preparedStatement.executeUpdate();

        }

    }

    @Override
    public void eliminar(Long id) throws SQLException {

        String query = "delete from direccionest where id_direccion=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)){

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        }

    }

    public Direccion getDireccion(ResultSet resultSet) throws SQLException {

        /**
         * ID_DIRECCION    NUMBER NOT NULL,
         *     CALLE           VARCHAR2 (45),
         *     NUMERO          VARCHAR2 (45),
         *     COLONIA         VARCHAR2 (45),
         *     CIUDAD          VARCHAR2 (45),
         *     ESTADO          VARCHAR (45),
         *     CP              VARCHAR2 (45),
         */

        Direccion d = new Direccion();
        d.setIdDireccion(resultSet.getLong("ID_DIRECCION"));
        d.setCalle(resultSet.getString("CALLE"));
        d.setNumero(resultSet.getString("NUMERO"));
        d.setColonia(resultSet.getString("COLONIA"));
        d.setCiudad(resultSet.getString("CIUDAD"));
        d.setEstado(resultSet.getString("ESTADO"));
        d.setCp(resultSet.getString("CP"));


     return  d;
    }
}
