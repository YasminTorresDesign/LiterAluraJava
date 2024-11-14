package com.aluracursos.literalurajava.principal;

import com.aluracursos.literalurajava.models.Autor;
import com.aluracursos.literalurajava.models.DatosLibros;
import com.aluracursos.literalurajava.models.Libro;
import com.aluracursos.literalurajava.services.ConsumoAPI;
import com.aluracursos.literalurajava.services.ConvierteDatos;

import java.util.*;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/";

    private List<Libro> libros;
    private List<Autor> autores;
    private List<String> idiomas;
    public void muestraElMenu() {

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por titulo 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    6 - Estadistica de libros agregados
                    7 - Top 10 libros mas descargados
                    8 - Buscar autor registrado
                    0 - Salir
                    ---------------------------------------------
                    Selecciona una opcion para continuar (1 - 8)
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
                case 6:
                    estadisticaLibrosAgregados();
                    break;
                case 7:
                    top10LibrosDescargados();
                    break;
                case 8:
                    buscarAutorAgregado();
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
    }

    private void listarLibrosRegistrados() {
    }

    private void listarAutoresRegistrados() {
    }

    private void listarAutoresVivosEnDeterminadoAnio() {
    }

    private void listarLibrosPorIdioma() {
    }

    private void estadisticaLibrosAgregados() {
    }

    private void top10LibrosDescargados() {
    }

    private void buscarAutorAgregado() {
    }


}
