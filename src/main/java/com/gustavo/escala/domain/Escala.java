package com.gustavo.escala.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Escala implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    private String titulo;
    private String descricao;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "escala_pessoa_extra",
            joinColumns = @JoinColumn(name = "escala_id"),
            inverseJoinColumns = @JoinColumn(name = "pessoa_id"))
    private Set<Pessoa> pessoasExtras = new HashSet<>();

    // Construtores, Getters e Setters
    public Escala() {
        super();
    }

    public Escala(Integer id, LocalDate data, Equipe equipe, String titulo, String descricao) {
        this.id = id;
        this.data = data;
        this.equipe = equipe;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public Set<Pessoa> getPessoasExtras() {
        return pessoasExtras;
    }

    public void addPessoaExtra(Pessoa pessoa) {
        this.pessoasExtras.add(pessoa);
    }

    public void removePessoaExtra(Pessoa pessoa) {
        this.pessoasExtras.remove(pessoa);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
