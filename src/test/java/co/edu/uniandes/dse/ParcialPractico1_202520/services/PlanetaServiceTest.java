package co.edu.uniandes.dse.ParcialPractico1_202520.services;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import co.edu.uniandes.dse.ParcialPractico1_202520.entities.PlanetaEntity;
import co.edu.uniandes.dse.ParcialPractico1_202520.exceptions.IllegalOperationException;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(PlanetaService.class)
public class PlanetaServiceTest {
    @Autowired
    private PlanetaService planetaService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<PlanetaEntity> planetaList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from PlanetaEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PlanetaEntity planeta = factory.manufacturePojo(PlanetaEntity.class);
            planeta.setNombre("I");
            planeta.setPoblacion(1);
            entityManager.persist(planeta);
            planetaList.add(planeta);
        }

    }
    @Test
    void testCreatePlaneta_ok() throws IllegalOperationException {
        PlanetaEntity nuevo = factory.manufacturePojo(PlanetaEntity.class);
        nuevo.setNombre("I");
        PlanetaEntity result = planetaService.createPlaneta(nuevo);
        assertNotNull(result);

        PlanetaEntity enBD = entityManager.find(PlanetaEntity.class, result.getId());
        assertNotNull(enBD);
        assertEquals(nuevo.getNombre(), enBD.getNombre());
    }
    @Test 
    void testCreatePlaneta_ntook() throws IllegalOperationException {
        PlanetaEntity nuevo = factory.manufacturePojo(PlanetaEntity.class);
        nuevo.setNombre("Tipo de Servicio Nuevo");
        assertThrows(RuntimeException.class, () -> planetaService.createPlaneta(nuevo));

       
    }
}
