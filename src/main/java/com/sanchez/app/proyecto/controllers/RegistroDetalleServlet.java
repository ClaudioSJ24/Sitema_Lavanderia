package com.sanchez.app.proyecto.controllers;

import com.sanchez.app.proyecto.interfaces.IService;
import com.sanchez.app.proyecto.models.Cliente;
import com.sanchez.app.proyecto.models.Registro;
import com.sanchez.app.proyecto.services.ClientesService;
import com.sanchez.app.proyecto.services.RegistrosService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/registros/detalle")
public class RegistroDetalleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conexion = (Connection) req.getAttribute("conexion");
        IService<Registro> registroService = new RegistrosService(conexion);
        long id;

        try {
            id = Long.parseLong(req.getParameter("id"));

        } catch (NumberFormatException e) {
            id = 0L;
        }
        Registro registro = new Registro();

        if (id > 0) {
            Optional<Registro> opRegistro = registroService.getBy(id);
            if (opRegistro.isPresent()) {

                registro = opRegistro.get();
                req.setAttribute("registro", registro);
                getServletContext().getRequestDispatcher("/detalleRegistro.jsp").forward(req,resp);

            } else {

                resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                        "NO EXISTE LA ROPA EN LA BASE DE DATOS");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "EL ID ES NULO EN LA URL");

        }
    }
}
