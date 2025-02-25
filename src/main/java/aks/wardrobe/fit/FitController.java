package aks.wardrobe.fit;

import aks.wardrobe.consts.Links;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/trapstar")
public class FitController {

    Fit fit;
    @Autowired
    private final FitService fitService;
    public FitController(FitService fitService){
        this.fitService = fitService;
        fit = new Fit(fitService, fitService.getElements(Links.MEN_SHIRTS), null);
    }

    @GetMapping("/shirt")
    public String getShirt(){

        int num = new Random().nextInt(fit.getShirts().size() + 1);
        return fit.getShirts().get(num).outerHtml();
    }

}
