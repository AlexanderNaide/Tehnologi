package com.satal.tehnologi.generator;

import com.satal.tehnologi.module.Student;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.record.MergeCellsRecord;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

public class ExelStudentGenerator {

    private List<Student> studentList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExelStudentGenerator(List<Student> studentList) {
        this.studentList = studentList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeader(){
        sheet = workbook.createSheet("Student");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "Student Name", style);
        createCell(row, 2, "Email", style);
        createCell(row, 3, "Mobile No", style);
    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer){
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }

    private void write(){


        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (Student student : studentList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, student.getId(), style);
            createCell(row, columnCount++, student.getName(), style);
            createCell(row, columnCount++, student.getEMail(), style);
            createCell(row, columnCount++, student.getPhoneNumber(), style);
        }

        Row r2 = sheet.createRow(21);
        Cell cell = r2.createCell(0);
        cell.setCellValue("Обьединяшка");
        XSSFFont font2 = workbook.createFont();
        font2.setBold(true);
        font2.setItalic(true);
        font2.setFontHeight(22);
        cell.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        cell.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
        cell.getCellStyle().setFont(font2);


        CellRangeAddress cra = CellRangeAddress.valueOf("A22:D23");
        RegionUtil.setBorderTop(BorderStyle.DOTTED, cra, sheet);
        RegionUtil.setBorderLeft(BorderStyle.DOTTED, cra, sheet);
        RegionUtil.setBorderRight(BorderStyle.DOTTED, cra, sheet);
        RegionUtil.setBorderBottom(BorderStyle.DOTTED, cra, sheet);

        sheet.addMergedRegion(cra);






    }

    private void createCellMerge(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);

        cell.setCellValue((Integer) valueOfCell);

        cell.setCellStyle(style);
    }

    public void generatedExelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }


}
