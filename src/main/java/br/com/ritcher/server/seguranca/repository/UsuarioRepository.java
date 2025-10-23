package br.com.ritcher.server.seguranca.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

import br.com.ritcher.server.seguranca.model.Usuario;
import reactor.core.publisher.Flux;

public interface UsuarioRepository 
					extends ReactiveCrudRepository<Usuario, Long>, ReactiveSortingRepository<Usuario, Long>{

	public Flux<Usuario>findAllByNomeOrderByNome(String nome);
}