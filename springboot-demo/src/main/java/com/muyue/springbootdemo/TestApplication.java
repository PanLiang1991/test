package com.muyue.springbootdemo;

import com.google.common.collect.Lists;
import lombok.NonNull;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @Author <a href="panliang@cai-inc.com">沐月</a>
 * @Date 2018/10/10 上午10:52 Copyright (c) 2016 政采云有限公司
 */
@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) throws Exception {
        Long temp = 1542556800000L;
        System.out.println(String.valueOf(temp));

        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,1);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date) + " 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date =  sdf.parse(dateString);
        System.out.println(date);

        List<Map<String, String>> list = Lists.newArrayList();
        String filePath = "src/main/resources/税务系统政采节活动.xlsx";
        String itemIds = "1,2,23243,4,5,6,7,8,9,100390";
        String[] ids = itemIds.split(",");
        List<Long> idLists = arraysToList(ids);

        Workbook wb = readExcel(filePath);
        getAcitvityPrices(wb);
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(0);
        System.out.println(row.getCell(0));

//        System.out.println(file.exists() + ":" + file.getCanonicalPath());

    }

    public static Workbook readExcel(String filePath) throws IOException {
        if (null == filePath) {
            return null;
        }
        String suffix = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = new FileInputStream(filePath);

        if (".xls".equals(suffix)) {
            return new HSSFWorkbook();
        } else if (".xlsx".equals(suffix)) {
            return new XSSFWorkbook(is);
        } else {
            return null;
        }
    }

    public static Map<Long, Integer> getAcitvityPrices(Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);
        Map<Long, Integer> activityMap = new HashMap<>();
        Iterator<Row> iterator = sheet.rowIterator();
        while (iterator.hasNext()) {
            Row row = iterator.next();
            if (Objects.equals(0, row.getRowNum())) {
                continue;
            }

            String itemId = NumberToTextConverter.toText(row.getCell(0).getNumericCellValue());
            String acitvityPrice = NumberToTextConverter.toText(row.getCell(1).getNumericCellValue());
//            String itemId = row.getCell(0).getStringCellValue();
//            String acitvityPrice = row.getCell(1).getStringCellValue();
            activityMap.put(Long.valueOf(itemId), Integer.valueOf(acitvityPrice));
        }
        return activityMap;
    }

    public static String getValueOfCell(@NonNull Cell cell) {
        String value;
        if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
            value = NumberToTextConverter.toText(cell.getNumericCellValue());
        } else {
            value = cell.toString();
        }
        return value.trim();
    }

    private static List<Long> arraysToList(String[] arr) {
        List<String> stringList = Arrays.asList(arr);
        List<Long> newlist = Lists.newArrayListWithCapacity(stringList.size());
        for (String str : stringList) {
            newlist.add(Long.valueOf(str));
        }
        return newlist;
    }


}
