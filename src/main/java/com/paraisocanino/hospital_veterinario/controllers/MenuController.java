package com.paraisocanino.hospital_veterinario.controllers;

import com.paraisocanino.hospital_veterinario.models.Menu;
import com.paraisocanino.hospital_veterinario.models.Sucursal;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponse;
import com.paraisocanino.hospital_veterinario.payload.response.GeneralResponseList;
import com.paraisocanino.hospital_veterinario.repository.MenuRepository;
import com.paraisocanino.hospital_veterinario.repository.SucursalRepository;
import com.paraisocanino.hospital_veterinario.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/all")
    public GeneralResponseList getMenus(@RequestHeader("Authorization") String tokenAdmin) {
        List<Menu> menus = menuRepository.findAll();

        List<Menu> menuResponse = new ArrayList<>();

        for (Menu menu : menus) {
            Menu menur = new Menu();
            menur.setIdmenu(menu.getIdmenu());
            menur.setName(menu.getName());
            menur.setOrdenmenu(menu.getOrdenmenu());
            menur.setUsuariocreacion(menu.getUsuariocreacion());
            menur.setFechacreacion(menu.getFechacreacion());
            menur.setUsuariomodificacion(menu.getUsuariomodificacion());
            menur.setFechamodificacion(menu.getFechamodificacion());

            menuResponse.add(menur);
        }

        final GeneralResponseList response = new GeneralResponseList();
        response.setCode(200);
        response.setMessage("Exitoso");
        response.setData(menuResponse);

        return response;
    }

    @PostMapping("/save")
    public GeneralResponse postMenu(@RequestBody Menu menu, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        menu.setFechamodificacion(dateNow());
        menu.setUsuariomodificacion(user);
        menu.setUsuariocreacion(user);
        menu.setFechacreacion(dateNow());

        menuRepository.save(menu);
        final GeneralResponse response = new GeneralResponse();
        response.setCode(200);
        response.setMessage("Datos Guardados");
        response.setData(menu);

        return response;
    }

    @PutMapping("/update")
    public GeneralResponse putMenu(@RequestBody Menu menu, @RequestHeader("Authorization") String tokenAdmin) {

        tokenAdmin = tokenAdmin.replace("Bearer ", "");
        final String user = jwtUtils.getUserNameFromJwtToken(tokenAdmin);
        final GeneralResponse response = new GeneralResponse();

        if (menuRepository.findById(menu.getIdmenu()).isPresent()) {

            Optional<Menu> currenMenu = menuRepository.findById(menu.getIdmenu());

            menu.setFechamodificacion(dateNow());
            menu.setUsuariomodificacion(user);
            menu.setUsuariocreacion(currenMenu.get().getUsuariocreacion());
            menu.setFechacreacion(currenMenu.get().getFechacreacion());
            menuRepository.save(menu);

            response.setCode(200);
            response.setMessage("Menu Actualizada");
        } else {
            response.setCode(401);
            response.setMessage("Error al actualizar");
        }
        response.setData(menu);
        return response;

    }

    @DeleteMapping(path = "/eliminar")
    public GeneralResponse EliminaProducto(@RequestParam Integer id) {
        final GeneralResponse response = new GeneralResponse();
        Optional<Menu> currenMenu = menuRepository.findById(id);
        if (currenMenu.isPresent()) {
            menuRepository.delete(currenMenu.get());
            response.setCode(200);
            response.setMessage("Se eliminó el menu : " + id);

        } else {
            response.setCode(200);
            response.setMessage("No existe el menu :" + id);
        }
        return response;
    }

    private LocalDate dateNow() {
        return LocalDate.now();
    }
}
