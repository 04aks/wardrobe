package aks.wardrobe.trapstar;


public class Fit {
    
    private String name;
    private String type;
    private String imageFront;
    private String imageBack;
    private String price;
    private Fit(Builder builder){
        this.name = builder.name;
        this.type = builder.type;
        this.imageFront = builder.imageFront;
        this.imageBack = builder.imageBack;
        this.price = builder.price;
    }
    public String getImageBack() {
        return imageBack;
    }
    public String getImageFront() {
        return imageFront;
    }
    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public String getType() {
        return type;
    }



    public static class Builder{
        private String name;
        private String type;
        private String imageFront;
        private String imageBack;
        private String price;

        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder type(String type){
            this.type = type;
            return this;
        }
        public Builder imageFront(String imageFront){
            this.imageFront = imageFront;
            return this;
        }
        public Builder imageBack(String imageBack){
            this.imageBack = imageBack;
            return this;
        }
        public Builder price(String price){
            this.price = price;
            return this;
        }

        public Fit build(){
            return new Fit(this);
        }
    }
}
