package br.com.projeto.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.model.Pessoa;
import br.com.projeto.api.repository.ApiRepository;
import br.com.projeto.api.service.ApiService;


@RestController
public class ApiController {
    
    @Autowired
    private ApiRepository repo;

    @Autowired
    private ApiService service;

    @GetMapping("/users")
    public ResponseEntity<List<Pessoa>> findAll(){
        return service.listar();
    }

    // @PostMapping("/api")
    // public Pessoa create(@RequestBody Pessoa obj){
    //     return repo.save(obj);
    // }
    @PostMapping("/api")
     public ResponseEntity<?> create(@RequestBody Pessoa obj){
        return service.cadastrar(obj);
    }

    @GetMapping("/user/{id}")
    public Optional<Pessoa> findById(@PathVariable Long id){
        return repo.findById(id);
    }

    @GetMapping("/users/order")
    public List<Pessoa> orderByNome(){
        return repo.findByOrderByNome();
    }

    @PutMapping("/user")
    public Pessoa updateUser(@RequestBody Pessoa obj){
        Optional<Pessoa> optionalPessoa = repo.findById(obj.getId());
        if(optionalPessoa.isPresent()){
            return repo.save(obj);
        }else{
            return obj;
        }
        
    }

    @DeleteMapping("/user/{id}")
    public Void deleteUser(@PathVariable Long id){
        repo.deleteById(id);
        return null;
    }
}
