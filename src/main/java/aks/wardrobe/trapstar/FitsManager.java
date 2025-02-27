package aks.wardrobe.trapstar;

import org.jsoup.nodes.Element;
import java.util.List;

public class FitsManager {
    private List<Element> shirts;
    private List<Element> bottoms;

    FitService fitService;
    public FitsManager(List<Element> shirts, List<Element> bottoms){
        this.shirts = shirts;
        this.bottoms = bottoms;
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
