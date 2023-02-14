package Guru99_BankingOnline_Utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class XLUtilityManager {

    public static String[][] getData() throws IOException {
        File excelFile = new File("C:\\Users\\user\\IdeaProjects\\Guru99_BankingOnline\\TestData\\LoginData.xlsx");
        FileInputStream fis = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int noOfrows = sheet.getPhysicalNumberOfRows();    //Give no of physical no of row
        //System.out.println(sheet.getLastRowNum());  //Same as above but return index value
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        String data[][] = new String[noOfrows - 1][noOfColumns];
        for (int i = 0; i < noOfrows - 1; i++) {
            for (int j = 0; j < noOfColumns; j++) {
                DataFormatter df = new DataFormatter();
                data[i][j] = df.formatCellValue(sheet.getRow(i + 1).getCell(j));
                //System.out.println(sheet.getRow(i).getCell(j).getStringCellValue());
            }
        }

        workbook.close();
        fis.close();
        return data;
    }


}






