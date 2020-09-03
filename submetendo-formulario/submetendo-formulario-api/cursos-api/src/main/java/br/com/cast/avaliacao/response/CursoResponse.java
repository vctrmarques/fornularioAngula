package br.com.cast.avaliacao.response;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.cast.avaliacao.model.Curso;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CursoResponse {

	private Long id;
	
	private String assunto;
	
	private LocalDate dtInicio;
	
	private LocalDate dtFim;
	
	private Integer qtdAluno;

	private Long categoriaId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public LocalDate getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(LocalDate dtInicio) {
		this.dtInicio = dtInicio;
	}

	public LocalDate getDtFim() {
		return dtFim;
	}

	public void setDtFim(LocalDate dtFim) {
		this.dtFim = dtFim;
	}
	
	public Integer getQtdAluno() {
		return qtdAluno;
	}

	public void setQtdAluno(Integer qtdAluno) {
		this.qtdAluno = qtdAluno;
	}


	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public CursoResponse() {}
	
	public CursoResponse(Curso curso) {
		this.setId(curso.getId());
		this.setAssunto(curso.getAssunto());
		this.setDtInicio(curso.getDtInicio());
		this.setQtdAluno(curso.getQtdAluno());
		this.setCategoriaId(curso.getCategoria().getId());
	}
	
}
