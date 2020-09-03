package br.com.cast.avaliacao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.avaliacao.exeception.CursoException;
import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.model.Curso;
import br.com.cast.avaliacao.request.CursoResquest;
import br.com.cast.avaliacao.response.CursoResponse;
import br.com.cast.avaliacao.rest.Resposta;
import br.com.cast.avaliacao.service.CategoriaService;
import br.com.cast.avaliacao.service.CursoService;
import br.com.cast.avaliacao.util.Constantes;

@RestController
@CrossOrigin("*")
public class RestCursoController {

	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	Logger logger = LoggerFactory.getLogger(RestCursoController.class);

	@RequestMapping(path=Constantes.Url.URL_CURSO, method = RequestMethod.GET)
	public List<CursoResponse> listar() throws CursoException {
		List<CursoResponse> listCurso = new ArrayList<CursoResponse>();
		try {
			listCurso = cursoService.buscarTodos();
			logger.info("Consultando todos os cursos");
		}catch(CursoException e) {
			throw(e);
		}
		return listCurso;
	}

	@RequestMapping(path=Constantes.Url.URL_CURSO + "/{id}", method = RequestMethod.GET)
	public @ResponseBody Resposta consultarcursoPorId(@PathVariable Long id) {
		Resposta resposta = new Resposta();
		try {
			Curso curso = cursoService.buscarCursoPorId(id);
			resposta.setResposta(curso); 
			logger.info(String.format("Consultando curso %s", id));
		}catch(CursoException e) {
			resposta.setCodigo(Constantes.Status.CODIGO_ERRO);
			resposta.setMensagem(e.getMensagem());
		}
		return resposta;
	}

	@RequestMapping(path=Constantes.Url.URL_CURSO, method = RequestMethod.POST)
	public Curso criar(@Valid @RequestBody CursoResquest cursoResquest){
		return cursoService.salvarCurso(cursoResquest);
	}

	@RequestMapping(path=Constantes.Url.URL_CURSO + "/{id}", method = RequestMethod.PUT)
	public @ResponseBody Resposta atualizarcurso(@RequestBody Curso cursoAtualizado, @PathVariable Long id) {
		Resposta resposta = new Resposta();
		try {
			Curso cursoSalvo = cursoService.buscarCursoPorId(id);
			cursoSalvo.setAssunto(cursoAtualizado.getAssunto());
			cursoSalvo.setDtInicio(cursoAtualizado.getDtInicio());
			cursoSalvo.setDtFim(cursoAtualizado.getDtFim());
			cursoSalvo.setQtdAluno(cursoAtualizado.getQtdAluno());
			cursoSalvo.setCategoria(cursoAtualizado.getCategoria());
			//Curso novoCurso = cursoService.salvarCurso(cursoSalvo);
			//resposta.setResposta(novoCurso);
			logger.info(String.format("Atualizando curso %s", id));
		}catch(CursoException e) {
			resposta.setCodigo(Constantes.Status.CODIGO_ERRO);
			resposta.setMensagem(e.getMensagem());
		}
		return resposta;
	}

	@RequestMapping(path=Constantes.Url.URL_CURSO + "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Resposta removercurso(@PathVariable Long id) {
		Resposta resposta = new Resposta();
		try {
			cursoService.removerCurso(id);
			logger.info(String.format("Removendo curso %s", id));
		}catch(CursoException e) {
			resposta.setCodigo(Constantes.Status.CODIGO_ERRO);
			resposta.setMensagem(e.getMensagem());
		}
		return resposta;
	}
	
	@RequestMapping(path=Constantes.Url.URL_CATEGORIA, method = RequestMethod.GET)
	 public List<Categoria> listarCategoria() {
		 return categoriaService.findAll(); 
	}
	
	/*
	@GetMapping
	public List<Contato> listar() {
		return contatos.findAll();
	}

	@PostMapping
	public Contato criar(@Valid @RequestBody Contato contato) {
		return contatos.save(contato);
	}
 */


}
