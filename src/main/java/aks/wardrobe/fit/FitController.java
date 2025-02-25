package aks.wardrobe.fit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class FitController {

    private final Soup soup;
    public FitController(Soup soup){
        this.soup = soup;
    }


    @GetMapping("/get")
    public String getDocTitle(){
        return soup.test();
    }

}
