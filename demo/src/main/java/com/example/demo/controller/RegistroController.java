package com.example.demo.controller;

import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.model.Cliente;
import com.example.demo.model.Usuario;
import com.example.demo.model.Rol;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class RegistroController {

    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistroController(UsuarioRepository usuarioRepository, 
                              ClienteRepository clienteRepository,
                              PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("cliente", new Cliente()); 
        model.addAttribute("usuario", new Usuario());
        return "registro"; 
    }

    @PostMapping("/registro")
    public String registrarCliente(@ModelAttribute("cliente") Cliente cliente,
                                   @ModelAttribute("usuario") Usuario usuario,
                                   RedirectAttributes redirectAttributes) {

        String contraseñaEncriptada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(contraseñaEncriptada);
        usuario.setRol(Rol.CLIENTE); 

        usuarioRepository.save(usuario);

        cliente.setUsuario(usuario);
        
        clienteRepository.save(cliente);

        redirectAttributes.addFlashAttribute("mensaje", "¡Registro exitoso! Ya puedes iniciar sesión.");
        return "redirect:/login"; 
    }
}