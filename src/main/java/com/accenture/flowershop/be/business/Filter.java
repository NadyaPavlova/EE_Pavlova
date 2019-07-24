package com.accenture.flowershop.be.business;

public class Filter {
    private String name;
    private String minPrice;
    private String maxPrice;


    public Filter(){
    }

    public Filter( String name, String minPrice, String maxPrice){
        this.name = name;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;

    }

    @Override
    public String toString() {
        String request = "";

        if(!name.isEmpty()){
            request += "f.nameFlower like "+ "'%"+name+"%'";
        }

        if((!minPrice.isEmpty()) && (!maxPrice.isEmpty())){
            if(!request.isEmpty()) {
                request += " and ";
            }
            request += "f.price between " + minPrice + " and " + maxPrice;
        }
        else if(!minPrice.isEmpty() && maxPrice.isEmpty()){
            if(!request.isEmpty()) {
                request += " and ";
            }
            request += "f.price > " + minPrice;
        } else if(minPrice.isEmpty() && !maxPrice.isEmpty()){
            if(!request.isEmpty()) {
                request += " and ";
            }
            request += "f.price < "+maxPrice;
        }

        if(request.isEmpty()){
            return "select f from Flower f " ;
        }

        return "select f from Flower f where " + request;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}