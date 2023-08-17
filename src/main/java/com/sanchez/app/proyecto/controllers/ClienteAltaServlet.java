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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/clientes/alta")
public class ClienteAltaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/altaCliente.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conexion = (Connection) req.getAttribute("conexion");
        IService<Cliente> clienteService = new ClientesService(conexion);


        Integer ropaId = Integer.valueOf(req.getParameter("ropaId"));
        String nombre = req.getParameter("nombre");
        String telefono = req.getParameter("telefono");

        Map<String, String> errores = new HashMap<>();

        if (ropaId == null) {

            errores.put("idRopa", "campo requerido");

        }

        if (nombre == null  || nombre.isBlank()) {

            errores.put("nombre", "campo requerido");

        }

        if (telefono == null || telefono.isBlank()) {

            errores.put("telefono", "campo requerido");

        }



        if (errores.isEmpty()) {

            Cliente c = new Cliente();
            c.setIdCliente(0L);
            c.setRopaId(ropaId.longValue());
            c.setNombre(nombre);
            c.setTelefono(telefono);

            clienteService.guardar(c);
            resp.sendRedirect(req.getContextPath()+"/clientes/listar");

        }else{

            req.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/altaCliente.jsp").forward(req, resp);

        }

    }



}
