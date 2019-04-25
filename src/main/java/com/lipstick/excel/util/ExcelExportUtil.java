package com.lipstick.excel.util;

import com.github.liaochong.myexcel.core.DefaultExcelBuilder;
import com.github.liaochong.myexcel.core.DefaultStreamExcelBuilder;
import com.github.liaochong.myexcel.utils.AttachmentExportUtil;
import lombok.NonNull;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * @ClassName ExcelExportUtil
 * @Description excel导出工具类
 * @Author zhanguowei
 * @Date 2019/4/23 17:44
 **/
public class ExcelExportUtil<T> {

    /**
     * @param clazz    对应的实体类
     * @param dataList 导出数据
     * @param fileName excel文件名
     * @param response
     * @param <T>
     * @throws IOException
     */
    public static <T> void defaultExport(@NonNull Class<T> clazz, @NonNull List<T> dataList, @NonNull String fileName, @NonNull HttpServletResponse response) throws IOException {
        if (null == clazz) {
            throw new NullPointerException("clazz is marked @NonNull but is null");
        }
        if (StringUtils.isEmpty(dataList)) {
            throw new NullPointerException("dataList is marked @NonNull but is null");
        }
        if (!StringUtils.hasLength(fileName)) {
            throw new NullPointerException("fileName is marked @NonNull but is null");
        }
        Workbook workbook = DefaultExcelBuilder.of(clazz).build(dataList);
        AttachmentExportUtil.export(workbook, fileName, response);
    }

    public static <T> void defaultStreamExport(@NonNull Class<T> clazz, @NonNull List<T> dataList, @NonNull String fileName, @NonNull HttpServletResponse response) throws IOException {
        if (null == clazz) {
            throw new NullPointerException("clazz is marked @NonNull but is null");
        }
        if (StringUtils.isEmpty(dataList)) {
            throw new NullPointerException("dataList is marked @NonNull but is null");
        }
        if (!StringUtils.hasLength(fileName)) {
            throw new NullPointerException("fileName is marked @NonNull but is null");
        }

        // 显式标明开始构建
        DefaultStreamExcelBuilder defaultExcelBuilder = DefaultStreamExcelBuilder.of(clazz)
                .threadPool(Executors.newFixedThreadPool(10))
                .start();
        // 多线程异步获取数据并追加至excel，join等待线程执行完成
        CompletableFuture future = CompletableFuture.runAsync(() -> {
            // 数据追加
            defaultExcelBuilder.append(dataList);
        });
        future.join();
        // 最终构建
        Workbook workbook = defaultExcelBuilder.build();
        AttachmentExportUtil.export(workbook, fileName, response);
    }
}
