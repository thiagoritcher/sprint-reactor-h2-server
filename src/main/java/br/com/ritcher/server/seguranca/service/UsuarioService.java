package br.com.ritcher.server.seguranca.service;

import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ritcher.server.seguranca.model.Usuario;
import br.com.ritcher.server.seguranca.repository.UsuarioRepository;
import lombok.extern.apachecommons.CommonsLog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CommonsLog
@Service
@Transactional
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public <S extends Usuario> Mono<S> save(S entity) {

		if(log.isDebugEnabled()) 
			log.debug("Saving " + entity);

		entity.setNew();

		entity.setId(Math.abs(new Random().nextLong()));
		return usuarioRepository.save(entity)
				.checkpoint("save")
				.doOnError(e -> log.error(e));
	}

	public Mono<Usuario> findById(Long id) {
		return usuarioRepository.findById(id)
				.checkpoint("findById")
				.doOnError(e -> log.error(e));
	}

	public Flux<Usuario> findAll() {
		return usuarioRepository.findAll()
				.checkpoint("findAll")
				.doOnError(e -> log.error(e));
	}

	public Mono<Usuario> deleteUsuario(Long usuarioId){
        return usuarioRepository.findById(usuarioId)
				.checkpoint("find")
                .flatMap(existingUsuario -> usuarioRepository.delete(existingUsuario)
				.checkpoint("delete")
                .then(Mono.just(existingUsuario)))
				.doOnError(e -> log.error(e));
    }
	
    public Mono<Usuario> updateUsuario(Long usuarioId, Usuario o){
        return usuarioRepository.findById(usuarioId)
				.checkpoint("find")
                .flatMap(db -> {
                	BeanUtils.copyProperties(o, db, "id");
                    return usuarioRepository.save(db)
							.checkpoint("save")
                    		.doOnError(e -> log.error(e));
                });
    }	
    
    public Flux<Usuario> findAllQuery(String query, PageRequest page){
        return usuarioRepository.findAllByNomeOrderByNome(query)
				.checkpoint("findAll")
				.doOnError(e -> log.error(e));
    }
}