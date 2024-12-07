package com.example.ExamApp;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("registered-exams")
public class RegisteredExamsView extends VerticalLayout {

    private final ExamService examService;

    private final VerticalLayout examsContainer; // Külön konténer a vizsgalistának

    @Autowired
    public RegisteredExamsView(ExamService examService) {
        this.examService = examService;

        setSizeFull();
        getElement().getStyle().set("background-color", "#edf8ff");

        // Cím
        H1 title = new H1("Felvett vizsgák");
        add(title);

        // Vizsgalisták konténere
        examsContainer = new VerticalLayout();
        examsContainer.setWidthFull();
        add(examsContainer);

        // Felvett vizsgák megjelenítése
        refreshRegisteredExams();

        // "Vissza a főoldalra" gomb
        Button backToHomeButton = new Button("Vissza", event -> getUI().ifPresent(ui -> ui.navigate("home")));
        backToHomeButton.getElement().getStyle().set("background-color", "#82cd8e");
        add(backToHomeButton); // A "Vissza" gombot csak egyszer adjuk hozzá
    }

    private void refreshRegisteredExams() {
        examsContainer.removeAll(); // Csak a vizsgalistát töröljük és frissítjük

        List<String> registeredExams = examService.getRegisteredExams();

        if (registeredExams.isEmpty()) {
            // Ha nincs felvett vizsga, jelenítsük meg az üzenetet
            examsContainer.add(new Paragraph("Még egyetlen vizsgát sem vett fel."));
        } else {
            // Ha van felvett vizsga, jelenítsük meg őket
            for (String examName : registeredExams) {
                HorizontalLayout examRow = createExamRow(examName);
                examsContainer.add(examRow);
            }
        }
    }

    private HorizontalLayout createExamRow(String examName) {
        // Vizsga neve
        Paragraph examNameLabel = new Paragraph(examName);
        examNameLabel.getElement().getStyle().set("margin", "0");

        // Leadás gomb
        Button dropButton = new Button("Leadás");
        dropButton.getElement().getStyle().set("background-color", "#cab0ce");
        dropButton.addClickListener(event -> {
            dropExam(examName);
        });

        // Egy sorban megjelenítjük a vizsga nevét és a gombot
        HorizontalLayout examRow = new HorizontalLayout(examNameLabel, dropButton);
        examRow.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        examRow.setWidthFull();

        return examRow;
    }

    private void dropExam(String examName) {
        String resultMessage = examService.dropExam(examName); // Vizsga leadása
        Notification.show(resultMessage, 3000, Notification.Position.MIDDLE); // Visszajelzés
        refreshRegisteredExams(); // Vizsga lista frissítése
    }
}
