<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.sanchez.app.proyecto.models.*" %>

<%

    //
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores"); //null
     Trabajador trabajador = (Trabajador) request.getAttribute("trabajador");



%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"
             integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
             crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="//cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
<script src="//cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
</head>
<body>

 <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header" id="div1">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                                    <a class="navbar-brand" href="#" id="enlace1">Lavanderia App</a>
            </div>


            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false">ropa<span
                                class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/ropa/listar">Lista ropa</a></li>
                            <li><a href="<%=request.getContextPath()%>/ropa/alta">Alta Ropa</a></li>

                        </ul>

                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false">Clientes<span
                                class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/clientes/listar">Lista Clientes</a></li>
                            <li><a href="<%=request.getContextPath()%>/clientes/alta">Alta Cliente</a></li>

                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                            aria-haspopup="true" aria-expanded="false">Trabajadores<span
                                class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/trabajadores/listar">Lista trabajadores</a></li>
                            <li><a href="<%=request.getContextPath()%>/trabajadores/alta">Alta Trabajadores</a></li>

                        </ul>
                    </li>
                    <li class="dropdown">
                       <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                          aria-haspopup="true" aria-expanded="false">Registros<span
                           class="caret"></span></a>
                       <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/registros/listar"> Lista Registros</a></li>
                            <li><a href="<%=request.getContextPath()%>/registros/alta">Alta Registro</a></li>

                       </ul>
                    </li>

                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
      </nav>

    <div class="container">

        <div class="row">

            <div class="col-12">

                <h2>Editar Trabajador</h2>

            </div>

        </div>

        <br>
        <% if(errores != null && errores.size() > 0) { %>
            <ul class="alert alert-danger mx-5 px-5">

                <% for(String error: errores.values()) {%>

                    <li> <%=error%> </li>
                    <% } %>

            </ul>
            <% } %>

        <div class="row">

            <form action="<%=request.getContextPath()%>/trabajadores/alta" method="post">

                <div class="col-md-12">

                    <div class="form-group">

                        <label for="">Direccion Id</label>
                        <input type="text" name="direccionId" id="direccionId" class="form-control" value="<%=trabajador.getDireccionId() != null? trabajador.getDireccionId(): ""%>">
                        <%
                            if(errores != null && errores.containsKey("direccionId")){
                                out.println("<span class='text-danger'>"+ errores.get("direccionId") + "</span>");
                            }
                        %>

                    </div>

                    <div class="form-group">

                        <label for="">Nombre</label>
                        <input type="text" name="nombre" id="nombre" class="form-control" value="<%=trabajador.getNombre() != null? trabajador.getNombre(): ""%>">
                        <%
                            if(errores != null && errores.containsKey("nombre")){
                                out.println("<span class='text-danger'>"+ errores.get("nombre") + "</span>");
                            }
                        %>

                    </div>

                    <div class="form-group">

                        <label for="">Apellido P</label>
                        <input type="text" name="apellidoP" id="apellidoP" class="form-control" value="<%=trabajador.getApellidoP() != null? trabajador.getApellidoP(): ""%>">
                        <%
                            if(errores != null && errores.containsKey("apellidoP")){
                                out.println("<span class='text-danger'>"+ errores.get("apellidoP") + "</span>");
                            }
                        %>

                    </div>

                    <div class="form-group">

                        <label for="">Apellido M</label>
                        <input type="text" name="apellidoM" id="apellidoM" class="form-control" value="<%=trabajador.getApellidoM() != null? trabajador.getApellidoM(): ""%>">
                        <%
                            if(errores != null && errores.containsKey("apellidoM")){
                                out.println("<span class='text-danger'>"+ errores.get("apellidoM") + "</span>");
                            }
                        %>

                    </div>

                    <div class="form-group">

                        <label for="">Telefono</label>
                        <input type="text" name="telefono" id="telefono" class="form-control" value="<%=trabajador.getTelefono() != null? trabajador.getTelefono(): ""%>">
                        <%
                            if(errores != null && errores.containsKey("telefono")){
                                out.println("<span class='text-danger'>"+ errores.get("telefono") + "</span>");
                            }
                        %>

                    </div>

                    <div class="form-group">

                        <label for="">Sueldo</label>
                        <input type="text" name="sueldo" id="sueldo" class="form-control" value="<%=trabajador.getSueldo() != null? trabajador.getSueldo(): ""%>">
                        <%
                            if(errores != null && errores.containsKey("sueldo")){
                                out.println("<span class='text-danger'>"+ errores.get("sueldo") + "</span>");
                            }
                        %>

                    </div>




                     <div class="form-group">

                         <button type="submit" class="btn btn-success">Guardar</button>

                     </div>



                </div>

            </form>

        </div>

    </div>



</body>
</html>