package com.gustavo.escala.resources;

import com.gustavo.escala.domain.Escala;
import com.gustavo.escala.dtos.EscalaDTO;
import com.gustavo.escala.services.EscalaService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/escalas")
public class EscalaResource {
    @Autowired
    private EscalaService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EscalaDTO> findById(@PathVariable Integer id){
        Escala obj = service.findById(id);
        return ResponseEntity.ok().body(new EscalaDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<EscalaDTO>> findAll(){
        List<Escala> list = service.findAll();
        List<EscalaDTO> listDTO = list.stream().map(obj -> new EscalaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EscalaDTO> create(@Valid @RequestBody EscalaDTO objDTO){
        Escala obj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<EscalaDTO> update(@PathVariable Integer id,@Valid @RequestBody EscalaDTO objDTO){
        Escala newObj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new EscalaDTO(newObj));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<EscalaDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}