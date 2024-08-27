package com.paraisocanino.hospital_veterinario.models;

import org.hibernate.annotations.GenericGenerator;

import com.paraisocanino.hospital_veterinario.payload.Bitacora;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "color")
public class Color extends Bitacora {
    
        @Id
        @GenericGenerator(name = "color", strategy = "enhanced-table", parameters = {
                @org.hibernate.annotations.Parameter(name = "color", value = "sequence_color") })
        @GeneratedValue(generator = "secuence_color", strategy = GenerationType.TABLE)
        @SequenceGenerator(name = "secuence_color", allocationSize=1)
        private Integer idColor;

        @NotBlank
        @Size(max = 100)
        private String nombre;
    
        public Integer getIdColor() {
            return idColor;
        }
    
        public void setIColor(Integer idColor) {
            this.idColor = idColor;
        }
    
        public String getNombre() {
            return nombre;
        }
    
        public void setNombre(String name) {
            this.nombre = name;
        }
}
