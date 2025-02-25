package aks.wardrobe.fit;

import aks.wardrobe.consts.Links;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Soup {

    public String test(){

        try{
            Document doc = Jsoup.connect(Links.MEN_STORE).get();


            String title = doc.title();
            return "Doc title: " + title;
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        return null;
    }
}
