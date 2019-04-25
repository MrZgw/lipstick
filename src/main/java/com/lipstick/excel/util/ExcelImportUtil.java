package com.lipstick.excel.util;

import com.github.liaochong.myexcel.core.DefaultExcelReader;
import com.lipstick.excel.entity.Student;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ExcelImportUtil
 * @Description excel导入工具类
 * @Author zhanguowei
 * @Date 2019/4/23 17:09
 **/
@Slf4j
public class ExcelImportUtil<T> {

    public static <T> List<T> importExcel(@NonNull Class<T> clazz, @NonNull FileInputStream fileInputStream) throws Exception {
        if (clazz == null) {
            throw new NullPointerException("clazz is marked @NonNull but is null");
        }
        if (fileInputStream == null) {
            throw new NullPointerException("fileInputStream is marked @NonNull but is null");
        }
        List<T> list = new ArrayList<>();
        DefaultExcelReader.of(clazz).sheet(0)
                .rowFilter(row -> row.getRowNum() > 0)
                .readThen(fileInputStream, artCrowd -> {
                    log.info("student info: {}", artCrowd.toString());
                    list.add((T) artCrowd);
                });
        return list;
    }
}
