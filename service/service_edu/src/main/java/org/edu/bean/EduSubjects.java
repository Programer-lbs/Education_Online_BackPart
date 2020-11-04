package org.edu.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EduSubjects {
    private EduSubject subject;
    private List<EduSubject> subjectList = new ArrayList<>();
}
