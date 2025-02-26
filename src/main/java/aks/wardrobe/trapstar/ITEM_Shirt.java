package aks.wardrobe.trapstar;

import aks.wardrobe.consts.Links;

public class ITEM_Shirt extends Item{

    public ITEM_Shirt(){
        link = Links.MEN_SHIRTS;
        cssQuery = "ul.collection-list";
        target = "li";
    }
    
}
