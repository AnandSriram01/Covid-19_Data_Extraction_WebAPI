import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class JavaToExcel {

	

	public void writeFile(Statistics_State[] stats, int size) throws IOException {
		
		System.out.println("One");
		XSSFWorkbook workbook = new XSSFWorkbook();
		System.out.println("Two");
		XSSFSheet sheet = workbook.createSheet("StateWise");
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		
		int rowIndex = 1;
		int colIndex;
		
		Row rowFirst = sheet.createRow(0);
		
		for (colIndex = 0; colIndex < 5 ; colIndex++)
		{
			Cell cell = rowFirst.createCell(colIndex);
			
			switch(colIndex)
			{
				case 0 : cell.setCellValue("STATE"); break;
				case 1 : cell.setCellValue("CONFIRMED"); break;
				case 2 : cell.setCellValue("ACTIVE"); break;
				case 3 : cell.setCellValue("RECOVERED"); break;
				case 4 : cell.setCellValue("DECEASED"); break;
			}
			
			cell.setCellStyle(JavaToExcel.Format(workbook,colIndex));
			
		}
		
		for (int i=0; i<size; i++)
		{
			Row row = sheet.createRow(rowIndex++);

			for (colIndex = 0; colIndex < 5 ; colIndex++)
			{
				Cell cell = row.createCell(colIndex);
				switch(colIndex)
				{
				
					case 0 : cell.setCellValue(stats[i].getName()); break;
					case 1 : cell.setCellValue(stats[i].getConfirmed()); break;
					case 2 : cell.setCellValue(stats[i].getActive()); break;
					case 3 : cell.setCellValue(stats[i].getRecovered()); break;
					case 4 : cell.setCellValue(stats[i].getDeceased()); break;
				}
				
				sheet.autoSizeColumn(colIndex);
				cell.setCellStyle(JavaToExcel.Format(workbook,colIndex));		
	//			style = JavaToExcel.Format(workbook);
			}			
		}
		
		FileOutputStream fo = new FileOutputStream("States.xlsx");
		workbook.write(fo);
		
		fo.close();
		workbook.close();
		
		System.out.println("Finished");
		
	}
	
	public static CellStyle Format(XSSFWorkbook workbook, int colIndex)
	{
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		if (colIndex != 0)
			style.setAlignment(HorizontalAlignment.CENTER);
		else
			style.setAlignment(HorizontalAlignment.LEFT);
		font.setFontHeightInPoints((short)18);
		font.setBold(true);
		style.setFont(font);
		
		return style;
	}

	public void activeCases(Statistics_State[] stats, int size) throws IOException {
		
		FileInputStream fis=new FileInputStream("States.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Active_Cases");
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		DataFormat poiFormat = workbook.createDataFormat();
//		String excelFormatPattern = DateFormatConverter.convert(Locale.UK,"dd/MM/yyyy"); 
		
		int rowIndex = 1;
		int colIndex;
		
		Row rowFirst = sheet.getRow(0);
		Row rowTotal = sheet.getRow(1);
		colIndex = rowTotal.getLastCellNum();
		Cell cellDate = rowFirst.createCell(colIndex);
		cellDate.setCellValue(stats[0].getLastUpdated().toLocalDate());
//		cellStyle.setDataFormat(poiFormat.getFormat(excelFormatPattern));
//		short df = workbook.createDataFormat().getFormat("dd/mm/yyyy");
//		cellStyle.setDataFormat(df);
//		cellStyle.setDataFormat(workbook.createDataFormat().getFormat("dd/MM/yyyy"));
		cellStyle.setDataFormat((short)15);
		cellDate.setCellStyle(cellStyle);
		
		cellDate = rowFirst.getCell(colIndex);
		Cell cellDate2 = rowFirst.getCell(colIndex-1);
		
		System.out.println(cellDate.getDateCellValue());
		System.out.println(cellDate2.getDateCellValue());
		
//		Row rowSecond = sheet.getRow(1);
//		colIndex = rowSecond.getLastCellNum();
		System.out.println(colIndex);
		String state_name;
		int flag = 0; //Same date or not
		
		if (cellDate.getDateCellValue().equals(cellDate2.getDateCellValue()))
			{
				cellDate.setCellValue("");
				colIndex--;
				flag = 1;
			}
		
//		JavaToExcel.StateNames(workbook, sheet, stats, size);
		
		CellStyle Style = workbook.createCellStyle();
		Font font = workbook.createFont();
		int flag2 = 0;
		int flag_name = 0;
		int index;
		for (int i=0; i < size; i++)
		{
//			Row row = sheet.getRow(i+1);
			index = i;
//			int active = stats[i].getActive();
//			Cell cellPrev = row.getCell(colIndex-1);
//			int activePrev = (int) cellPrev.getNumericCellValue();
//			System.out.println(active + " " + activePrev);
			String name = stats[i].getName();
			System.out.println(name);
//			if (active < activePrev)
//			{
//				Style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
//				Style.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
//				flag2 = 1;
//			}
			
			for (int j=0; j < size; j++ ) {

				index = j;
				Row row2 = sheet.getRow(j+1);
				Cell cellFirst = row2.getCell(1);
				state_name = cellFirst.getStringCellValue();
				System.out.println(state_name);
				if (name.equals(state_name)) 
					break;
			
			}
			
			int active = stats[i].getActive();
			Row row = sheet.getRow(index+1);
//			Cell cellPrev = row.getCell(colIndex-1);
//			int activePrev = (int) cellPrev.getNumericCellValue();
//			System.out.println(active + " " + activePrev);
//			
//			if (active < activePrev)
//			{
//				Style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
//				Style.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
//				flag2 = 1;
//			}
//			
		   
//			else
//			{
//				Style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
//				Style.setFillPattern(FillPatternType.NO_FILL); 
//			}
			
			if (flag == 1)
			{
				Cell cell = row.getCell(colIndex);
				System.out.println(colIndex);
				cell.setCellValue(active);
//				if (flag2 == 1)
//					cell.setCellStyle(Style);
			}
			
			else
			{
				Cell cell = row.createCell(colIndex);
				cell.setCellValue(active);
//				if (flag2 == 1)
//					cell.setCellStyle(Style);
			}						
		}
		
		FileOutputStream fo = new FileOutputStream("States.xlsx");
		workbook.write(fo);
		
		fo.close();
		workbook.close();
		fis.close();
		
		System.out.println("Finished");
		
	}

	private static void StateNames(XSSFWorkbook workbook, XSSFSheet sheet, Statistics_State[] stats, int size) {
		
		for (int i = 0; i < size; i++)
		{
			Row row = sheet.createRow(i+1);
			Cell cell = row.createCell(0);
			cell.setCellValue(stats[i].getName());
		}
		
	}
	
	
	

}
