package com.sanchez.app.proyecto.controllers;

import com.sanchez.app.proyecto.interfaces.IService;
import com.sanchez.app.proyecto.models.Registro;
import com.sanchez.app.proyecto.models.Ropa;
import com.sanchez.app.proyecto.services.RegistrosService;
import com.sanchez.app.proyecto.services.RopaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/registros/alta")

public class RegistroAltaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/altaRegistro.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conexion = (Connection) req.getAttribute("conexion");
        IService<Registro> registroIService = new RegistrosService(conexion);

        Integer clienteId = Integer.valueOf(req.getParameter("clienteId"));
        Integer trabajadorId = Integer.valueOf(req.getParameter("trabajadorId"));

        String fechaRecibido = req.getParameter("fechaRecibido"); // 08/09/6534
        LocalDate fechaR;

        try {
            fechaR = LocalDate.parse(fechaRecibido, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }catch (DateTimeParseException e){

            fechaR = null;

        }

        String fechaEntrega = req.getParameter("fechaEntrega"); // 08/09/6534
        LocalDate fechaE;

        try {
            fechaE = LocalDate.parse(fechaRecibido, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }catch (DateTimeParseException e){

            fechaE = null;

        }

        Map<String, String> errores = new HashMap<>();

        if (clienteId == null ) {

            errores.put("cliente", "el nombre es requerido");

        }

        if (trabajadorId== null ) {

            errores.put("trabajador", "el nombre es requerido");

        }


        if (fechaRecibido == null || fechaRecibido.isBlank()) {

            errores.put("fechaRecibido", "el fechaNacimiento es requerido");

        }

        if (fechaEntrega == null || fechaEntrega.isBlank()) {

            errores.put("fechaRecibido", "el fechaNacimiento es requerido");

        }

        if (errores.isEmpty()) {

            Registro r = new Registro();
            r.setIdRegistro(0L);
            r.setClienteId(clienteId.longValue());
            r.setTrabajadorId(trabajadorId.longValue());
            r.setFechaRecibido(fechaR);
            r.setFechaEntrega(fechaE);

            registroIService.guardar(r);

            resp.sendRedirect(req.getContextPath()+"/registros/listar");

        }else {

            req.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/altaRegistro.jsp").forward(req, resp);
        }


    }
}
