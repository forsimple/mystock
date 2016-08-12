package admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

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
}
