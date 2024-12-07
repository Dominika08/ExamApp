package com.example.ExamApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExamServiceTest {

    private ExamService examService;

    @BeforeEach
    void setUp() {
        // Új példány létrehozása minden teszt előtt
        examService = new ExamService();
    }

    @Test
    void testRegisterForExamAddsExam() {
        // Vizsga felvétele
        String result = examService.registerForExam("Programozási nyelvek II.");

        // Eredmény ellenőrzése
        assertEquals("Sikeresen felvette a vizsgát: Programozási nyelvek II.", result);

        // Ellenőrizzük, hogy a vizsga valóban fel lett véve
        List<String> registeredExams = examService.getRegisteredExams();
        assertTrue(registeredExams.contains("Programozási nyelvek II."));
    }

    @Test
    void testRegisterForExamPreventsDuplicates() {
        // Ugyanazon vizsga felvétele kétszer
        examService.registerForExam("Programozási nyelvek II.");
        String result = examService.registerForExam("Programozási nyelvek II.");

        // Ellenőrizzük a duplikáció üzenetet
        assertEquals("Ezt a vizsgát már felvette: Programozási nyelvek II.", result);

        // Ellenőrizzük, hogy csak egyszer szerepel a listában
        List<String> registeredExams = examService.getRegisteredExams();
        assertEquals(1, registeredExams.size());
        assertTrue(registeredExams.contains("Programozási nyelvek II."));
    }

    @Test
    void testDropExamRemovesExam() {
        // Vizsga felvétele és leadása
        examService.registerForExam("Adatbázisrendszerek");
        String result = examService.dropExam("Adatbázisrendszerek");

        // Ellenőrizzük a leadás üzenetet
        assertEquals("Sikeresen leadta a vizsgát: Adatbázisrendszerek", result);

        // Ellenőrizzük, hogy a vizsga nincs többé a listában
        List<String> registeredExams = examService.getRegisteredExams();
        assertFalse(registeredExams.contains("Adatbázisrendszerek"));
    }

    @Test
    void testDropExamHandlesNonexistentExam() {
        // Olyan vizsga leadása, amely nincs felvéve
        String result = examService.dropExam("Nemlétező vizsga");

        // Ellenőrizzük az üzenetet
        assertEquals("Ez a vizsga nincs felvéve: Nemlétező vizsga", result);

        // Ellenőrizzük, hogy a lista üres maradt
        List<String> registeredExams = examService.getRegisteredExams();
        assertTrue(registeredExams.isEmpty());
    }

    @Test
    void testGetRegisteredExamsReturnsCorrectList() {
        // Több vizsga felvétele
        examService.registerForExam("Programozási nyelvek II.");
        examService.registerForExam("Adatbázisrendszerek");

        // Ellenőrizzük a felvett vizsgák listáját
        List<String> registeredExams = examService.getRegisteredExams();
        assertEquals(2, registeredExams.size());
        assertTrue(registeredExams.contains("Programozási nyelvek II."));
        assertTrue(registeredExams.contains("Adatbázisrendszerek"));
    }
}
