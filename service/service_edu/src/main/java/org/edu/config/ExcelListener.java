package org.edu.config;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.edu.bean.EduSubject;
import org.edu.service.SubjectService;

public class ExcelListener extends AnalysisEventListener<EduSubject> {
    private SubjectService subjectService;
    //读取每行数据
    @Override
    public void invoke(EduSubject subject, AnalysisContext analysisContext) {
        EduSubject eduSubject = subjectService.existSubject(subject.getTitle(), subject.getParentId());
        if(eduSubject==null){
            subjectService.saveSubject(subject);
        }
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
    public ExcelListener(SubjectService subjectService){
        this.subjectService = subjectService;
    }
}
