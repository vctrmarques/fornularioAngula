package br.com.cast.avaliacao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.cast.avaliacao.model.Categoria;

@Service
public interface CategoriaService {

	public List<Categoria> findAll();
}
