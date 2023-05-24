package br.com.projeto.api.controller;

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

import br.com.projeto.api.model.Pessoa;
import br.com.projeto.api.repository.ApiRepository;
import br.com.projeto.api.service.ApiService;


@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/api")
public class ApiController {
    
    @Autowired
    private ApiRepository repo;

    @Autowired
    private ApiService service;

    @GetMapping("/users")
    public ResponseEntity<List<Pessoa>> findAll(){
        return service.listar();
    }
  
    @PostMapping("/create")
     public ResponseEntity<?> create(@RequestBody Pessoa obj){
        return service.cadastrar(obj);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findOnePerson(@PathVariable Long id){
        return service.FindOnePerson(id);
    }
    

    // @GetMapping("/users/order")
    // public List<Pessoa> orderByNome(){
    //     return repo.findByOrderByNome();
    // }

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody Pessoa obj){
      return service.update(obj);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
       return service.delete(id);
    }
}
