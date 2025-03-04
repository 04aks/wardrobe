package aks.wardrobe.trapstar;

import java.util.List;
import java.util.Random;
import org.jsoup.nodes.Element;
import aks.wardrobe.consts.Links;

public class ITEM_Shirt extends Item{    
    public ITEM_Shirt(){
        link = Links.MEN_SHIRTS;
        cssQuery = "ul.collection-list";
        target = "li";
    }

    @Override
    public void selectItem(List<Element> items) {
        int randomNum = new Random().nextInt(items.size());
        setItem(items.get(randomNum));
    }
    
}
