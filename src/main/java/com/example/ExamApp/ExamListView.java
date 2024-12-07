package com.example.ExamApp;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("exam-list")
public class ExamListView extends VerticalLayout {

    private final ExamService examService;

    @Autowired
    public ExamListView(ExamService examService) {
        this.examService = examService;

        setSizeFull();
        getElement().getStyle().set("background-color", "#edf8ff");

        // Cím
        H1 title = new H1("Vizsgák");
        add(title);

        // Vizsga listája
        Button exam1Button = new Button("Programozási nyelvek II.", event -> registerForExam("Programozási nyelvek II."));
        Button exam2Button = new Button("Diszkrét matematika", event -> registerForExam("Diszkrét matematika"));
        Button exam3Button = new Button("Műszaki mérés", event -> registerForExam("Műszaki mérés"));
        Button exam4Button = new Button("Adatbázisrendszerek", event -> registerForExam("Adatbázisrendszerek"));
        Button exam5Button = new Button("Digitális alkalmazások", event -> registerForExam("Digitális alkalmazások"));
        Button exam6Button = new Button("Adatszerkezetek és algoritmusok", event -> registerForExam("Adatszerkezetek és algoritmusok"));

        // Hozzáadás a layout-hoz
        add(exam1Button, exam2Button, exam3Button, exam4Button, exam5Button,exam6Button);

        // "Vissza a főoldalra" gomb
        Button backToHomeButton = new Button("Vissza", event -> getUI().ifPresent(ui -> ui.navigate("home")));
        add(backToHomeButton);
        backToHomeButton.getElement().getStyle().set("background-color", "#82cd8e");
    }

    // Vizsgára való jelentkezés
    protected void registerForExam(String examName) {
        String resultMessage = examService.registerForExam(examName); // Vizsga hozzáadása a szolgáltatáshoz
        Notification.show(resultMessage, 3000, Notification.Position.MIDDLE); // Visszajelzés a felhasználónak
    }
}
