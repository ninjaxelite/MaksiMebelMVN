/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maksimebel.mvn.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Magamet
 */
public class Book implements Serializable{
    
    private String name;
    private String publisher;
    private BigDecimal price;
    
    public Book() {
    }

    public Book(String name, String publisher, BigDecimal price) {
        this.name = name;
        this.publisher = publisher;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" + "name=" + name + ", publisher=" + publisher + ", price=" + price + '}';
    }
    
}
