package com.gustavo.escala.resources;

import com.gustavo.escala.domain.Equipe;
import com.gustavo.escala.dtos.EquipeDTO;
import com.gustavo.escala.services.EquipeService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/equipes")
public class EquipeResource {
    @Autowired
    private EquipeService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EquipeDTO> findById(@PathVariable Integer id){
        Equipe obj = service.findById(id);

        return ResponseEntity.ok().body(new EquipeDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<EquipeDTO>> findAll(){
        List<Equipe> list = service.findAll();
        List<EquipeDTO> listDTO = list.stream().map(obj -> new EquipeDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<EquipeDTO> create(@Valid @RequestBody EquipeDTO objDto){
        Equipe newObj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EquipeDTO> update(@PathVariable Integer id, @Valid @RequestBody EquipeDTO objDTO){
        Equipe obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new EquipeDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<EquipeDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
