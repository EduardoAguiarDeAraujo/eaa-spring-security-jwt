package eaa.eng.security.controller;

import eaa.eng.security.model.Usuario;
import eaa.eng.security.model.dto.LoginDto;
import eaa.eng.security.model.dto.TokenDto;
import eaa.eng.security.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping()
    public ResponseEntity login(@RequestBody @Valid LoginDto loginDto){
        var usuarioAutenticado = new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password());
        var authentication = authenticationManager.authenticate(usuarioAutenticado);
        var token = tokenService.getToken((Usuario) authentication.getPrincipal());
        TokenDto tokenDto = new TokenDto(token);
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/novo")
    public ResponseEntity novoLogin(@RequestBody @Valid LoginDto loginDto){
        return ResponseEntity.ok(loginDto);
    }

    @GetMapping()
    public String ok(){
       return "OKAY!!";
    }
}
