package eaa.eng.security.service;


import eaa.eng.security.model.Usuario;
import eaa.eng.security.model.dto.LoginDto;
import eaa.eng.security.repository.UsuarioRepository;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario salvar(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public Usuario novoLogin(LoginDto login){
        Usuario usuario = new Usuario();
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(UUID id){
        return usuarioRepository.findById(id).get();
    }

//    public Usuario atualizar(Usuario usuario){
//        Usuario userDetails = usuarioRepository.findByUsername(usuario.getUsername());
//        return usuarioRepository.save(usuario);
//    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    @Override
    public  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByUsername(username);
    }

}
