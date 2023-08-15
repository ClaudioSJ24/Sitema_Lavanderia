package com.sanchez.app.proyecto.filter;

import com.sanchez.app.proyecto.utils.ConexionDB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.sql.Connection;

@WebFilter("/*")
public class ConexionFilter implements Filter {

    private Connection getConnection(){
        ConexionDB c = new ConexionDB();
        return c.getInstance();
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        Connection conexion = this.getConnection();
        servletRequest.setAttribute("conexion", conexion);

        try {

            filterChain.doFilter(servletRequest, servletResponse);

        }catch (IOException e){

            throw  new RuntimeException(e);

        }catch (ServletException se){

            throw new RuntimeException(se);

        }
    }
}
