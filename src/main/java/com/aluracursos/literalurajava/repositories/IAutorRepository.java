package com.aluracursos.literalurajava.repositories;

import com.aluracursos.literalurajava.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IAutorRepository extends JpaRepository<Autor, Long> {

    @Query(value = "SELECT * FROM autores WHERE nombre ILIKE %:nombre%", nativeQuery = true)
    Optional<Autor> findByNombre(@Param("nombre") String nombre);

    //Optional<Autor> findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :fecha AND (a.fechaDeMuerte IS NULL OR a.fechaDeMuerte >= :fecha)")
    List<Autor> autoresPorFechaDeMuerte(int fecha);
}
