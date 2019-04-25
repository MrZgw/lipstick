package com.lipstick.excel.entity;

import com.github.liaochong.myexcel.core.WorkbookType;
import com.github.liaochong.myexcel.core.annotation.ExcelColumn;
import com.github.liaochong.myexcel.core.annotation.ExcelTable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

/**
 * @ClassName Student
 * @Description 学生信息
 * @Author zhanguowei
 * @Date 2019/4/23 17:13
 **/
@Data
@AllArgsConstructor
@ExcelTable(sheetName = "学生信息", workbookType = WorkbookType.SXLSX, rowAccessWindowSize = 100, useFieldNameAsTitle = true)
public class Student {
    @ExcelColumn(index = 0, order = 1, title = "学生编号")
    private String studentNo;

    @ExcelColumn(index = 1, order = 1, title = "姓名")
    private String studentName;

    @ExcelColumn(index = 2, order = 1, title = "年龄")
    private String studentAge;

    @ExcelColumn(index = 3, order = 1, title = "住址")
    private String address;

    @ExcelColumn(index = 4, order = 1, title = "时间", dateFormatPattern = "yyyy-MM-dd")
    private LocalDate registerDate;
}
