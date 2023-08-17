package com.sanchez.app.proyecto.controllers;

import com.sanchez.app.proyecto.models.Ropa;
import com.sanchez.app.proyecto.interfaces.IService;
import com.sanchez.app.proyecto.services.RopaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/ropa/listar")
public class RopaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conexion = (Connection) req.getAttribute("conexion");
        IService<Ropa> ropaIService = new RopaService(conexion);
        List<Ropa> ropa = ropaIService.listar();

        /**
         * for (int i = 0; i < ropa.size(); i++) {
         *
         *             resp.getWriter().println("<h1>"+ ropa.get(i).getIdRopa()+" -> " +
         *                     ropa.get(i).getTotalPagar()+" -> "+ ropa.get(i).getPesoTotal()+"</h1>");
         *
         *         }
         */

        req.setAttribute("ropa", ropa);
        getServletContext().getRequestDispatcher("/listaRopa.jsp").forward(req,resp);

    }
}
