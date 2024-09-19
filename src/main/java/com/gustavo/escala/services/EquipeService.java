package com.gustavo.escala.services;


import com.gustavo.escala.domain.Equipe;
import com.gustavo.escala.domain.Escala;
import com.gustavo.escala.domain.Pessoa;
import com.gustavo.escala.dtos.EquipeDTO;
import com.gustavo.escala.repositories.EquipeRepository;
import com.gustavo.escala.repositories.PessoaRepository;
import com.gustavo.escala.services.exceptions.DataIntegrityViolationException;
import com.gustavo.escala.services.exceptions.ObjectNotFoundExcpetion;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;
    public Equipe findById(Integer id){
        Optional<Equipe> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundExcpetion("Objeto não econtrado! Id: " + id));
    }

    public List<Equipe> findAll(){ return repository.findAll(); }

    public Equipe create(EquipeDTO objDto) {
        objDto.setId(null);
        validaNome(objDto);
        Equipe newObj = new Equipe(objDto);
        return repository.save(newObj);
    }

    public Equipe update(Integer id, EquipeDTO objDTO){
        objDTO.setId(id);
        Equipe oldObj = findById(id);
        validaNome(objDTO);
        oldObj.setNome(objDTO.getNome());

        if (objDTO.getMembrosIds() != null && !objDTO.getMembrosIds().isEmpty()) {
            oldObj.getMembros().clear();

            List<Pessoa> novosMembros = pessoaRepository.findAllById(objDTO.getMembrosIds());
            oldObj.getMembros().addAll(novosMembros);
        }

        return repository.save(oldObj);
    }

    public void delete(Integer id){
        Equipe obj = findById(id);
        if(obj.getMembros().size() > 0){
            throw new DataIntegrityViolationException("A equipe ainda possui membros alocados. Por favor, realoque-os antes de prosseguir com esta ação.");
        }

        if(obj.getEscalas().size() > 0){
            throw new DataIntegrityViolationException("A equipe ainda está vinculada a uma escala. Por favor, encerre suas escalas antes de prosseguir.");
        }
        repository.deleteById(id);
    }

    public void validaNome(EquipeDTO objDTO) {
        // Verificar se já existe uma equipe com o mesmo nome
        Optional<Equipe> obj = repository.findByNome(objDTO.getNome());

        // Se uma equipe com o nome já existir e não for a mesma equipe (baseado no ID), lançar exceção
        if (obj.isPresent() && !obj.get().getId().equals(objDTO.getId())) {
            throw new DataIntegrityViolationException("Nome já cadastrado no sistema!");
        }
    }

}
