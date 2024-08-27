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
@Table(name = "laboratorio")
public class Laboratorio extends Bitacora {

        @Id
        @GenericGenerator(name = "laboratorio", strategy = "enhanced-table", parameters = {
        @org.hibernate.annotations.Parameter(name = "laboratorio", value = "sequence_laboratorio") })
        @GeneratedValue(generator = "secuence_laboratorio", strategy = GenerationType.TABLE)
        @SequenceGenerator(name = "secuence_laboratorio", allocationSize=1)
        private Integer idLaboratorio;

        @NotBlank
        @Size(max = 100)
        private String nombre;

        @Size(max = 300)
        private String descripcion;
    
        public Integer getIdLaboratorio() {
            return idLaboratorio;
        }
    
        public void setIdLaboratorio(Integer idlaboratorio) {
            this.idLaboratorio = idlaboratorio;
        }
    
        public String getNombre() {
            return nombre;
        }
    
        public void setNombre(String name) {
            this.nombre = name;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }
}
