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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/clientes/listar")
public class ClientesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conexion = (Connection) req.getAttribute("conexion");

        IService<Cliente> clienteIService = new ClientesService(conexion);
        List<Cliente> clientes = clienteIService.listar();

        /**
         *
         * for (int i = 0; i < clientes.size(); i++) {
         *
         *             resp.getWriter().println("<h1>"+clientes.get(i).getNombre()+"-->"+clientes.get(i).getTelefono()+"</h1>");
         *
         *  }
         *
         */

        req.setAttribute("clientes", clientes);
        getServletContext().getRequestDispatcher("/listaClientes.jsp").forward(req,resp);


    }
}
