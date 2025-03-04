package aks.wardrobe.trapstar;

import java.util.List;
import aks.wardrobe.consts.Other;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trapstar")
public class FitController {

    // STATIC CLASSES -------------------//
    FitsManager fitsM;
    ITEM_Shirt shirt = new ITEM_Shirt();
    ITEM_Jogger jogger = new ITEM_Jogger();
    // ----------------------------------//

    @Autowired
    private final FitService fitService;
    public FitController(FitService fitService){
        this.fitService = fitService;
        fitsM = new FitsManager(fitService.getElements(shirt, null), fitService.getElements(jogger, Other.FILTER_JOGGER));
    }

    @GetMapping("/fit")
    public List<Fit> getFit(){
        List<Fit> myFit = fitService.getRandomFit(fitsM, shirt, jogger);
        return myFit;
    }

}
