package com.lipstick.controller;

import com.lipstick.excel.entity.Student;
import com.lipstick.excel.util.ExcelExportUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ExcelExportController
 * @Description excel处理Controller
 * @Author zhanguowei
 * @Date 2019/4/24 17:44
 **/
@RestController
@RequestMapping("/excel")
@Slf4j
public class ExcelExportController {

    @GetMapping("/export")
    public void excelExport() {
        try {
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

            List<Student> dataList = new ArrayList<>();
            for (int i = 1; i <= 10000; i++) {
                dataList.add(new Student(String.valueOf(i), "李四", "30", "北京市", LocalDate.now()));
            }
//            ExcelExportUtil.defaultExport(Student.class, dataList, "student", response);
            ExcelExportUtil.defaultStreamExport(Student.class, dataList, "student", response);
        } catch (Exception e) {
            log.error("excel export error: {}", e.getMessage());
        }

    }
}
