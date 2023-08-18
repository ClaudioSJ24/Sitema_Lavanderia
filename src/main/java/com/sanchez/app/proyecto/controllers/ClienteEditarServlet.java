package com.sanchez.app.proyecto.controllers;

import com.sanchez.app.proyecto.interfaces.IService;
import com.sanchez.app.proyecto.models.Cliente;
import com.sanchez.app.proyecto.models.Ropa;
import com.sanchez.app.proyecto.services.ClientesService;
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

@WebServlet("/clientes/editar")
public class ClienteEditarServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conexion = (Connection) req.getAttribute("conexion");
        ClientesService clienteService = new ClientesService(conexion);
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

                cliente = opCliente.get();
                req.setAttribute("cliente", cliente);
                getServletContext().getRequestDispatcher("/editarCliente.jsp").forward(req,resp);

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
        ClientesService clienteService = new ClientesService(conexion);


        Integer ropaId = Integer.valueOf(req.getParameter("ropaId"));
        String nombre = req.getParameter("nombre");
        String telefono = req.getParameter("telefono");

        Map<String, String> errores = new HashMap<>();

        if (ropaId == null) {

            errores.put("idCliente", "campo requerido");

        }

        if (nombre == null  || nombre.isBlank()) {

            errores.put("nombre", "campo requerido");

        }

        if (telefono == null || telefono.isBlank()) {

            errores.put("telefono", "campo requerido");

        }

        long id;
        id = Long.parseLong(req.getParameter("id"));
        Cliente cliente = new Cliente();
        cliente.setIdCliente(id);
        cliente.setRopaId(ropaId.longValue());
        cliente.setNombre(nombre);
        cliente.setTelefono(telefono);


        if (errores.isEmpty()) {


            clienteService.guardar(cliente);
            resp.sendRedirect(req.getContextPath()+"/clientes/listar");

        }else{
            req.setAttribute("cliente", cliente);
            req.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/editarCliente.jsp").forward(req, resp);

        }

    }
}
