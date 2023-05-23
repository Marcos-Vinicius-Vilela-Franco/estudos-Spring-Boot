package br.com.projeto.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.api.model.Pessoa;

@Repository
public interface ApiRepository extends CrudRepository<Pessoa,Long> {
    List<Pessoa> findAll();
    
    List<Pessoa>findByOrderByNome();

    int countById(Long id);
}
