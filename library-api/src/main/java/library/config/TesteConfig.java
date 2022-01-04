package library.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import library.entities.Autor;
import library.entities.Livro;
import library.repositories.AutorRepository;
import library.repositories.LivroRepository;


//instanciando tudo na classe teste

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {
	
	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private AutorRepository autorRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		//povoando o banco de dados
		
		Autor autor1 = new Autor(null, "John Piper", "pipier@gmail.com");
		Autor autor2 = new Autor(null, "John Stott", "stott@gmail.com");
		Autor autor3 = new Autor(null, "Tim Keller", "keller@gmail.com");
		Autor autor4 = new Autor(null, "C S Lewis", "lewis@gmail.com");

		autorRepository.saveAll(Arrays.asList(autor1,autor2,autor3,autor4));

		Livro l1 = new Livro(null, "Pense", "16/05/2010", 324, autor1);
		Livro l2 = new Livro(null, "O Discipulo Radical", "30/11/2011", 100, autor2);
		Livro l3 = new Livro(null, "As 5 Linguagens do Amor", "03/01/2001", 624, autor4);
		Livro l4 = new Livro(null, "O Deus Prodigo", "27/05/2018", 295, autor3);
		
		livroRepository.saveAll(Arrays.asList(l1,l2,l3,l4));
		
		
	}

}