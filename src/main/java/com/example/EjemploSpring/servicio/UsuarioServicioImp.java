package com.example.EjemploSpring.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.EjemploSpring.dao.UsuarioCrud;
import com.example.EjemploSpring.modelo.Usuario;

@Service
public class UsuarioServicioImp implements IUsuarioServicio {

    @Autowired
    private UsuarioCrud crudUsuario;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        return (List<Usuario>) crudUsuario.findAll();
    }

    @Override
    @Transactional
    public void guardar(Usuario user) {
        crudUsuario.save(user);
    }

    @Override
    @Transactional
    public void eliminar(Usuario user) {
        crudUsuario.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscar(Usuario user) {
        return crudUsuario.findById(user.getCedula()).orElse(null);
    }

}