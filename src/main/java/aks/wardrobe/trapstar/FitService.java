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

        List<Element> shirtsList = fit.getShirts();
        List<Element> joggersList = fit.getBottoms();
        if(shirtsList.isEmpty() || joggersList.isEmpty() || shirtsList == null || joggersList == null){
            throw new NullPointerException("Haven't scrapet shit");
        }

        Random random = new Random();
        int randomShirt = random.nextInt(fit.getShirts().size());
        int randomJogger = random.nextInt(fit.getBottoms().size());

        Element shirt, jogger;
        shirt = shirtsList.get(randomShirt);
        jogger = joggersList.get(randomJogger);

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

    public String getName(Element element){
        return element.select("div.text-left").select("p").text();
    }
    public String getPrice(Element element) {
        String price = getPriceSpan(element);
        System.out.println("MANCHESTER'S BOOGEYMAN " + price);
        return price != null && !price.isEmpty() ? price : getPriceSpans(element);
    }
    // EXTRA 
    String getPriceSpan(Element element){
        String priceString = element.select("div.text-left").select("span").text();
        String price = "";
        try{
            if(priceString.contains(Other.PRICE_CHOPPER)){
                price = priceString.substring(priceString.indexOf("£"), priceString.length() - (Other.PRICE_CHOPPER.length()) - 1).trim();
            }
            else{
                price = priceString.substring(priceString.indexOf("£")).trim();
            }
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        } 
        return price;
    }
    String getPriceSpans(Element element){
        String priceString = element.select("div.text-left").select("span.text-scheme-accent").text(); 
        System.out.println("ROOT CAUSE OF THE FUCKERY "+priceString);
        String price = "";
        try{
            price = priceString.substring(Other.PRICE_CHOPPER.length() - 1).trim();
        }catch(Exception e){
            System.out.println("Error [two prices]: " + e.getMessage());
        }
        return price;
    }
    
}
