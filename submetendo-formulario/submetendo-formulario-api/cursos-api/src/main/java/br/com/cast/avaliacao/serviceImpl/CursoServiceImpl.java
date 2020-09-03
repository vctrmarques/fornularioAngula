package br.com.cast.avaliacao.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import br.com.cast.avaliacao.exeception.CursoException;
import br.com.cast.avaliacao.exeception.ResourceNotFoundException;
import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.model.Curso;
import br.com.cast.avaliacao.repository.CategoriaRepository;
import br.com.cast.avaliacao.repository.CursoRepository;
import br.com.cast.avaliacao.request.CursoResquest;
import br.com.cast.avaliacao.response.CursoResponse;
import br.com.cast.avaliacao.service.CursoService;

@Service
public class CursoServiceImpl implements CursoService {

	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
    private MessageSource messageSource;
	
	@Override
	public Curso salvarCurso(@Valid CursoResquest cursoResquest) {
		
//		if (cursoRepository.existsByDtInicio(cursoResquest.getDtInicio())) {
//			return Utils.badRequest(false, "??????? FALTA IMPLEMENTAR.");
//		}
//		
//		if (cursoRepository.existsByDtFim(cursoResquest.getDtFim())) {
//			return Utils.badRequest(false, "Existe(m) curso(s) planejados(s) dentro do período informado.");
//		}
		
		Curso curso = new Curso(cursoResquest);
		setEntidades(curso, cursoResquest);
		return cursoRepository.save(curso);
	}

	@Override
	public void removerCurso(Long id) throws CursoException {
		try {
			Curso curso = buscarCursoPorId(id);
			cursoRepository.delete(curso);
		}catch(CursoException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new CursoException(String.format(messageSource.getMessage("Curso.erro.remocao", null, Locale.getDefault()), id),e);
		}
	}

	@Override
	public Curso buscarCursoPorId(Long id) throws CursoException {
		try {
			Optional<Curso> curso = cursoRepository.findById(id);
			if (curso.isPresent()) {
				return curso.get();
			}else {
				throw new CursoException(String.format(messageSource.getMessage("Curso.erro.Candidato.naoencontrado", null, Locale.getDefault()), id), null);
			}
		}catch(CursoException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new CursoException(String.format(messageSource.getMessage("Curso.erro.consulta", null, Locale.getDefault()), id),e);
		}
	}

	@Override
	public List<CursoResponse> buscarTodos() throws CursoException {
		try {
			List<CursoResponse> cursosResponse = new ArrayList<CursoResponse>();
			
			Iterable<Curso> cursos = cursoRepository.findAll();
			
			for (Curso curso: cursos) {
				CursoResponse cursoResponse = new CursoResponse();
				cursoResponse.setId(curso.getId());
				cursoResponse.setAssunto(curso.getAssunto());
				cursoResponse.setDtInicio(curso.getDtInicio());
				cursoResponse.setDtFim(curso.getDtFim());
				cursoResponse.setQtdAluno(curso.getQtdAluno());
				cursoResponse.setCategoriaId(curso.getCategoria().getId());
				
				cursosResponse.add(cursoResponse);
			}
			return cursosResponse;
		}catch(Exception e) {
			e.printStackTrace();
			throw new CursoException(messageSource.getMessage("Curso.erro.nenhum.encontrado", null, Locale.getDefault()),e);
		}
	}
	
	private void setEntidades(Curso curso, CursoResquest cursoRequest) {
		
		if (Objects.nonNull(cursoRequest.getCategoriaId())) {
			Categoria categoria = categoriaRepository.findById(cursoRequest.getCategoriaId()).orElseThrow(
					() -> new ResourceNotFoundException("Categoria", "id", cursoRequest.getCategoriaId()));
			curso.setCategoria(categoria);
		}
	}

}
