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
import java.util.Optional;

@WebServlet("/ropa/eliminar")
public class RopaEliminarServlet extends HttpServlet {

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
                ropaIService.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/ropa/listar");
            } else {

                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "nO EXISTE EL CHOFER EN LA BASE DE DATOS");
            }
        }else

        {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "EL ID ES NULO EN LA URL");

        }

    }
}
