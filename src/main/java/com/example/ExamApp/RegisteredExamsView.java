package com.example.ExamApp;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("registered-exams")
public class RegisteredExamsView extends VerticalLayout {

    private final ExamService examService;

    @Autowired
    public RegisteredExamsView(ExamService examService) {
        this.examService = examService;

        setSizeFull();
        getElement().getStyle().set("background-color", "#edf8ff");

        // Cím
        H1 title = new H1("Felvett vizsgák");
        add(title);

        // Felvett vizsgák listájának megjelenítése
        List<String> registeredExams = examService.getRegisteredExams();

        if (registeredExams.isEmpty()) {
            // Ha nincs felvett vizsga, jelenítsük meg az üzenetet
            add(new Paragraph("Még egyetlen vizsgát sem vett fel."));
        } else {
            // Ha van felvett vizsga, listázzuk őket
            for (String examName : registeredExams) {
                add(new Paragraph("• " + examName));
            }
        }

        // "Vissza a főoldalra" gomb
        Button backToHomeButton = new Button("Vissza", event -> getUI().ifPresent(ui -> ui.navigate("home")));
        add(backToHomeButton);
        backToHomeButton.getElement().getStyle().set("background-color", "#82cd8e");
    }
}
