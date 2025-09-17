package co.edu.uniandes.dse.ParcialPractico1_202520.entities;

import jakarta.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

public class PlanetaEntity {
    private String nombre;
    private double poblacion;
    private float diametro;

    @PodamExclude
    @OneToOne
    private SistemaSolar sistema;

}
