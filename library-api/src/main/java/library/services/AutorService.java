package library.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import library.entities.Autor;
import library.repositories.AutorRepository;
import library.services.exceptions.ResourceNotFoundException;


@Service
public class AutorService {
	
	//injeção de dependencia de forma transparente ao programador
		@Autowired 
		private AutorRepository repository;
		
		//retorna tds autores
		public List<Autor> findAll(){
			return repository.findAll();
		}
		
		//buscar autor por id
		public Autor findById(Long id) {
			
			Optional<Autor> obj = repository.findById(id);
			return obj.orElseThrow(() -> new ResourceNotFoundException(id));
			
		}
		
		//salvar no bd(retorna autor salvo)
		public Autor insert(Autor obj) {
			//insere no bd um novo obj do tipo autor
			return repository.save(obj);
		}
		
		//deletar
		public void delete(Long id) {
			try {
				repository.deleteById(id);
			}catch (EmptyResultDataAccessException e) {
				throw new ResourceNotFoundException(id);
			}
			
		}
		
		//atualizar autor
		@SuppressWarnings("deprecation")
		public Autor update(Long id, Autor obj) {
			try {
				
				Autor entity = repository.getOne(id); //instancia um produto sem efetuar op no bd
				updateData(entity, obj);
				return repository.save(entity);
				
			}catch(EntityNotFoundException e) {
				
				throw new ResourceNotFoundException(id);
				
			}
			
		}

		private void updateData(Autor entity, Autor obj) {
			entity.setNome(obj.getNome());
			entity.setEmail(obj.getEmail());
			
		}
	
}
