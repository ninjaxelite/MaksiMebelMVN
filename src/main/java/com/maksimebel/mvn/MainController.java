/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maksimebel.mvn;

import com.maksimebel.mvn.entity.Book;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author Magamet
 */
@Named(value = "mCon")
@SessionScoped
//@ManagedBean(name = "mCon")
//@SessionScoped
public class MainController implements Serializable{
    List<Book> books = new ArrayList<>();
    
    @PostConstruct
    public void init(){
        books.add(new Book("test 1", "p1", new BigDecimal(13.99)));
        books.add(new Book("test 2", "p2", new BigDecimal(10.40)));
        books.add(new Book("test 3", "p3", new BigDecimal(26.55)));
        books.add(new Book("test 4", "p3", new BigDecimal(44.99)));
        books.add(new Book("test 5", "p4", new BigDecimal(9.99)));
        
    }
    
    public void buttonAction(ActionEvent actionEvent) {
        addMessage("THIS IS THE NEW WORLD");
    }
     
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
