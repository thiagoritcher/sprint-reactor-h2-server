package br.com.ritcher.server.seguranca.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ritcher.server.lib.PostQuery;
import br.com.ritcher.server.seguranca.model.Usuario;
import br.com.ritcher.server.seguranca.service.UsuarioService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/seguranca/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Usuario> create(@RequestBody Usuario Usuario) {
		return usuarioService.save(Usuario);
	}

	@GetMapping
	public Flux<Usuario> getAllUsuarios(){
		return usuarioService.findAll();
	}

	@PostMapping("/query")
	public Flux<Usuario> getAllUsuarioQuery(@RequestBody PostQuery query){
		return usuarioService.findAllQuery(query.getQuery(), PageRequest.of(0, 50));
	}

	@GetMapping("/{id}")
	 public Mono<ResponseEntity<Usuario>> getUsuarioById(@PathVariable Long id){
        Mono<Usuario> Usuario = usuarioService.findById(id);
        return Usuario.map(u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
	
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Usuario>> updateUsuarioById(@PathVariable Long id, @RequestBody Usuario Usuario){
        return usuarioService.updateUsuario(id,Usuario)
                .map(updatedUsuario -> ResponseEntity.ok(updatedUsuario))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
    
    
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUsuarioById(@PathVariable Long id){
        return usuarioService.deleteUsuario(id)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}