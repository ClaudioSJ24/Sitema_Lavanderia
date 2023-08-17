package com.sanchez.app.proyecto.controllers;

import com.sanchez.app.proyecto.interfaces.IService;
import com.sanchez.app.proyecto.models.Ropa;
import com.sanchez.app.proyecto.services.RopaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/ropa/editar")
public class RopaEditarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conexion = (Connection) req.getAttribute("conexion");
        IService<Ropa> ropaIService = new RopaService(conexion);
        long id;

        try {
            id = Long.parseLong(req.getParameter("id"));

        } catch (NumberFormatException e) {
            id = 0L;
        }
        Ropa ropa = new Ropa();

        if (id > 0) {
            Optional<Ropa> opRopa = ropaIService.getBy(id);
            if (opRopa.isPresent()) {

                ropa = opRopa.get();
                req.setAttribute("ropa", ropa);
                getServletContext().getRequestDispatcher("/editarRopa.jsp").forward(req,resp);

            } else {

                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "NO EXISTE LA ROPA EN LA BASE DE DATOS");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "EL ID ES NULO EN LA URL");

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conexion = (Connection) req.getAttribute("conexion");
        IService<Ropa> ropaIService = new RopaService(conexion);

        Integer pantalones = Integer.valueOf(req.getParameter("pantalones"));
        Integer camisas = Integer.valueOf(req.getParameter("camisas"));
        Integer vestidos = Integer.valueOf(req.getParameter("vestidos"));
        Integer playeras = Integer.valueOf(req.getParameter("playeras"));
        Integer faldas = Integer.valueOf(req.getParameter("faldas"));
        Float pesoTotal = Float.valueOf(req.getParameter("pesoTotal"));
        Float totalPagar = Float.valueOf(req.getParameter("totalPagar"));

        Map<String, String> errores = new HashMap<>();

        if (pantalones == null) {

            errores.put("pantalon", "campo requerido");

        }

        if (camisas == null) {

            errores.put("camisas", "campo requerido");

        }

        if (vestidos == null) {

            errores.put("vestidos", "campo requerido");

        }

        if (playeras == null) {

            errores.put("playeras", "campo requerido");

        }

        if (faldas == null) {

            errores.put("faldas", "campo requerido");

        }

        if (pantalones == null) {

            errores.put("pantalon", "campo requerido");

        }

        if (pantalones == null) {

            errores.put("pantalon", "campo requerido");

        }

        if (pesoTotal == null) {

            errores.put("pesoTotal", "campo requerido");

        }

        if (totalPagar == null) {

            errores.put("totalPagar", "campo requerido");

        }

        long id;
        id = Long.parseLong(req.getParameter("id"));
        Ropa r = new Ropa();
        r.setIdRopa(id);
        r.setPantalones(pantalones);
        r.setCamisas(camisas);
        r.setVestidos(vestidos);
        r.setPlayeras(playeras);
        r.setFaldas(faldas);
        r.setPesoTotal(pesoTotal);
        r.setTotalPagar(totalPagar);

        if (errores.isEmpty()) {

            ropaIService.guardar(r);

            resp.sendRedirect(req.getContextPath()+"/ropa/listar");

        }else{

            req.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/editarRopa.jsp").forward(req, resp);
        }
    }
}
