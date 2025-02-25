package aks.wardrobe.fit;

import aks.wardrobe.consts.Links;
import org.jsoup.nodes.Element;

import java.util.List;

public class Fit {
    private List<Element> shirts;
    private List<Element> bottoms;

    FitService fitService;
    public Fit(FitService fitService, List<Element> shirts, List<Element> bottoms){
        this.fitService = fitService;
        this.shirts = shirts;
    }

    public void setShirts(List<Element> shirts) {
        this.shirts = shirts;
    }
    public List<Element> getShirts() {
        return shirts;
    }

    public void setBottoms(List<Element> bottoms) {
        this.bottoms = bottoms;
    }
    public List<Element> getBottoms() {
        return bottoms;
    }
}
