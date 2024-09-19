package com.gustavo.escala.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gustavo.escala.dtos.EquipeDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Equipe implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "equipe", fetch = FetchType.EAGER)
    private Set<Pessoa> membros = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Escala> escalas = new HashSet<>();

    public Equipe() {
        super();
    }

    public Equipe(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Equipe(EquipeDTO dto) {
        this.id = dto.getId();
        this.nome = dto.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Escala> getEscalas() {
        return escalas;
    }

    public void setEscalas(Set<Escala> escalas) {
        this.escalas = escalas;
    }

    public Set<Pessoa> getMembros() {
        return membros;
    }

    public void addMembro(Pessoa pessoa){
        this.membros.add(pessoa);
        pessoa.setEquipe(this);
    }

    public void addMembro(List<Pessoa> pessoas) {
        for (Pessoa pessoa : pessoas) {
            addMembro(pessoa); // Usa o método já existente para adicionar um por um
        }
    }

    public void removeMembro(Pessoa pessoa){
        this.membros.remove(pessoa);
        pessoa.setEquipe(null);
    }
}
