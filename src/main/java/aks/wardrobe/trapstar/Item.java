package aks.wardrobe.trapstar;

import java.util.List;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import aks.wardrobe.consts.Other;

public class Item {
    Element item;
    public String link;
    public String cssQuery;
    public String target;
    // Images vars
    private String images[];
    public static final int FRONT_IMG = 0;
    public static final int BACK_IMG = 1;

    ////////////////////////////
    public String getLink() {
        return link;
    }
    public String getCssQuery() {
        return cssQuery;
    }
    public String getTarget() {
        return target;
    }
    public void setImages(String[] images) {
        this.images = images;
    }
    public String getImage(int type) {
        return images[type];
    }
    /////////////////////////////

    public void setItem(Element item) {
        this.item = item;
    }
    public Element getItem() {
        return item;
    }

    public Item getInstance(){
        return this;
    }
    public void selectItem(List<Element> items){}
    String getName(){
        return item.select("div.text-left").select("p").text();
    }
    String getPrice() {
        String price = getPriceSpan();
        // System.out.println("MANCHESTER'S BOOGEYMAN " + price);
        return price != null && !price.isEmpty() ? price : getPriceSpans();
    }
    // EXTRA 
    String getPriceSpan(){
        String priceString = item.select("div.text-left").select("span").text();
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
    String getPriceSpans(){
        String priceString = item.select("div.text-left").select("span.text-scheme-accent").text(); 
        // System.out.println("ROOT CAUSE OF THE FUCKERY "+priceString);
        String price = "";
        try{
            price = priceString.substring(Other.PRICE_CHOPPER.length() - 1).trim();
        }catch(Exception e){
            System.out.println("Error [two prices]: " + e.getMessage());
        }
        return price;
    }
    void getItemImages(){

        Elements elements = item.select("img");
        String[] links = new String[2];
        int i = 0;
        for(Element e : elements){
            links[i] = "https:" + e.attr("src");
            i++;
        }
        setImages(links);
    }
}
