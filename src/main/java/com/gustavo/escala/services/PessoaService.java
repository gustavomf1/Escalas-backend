package com.gustavo.escala.services;

import com.gustavo.escala.domain.Pessoa;
import com.gustavo.escala.dtos.PessoaDTO;
import com.gustavo.escala.repositories.PessoaRepository;
import com.gustavo.escala.services.exceptions.DataIntegrityViolationException;
import com.gustavo.escala.services.exceptions.ObjectNotFoundExcpetion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public Pessoa findById(Integer id){
        Optional<Pessoa> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundExcpetion("Objeto não econtrado! Id: " + id));
    }

    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    public Pessoa create(PessoaDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEmail(objDTO);
        Pessoa newObj = new Pessoa(objDTO);
        return repository.save(newObj);
    }

    public Pessoa update(Integer id, PessoaDTO objDTO) {
        objDTO.setId(id);
        Pessoa oldObj = findById(id);
        validaPorCpfEmail(objDTO);
        oldObj = new Pessoa(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Pessoa obj = findById(id);
        if(obj.getEquipe().getEscalas().size() > 0){
            throw new DataIntegrityViolationException("Pessoa já participante de uma escala, retire da equipe para deletar");
        }
        repository.deleteById(id);
    }

    private void validaPorCpfEmail(PessoaDTO objDTO) {
        Optional<Pessoa> obj = repository.findByCpf(objDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = repository.findByEmail(objDTO.getEmail());

        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }



}
