package com.qa.Utils.ExcelUtils;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readingExcel {

    static String testDataExcelPath = null;
    static String currentDir = System.getProperty("user.dir");
    static String testExcelFile = "testdata.xlsx";
    static XSSFWorkbook excelWorkBook;
    static XSSFSheet excelWorkSheet;
    static XSSFCell cell;
    static XSSFRow row;
    static int rownum;
    static int colnum;

    public void excelFileSheet(String sheetName) throws IOException {

        testDataExcelPath = currentDir + "/src/test/resources/";

        FileInputStream ExcelFile = new FileInputStream(testDataExcelPath + testExcelFile);
        excelWorkBook = new XSSFWorkbook(ExcelFile);
        excelWorkSheet = excelWorkBook.getSheet(sheetName);

    }

    public static String getCellData(int RowNum, int ColNum) {
        cell = excelWorkSheet.getRow(RowNum).getCell(ColNum);
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    //This method takes row number as a parameter and returns the data of given row number.
    public static XSSFRow getRowData(int RowNum) {
        row = excelWorkSheet.getRow(RowNum);
        return row;
    }

    public static void setCellData(String value, int RowNum, int ColNum) throws IOException {
        row = excelWorkSheet.getRow(RowNum);
        cell = row.getCell(ColNum);
        if (cell == null) {
            cell = row.createCell(ColNum);
            cell.setCellValue(value);
        } else {
            cell.setCellValue(value);
        }
        // Constant variables Test Data path and Test Data file name
        FileOutputStream fileOut = new FileOutputStream(testDataExcelPath + testExcelFile);
        excelWorkBook.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

}
