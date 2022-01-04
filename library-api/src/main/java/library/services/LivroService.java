package library.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import library.dto.LivroDto;
import library.entities.Livro;
import library.repositories.AutorRepository;
import library.repositories.LivroRepository;
import library.services.exceptions.ResourceNotFoundException;


@Service
public class LivroService {

	//injeção de dependencia de forma transparente ao programador
		@Autowired 
		private LivroRepository repository;
		
		@Autowired
		private AutorRepository autorRepository;
		
		//retorna tds livros
		public List<Livro> findAll(){
			return repository.findAll();
		}
		
		//buscar livro por id
		public Livro findById(Long id) {
			
			Optional<Livro> obj = repository.findById(id);
			return obj.orElseThrow(() -> new ResourceNotFoundException(id));
			
		}
		
		//salvar no bd(retorna livro salvo)
		public Livro insert(LivroDto obj) {
			var livro = new Livro();
			livro.setNome(obj.getNome());
			livro.setDataDeLancamento(obj.getDataDeLancamento());
			livro.setNumPaginas(obj.getNumPaginas());
			livro.setAutor(autorRepository.findById(obj.getAutorId()).get());
			
			//insere no bd um novo obj do tipo livvro
			return repository.save(livro);
		}
		
		//deletar
		public void delete(Long id) {
			try {
				repository.deleteById(id);
			}catch (EmptyResultDataAccessException e) {
				throw new ResourceNotFoundException(id);
			}
			
		}
		
		//atualizar livro
		public Livro update(Long id, LivroDto obj) {
			try {
				
				var livro = new Livro();
				updateData(livro, obj);
				return repository.save(livro);
				
			}catch(EntityNotFoundException e) {
				
				throw new ResourceNotFoundException(id);
				
			}
			
		}

		private void updateData(Livro livro, LivroDto obj) {
			livro.setNome(obj.getNome());
			livro.setDataDeLancamento(obj.getDataDeLancamento());
			livro.setNumPaginas(obj.getNumPaginas());
			livro.setAutor(autorRepository.findById(obj.getAutorId()).get());


		}
	
}
