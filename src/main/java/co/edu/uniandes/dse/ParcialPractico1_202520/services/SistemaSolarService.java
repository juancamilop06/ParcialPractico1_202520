package co.edu.uniandes.dse.ParcialPractico1_202520.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.SistemaSolar;
import co.edu.uniandes.dse.ParcialPractico1_202520.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.ParcialPractico1_202520.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.ParcialPractico1_202520.repositories.SistemaSolarRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SistemaSolarService {
    @Autowired
    SistemaSolarRepository sistemaSolarRepository;

    @Transactional
    public SistemaSolar createSistemaSolar(SistemaSolar sistemaSolar) throws IllegalOperationException {
        if (sistemaSolar.getNombre() == null || sistemaSolar.getNombre().length()>=31) {
            throw new IllegalOperationException("nombre is not valid");
        }
        if (sistemaSolar.getRatioMinimo() < 0.2 || sistemaSolar.getRatioMinimo() > 0.6) {
            throw new IllegalOperationException("ratio is not valid");
        } 
        if (sistemaSolar.getNumStormtroopers() < 1000) {
            throw new IllegalOperationException("numStormtroopers is not valid");
        } 

        return sistemaSolarRepository.save(sistemaSolar);

    }
}
