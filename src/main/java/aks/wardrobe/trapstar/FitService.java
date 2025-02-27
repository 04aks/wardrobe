package aks.wardrobe.trapstar;

import aks.wardrobe.consts.Other;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.Random;

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
    public List<Element> getElements(Item item, String filter){

        try{
            Document doc = Jsoup.connect(item.getLink()).get();

            Element ulElement = doc.selectFirst(item.getCssQuery());
            if(ulElement != null){
                Elements items = ulElement.select(item.getTarget());
                // REMOVE TRACKSUITS because they aren't JOGGERS duh
                if(filter != null) items.removeIf(e -> e.text().contains(filter));
                return items.stream().toList();
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public List<Element> getRandomFit(Fit fit){

        if(fit.getShirts().isEmpty() && fit.getBottoms().isEmpty()){
            throw new NullPointerException("Haven't scrapet shit");
        }

        Random random = new Random();
        int randomShirt = random.nextInt(fit.getShirts().size());
        int randomJogger = random.nextInt(fit.getBottoms().size());

        Element shirt, jogger;
        shirt = fit.getShirts().get(randomShirt);
        jogger = fit.getBottoms().get(randomJogger);

        String filter = "Tracksuit";
        System.out.println(jogger.text().contains(filter));
        System.out.println("Text in question: " + jogger.text());

        return List.of(shirt, jogger);
    }
}
