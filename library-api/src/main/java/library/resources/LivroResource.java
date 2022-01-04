package library.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import library.dto.LivroDto;
import library.entities.Livro;
import library.services.LivroService;



//controller
//endpoints

@CrossOrigin(origins = "http://localhost:8080") //porta do Vue
@RestController
@RequestMapping(value = "/livros")
public class LivroResource {
	
	@Autowired
	private LivroService service;
	
	//get - recupera os dados do bd
	//listar livros
	@GetMapping
	public ResponseEntity<List<Livro>> findAll() {
			
		List<Livro> list = service.findAll();
			
		return ResponseEntity.ok().body(list);
			
	}
		
	//buscar por id
	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Long id){
		Livro obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
		
	//insere dados
	@PostMapping
	public ResponseEntity<Livro> insert(@RequestBody LivroDto obj){
		//chama o service pra executar a operação de insert
		var livroResponse = service.insert(obj);
		//retornando codigo http 201
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livroResponse.getId()).toUri();
		return ResponseEntity.created(uri).body(livroResponse);
	}
		
	//delete
	@DeleteMapping(value = "/{id}")
		
	public ResponseEntity<Void> delete(@PathVariable Long  id){
		service.delete(id);
		//cod 204
		return ResponseEntity.noContent().build();
	}
		
	//put
	@PutMapping(value = "/{id}")
	public ResponseEntity<Livro> update(@PathVariable Long id, @RequestBody LivroDto obj){
		var livroResponse = service.update(id, obj);
		return ResponseEntity.ok().body(livroResponse);
	}	

}
