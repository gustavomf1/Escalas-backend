package com.gustavo.escala.services;

import com.gustavo.escala.domain.Equipe;
import com.gustavo.escala.domain.Escala;
import com.gustavo.escala.domain.Pessoa;
import com.gustavo.escala.dtos.EscalaDTO;
import com.gustavo.escala.repositories.EscalaRepository;
import com.gustavo.escala.repositories.PessoaRepository;
import com.gustavo.escala.services.exceptions.ObjectNotFoundExcpetion;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EscalaService {

    @Autowired
    private EscalaRepository repository;

    @Autowired
    private EquipeService equipeService;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Escala findById(Integer id) {
        Optional<Escala> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExcpetion("Objeto não encontrado! ID: " + id));
    }

    public List<Escala> findAll() {
        return repository.findAll();
    }

    public Escala create(@Valid EscalaDTO objDTO) {
        Escala escala = newEscala(objDTO);
        return repository.save(escala);
    }

    public Escala update(Integer id, EscalaDTO objDTO) {
        objDTO.setId(id);
        Escala oldObj = findById(id);
        Escala updatedObj = newEscala(objDTO);
        return repository.save(updatedObj);
    }

    private Escala newEscala(EscalaDTO obj) {
        Equipe equipe = equipeService.findById(obj.getEquipe());
        Escala escala = new Escala();
        if (obj.getId() != null) {
            escala.setId(obj.getId());
        }
        escala.setDescricao(obj.getDescricao());
        escala.setEquipe(equipe);
        escala.setTitulo(obj.getTitulo());
        escala.setData(obj.getData());

        // Adiciona cada pessoa extra usando o método addPessoaExtra
        obj.getPessoasExtrasIds().forEach(id -> {
            Pessoa pessoa = pessoaRepository.findById(id)
                    .orElseThrow(() -> new ObjectNotFoundExcpetion("Pessoa não encontrada! ID: " + id));
            escala.addPessoaExtra(pessoa);
        });

        return escala;
    }

    public void delete(Integer id) {
        Escala obj = findById(id);
        repository.deleteById(id);
    }
}
