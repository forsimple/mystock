package me.wh.stock.admin.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtils {

    public static List<List<String>> getTableById(String url, String tableId, int skipRowNum) {
        List<List<String>> results = new ArrayList<List<String>>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements trs = doc.select("#"+tableId+" tr");
            if (trs != null) {
                int size = trs.size();
                if (skipRowNum >= size)
                    return results;
                for (int i=skipRowNum;i<size;i++) {
                    Element tr=trs.get(i);
                    List<String> oneRow = new ArrayList<String>();
                    for (Element td : tr.children()) {
                        oneRow.add(td.text());
                        System.out.print(td.text() + "\t");
                    }
                    System.out.println();
                    results.add(oneRow);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }
}
