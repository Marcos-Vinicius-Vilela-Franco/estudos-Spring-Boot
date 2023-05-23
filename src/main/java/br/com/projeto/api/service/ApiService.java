package br.com.projeto.api.service;

import java.util.List;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.model.Mensagem;
import br.com.projeto.api.model.Pessoa;
import br.com.projeto.api.repository.ApiRepository;

@Service
public class ApiService {
    @Autowired
    private Mensagem mensagem;

    @Autowired
    private ApiRepository repo;

    //método para cadastrar "pessoa"
    public ResponseEntity<?> cadastrar(Pessoa obj){
        if(obj.getNome().equals("")){
            mensagem.setMensagem("o nome precisa ser preenchido!");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }else if(obj.getIdade()<0){
            mensagem.setMensagem("Informe uma idade válida!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(repo.save(obj),HttpStatus.CREATED);
        }
    }

    //lista todas pessoas
    public ResponseEntity<List<Pessoa>> listar(){
        return new ResponseEntity<>(repo.findAll(),HttpStatus.OK);
    }

    //método para encontrar uma pessoa
    public ResponseEntity<?> FindOnePerson(Long id){
        if(id<0 || repo.countById(id) == 0){
            mensagem.setMensagem("id inválido!");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(repo.findById(id),HttpStatus.OK);
        }
    }

}
