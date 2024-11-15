package com.aluracursos.literalurajava.services;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);

}
