package com.example.ExamApp;

import com.helger.commons.callback.IThrowingRunnable;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ExamListViewTest {

    @Mock
    private ExamService examService; // Mockoljuk az ExamService-t

    @InjectMocks
    private ExamListView examListView; // Az osztály, amit tesztelünk

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Mockok inicializálása
    }

    @Test
    void testRegisterForExam() {
        // Mock: ExamService viselkedése
        ExamService examService = mock(ExamService.class);
        when(examService.registerForExam("Programozási nyelvek II.")).thenReturn("Sikeres jelentkezés");

        // ExamListView létrehozása
        ExamListView examListView = new ExamListView(examService);

        // Static mock: Notification.show metódus
        try (MockedStatic<Notification> mockedNotification = mockStatic(Notification.class)) {
            examListView.registerForExam("Programozási nyelvek II.");

            // Ellenőrzés: Notification.show() meghívásának igazolása
            mockedNotification.verify(() -> Notification.show("Sikeres jelentkezés", 3000, Notification.Position.MIDDLE), times(1));
        }

        };
    }

