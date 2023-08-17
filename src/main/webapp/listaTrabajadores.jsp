<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.sanchez.app.proyecto.models.*" %>

<%

    //Obtener lista de ropa
    List<Trabajador> trabajadores = (List<Trabajador>) request.getAttribute("trabajadores");

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

            <div class="col-6">

                <h2>Listado de Trabajadores</h2>

            </div>

            <div class="col-6">

                <a href="<%=request.getContextPath()%>/trabajadores/alta"  class="btn btn-success"> Alta Trabajador</a>

            </div>

        </div>

        <div class="row">

            <div class="col-12">

                <div class="table-responsive">

                    <table class="table table-bordered table-striped" id="tablaTranajadores" width="100%" cellspacing="0">

                        <thead>

                            <tr>

                                <th>Id Trabajador</th>
                                <th>Id Direccion</th>
                                <th>Nombre</th>
                                <th>Ap Paterno</th>
                                <th>Ap Materno</th>
                                <th>Telefono</th>
                                <th>Sueldo</th>

                                <th></th>
                                <th></th>
                                <th></th>

                            </tr>

                        </thead>

                        <tbody>

                            <% for(Trabajador t: trabajadores) {%>
                                <tr>

                                    <td> <%=t.getIdTrabajador()%> </td>
                                    <td> <%=t.getDireccionId()%> </td>
                                    <td> <%=t.getNombre()%> </td>
                                    <td> <%=t.getApellidoP()%> </td>
                                    <td> <%=t.getApellidoM()%> </td>
                                    <td> <%=t.getTelefono()%> </td>
                                    <td> <%=t.getSueldo()%> </td>
                                    <td>

                                        <a href="<%=request.getContextPath()%>/trabajadores/detalle?id=<%=t.getIdTrabajador()%>" class="btn btn-success">

                                            Detalle

                                        </a>

                                    </td>

                                    <td>

                                        <a href="<%=request.getContextPath()%>/trabajadores/editar?id=<%=t.getIdTrabajador()%>" class="btn btn-primary">

                                            Editar

                                        </a>

                                    </td>

                                    <td>

                                        <a href="<%=request.getContextPath()%>/trabajadores/eliminar?id=<%=t.getIdTrabajador()%>" class="btn btn-danger">

                                            Eliminar

                                        </a>

                                    </td>
                                </tr>

                                <% } %>

                        </tbody>

                    </table>

                </div>

            </div>

        </div>

    </div>



</body>
</html>