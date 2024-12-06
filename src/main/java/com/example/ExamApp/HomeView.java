package com.example.ExamApp;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("home")
public class HomeView extends VerticalLayout {

    public HomeView() {
        setSizeFull();
        getElement().getStyle().set("background-color", "#edf8ff");
        // Cím
        H1 title = new H1("Főoldal");

        // Gombok a különböző lehetőségekhez
        Button registerForExamButton = new Button("Jelentkezés vizsgára", event -> getUI().ifPresent(ui -> ui.navigate("exam-list")));
        Button viewRegisteredExamsButton = new Button("Felvett vizsgák", event -> getUI().ifPresent(ui -> ui.navigate("registered-exams")));
        Button logoutButton = new Button("Kijelentkezés", event -> logout());
        logoutButton.getElement().getStyle().set("background-color", "#ffcccc");

        // Hozzáadás a layout-hoz
        add(title, registerForExamButton, viewRegisteredExamsButton, logoutButton);
    }

    // Kijelentkezés
    private void logout() {
        System.out.println("Kijelentkezés...");
        getUI().ifPresent(ui -> ui.navigate(""));
    }
}
