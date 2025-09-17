package co.edu.uniandes.dse.ParcialPractico1_202520.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.PlanetaEntity;
import co.edu.uniandes.dse.ParcialPractico1_202520.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.ParcialPractico1_202520.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.ParcialPractico1_202520.repositories.PlanetaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlanetaService {
    @Autowired
    PlanetaRepository planetaRepository;

    @Transactional
    public PlanetaEntity createPlaneta(PlanetaEntity planetaEntity) throws IllegalOperationException {
        boolean okey = false;
        if (planetaEntity.getNombre() == null ) {
            throw new IllegalOperationException("nombre is not valid");
        }
        if (planetaEntity.getPoblacion() <= 0) {
            throw new IllegalOperationException("poblacion is not valid");
        } 
        if (planetaEntity.getNombre().endsWith("I") || planetaEntity.getNombre().endsWith("II") || planetaEntity.getNombre().endsWith("III")){
            okey = true;
        }else{
            throw new IllegalOperationException("nombre is not valid");
        }
         
        return planetaRepository.save(planetaEntity);
        

    }
}
