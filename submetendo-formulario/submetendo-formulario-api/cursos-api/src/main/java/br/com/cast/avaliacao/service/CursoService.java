package br.com.cast.avaliacao.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cast.avaliacao.exeception.CursoException;
import br.com.cast.avaliacao.model.Curso;
import br.com.cast.avaliacao.request.CursoResquest;
import br.com.cast.avaliacao.response.CursoResponse;

@Service
public interface CursoService {

	public Curso salvarCurso(@Valid CursoResquest cursoResquest);
	
	public void removerCurso(Long id) throws CursoException;
	
	public Curso buscarCursoPorId(Long id) throws CursoException;
	
	public List<CursoResponse> buscarTodos() throws CursoException;


}
