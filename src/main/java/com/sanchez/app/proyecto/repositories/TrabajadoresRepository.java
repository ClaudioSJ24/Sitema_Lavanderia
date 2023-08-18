package com.sanchez.app.proyecto.repositories;

import com.sanchez.app.proyecto.interfaces.ITrabajadoresRepository;
import com.sanchez.app.proyecto.models.Cliente;
import com.sanchez.app.proyecto.models.Trabajador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrabajadoresRepository implements ITrabajadoresRepository {

    private Connection conexion;

    public TrabajadoresRepository(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Trabajador> listar() throws SQLException {

        List<Trabajador> trabajadores = new ArrayList<>();
        try (Statement statement = conexion.createStatement();

             ResultSet resultSet = statement.executeQuery("SELECT * FROM TRABAJADORESL")
        ){

            while (resultSet.next()){

                Trabajador t = this.getTrabajador(resultSet);
                trabajadores.add(t);

            }
        }catch (SQLException e){

            throw new RuntimeException(e);
        }
        return trabajadores;
    }



    @Override
    public Trabajador getBy(Long id) throws SQLException {
        Trabajador trabajador = null;

        try (PreparedStatement preparedStatement = conexion
                .prepareStatement("SELECT * FROM trabajadoresl where ID_TRABAJADOR=?")){

            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()){

                if (resultSet.next()) {

                    trabajador = this.getTrabajador(resultSet);

                }

            }
        }
        return trabajador;
    }

    @Override
    public void guardar(Trabajador trabajador) throws SQLException {

        String query;

        /**
         * ID_TRABAJADOR       NUMBER NOT NULL,
         *     DIRECCION_ID        NUMBER NOT NULL,
         *     NOMBRE              VARCHAR2 (200),
         *     AP_PATERNO          VARCHAR2 (200),
         *     AP_MATERNO          VARCHAR2 (200),
         *     TELEFONO            VARCHAR2 (45),
         *     SUELDO              FLOAT,
         */

        if (trabajador.getIdTrabajador() != null && trabajador.getIdTrabajador() > 0) {

            query = "update trabajadoresl set direccion_id=?, nombre=?, ap_paterno, " +
                    "ap_materno=?, telefono=?, sueldo=? " +
                    "where id_trabajador=?";

        }else {

            query = "insert into trabajadoresl (id_trabajador, direccion_id, nombre, ap_paterno, " +
                    "ap_materno, telefono, sueldo)" +
                    "values(SEQUENCETRABAJADOR.NEXTVAL,?,?,?,?,?,?)";
        }try(PreparedStatement preparedStatement = conexion.prepareStatement(query)) {

            if (trabajador.getIdTrabajador() != null && trabajador.getIdTrabajador() > 0) {

                preparedStatement.setLong(1, trabajador.getDireccionId());
                preparedStatement.setString(2, trabajador.getNombre());
                preparedStatement.setString(3, trabajador.getApellidoP());
                preparedStatement.setString(4, trabajador.getApellidoM());
                preparedStatement.setString(5, trabajador.getTelefono());
                preparedStatement.setFloat(6, trabajador.getSueldo());
                preparedStatement.setLong(7, trabajador.getIdTrabajador());

            }else{

                preparedStatement.setLong(1, trabajador.getDireccionId());
                preparedStatement.setString(2, trabajador.getNombre());
                preparedStatement.setString(3, trabajador.getApellidoP());
                preparedStatement.setString(4, trabajador.getApellidoM());
                preparedStatement.setString(5, trabajador.getTelefono());
                preparedStatement.setFloat(6, trabajador.getSueldo());

            }

            preparedStatement.executeUpdate();

        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {

        String query = "delete from trabajadoresl where id_trabajador=?";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Long guardarReturnId(Trabajador trabajador) throws SQLException {
        String query;
        Long result = -1L;


        query = "insert into trabajadoresl (ID_TRABAJADOR, DIRECCION_ID, nombre, ap_paterno, ap_materno, telefono, sueldo)" +
                "values(SEQUENCETRABAJADOR.NEXTVAL,?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(query, new String[]{"ID_TRABAJADOR"})) {

            if (trabajador.getIdTrabajador() != null && trabajador.getIdTrabajador() > 0) {

                preparedStatement.setLong(1, trabajador.getIdTrabajador());
                preparedStatement.setLong(2, trabajador.getDireccionId());
                preparedStatement.setString(3, trabajador.getNombre());
                preparedStatement.setString(4, trabajador.getApellidoP());
                preparedStatement.setString(5, trabajador.getApellidoM());
                preparedStatement.setString(6, trabajador.getTelefono());
                preparedStatement.setFloat(7, trabajador.getSueldo());

                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {

                    result = resultSet.getLong(1);


                }

            }

        }

        return result;

    }

    public Trabajador getTrabajador(ResultSet resultSet) throws SQLException{

        /**
         * ID_TRABAJADOR       NUMBER NOT NULL,
         *     DIRECCION_ID        NUMBER NOT NULL,
         *     NOMBRE              VARCHAR2 (200),
         *     AP_PATERNO          VARCHAR2 (200),
         *     AP_MATERNO          VARCHAR2 (200),
         *     TELEFONO            VARCHAR2 (45),
         *     SUELDO              FLOAT,
         */

        Trabajador t = new Trabajador();
        t.setIdTrabajador(resultSet.getLong("ID_TRABAJADOR"));
        t.setDireccionId(resultSet.getLong("DIRECCION_ID"));
        t.setNombre(resultSet.getString("NOMBRE"));
        t.setApellidoP(resultSet.getString("AP_PATERNO"));
        t.setApellidoM(resultSet.getString("AP_MATERNO"));
        t.setTelefono(resultSet.getString("TELEFONO"));
        t.setSueldo(resultSet.getFloat("SUELDO"));

        return t;


    }


}
