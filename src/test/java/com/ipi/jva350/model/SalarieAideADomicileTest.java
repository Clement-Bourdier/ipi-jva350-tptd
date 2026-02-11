package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.LinkedHashSet;

public class SalarieAideADomicileTest {

    @ParameterizedTest
    @CsvSource({
            "9", "10"
    })
    public void aLegalementDroitADesCongesPayesFalseOk(double nbrJourstravaille) {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(nbrJourstravaille);
        //When
        boolean droitCongePayes = salarie.aLegalementDroitADesCongesPayes();
        //Then
        Assertions.assertFalse(droitCongePayes);
    }

    @Test
    public void aLegalementDroitADesCongesPayesTrueOk() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(11);
        //When
        boolean droitCongePayes = salarie.aLegalementDroitADesCongesPayes();
        //Then
        Assertions.assertTrue(droitCongePayes);
    }

    @Test
    public void calculeJoursDeCongeDecomptesPourPlageDateDebutApresDateFinOk() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        //When
        LinkedHashSet<LocalDate> listConges = salarie.calculeJoursDeCongeDecomptesPourPlage(LocalDate.of(2025,1,1), LocalDate.of(2024,1,1));
        //Then
        Assertions.assertEquals(salarie.getCongesPayesPris(), listConges);
    }

    @Test
    public void calculeJoursDeCongeDecomptesPourPlageDateSemaineNormalOk() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        //When
        LinkedHashSet<LocalDate> listConges = salarie.calculeJoursDeCongeDecomptesPourPlage(LocalDate.of(2026,5,4), LocalDate.of(2026,5,8));
        //Then
        Assertions.assertEquals(salarie.getCongesPayesPris().size()+5, listConges.size());
    }

    @Test
    public void calculeJoursDeCongeDecomptesPourPlageDateSemaineJourFerieOk() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        //When
        LinkedHashSet<LocalDate> listConges = salarie.calculeJoursDeCongeDecomptesPourPlage(LocalDate.of(2026,4,27), LocalDate.of(2026,5,1));
        //Then
        Assertions.assertEquals(salarie.getCongesPayesPris().size()+5, listConges.size());
    }

    @Test
    public void estJourOuvrableOk() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        //When
        boolean isOuvrable = salarie.estJourOuvrable(LocalDate.of(2026,2,11));
        //Then
        Assertions.assertTrue(isOuvrable);
    }

    @Test
    public void estJourOuvrableDimancheOk() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        //When
        boolean isOuvrable = salarie.estJourOuvrable(LocalDate.of(2026,2,15));
        //Then
        Assertions.assertFalse(isOuvrable);
    }

    @Test
    public void estJourOuvrableFerieOk() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        //When
        boolean isOuvrable = salarie.estJourOuvrable(LocalDate.of(2026,5,1));
        //Then
        Assertions.assertFalse(isOuvrable);
    }

    @Test
    public void estHabituellementTravailleOk() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        //When
        boolean isTravailler = salarie.estHabituellementTravaille(LocalDate.of(2025,2,11));
        //Then
        Assertions.assertTrue(isTravailler);
    }

    @Test
    public void estHabituellementTravailleDimancheOk() {
        //Given
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        //When
        boolean isTravailler = salarie.estHabituellementTravaille(LocalDate.of(2025,2,15));
        //Then
        Assertions.assertFalse(isTravailler);
    }


}
