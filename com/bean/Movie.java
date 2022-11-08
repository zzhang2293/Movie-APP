package com.bean;

import java.util.Date;

public class Movie {
    private String name;
    private String actor;
    private double score;
    private double numRate;
    private double time;
    private double price;
    private int number;
    private Date startTime;

    public Movie(String name, String actor, double time, double price, int number, Date startTime) {
        this.name = name;
        this.actor = actor;
        this.time = time;
        this.price = price;
        this.number = number;
        this.startTime = startTime;
    }
    public Movie(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public double getSocre() {
        return score;
    }

    public void setSocre(double socre) {
        this.score = socre;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {

        this.startTime = startTime;
    }
    public void editScore(double score){
        this.score = (this.score + score) / (numRate + 1);
        numRate ++;
    }
}
