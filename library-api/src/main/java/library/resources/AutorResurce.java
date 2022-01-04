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

import library.entities.Autor;
import library.services.AutorService;

/*o controller é responsável pelas ações da api, 
 * criar, atualizar, salvar, deletar e buscar"
 * endpoints
 * 
 **/


@CrossOrigin(origins = "http://localhost:8080") //porta do Vue
@RestController
@RequestMapping(value = "/autores")
public class AutorResurce {
	
	@Autowired
	private AutorService service;
	
	//get - recupera os dados do bd
	//listar autores
	@GetMapping
	public ResponseEntity<List<Autor>> findAll() {
			
		List<Autor> list = service.findAll();
			
		return ResponseEntity.ok().body(list);
			
	}
		
	//buscar por id
	@GetMapping(value = "/{id}")
	public ResponseEntity<Autor> findById(@PathVariable Long id){
		Autor obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
		
	//insere dados
	@PostMapping
	public ResponseEntity<Autor> insert(@RequestBody Autor obj){
		//chama o service pra executar a operação de insert
		obj = service.insert(obj);
		//retornando codigo http 201
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
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
	public ResponseEntity<Autor> update(@PathVariable Long id, @RequestBody Autor obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}	

}
