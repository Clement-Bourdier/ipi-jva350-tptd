package com.ipi.jva350.service;

import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;

class SalarieAideADomicileServiceTest {

    @InjectMocks
    SalarieAideADomicileService salarieAideADomicileService;

    @Mock
    SalarieAideADomicileRepository salarieAideADomicileRepository;

    public SalarieAideADomicileServiceTest() {
        this.salarieAideADomicileService = new SalarieAideADomicileService();
    }

    @Test
    void ajouteCongeOk() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setNom("BERNARD");
        salarie.setMoisDebutContrat(LocalDate.parse("2020-02-02"));
        salarie.setMoisEnCours(LocalDate.parse("2026-01-01"));
        salarie.setJoursTravaillesAnneeNMoins1(50);
        salarie.setCongesPayesAcquisAnneeNMoins1(10);
        //When
        /*Mockito.when(salarieAideADomicileService.ajouteConge(salarie, LocalDate.parse("2026-01-05"), LocalDate.parse("2026-01-10")))
                .thenReturn(salarieAideADomicileRepository.save(salarie));*/
        //Then



        //Mock de la bdd


    }

}
