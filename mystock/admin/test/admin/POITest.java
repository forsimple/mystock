package admin;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

public class POITest {

    @Test
    public void readCSVtest() throws IOException, URISyntaxException{
        String url="http://218.244.146.57/static/all.csv";
        
       InputStream in=new URL(url).openConnection().getInputStream();
       BufferedReader reader=new BufferedReader(new InputStreamReader(in,"GBK"));
       String line;
       while((line=reader.readLine()) !=null){
           System.out.println(line);
       }
    }
    
    
    private static void readXls(String filePath) throws IOException{  
        InputStream is = new FileInputStream( filePath);  
         HSSFWorkbook hssfWorkbook = new HSSFWorkbook( is);   
          
        // 循环工作表Sheet  
        for(int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++){  
          HSSFSheet hssfSheet = hssfWorkbook.getSheetAt( numSheet);  
          if(hssfSheet == null){  
            continue;  
          }  
            
          // 循环行Row   
          for(int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++){  
            HSSFRow hssfRow = hssfSheet.getRow( rowNum);  
            if(hssfRow == null){  
              continue;  
            }  
              
            // 循环列Cell    
            for(int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++){  
              HSSFCell hssfCell = hssfRow.getCell( cellNum);  
              if(hssfCell == null){  
                continue;  
              }  
                
              System.out.print("    " + getValue( hssfCell));  
            }  
            System.out.println();  
          }  
        }  
      }  
        
      private  static String getValue(HSSFCell hssfCell){  
        if(hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN){  
          return String.valueOf( hssfCell.getBooleanCellValue());  
        }else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC){  
          return String.valueOf( hssfCell.getNumericCellValue());  
        }else{  
          return String.valueOf( hssfCell.getStringCellValue());  
        }  
      }  
}
