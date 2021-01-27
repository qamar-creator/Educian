package utils;

 
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 

public class excel_utils {
	
	public static void main( String [] arg) {
		
		
		
		getrowcount();
	}
	
	public static void getrowcount()
	{
		
		try
		{
		String systempath=	System.getProperty("user.dir");
		System.out.println(systempath);
		
		XSSFWorkbook workbook=new XSSFWorkbook ("C:\\Users\\MinzaMushtaq3\\eclipse-workspace\\Educian\\excel data\\login.xlsx");
	
	   XSSFSheet sheet=workbook.getSheet("login");
	  int count= sheet.getPhysicalNumberOfRows();
	  
	  System.out.println(count);
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	
	}

}
