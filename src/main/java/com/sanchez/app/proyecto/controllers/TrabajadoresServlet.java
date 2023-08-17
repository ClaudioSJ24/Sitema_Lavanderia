package com.sanchez.app.proyecto.controllers;

import com.sanchez.app.proyecto.interfaces.IService;
import com.sanchez.app.proyecto.models.Trabajador;
import com.sanchez.app.proyecto.repositories.TrabajadoresRepository;
import com.sanchez.app.proyecto.services.TrabajadoresService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/trabajadores/listar")

public class TrabajadoresServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conexion = (Connection) req.getAttribute("conexion");
        IService<Trabajador> trabajadorIService = new TrabajadoresService(conexion);
        List<Trabajador> trabajadores = trabajadorIService.listar();

        /**
         *
         * for (int i = 0; i < trabajadores.size(); i++) {
         *
         *             resp.getWriter().println("<h1>"+ trabajadores.get(i).getNombre()+"-->"+trabajadores.get(i).getApellidoP()+"-->"+trabajadores.get(i).getApellidoM()+"</h1>");
         *
         *         }
         *
         */

        req.setAttribute("trabajadores", trabajadores);
        getServletContext().getRequestDispatcher("/listaTrabajadores.jsp").forward(req,resp);




    }
}
