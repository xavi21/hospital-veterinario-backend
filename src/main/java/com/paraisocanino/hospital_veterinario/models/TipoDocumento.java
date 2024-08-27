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
@Table(name = "tipo_documento")
public class TipoDocumento extends Bitacora {
    
    @Id
    @GenericGenerator(name = "tipo_documento", strategy = "enhanced-table", parameters = {
            @org.hibernate.annotations.Parameter(name = "tipo_documento", value = "sequence_tipo_documento") })
    @GeneratedValue(generator = "secuence_tipo_documento", strategy = GenerationType.TABLE)
    @SequenceGenerator(name = "secuence_tipo_documento", allocationSize=1)
        private Integer idTipoDocumento;

        @NotBlank
        @Size(max = 100)
        private String nombre;
    
        public Integer getIdTipoDocumento() {
            return idTipoDocumento;
        }
    
        public void setIdTipoDocumento(Integer idTipoDocumento) {
            this.idTipoDocumento = idTipoDocumento;
        }
    
        public String getNombre() {
            return nombre;
        }
    
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
}
