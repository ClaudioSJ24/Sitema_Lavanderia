package com.sanchez.app.proyecto.controllers;

import com.sanchez.app.proyecto.interfaces.IService;
import com.sanchez.app.proyecto.models.Registro;
import com.sanchez.app.proyecto.services.RegistrosService;
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
import java.util.Optional;

@WebServlet("/registros/editar")
public class RegistroEditarServlet extends HttpServlet {

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
                getServletContext().getRequestDispatcher("/editarRegistro.jsp").forward(req,resp);

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

        Registro registro = new Registro();
        registro.setIdRegistro(0L);
        registro.setClienteId(clienteId.longValue());
        registro.setTrabajadorId(trabajadorId.longValue());
        registro.setFechaRecibido(fechaR);
        registro.setFechaEntrega(fechaE);

        if (errores.isEmpty()) {

            registroIService.guardar(registro);

            resp.sendRedirect(req.getContextPath()+"/registros/listar");

        }else {
            req.setAttribute("registro", registro);

            req.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/editarRegistro.jsp").forward(req, resp);
        }


    }
}
