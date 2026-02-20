package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

public class EntrepriseTest {

    @Test
    public void EstDansPlageTrueOk() {
        //Given
        LocalDate debut = LocalDate.parse("2020-04-20");
        LocalDate fin = LocalDate.parse("2020-04-22");
        LocalDate date = LocalDate.parse("2020-04-21");
        Entreprise entreprise = new Entreprise();
        //When
        boolean isInPlage = entreprise.estDansPlage(date, debut, fin);
        //Then
        Assertions.assertTrue(isInPlage);
    }

    @ParameterizedTest
    @CsvSource({
            "'2020-04-23','2020-04-20','2020-04-22'",
            "'2020-04-23','2020-04-22','2020-04-20'"
    })
    public void EstDansPlageFalseOk(String date, String debut, String fin) {
        //Given
        Entreprise entreprise = new Entreprise();
        //When
        boolean isInPlage = entreprise.estDansPlage(LocalDate.parse(date), LocalDate.parse(debut), LocalDate.parse(fin));
        //Then
        Assertions.assertFalse(isInPlage);
    }

    @ParameterizedTest
    @CsvSource({
            "'2026-05-01'",
            "'2026-12-25'"
    })
    public void EstJourFerieTrueOk(String date) {
        //Given
        Entreprise entreprise = new Entreprise();
        //When
        boolean isFerie = entreprise.estJourFerie(LocalDate.parse(date));
        //Then
        Assertions.assertTrue(isFerie);
    }

    @ParameterizedTest
    @CsvSource({
            "'2026-05-02'",
            "'2026-12-24'"
    })
    public void EstJourFerieFalseOk(String date) {
        //Given
        Entreprise entreprise = new Entreprise();
        //When
        boolean isFerie = entreprise.estJourFerie(LocalDate.parse(date));
        //Then
        Assertions.assertFalse(isFerie);
    }

    @ParameterizedTest
    @CsvSource({
            "'2026-01-01',0.7333333333333333",
            "'2026-02-01',0.8",
            "'2026-09-01',0.4666666666666667",
    })
    public void ProportionPondereeDuMoisOk(String mois, double expected) {
        //Given
        Entreprise entreprise = new Entreprise();
        //When
        double resultat = entreprise.proportionPondereeDuMois(LocalDate.parse(mois));
        //Then
        Assertions.assertEquals(expected, resultat);
    }

    @ParameterizedTest
    @CsvSource({
            "'2026-01-01','2025-06-01'",
            "'2025-01-01','2024-06-01'",
            "'2024-01-01','2023-06-01'"
    })
    public void getPremierJourAnneeDeCongesOk(String annee, String expected) {
        //Given
        Entreprise entreprise = new Entreprise();
        //When
        LocalDate result = entreprise.getPremierJourAnneeDeConges(LocalDate.parse(annee));
        //Then
        Assertions.assertEquals(expected, result.toString());
    }
}
