package com.gustavo.escala.services;

import com.gustavo.escala.domain.Equipe;
import com.gustavo.escala.domain.Pessoa;
import com.gustavo.escala.dtos.PessoaDTO;
import com.gustavo.escala.repositories.EquipeRepository; // Adicionei o repositório da equipe
import com.gustavo.escala.repositories.PessoaRepository;
import com.gustavo.escala.services.exceptions.DataIntegrityViolationException;
import com.gustavo.escala.services.exceptions.ObjectNotFoundExcpetion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private EquipeRepository equipeRepository; // Adicionei o repositório da equipe

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Pessoa findById(Integer id) {
        Optional<Pessoa> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExcpetion("Objeto não encontrado! Id: " + id)); // Correção do nome da exceção
    }

    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    public Pessoa create(PessoaDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validaPorCpfEmail(objDTO);

        Equipe equipe = null;

        if (objDTO.getIdEquipe() != null) {
            equipe = equipeRepository.findById(objDTO.getIdEquipe())
                    .orElseThrow(() -> new ObjectNotFoundExcpetion("Equipe não encontrada!"));
        }

        Pessoa newObj = new Pessoa(objDTO, equipe);
        return repository.save(newObj);
    }


    public Pessoa update(Integer id, PessoaDTO objDTO) {
        objDTO.setId(id);
        Pessoa oldObj = findById(id);
        validaPorCpfEmail(objDTO);

        // Atualiza os campos necessários
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setNome(objDTO.getNome());
        oldObj.setEmail(objDTO.getEmail());

        // Atualiza a senha se ela foi alterada (opcional)
        if (objDTO.getSenha() != null && !objDTO.getSenha().isEmpty()) {
            oldObj.setSenha(encoder.encode(objDTO.getSenha()));
        }

        // Associa a equipe se o idEquipe estiver presente no DTO
        if (objDTO.getIdEquipe() != null) {
            Equipe equipe = equipeRepository.findById(objDTO.getIdEquipe())
                    .orElseThrow(() -> new ObjectNotFoundExcpetion("Equipe não encontrada!"));
            oldObj.setEquipe(equipe);
        }

        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Pessoa obj = findById(id);
        if (obj.getEquipe() != null && obj.getEquipe().getEscalas().size() > 0) {
            throw new DataIntegrityViolationException("Pessoa já participante de uma escala, retire da equipe para deletar.");
        }
        repository.deleteById(id);
    }

    private void validaPorCpfEmail(PessoaDTO objDTO) {
        Optional<Pessoa> obj = repository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && !obj.get().getId().equals(objDTO.getId())) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = repository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && !obj.get().getId().equals(objDTO.getId())) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }
}
