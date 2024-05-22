package eaa.eng.security.repository;

import eaa.eng.security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

//    Optional<Usuario> findByUsername(String username);

    UserDetails findByUsername(String username);
}
