package com.sanchez.app.proyecto.controllers;

import com.sanchez.app.proyecto.interfaces.IService;
import com.sanchez.app.proyecto.models.Cliente;
import com.sanchez.app.proyecto.models.Trabajador;
import com.sanchez.app.proyecto.services.ClientesService;
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

@WebServlet("/trabajadores/alta")
public class TrabajadorAltaServlet extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/altaTrabajador.jsp").forward(req,resp);
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



        if (errores.isEmpty()) {

            Trabajador t = new Trabajador();
            t.setIdTrabajador(0L);
            t.setDireccionId(Long.valueOf(direccionId));
            t.setNombre(nombre);
            t.setApellidoP(apellidoP);
            t.setApellidoM(apellidoM);
            t.setTelefono(telefono);
            t.setSueldo(sueldo);

            trabajadorIService.guardar(t);
            resp.sendRedirect(req.getContextPath()+"/trabajadores/listar");

        }else{

            req.setAttribute("errores",errores);
            getServletContext().getRequestDispatcher("/altaTrabajador.jsp").forward(req, resp);

        }

    }


}
