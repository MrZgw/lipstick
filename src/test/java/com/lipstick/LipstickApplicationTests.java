package com.lipstick;

import com.lipstick.excel.entity.Student;
import com.lipstick.excel.util.ExcelImportUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LipstickApplicationTests {

    @Test
    public void excelImport() throws Exception {
        List<Student> list= ExcelImportUtil.importExcel(Student.class,new FileInputStream(new File("D:\\student.xlsx")));
        list.get(0);
    }

    @Test
    public void excelExport(){

    }

}
