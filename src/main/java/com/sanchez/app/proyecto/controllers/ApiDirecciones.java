package com.sanchez.app.proyecto.controllers;

import com.google.gson.Gson;
import com.sanchez.app.proyecto.interfaces.IDireccionesService;
import com.sanchez.app.proyecto.interfaces.IService;
import com.sanchez.app.proyecto.models.Direccion;
import com.sanchez.app.proyecto.services.DireccionesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/direcciones")
public class ApiDirecciones extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conexion = (Connection) req.getAttribute("conexion");
        IDireccionesService iDireccionesService = new DireccionesService(conexion);

        String calle = req.getParameter("calle");
        String numero = req.getParameter("numero");
        String colonia = req.getParameter("colonia");

        String ciudad = req.getParameter("ciudad");
        String estado = req.getParameter("estado");
        String cp = req.getParameter("cp");

        Direccion direccion = new Direccion();

        direccion.setCalle(calle);
        direccion.setNumero(numero);
        direccion.setColonia(colonia);
        direccion.setCiudad(ciudad);
        direccion.setEstado(estado);
        direccion.setCp(cp);

        resp.setContentType("/application/json");

        Map<String, String> respuesta = new HashMap<>();

        if(calle == null || numero == null || colonia == null || ciudad == null || estado == null || cp == null     ){

            try (PrintWriter out = resp.getWriter()){

                resp.setStatus(400);
                respuesta.put("message", "Debes ingresar todos los valores");
                respuesta.put("Status", "Erros");
                String json = new Gson().toJson(respuesta);
                out.println(json);

            }
        }else {

            Long id = iDireccionesService.guardarReturnId(direccion);
            try (PrintWriter out = resp.getWriter()) {

                resp.setStatus(201);
                respuesta.put("message", id.toString());
                respuesta.put("Status", "success");
                String json = new Gson().toJson(respuesta);
                out.println(json);
            }

        }
    }
}
