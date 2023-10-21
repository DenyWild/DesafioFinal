package com.desafioFinal.DesafioFinal.repositories;

import com.desafioFinal.DesafioFinal.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
