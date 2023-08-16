package com.sanchez.app.proyecto.interfaces;

import com.sanchez.app.proyecto.models.Cliente;

public interface IClientesService extends IService<Cliente>{

    Long guardarReturnId(Cliente cliente);
}
