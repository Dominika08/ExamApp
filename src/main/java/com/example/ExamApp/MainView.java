package com.example.ExamApp;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        setSizeFull();
        getElement().getStyle().set("background-color", "#f0f0f0");
        // Cím
        H1 title = new H1("ExamApp Bejelentkezés");

        // Felhasználónév és jelszó mezők
        TextField usernameField = new TextField("Felhasználónév");
        PasswordField passwordField = new PasswordField("Jelszó");

        // Bejelentkezés gomb
        Button loginButton = new Button("Bejelentkezés", event -> handleLogin(usernameField.getValue(), passwordField.getValue()));

        // Hozzáadás a layout-hoz
        add(title, usernameField, passwordField, loginButton);
    }

    // Bejelentkezési logika
    private void handleLogin(String username, String password) {
        // Ide jön a bejelentkezési ellenőrzés
        if ("admin".equals(username) && "admin123".equals(password)) {
            System.out.println("Sikeres bejelentkezés");
            // Navigálás a főoldalra
            getUI().ifPresent(ui -> ui.navigate("home"));
        } else {
            System.out.println("Hibás felhasználónév vagy jelszó");
        }
    }
}
