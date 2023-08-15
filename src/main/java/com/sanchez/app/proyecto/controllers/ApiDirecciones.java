package com.sanchez.app.proyecto.controllers;

import com.sanchez.app.proyecto.interfaces.IService;
import com.sanchez.app.proyecto.models.Direccion;
import com.sanchez.app.proyecto.services.DireccionesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/direcciones/api")
public class ApiDirecciones extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conexion = (Connection) req.getAttribute("conexion");
        IService<Direccion> direccionIService = new DireccionesService(conexion);
        List<Direccion> direcciones = direccionIService.listar();

        for (int i = 0; i < direcciones.size(); i++) {

            resp.getWriter().println("<h1>"+direcciones.get(i).getCiudad()+" --> "+direcciones.get(i).getColonia()+"-->"+direcciones.get(i).getEstado()+"</h1>");

        }

    }
}
