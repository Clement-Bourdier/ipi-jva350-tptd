package com.ipi.jva350.service;


import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SalarieAideADomicileServiceTestInte {

    @Autowired
    SalarieAideADomicileService salarieAideADomicileService;

    @Autowired
    SalarieAideADomicileRepository salarieTrouverRepository;


    @BeforeEach
    public void setup() {
        salarieTrouverRepository.deleteAll();
    }

    @Test
    public void ajouteCongeOk() throws SalarieException {
        //Given
        SalarieAideADomicile salarieInjecter = new SalarieAideADomicile();
        salarieInjecter.setNom("BERNARD");
        salarieInjecter.setMoisDebutContrat(LocalDate.parse("2020-02-02"));
        salarieInjecter.setMoisEnCours(LocalDate.parse("2026-01-01"));
        salarieInjecter.setJoursTravaillesAnneeNMoins1(50);
        salarieInjecter.setCongesPayesAcquisAnneeNMoins1(10);

        salarieAideADomicileService.creerSalarieAideADomicile(salarieInjecter);
        //When
        salarieAideADomicileService.ajouteConge(salarieInjecter, LocalDate.parse("2026-01-05"), LocalDate.parse("2026-01-10"));
        //Then
        SalarieAideADomicile salarieTrouver = salarieTrouverRepository.findByNom("BERNARD");
        Assertions.assertEquals(salarieTrouver.getCongesPayesPris().size(), 6);
    }

    @Test
    public void ajouteCongePasDroitKo() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        //When
        SalarieException erreur = Assertions.assertThrows(SalarieException.class, () ->
                salarieAideADomicileService.ajouteConge(salarie, LocalDate.parse("2026-02-12"), LocalDate.parse("2026-02-13")));
        //Then
        Assertions.assertEquals(erreur.getMessage(), "N'a pas légalement droit à des congés payés !");
    }

    @Test
    public void ajouteCongePasDecongésKo() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(50);
        //When
        SalarieException erreur = Assertions.assertThrows(SalarieException.class, () ->
                salarieAideADomicileService.ajouteConge(salarie, LocalDate.parse("2026-02-12"), LocalDate.parse("2026-02-11")));
        //Then
        Assertions.assertEquals(erreur.getMessage(), "Pas besoin de congés !");
    }

    @Test
    public void ajouteCongePasCongésMoisIncorrectKo() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(50);
        salarie.setMoisEnCours(LocalDate.parse("2026-03-12"));
        //When
        SalarieException erreur = Assertions.assertThrows(SalarieException.class, () ->
                salarieAideADomicileService.ajouteConge(salarie, LocalDate.parse("2026-02-12"), LocalDate.parse("2026-02-13")));
        //Then
        Assertions.assertEquals(erreur.getMessage(), "Pas possible de prendre de congé avant le mois en cours !");
    }

    @Test
    public void ajouteCongePasCongésAnneesIncorrectKo() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(50);
        salarie.setMoisEnCours(LocalDate.parse("2025-03-12"));
        //When
        SalarieException erreur = Assertions.assertThrows(SalarieException.class, () ->
                salarieAideADomicileService.ajouteConge(salarie, LocalDate.parse("2026-02-12"), LocalDate.parse("2026-02-13")));
        //Then
        Assertions.assertEquals(erreur.getMessage(), "Pas possible de prendre de congé dans l'année de congés suivante (hors le premier jour)");
    }

    @Test
    public void ajouteCongePasCongésCongesAquisInsuffisantKo() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(50);
        salarie.setMoisEnCours(LocalDate.parse("2026-01-12"));
        salarie.setCongesPayesAcquisAnneeNMoins1(2);
        //When
        SalarieException erreur = Assertions.assertThrows(SalarieException.class, () ->
                salarieAideADomicileService.ajouteConge(salarie, LocalDate.parse("2026-02-12"), LocalDate.parse("2026-02-13")));
        //Then
        Assertions.assertEquals(erreur.getMessage(),
                "Conges Payes Pris Decomptes (3) dépassent les congés acquis en année N-1 : 2.0");
    }

    /*
    @Test
    public void ajouteCongePasCongésLimiteEntrepriseDepasser() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(50);
        salarie.setMoisEnCours(LocalDate.parse("2026-01-12"));
        salarie.setCongesPayesAcquisAnneeNMoins1(50);
        salarie.setCongesPayesPrisAnneeNMoins1(1);
        salarieTrouverRepository.save(salarie);
        //When
        SalarieException erreur = Assertions.assertThrows(SalarieException.class, () ->
                salarieAideADomicileService.ajouteConge(salarie, LocalDate.parse("2026-02-12"), LocalDate.parse("2026-03-13")));
        //Then
        Assertions.assertEquals(erreur.getMessage(),
                "Conges Payes Pris Decomptes (3) dépassent la limite des règles de l'entreprise : 2.0");
    }*/

}
