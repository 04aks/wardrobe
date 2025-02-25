package aks.wardrobe.fit;

import aks.wardrobe.consts.Links;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class FitService {


    public String getDocName(String link){
        try{
            Document doc = Jsoup.connect(link).get();
            return doc.title();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public List<Element> getElements(String link){

        try{
            Document doc = Jsoup.connect(link).get();

            Element ulElement = doc.selectFirst("ul.collection-list");
            if(ulElement != null){
                Elements items = ulElement.select("li");
                return items.stream().toList();
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
