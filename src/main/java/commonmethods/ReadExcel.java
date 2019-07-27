package commonmethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	String keyword, object, objecttype, value;

	public static ArrayList<String> readData(String filePath, String fileName, String sheetName) throws IOException {
		List<String> output = new ArrayList<String>();
		// Create an object of File class to open xlsx file

		File file = new File(filePath + "\\" + fileName);

		// Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Workbook Workbook = null;

		// Find the file extension by splitting file name in substring and getting only
		// extension name

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		// Check condition if the file is xlsx file

		if (fileExtensionName.equals(".xlsx")) {

			// If it is xlsx file then create object of XSSFWorkbook class

			Workbook = new XSSFWorkbook(inputStream);

		}

		// Check condition if the file is xls file

		else if (fileExtensionName.equals(".xls")) {

			// If it is xls file then create object of XSSFWorkbook class

			Workbook = new HSSFWorkbook(inputStream);

		}

		// Read excel sheet by sheet name

		Sheet Sheet = Workbook.getSheet(sheetName);

		// Find number of rows in excel file

		int rowCount = Sheet.getLastRowNum() - Sheet.getFirstRowNum();

		// Create a loop over all the rows of excel file to read it

		for (int i = 0; i <= rowCount; i++) {

			Row row = Sheet.getRow(i);

			// Create a loop to print cell values in a row

			for (int j = 0; j < row.getLastCellNum(); j++) {

				output.add(row.getCell(j).getStringCellValue());

			}

		}

		return (ArrayList<String>) output;
	}

	public static Sheet readData_Keyword(String filePath, String fileName, String sheetName)
			throws IOException, NullPointerException {
		// Create a object of File class to open xlsx file
		File file = new File(filePath + "\\" + fileName);
		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		Workbook Workbook = null;
		// Find the file extension by spliting file name in substing and getting only
		// extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		// Check condition if the file is xlsx file
		if (fileExtensionName.equals(".xlsx")) {
			// If it is xlsx file then create object of XSSFWorkbook class
			Workbook = new XSSFWorkbook(inputStream);
		}
		// Check condition if the file is xls file
		else if (fileExtensionName.equals(".xls")) {
			// If it is xls file then create object of XSSFWorkbook class
			Workbook = new HSSFWorkbook(inputStream);
		}
		// Read sheet inside the workbook by its name
		Sheet Sheet = Workbook.getSheet(sheetName);
		return Sheet;
	}

}
