package com.gustavo.escala.repositories;

import com.gustavo.escala.domain.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipeRepository extends JpaRepository<Equipe, Integer> {
    Optional<Equipe> findByNome(String nome);
}
