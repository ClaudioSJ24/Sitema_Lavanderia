package com.sanchez.app.proyecto.controllers;

import com.sanchez.app.proyecto.interfaces.IService;
import com.sanchez.app.proyecto.models.Ropa;
import com.sanchez.app.proyecto.models.Trabajador;
import com.sanchez.app.proyecto.services.RopaService;
import com.sanchez.app.proyecto.services.TrabajadoresService;
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

@WebServlet("/trabajadores/editar")
public class TrabajadorEditarServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conexion = (Connection) req.getAttribute("conexion");
        IService<Trabajador> trabajadorIService = new TrabajadoresService(conexion);
        long id;

        try {
            id = Long.parseLong(req.getParameter("id"));

        } catch (NumberFormatException e) {
            id = 0L;
        }
        Trabajador trabajador = new Trabajador();

        if (id > 0) {
            Optional<Trabajador> opTrabajador = trabajadorIService.getBy(id);
            if (opTrabajador.isPresent()) {

                trabajador = opTrabajador.get();
                req.setAttribute("trabajador", trabajador);
                getServletContext().getRequestDispatcher("/editarTrabajador.jsp").forward(req,resp);

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
        IService<Trabajador> trabajadorIService = new TrabajadoresService(conexion);


        Integer direccionId = Integer.valueOf(req.getParameter("direccionId"));
        String nombre = req.getParameter("nombre");
        String apellidoP = req.getParameter("apellidoP");
        String apellidoM = req.getParameter("apellidoM");
        String telefono = req.getParameter("telefono");
        Float sueldo = Float.valueOf(req.getParameter("sueldo"));


        Map<String, String> errores = new HashMap<>();

        if (direccionId == null) {

            errores.put("idDireccion", "campo requerido");

        }

        if (nombre == null  || nombre.isBlank()) {

            errores.put("nombre", "campo requerido");

        }

        if (apellidoP == null  || apellidoP.isBlank()) {

            errores.put("apellidoP", "campo requerido");

        }

        if (apellidoM == null  || apellidoM.isBlank()) {

            errores.put("apellidoM", "campo requerido");

        }

        if (telefono == null || telefono.isBlank()) {

            errores.put("telefono", "campo requerido");

        }

        if (sueldo == null ) {

            errores.put("sueldo", "campo requerido");

        }

        long id;
        id = Long.parseLong("id");
        Trabajador t = new Trabajador();
        t.setIdTrabajador(id);
        t.setDireccionId(direccionId.longValue());
        t.setNombre(nombre);
        t.setApellidoP(apellidoP);
        t.setApellidoM(apellidoM);
        t.setTelefono(telefono);
        t.setSueldo(sueldo);

        if (errores.isEmpty()) {

            trabajadorIService.guardar(t);
            resp.sendRedirect(req.getContextPath()+"/trabajadores/listar");

        }else{

            req.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/editarTrabajador.jsp").forward(req, resp);

        }

    }

}
