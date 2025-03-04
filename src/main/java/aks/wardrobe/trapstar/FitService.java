package aks.wardrobe.trapstar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import aks.wardrobe.consts.Other;

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
    public List<Fit> getRandomFit(FitsManager fit){

        if(fit.getShirts().isEmpty() && fit.getBottoms().isEmpty()){
            throw new NullPointerException("Haven't scraped shit");
        }

        Random random = new Random();
        int randomShirt = random.nextInt(fit.getShirts().size());
        int randomJogger = random.nextInt(fit.getBottoms().size());

        Element shirt, jogger;
        shirt = fit.getShirts().get(randomShirt);
        jogger = fit.getBottoms().get(randomJogger);

        Fit top = new Fit.Builder()
        .name(getName(shirt))
        .type(Other.TYPE_TOP)
        .price(getPrice(shirt))
        .imageFront(null)
        .imageBack(null)
        .build();     
        
        Fit bottoms = new Fit.Builder()
        .name(getName(jogger))
        .type(Other.TYPE_BOTTOMS)
        .price(getPrice(jogger))
        .imageFront(null)
        .imageBack(null)
        .build();  

        return List.of(top, bottoms);
    }

    String getName(Element element){
        return element.select("div.text-left").select("p").text();
    }
    String getPrice(Element element) {
        String price = getPriceSpan(element);
        return price != null && !price.isEmpty() ? price : getPriceSpans(element);
    }
    // EXTRA 
    String getPriceSpan(Element element){
        String price = element.select("div.text-left").select("span").text();
        return price.substring(price.indexOf("£"), price.length() - Other.PRICE_CHOPPER.length()).trim();
    }
    String getPriceSpans(Element element){
        return element.select("div.text-left").select("span.text-scheme-accent").text().substring(Other.PRICE_CHOPPER.length());
    }
    String[] getItemImage(Elements elements){

        String[] links = new String[2];
        for(int i = 0; i < links.length; i++){
            links[i] = elements.attr("src");
        }
        return links;
    }

    
}
