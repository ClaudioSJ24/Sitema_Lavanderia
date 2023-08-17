<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>

<%

    //
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores"); //null

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

                <h2>Listado de Ropa</h2>

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

            <form action="<%=request.getContextPath()%>/ropa/alta" method="post">

                <div class="col-md-12">

                    <div class="form-group">

                        <label for="">Pantalones</label>
                        <input type="text" name="pantalones" id="pantalones" class="form-control" value="${param.pantalones}">
                        <%
                            if(errores != null && errores.containsKey("pantalones")){
                                out.println("<span class='text-danger'>"+ errores.get("pantalones") + "</span>");
                            }
                        %>

                    </div>

                    <div class="form-group">

                        <label for="">Camisas</label>
                        <input type="text" name="camisas" id="camisas" class="form-control" value="${param.camisas}">
                        <%
                            if(errores != null && errores.containsKey("camisas")){
                                out.println("<span class='text-danger'>"+ errores.get("camisas") + "</span>");
                            }
                        %>

                    </div>

                    <div class="form-group">

                        <label for="">Vestidos</label>
                        <input type="text" name="vestidos" id="vestidos" class="form-control" value="${param.vestidos}">
                        <%
                            if(errores != null && errores.containsKey("vestidos")){
                                out.println("<span class='text-danger'>"+ errores.get("vestidos") + "</span>");
                            }
                        %>

                    </div>

                    <div class="form-group">

                        <label for="">Playeras</label>
                        <input type="text" name="playeras" id="playeras" class="form-control" value="${param.playeras}">
                        <%
                            if(errores != null && errores.containsKey("playeras")){
                                out.println("<span class='text-danger'>"+ errores.get("playeras") + "</span>");
                            }
                        %>

                    </div>

                    <div class="form-group">

                        <label for="">Faldas</label>
                        <input type="text" name="faldas" id="faldas" class="form-control" value="${param.faldas}">
                        <%
                            if(errores != null && errores.containsKey("faldas")){
                                out.println("<span class='text-danger'>"+ errores.get("faldas") + "</span>");
                            }
                        %>

                    </div>

                    <div class="form-group">

                        <label for="">Peso Total</label>
                        <input type="text" name="pesoTotal" id="pesoTotal" class="form-control" value="${param.pesoTotal}">
                        <%
                            if(errores != null && errores.containsKey("pesoTotal")){
                                out.println("<span class='text-danger'>"+ errores.get("pesoTotal") + "</span>");
                            }
                        %>

                    </div>

                    <div class="form-group">

                        <label for="">Total Pagar</label>
                        <input type="text" name="totalPagar" id="totalPagar" class="form-control" value="${param.totalPagar}">
                        <%
                            if(errores != null && errores.containsKey("totalPagar")){
                                out.println("<span class='text-danger'>"+ errores.get("totalPagar") + "</span>");
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