package aks.wardrobe.trapstar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import aks.wardrobe.consts.Other;
import java.io.IOException;
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
    public List<Fit> getRandomFit(FitsManager fit, ITEM_Shirt shirt, ITEM_Jogger jogger){

        List<Element> shirtsList = fit.getShirts();
        List<Element> joggersList = fit.getBottoms();
        if(shirtsList.isEmpty() || joggersList.isEmpty() || shirtsList == null || joggersList == null){
            throw new NullPointerException("Haven't scrapet shit");
        }

        // SELECT ONE ITEM FROM EACH LIST
        shirt.selectItem(shirtsList);
        jogger.selectItem(joggersList);
        // LOAD ITEMS' IMAGES
        shirt.getItemImages();
        jogger.getItemImages();

        // BUILD TOP
        Fit top = new Fit.Builder()
        .name(shirt.getName())
        .type(Other.TYPE_TOP)
        .price(shirt.getPrice())
        .imageFront(shirt.getImage(Item.FRONT_IMG))
        .imageBack(shirt.getImage(Item.BACK_IMG))
        .build();     
        
        // BUILD BOTTOMS
        Fit bottoms = new Fit.Builder()
        .name(jogger.getName())
        .type(Other.TYPE_BOTTOMS)
        .price(jogger.getPrice())
        .imageFront(jogger.getImage(Item.FRONT_IMG))
        .imageBack(jogger.getImage(Item.BACK_IMG))
        .build();  

        return List.of(top, bottoms);
    }

}
