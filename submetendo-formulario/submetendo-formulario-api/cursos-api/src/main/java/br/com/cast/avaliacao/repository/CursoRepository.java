package br.com.cast.avaliacao.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.cast.avaliacao.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	public Optional<Curso> findById(@Param("id") Long id);
	
	Boolean existsByDtInicio(LocalDate dtInicio);
	
	Boolean existsByDtFim(LocalDate dtFim);
	
}
