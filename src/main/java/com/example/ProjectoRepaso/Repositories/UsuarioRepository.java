package com.example.ProjectoRepaso.Repositories;

import com.example.ProjectoRepaso.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
