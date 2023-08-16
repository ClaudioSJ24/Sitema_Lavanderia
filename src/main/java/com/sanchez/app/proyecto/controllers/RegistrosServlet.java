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
import java.util.List;

@WebServlet("/registros/listar")
public class RegistrosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conexion = (Connection) req.getAttribute("conexion");

        IService<Registro> registroIService = new RegistrosService(conexion);
        List<Registro> registros = registroIService.listar();

        for (int i = 0; i < registros.size(); i++) {

            resp.getWriter().println("<h1>"+registros.get(i).getClienteId()+"-->"+registros.get(i).getFechaEntrega()+"</h1>");

        }
    }
}
