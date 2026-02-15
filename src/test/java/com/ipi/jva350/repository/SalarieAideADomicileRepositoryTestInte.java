package com.ipi.jva350.repository;

import com.ipi.jva350.model.SalarieAideADomicile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SalarieAideADomicileRepositoryTestInte {

    @Autowired
    public SalarieAideADomicileRepository salarieAideADomicileRepository;

    @Test
    public void findByNomTestOk(){
        // Given
        SalarieAideADomicile salarieInjecter = new SalarieAideADomicile();
        salarieInjecter.setNom("BERNARD");
        salarieAideADomicileRepository.save(salarieInjecter);
        // When
        SalarieAideADomicile salarieTrouver = salarieAideADomicileRepository.findByNom("BERNARD");
        // Then
        Assertions.assertEquals(salarieInjecter, salarieTrouver);
    }

    @Test
    public void findByNomTestKo(){
        // Given
        SalarieAideADomicile salarieInjecter = new SalarieAideADomicile();
        salarieInjecter.setNom("BERNARD");
        salarieAideADomicileRepository.save(salarieInjecter);
        // When
        SalarieAideADomicile salarieTrouver = salarieAideADomicileRepository.findByNom("PAS BERNARD");
        // Then
        Assertions.assertNull(salarieTrouver);
    }
}
