package br.com.alura.screammatch.repository;

import br.com.alura.screammatch.Model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie,Long> {

}
