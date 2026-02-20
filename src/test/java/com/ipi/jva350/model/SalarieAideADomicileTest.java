package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.LinkedHashSet;

class SalarieAideADomicileTest {

    @ParameterizedTest
    @CsvSource({
            "10", "11"
    })
    void aLegalementDroitADesCongesPayesTrueOk(double nbrJourstravaille) {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(nbrJourstravaille);
        //When
        boolean droitCongePayes = salarie.aLegalementDroitADesCongesPayes();
        //Then
        Assertions.assertTrue(droitCongePayes);
    }

    @Test
    void aLegalementDroitADesCongesPayesFalseOk() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(9);
        //When
        boolean droitCongePayes = salarie.aLegalementDroitADesCongesPayes();
        //Then
        Assertions.assertFalse(droitCongePayes);
    }

    @Test
    void calculeJoursDeCongeDecomptesPourPlageDateDebutApresDateFinOk() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        //When
        LinkedHashSet<LocalDate> listConges = salarie.calculeJoursDeCongeDecomptesPourPlage(LocalDate.of(2025,1,1), LocalDate.of(2024,1,1));
        //Then
        Assertions.assertEquals(salarie.getCongesPayesPris(), listConges);
    }

    @ParameterizedTest
    @CsvSource({
            "'2026-05-04', '2026-05-08', 5", //Periode de congés normale 2026-05-04 (LocalDate.parse)
            "'2026-04-30', '2026-05-05', 4" //Période de congés avec jour ferié (1 Mai)
    })
    void calculeJoursDeCongeDecomptesPourPlageDateSemaineNormalOk(
            String dtDebut, String dtFin,
            int nbrJour) {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        //When
        LinkedHashSet<LocalDate> listConges = salarie.calculeJoursDeCongeDecomptesPourPlage(
                LocalDate.parse(dtDebut), LocalDate.parse(dtFin));
        //Then
        Assertions.assertEquals(salarie.getCongesPayesPris().size()+nbrJour, listConges.size());
    }

    @Test
    void calculeJoursDeCongeDecomptesPourPlageDateSemaineJourFerieOk() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        //When
        LinkedHashSet<LocalDate> listConges = salarie.calculeJoursDeCongeDecomptesPourPlage(LocalDate.of(2026,4,27), LocalDate.of(2026,5,1));
        //Then
        Assertions.assertEquals(salarie.getCongesPayesPris().size()+5, listConges.size());
    }

    @Test
    void estJourOuvrableOk() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        //When
        boolean isOuvrable = salarie.estJourOuvrable(LocalDate.of(2026,2,11));
        //Then
        Assertions.assertTrue(isOuvrable);
    }

    @Test
    void estJourOuvrableDimancheOk() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        //When
        boolean isOuvrable = salarie.estJourOuvrable(LocalDate.of(2026,2,15));
        //Then
        Assertions.assertFalse(isOuvrable);
    }

    @Test
    void estJourOuvrableFerieOk() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        //When
        boolean isOuvrable = salarie.estJourOuvrable(LocalDate.of(2026,5,1));
        //Then
        Assertions.assertFalse(isOuvrable);
    }

    @Test
    void estHabituellementTravailleOk() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        //When
        boolean isTravailler = salarie.estHabituellementTravaille(LocalDate.of(2025,2,11));
        //Then
        Assertions.assertTrue(isTravailler);
    }

    @Test
    void estHabituellementTravailleDimancheOk() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        //When
        boolean isTravailler = salarie.estHabituellementTravaille(LocalDate.of(2025,2,15));
        //Then
        Assertions.assertFalse(isTravailler);
    }

}
