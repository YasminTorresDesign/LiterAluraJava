package com.aluracursos.literalurajava.principal;

import com.aluracursos.literalurajava.models.Datos;
import com.aluracursos.literalurajava.models.DatosLibros;
import com.aluracursos.literalurajava.services.ConsumoAPI;
import com.aluracursos.literalurajava.services.ConvierteDatos;

import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/";
    public void muestraElMenu() {

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por titulo 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEnDeterminadoAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }

        }

    }


    private void buscarLibroPorTitulo() {


        System.out.println("Catalogo de Libros");
        //Busqueda de libros por nombre
        System.out.println("Que libro quiere buscar: ");

        var json = consumoAPI.obtenerDatos(URL_BASE);
        //System.out.println(json);

        var datos = conversor.obtenerDatos(json, Datos.class);
        //System.out.println(datos);

        var tituloLibro = teclado.nextLine();
        json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if(libroBuscado.isPresent()){
            System.out.println("Libro Encontrado");
            System.out.println(libroBuscado.get());
        }else {
            System.out.println("Libro No Encontrado");
        }
    }

    private void listarLibrosRegistrados() {
        System.out.println("Registrados");
    }

    private void listarAutoresRegistrados() {
        System.out.println("Autores");
    }

    private void listarAutoresVivosEnDeterminadoAnio() {
        System.out.println("vivos");
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Idioma");
    }
}
