package api.utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders{
	
	@DataProvider(name = "Data")
	public String[][] getAllData() throws IOException {
		
		String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
		
		int rownum = XLUtility.getRowCount(path, "Sheet1");
		int colnum = XLUtility.getCellCount(path, "Sheet1", 1);
		
		String apidata[][] = new String[rownum][colnum];
		for (int i = 1; i <= rownum; i++) 
		{
			for (int j = 0; j < colnum; j++) 
				{
					apidata[i - 1][j] = XLUtility.getCellData(path, "Sheet1", i, j);
				}
		}
		return apidata;
		
	}
	
	@DataProvider(name ="UserNames")
	public String[] getuserNames() throws IOException {
		String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
		
		int rownum = XLUtility.getRowCount(path, "Sheet1");
		
		String apidata[] = new String[rownum];
		for (int i = 1; i <= rownum; i++) 
		{

			apidata[i - 1] = XLUtility.getCellData(path, "Sheet1", i,1);

		}
		return apidata;
		}
	
	
	
	}
	
