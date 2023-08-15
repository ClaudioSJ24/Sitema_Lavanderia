package com.sanchez.app.proyecto.interfaces;

import com.sanchez.app.proyecto.models.Direccion;

public interface IDireccionesService extends IService<Direccion>{

    Long guardarReturnId(Direccion direccion);
}
