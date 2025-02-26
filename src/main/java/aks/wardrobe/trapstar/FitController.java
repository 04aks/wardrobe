package aks.wardrobe.trapstar;

import java.util.List;
import java.util.Random;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trapstar")
public class FitController {

    // STATIC CLASSES -------------------//
    Fit fit;
    ITEM_Shirt shirt = new ITEM_Shirt();
    ITEM_Jogger jogger = new ITEM_Jogger();
    // ----------------------------------//

    @Autowired
    private final FitService fitService;
    public FitController(FitService fitService){
        this.fitService = fitService;
        fit = new Fit(fitService.getElements(shirt), fitService.getElements(jogger));
    }

    @GetMapping("/shirt")
    public String getShirt(){

        int num = new Random().nextInt(fit.getShirts().size());
        return fit.getShirts().get(num).outerHtml();
    }
    @GetMapping("/jogger")
    public String getJogger(){

        int num = new Random().nextInt(fit.getBottoms().size());
        return fit.getBottoms().get(num).outerHtml();
    }
    @GetMapping("/getfit")
    public String getFit(){
        List<Element> myFit = fitService.getRandomFit(fit);
        System.out.println("LIST SIZE: " + myFit.size());
        for(Element element : myFit){
            System.out.println(element.text());
        }

        return myFit.toString();
    }

}
