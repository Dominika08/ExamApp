package com.example.ExamApp;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService {
    private final List<String> registeredExams = new ArrayList<>();

    public String registerForExam(String examName) {
        if (registeredExams.contains(examName)) {
            return "Ezt a vizsgát már felvette: " + examName;
        } else {
            registeredExams.add(examName);
            return "Sikeresen felvette a vizsgát: " + examName;
        }
    }

    public List<String> getRegisteredExams() {
        return registeredExams;
    }
}
