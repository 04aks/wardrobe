package aks.wardrobe.trapstar;

import aks.wardrobe.consts.Links;

public class ITEM_Jogger extends Item{
    
    public ITEM_Jogger(){
        link = Links.MAN_JOGGERS;
        cssQuery = "ul.collection-list";
        target = "li";
    }
}
