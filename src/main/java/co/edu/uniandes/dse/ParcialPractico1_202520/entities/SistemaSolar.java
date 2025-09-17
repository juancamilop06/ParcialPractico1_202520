package co.edu.uniandes.dse.ParcialPractico1_202520.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
import lombok.Data;

@Data
@Entity
public class SistemaSolar extends BaseEntity {

    private String nombre;
    private RegionType region;
    private float ratioMinimo;
    private int numStormtroopers;
    @PodamExclude
    @OneToMany(mappedBy = "sistema", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<PlanetaEntity> planetas = new  ArrayList<>();
    
    

}
