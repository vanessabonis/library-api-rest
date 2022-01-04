package library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import library.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
