package utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtil {

	private Sheet sheet;
	private String filePath;
	private Workbook wb;

	public ExcelUtil(String excelPath, String sheetName) {
		try {
			this.filePath = excelPath;
			FileInputStream fis = new FileInputStream(excelPath);
			 wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(sheetName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Get cell data
	public String getCellData(int rowNum, int colNum) {
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(colNum);
		return cell.toString();
	}

	// Get row count
	public int getRowCount() {
		return sheet.getPhysicalNumberOfRows();
	}

	// Get column count
	public int getColumnCount() {
		return sheet.getRow(0).getPhysicalNumberOfCells();
	}

	public void setCellData(String data, int rowNum, int colNum) {
		try {
			Row row = sheet.getRow(rowNum);
			if (row == null) {
				row = sheet.createRow(rowNum);
			}

			Cell cell = row.getCell(colNum);
			if (cell == null) {
				cell = row.createCell(colNum);
			}

			cell.setCellValue(data);

			FileOutputStream fos = new FileOutputStream(filePath);
			wb.write(fos);
			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToExcelExample() {
		ExcelUtil excel = new ExcelUtil("src/test/resources/TestData/TestData.xlsx", "ExcelOutput");

		excel.setCellData("PASS", 1, 2); // Writes to cell C2 (row 1, column 2)
		excel.setCellData("FAIL", 2, 2); // Writes to cell C3

		try {
			excel.closeWorkbook();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readTestDataFromExcel() {
		ExcelUtil excel = new ExcelUtil("src/test/resources/TestData/TestData.xlsx", "LoginData");

		int rowCount = excel.getRowCount();

		for (int i = 1; i < rowCount; i++) {
			String username = excel.getCellData(i, 0);
			String password = excel.getCellData(i, 1);

			System.out.println("Username: " + username + " | Password: " + password);
			// You could now use these credentials in a login test

			try {
				excel.closeWorkbook();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Using a DataProvider for reading the data
	@DataProvider(name = "loginData")
	public static Object[][] getLoginData() {

		ExcelUtil excel = new ExcelUtil("src/test/resources/TestData/TestData.xlsx", "LoginData");

		int rows = excel.getRowCount();
		int cols = excel.getColumnCount();

		Object[][] data = new Object[rows - 1][cols]; // Skip header row

		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i - 1][j] = excel.getCellData(i, j);
			}
		}

		return data;

	}

	public void closeWorkbook() throws IOException {
		wb.close();
	}
}
