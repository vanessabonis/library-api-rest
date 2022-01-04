package library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import library.entities.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

}

/* com o extends jparepository 
 * podemos usar os métodos (sem precisar implementá-los)
 * 
 * save(), findOne(), findAll(), delete(), count, etc..
 * 
 * tb podemos definir métodos de busca
 * findByNameContaining() 
 * findById() 
 * */