package com.gustavo.escala.resources;

import com.gustavo.escala.domain.Pessoa;
import com.gustavo.escala.dtos.PessoaDTO;
import com.gustavo.escala.services.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResources {
    // localhost:8080/pessoas/1

    @Autowired
    private PessoaService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable Integer id){
        Pessoa obj = service.findById(id);

        return ResponseEntity.ok().body(new PessoaDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> findAll(){
        List<Pessoa> list = service.findAll();
        List<PessoaDTO> listDTO = list.stream().map(obj -> new PessoaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> create(@Valid @RequestBody PessoaDTO objDTO){
        Pessoa newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PessoaDTO> update(@PathVariable Integer id, @Valid @RequestBody PessoaDTO objDTO){
        Pessoa obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new PessoaDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PessoaDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
