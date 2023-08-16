package com.sanchez.app.proyecto.repositories;

import com.sanchez.app.proyecto.interfaces.IRegistrosRepository;
import com.sanchez.app.proyecto.models.Direccion;
import com.sanchez.app.proyecto.models.Registro;
import com.sanchez.app.proyecto.models.Trabajador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrosRepository implements IRegistrosRepository {

    private Connection conexion;

    public RegistrosRepository(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public Long guardarReturnId(Registro registro) throws SQLException {

        String sql = "";

        /**
         * ID_REGISTRO         NUMBER NOT NULL,
         *     CLIENTE_ID          NUMBER NOT NULL,
         *     TRABAJADOR_ID       NUMBER NOT NULL,
         *     FECHA_RECIBIDO      DATE,
         *     FECHA_ENTREGA       DATE,
         */

        long resultado = -1L;
        sql = "insert into registrosl (ID_REGISTRO, CLIENTE_ID, fecha_recibido, fecha_entrega, " +
                    "values (SEQUENCEREGISTRO.NEXTVAL,?,?,?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql, new String[]{"ID_DIRECCION"})) {

            if (registro.getIdRegistro() != null && registro.getIdRegistro() > 0) {

                stmt.setLong(1, registro.getClienteId());
                stmt.setLong(2, registro.getTrabajadorId());
                stmt.setDate(3, Date.valueOf(registro.getFechaRecibido()));
                stmt.setDate(4, Date.valueOf(registro.getFechaEntrega()));


                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {

                    resultado = rs.getLong(1);
                }


            }

        }

        return resultado;
    }

    public Registro getRegistro(ResultSet resultSet) throws SQLException {



        Registro r = new Registro();
        r.setIdRegistro(resultSet.getLong("ID_REGISTRO"));
        r.setClienteId(resultSet.getLong("CLIENTE_ID"));
        r.setTrabajadorId(resultSet.getLong("TRABAJADOR_ID"));
        r.setFechaRecibido(resultSet.getDate("FECHA_RECIBIDO").toLocalDate());
        r.setFechaEntrega(resultSet.getDate("FECHA_ENTREGA").toLocalDate());


        return  r;
    }

    @Override
    public List<Registro> listar() throws SQLException {
        List<Registro> registros = new ArrayList<>();
        try (Statement statement = conexion.createStatement();

             ResultSet resultSet = statement.executeQuery("SELECT * FROM REGISTROSL")
        ){

            while (resultSet.next()){

                Registro r = this.getRegistro(resultSet);
                registros.add(r);

            }
        }catch (SQLException e){

            throw new RuntimeException(e);
        }
        return registros;
    }

    @Override
    public Registro getBy(Long id) throws SQLException {
        Registro registro = null;

        try (PreparedStatement preparedStatement = conexion
                .prepareStatement("SELECT * FROM registrosl where ID_REGISTRO=?")){

            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()){

                if (resultSet.next()) {

                    registro = this.getRegistro(resultSet);

                }

            }
        }
        return registro;
    }

    @Override
    public void guardar(Registro registro) throws SQLException {

        String query;

        /**
         * ID_REGISTRO         NUMBER NOT NULL,
         *     CLIENTE_ID          NUMBER NOT NULL,
         *     TRABAJADOR_ID       NUMBER NOT NULL,
         *     FECHA_RECIBIDO      DATE,
         *     FECHA_ENTREGA       DATE,
         */

        if (registro.getIdRegistro() != null && registro.getIdRegistro()  > 0) {

            query = "update registrosl set cliente_id=?, trabajador_id=?, fecha_recibido=?, fecha_entrega=?  " +
                     "where id_registro=?";

        }else {

            query = "insert into registrosl (ID_REGISTRO, cliente_id, id_trabajador, fecha_recibido, fecha_entrega" +
                    "values(SEQUENCEREGISTRO.NEXTVAL,?,?,?,?)";
        }try(PreparedStatement preparedStatement = conexion.prepareStatement(query)) {

            if (registro.getIdRegistro() != null && registro.getIdRegistro()  > 0) {

                preparedStatement.setLong(1, registro.getClienteId());
                preparedStatement.setLong(2, registro.getTrabajadorId());
                preparedStatement.setDate(3, Date.valueOf(registro.getFechaRecibido()));
                preparedStatement.setDate(4, Date.valueOf(registro.getFechaEntrega()));
                preparedStatement.setLong(5, registro.getIdRegistro());



            }else{

                preparedStatement.setLong(1, registro.getClienteId());
                preparedStatement.setLong(2, registro.getTrabajadorId());
                preparedStatement.setDate(3, Date.valueOf(registro.getFechaRecibido()));
                preparedStatement.setDate(4, Date.valueOf(registro.getFechaEntrega()));

            }

            preparedStatement.executeUpdate();

        }

    }

    @Override
    public void eliminar(Long id) throws SQLException {

        String query = "delete from registrosl where id_registro=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }

    }
}
