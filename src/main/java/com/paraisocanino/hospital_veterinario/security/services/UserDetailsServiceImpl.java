package com.paraisocanino.hospital_veterinario.security.services;

import com.paraisocanino.hospital_veterinario.models.Usuario;
import com.paraisocanino.hospital_veterinario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String idusuario) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByidusuario(idusuario)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + idusuario));

        return UserDetailsImpl.build(user);
    }

}
