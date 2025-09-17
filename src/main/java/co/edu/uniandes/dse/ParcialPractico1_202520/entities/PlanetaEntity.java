package co.edu.uniandes.dse.ParcialPractico1_202520.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class PlanetaEntity extends BaseEntity{
    private String nombre;
    private double poblacion;
    private float diametro;

    @PodamExclude
    @OneToOne
    private SistemaSolar sistema;

}
