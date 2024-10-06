package com.gustavo.escala.services;

import com.gustavo.escala.domain.Equipe;
import com.gustavo.escala.domain.Escala;
import com.gustavo.escala.domain.Pessoa;
import com.gustavo.escala.domain.enums.Perfil;
import com.gustavo.escala.repositories.EquipeRepository;
import com.gustavo.escala.repositories.EscalaRepository;
import com.gustavo.escala.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private EscalaRepository escalaRepository;
    @Autowired
    private EquipeRepository equipeRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanciaDB(){
        Pessoa pessoa = new Pessoa(null, "13608035052", "Gustavo Martins", "gustavo_mf1@hotmail.com", encoder.encode("123"));
        pessoa.addPerfis(Perfil.ADMIN);

        Pessoa pessoa2 = new Pessoa(null, "15684242057", "Bianca bento", "bianca_mf1@hotmail.com", encoder.encode("123"));
        pessoa2.addPerfis(Perfil.FUNCIONARIO);

        Pessoa pessoa3 = new Pessoa(null, "15999242057", "Zezin silva", "zezin_mf1@hotmail.com", encoder.encode("123"));
        pessoa3.addPerfis(Perfil.FUNCIONARIO);

        Equipe equipe = new Equipe(null, "Charle");
        equipe.addMembro(pessoa);
        equipe.addMembro(pessoa2);

        Equipe equipe2 = new Equipe(null, "Bravo");
        equipe2.addMembro(pessoa3);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate data = LocalDate.parse("20-09-2024", formatter);

        Escala escala = new Escala(null, data, equipe, "Transporte de preso", "Levar preso até apucarana");
        Escala escala2 = new Escala(null, data, equipe2, "SLA SLA SLA", "Eu não sei.");
        escala2.addPessoaExtra(pessoa3);
        equipeRepository.saveAll(Arrays.asList(equipe, equipe2));
        pessoaRepository.saveAll(Arrays.asList(pessoa, pessoa2, pessoa3));
        escalaRepository.saveAll(Arrays.asList(escala, escala2));
    }
}
