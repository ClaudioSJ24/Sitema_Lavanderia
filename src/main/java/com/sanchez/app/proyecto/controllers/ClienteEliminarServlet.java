package com.sanchez.app.proyecto.controllers;


import com.sanchez.app.proyecto.interfaces.IService;
import com.sanchez.app.proyecto.models.Cliente;
import com.sanchez.app.proyecto.services.ClientesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/clientes/eliminar")
public class ClienteEliminarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conexion = (Connection) req.getAttribute("conexion");
        IService<Cliente> clienteService = new ClientesService(conexion);
        long id;

        try {
            id = Long.parseLong(req.getParameter("id"));

        } catch (NumberFormatException e) {
            id = 0L;
        }
        Cliente cliente = new Cliente();

        if (id > 0) {
            Optional<Cliente> opCliente = clienteService.getBy(id);
            if (opCliente.isPresent()) {

                clienteService.eliminar(id);

                resp.sendRedirect(req.getContextPath()+"/clientes/listar");

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
