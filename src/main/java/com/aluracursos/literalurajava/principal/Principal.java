package com.aluracursos.literalurajava.principal;

import com.aluracursos.literalurajava.models.*;

import com.aluracursos.literalurajava.repositories.IAutorRepository;
import com.aluracursos.literalurajava.repositories.ILibroRepository;
import com.aluracursos.literalurajava.services.ConsumoAPI;
import com.aluracursos.literalurajava.services.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private ILibroRepository libroRepository;
    private IAutorRepository autorRepository;
    private List<Libro> libros;
    private List<Autor> autores;
    private List<String> idiomas;


    public Principal(ILibroRepository libroRepository, IAutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        int opcion = 1;
        while (opcion != 0) {
            var menu = """
                    ---------------------------------------------
                                CATALOGO DE LIBROS
                    ---------------------------------------------
                    1. Buscar y registar libro
                    2. Listar libros registrados
                    3. Listar autores registrados
                    4. Listar autores vivos en un determinado año
                    5. Listar libros por idioma
                    6. Estadistica de libros registrados
                    7. Top 10 libros mas descargados
                    8. Buscar autor registrado
                    ---------------------------------------------
                    Selecciona una opcion (0- Salir)
                    """;
            System.out.println(menu);
            if (teclado.hasNextInt()) {
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibro();
                        break;
                    case 2:
                        listarLibrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivos();
                        break;
                    case 5:
                        listarLibrosPorIdioma();
                        break;
                    case 6:
                        estadisticaLibrosRegistrados();
                        break;
                    case 7:
                        top10LibrosDescargados();
                        break;
                    case 8:
                        buscarAutorRegistrado();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicacion");
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }
            } else {
                System.out.println("Opción no válida");
                teclado.next();
            }
        }
    }



    private void buscarLibro() {
        System.out.println("Ingrese el nombre del libro que desea agregar:");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        System.out.println("Respuesta de la API: " + json);
        registrarLibro(json);
    }

    private void registrarLibro(String json) {
        try {
            DatosAutor datosAutor = convierteDatos.obtenerDatos(json, DatosAutor.class);
            DatosLibros datosLibro = convierteDatos.obtenerDatos(json, DatosLibros.class);
            //Verifica si el autor ya existe
            Autor autor = autorRepository.findByNombre(datosAutor.nombre())
                    .orElseGet(() -> autorRepository.save(new Autor(datosAutor)));
            //Verifica si el libro ya existe
            if (libroRepository.findByTitulo(datosLibro.titulo()).isEmpty()) {
                Libro libro = new Libro(datosLibro);
                libro.setAutor(autor);
                libroRepository.save(libro);
                System.out.println(libro);
                System.out.println("Libro agregado con exito");

            } else {
                System.out.printf("---------------------------------------------\n");
                System.out.println("El libro ya se encuntra registrado");
            }
        } catch (NullPointerException e) {
            System.out.printf("---------------------------------------------\n");
            System.out.println("Libro no encontrado");
        }
    }

    private void listarLibrosRegistrados() {
        libros = libroRepository.findAll();
        libros.stream().forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        autores = autorRepository.findAll();
        autores.stream().forEach(System.out::println);
    }

    private void listarAutoresVivos() {
        System.out.println("Indica el año: ");
        int fecha = teclado.nextInt();
        autores = autorRepository.autoresPorFechaDeMuerte(fecha);
        if (autores.isEmpty()) {
            System.out.println("Ningun autor vivo en determinado año ");
        } else {
            autores.stream().forEach(System.out::println);
        }
    }

    public void listarLibrosPorIdioma() {
        idiomas = libroRepository.idiomasLibros();
        System.out.printf("------------------IDIOMAS--------------------\n");
        idiomas.stream().forEach(System.out::println);
        System.out.printf("---------------------------------------------\n");
        System.out.println("Ingresa el idioma por el que deseas buscar: ");
        var idiomaSeleccionado = teclado.nextLine().toLowerCase();
        libros = libroRepository.librosPoridioma(idiomaSeleccionado);
        if (libros.isEmpty()) {
            System.out.println("Opcion no valida");
        } else {
            libros.stream().forEach(System.out::println);
        }
    }

    public void estadisticaLibrosRegistrados() {
        DoubleSummaryStatistics estadictica = libroRepository.findAll().stream()
                .filter(l -> l.getNumeroDeDescargas() > 0)
                .collect(Collectors.summarizingDouble(Libro::getNumeroDeDescargas));
        System.out.println("Cantidad media de descargas: " + estadictica.getAverage());
        System.out.println("Cantidad maxima de descargas: " + estadictica.getMax());
        System.out.println("Cantidad minima de descargas: " + estadictica.getMin());
        System.out.println("Cantidad de resgistros evaluados para calcular las estadisticas: " + estadictica.getCount());
    }

    public void top10LibrosDescargados() {
        libroRepository.findTop10ByOrderByNumeroDeDescargasDesc().forEach(System.out::println);
    }

    public void buscarAutorRegistrado() {
        System.out.println("Nombre del autor que deseas buscar: ");
        var nombreAutor = teclado.nextLine().toLowerCase().trim();
        var autor = autorRepository.findByNombre(nombreAutor);
        System.out.println("Buscando autor con nombre: " + nombreAutor);
        System.out.println(autor);
        if (autor.isEmpty()) {
            System.out.println("El autor no se encuentra registrado");
        } else {
            System.out.println(autor);
        }
    }
}
