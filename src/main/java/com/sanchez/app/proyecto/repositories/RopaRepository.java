package com.sanchez.app.proyecto.repositories;

import com.sanchez.app.proyecto.interfaces.IRepository;
import com.sanchez.app.proyecto.models.Ropa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RopaRepository implements IRepository<Ropa> {
    private Connection conexion;

    public RopaRepository(Connection conexion) {

        this.conexion = conexion;

    }

    @Override
    public List<Ropa> listar() throws SQLException {

        List<Ropa> ropa = new ArrayList<>();

        try (Statement stmt = conexion.createStatement();

             ResultSet resultSet = stmt.executeQuery("SELECT * FROM ROPAC")){

            while (resultSet.next()){

                Ropa r = this.getRopa(resultSet);

                ropa.add(r);

            }

        }catch (SQLException e){

            throw new RuntimeException(e);

        }
        return ropa;
    }


    private Ropa getRopa(ResultSet resultSet) throws SQLException{

        Ropa r = new Ropa();
        r.setIdRopa(resultSet.getLong("ID_ROPA"));
        r.setPantalones(resultSet.getInt("PANTALONES"));
        r.setPantalones(resultSet.getInt("CAMISAS"));
        r.setPantalones(resultSet.getInt("VESTIDOS"));
        r.setPantalones(resultSet.getInt("PLAYERAS"));
        r.setPantalones(resultSet.getInt("FALDAS"));
        r.setPesoTotal(resultSet.getFloat("PESO_TOTAL"));
        r.setTotalPagar(resultSet.getFloat("TOTAL_PAGAR"));

        return r;


    }

    @Override
    public Ropa getBy(Long id) throws SQLException {

        Ropa ropa = null;

        try (PreparedStatement preparedStatement = conexion
                .prepareStatement("SELECT * FROM ropac Where ID_ROPA = ?")){

            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()){

                if (resultSet.next()) {

                    ropa = this.getRopa(resultSet);

                }

            }

        }
        return ropa;
    }

    @Override
    public void guardar(Ropa ropa) throws SQLException {

        String query;

        if (ropa.getIdRopa() != null & ropa.getIdRopa() > 0) {

            query = "update ropac set pantalones=?, camisas=?, vestidos=? " +
                    "playeras=?, faldas=?, peso_total=?, total_pagar=? " +
                    "where id_ropa=?";

        }else {

            query = "insert into ropac (id_ropa, pantalones, camisas, vestidos " +
                    "playeras, faldas, peso_total, total_pagar) " +
                    "values(SEQUENCEROPA.NEXTVAL,?,?,?,?,?,?,?)";

        }

        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)){

            if (ropa.getIdRopa() != null && ropa.getIdRopa() > 0 ) {

                preparedStatement.setInt(1, ropa.getPantalones());
                preparedStatement.setInt(2, ropa.getCamisas());
                preparedStatement.setInt(3, ropa.getVestidos());
                preparedStatement.setInt(4, ropa.getPlayeras());
                preparedStatement.setInt(5, ropa.getFaldas());
                preparedStatement.setFloat(6, ropa.getPesoTotal());
                preparedStatement.setFloat(7, ropa.getTotalPagar());
                preparedStatement.setLong(8, ropa.getIdRopa());

            }else{

                preparedStatement.setInt(1, ropa.getPantalones());
                preparedStatement.setInt(2, ropa.getCamisas());
                preparedStatement.setInt(3, ropa.getVestidos());
                preparedStatement.setInt(4, ropa.getPlayeras());
                preparedStatement.setInt(5, ropa.getFaldas());
                preparedStatement.setFloat(6, ropa.getPesoTotal());
                preparedStatement.setFloat(7, ropa.getTotalPagar());

            }

            preparedStatement.executeUpdate();

        }

    }

    @Override
    public void eliminar(Long id) throws SQLException {

        String query = "delete from ropac where id_ropa=?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)){

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }

    }
}
