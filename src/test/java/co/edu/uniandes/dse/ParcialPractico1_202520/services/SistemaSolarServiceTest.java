package co.edu.uniandes.dse.ParcialPractico1_202520.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.SistemaSolar;
import co.edu.uniandes.dse.ParcialPractico1_202520.exceptions.IllegalOperationException;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(SistemaSolarService.class)
public class SistemaSolarServiceTest {

    @Autowired
    private SistemaSolarService sistemaSolarService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    @Test
    void testCreateSistemaSolar_ok() throws IllegalOperationException {
        SistemaSolar sistema = factory.manufacturePojo(SistemaSolar.class);
        sistema.setNombre("ViaLactea");
        sistema.setRatioMinimo((float) 0.3);
        sistema.setNumStormtroopers(2000);

        SistemaSolar result = sistemaSolarService.createSistemaSolar(sistema);

        assertNotNull(result);
        SistemaSolar enBD = entityManager.find(SistemaSolar.class, result.getId());
        assertNotNull(enBD);
        assertEquals(sistema.getNombre(), enBD.getNombre());
    }

    @Test
    void testCreateSistemaSolar_nombreInvalido_null() {
        SistemaSolar sistema = factory.manufacturePojo(SistemaSolar.class);
        sistema.setNombre(null);
        sistema.setRatioMinimo((float) 0.3);
        sistema.setNumStormtroopers(2000);

        assertThrows(IllegalOperationException.class, () -> {
            sistemaSolarService.createSistemaSolar(sistema);
        });
    }

    @Test
    void testCreateSistemaSolar_nombreInvalido_largo() {
        SistemaSolar sistema = factory.manufacturePojo(SistemaSolar.class);
        sistema.setNombre("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJK"); // > 30
        sistema.setRatioMinimo((float) 0.3);
        sistema.setNumStormtroopers(2000);

        assertThrows(IllegalOperationException.class, () -> {
            sistemaSolarService.createSistemaSolar(sistema);
        });
    }

    
}
