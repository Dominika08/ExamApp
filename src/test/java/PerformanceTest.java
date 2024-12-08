package com.example.ExamApp;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PerformanceTest {

    // Alap URL beállítása
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";  // A helyi szerver URL-je
    }

    // Performance teszt: A válaszidő mérése
    @Test
    public void testApiPerformance() {
        long startTime = System.currentTimeMillis(); // Kezdő időpont

        // GET kérés küldése
        given()
                .when()
                .get("/api/exams")
                .then()
                .statusCode(200)  // Ellenőrzi, hogy a válasz 200 OK
                .time(lessThan(2000L));  // A válaszidő nem haladhatja meg a 2000 ms-t

        long endTime = System.currentTimeMillis(); // Befejező időpont
        long duration = endTime - startTime; // Különbség: válaszidő

        System.out.println("API válaszidő: " + duration + " ms");
    }

    // Performance teszt: Több párhuzamos kérés küldése
    @Test
    public void testApiPerformanceWithMultipleRequests() {
        int requestCount = 100;  // Tesztelendő kérések száma

        long startTime = System.currentTimeMillis(); // Kezdő időpont

        for (int i = 0; i < requestCount; i++) {
            given()
                    .when()
                    .get("/api/exams")
                    .then()
                    .statusCode(200);
        }

        long endTime = System.currentTimeMillis(); // Befejező időpont
        long duration = endTime - startTime; // Különbség: válaszidő

        System.out.println("Összes kérelem válaszideje: " + duration + " ms");
        System.out.println("Átlagos válaszidő: " + (duration / requestCount) + " ms");
    }
}
