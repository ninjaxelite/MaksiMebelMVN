/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maksimebel.mvn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Magamet
 */
@Named(value = "mCon")
@SessionScoped
public class MainController implements Serializable {

    private MenuModel availableCitiesModel, destCitiesModel;
    private MenuModel currencyModel;
    private String originPort;
    private String destinationPort;
    private Boolean oneWayOrNot = false;
    private Date dateFrom;
    private Date dateTo;
    private String currency;
    private String menuOrg = "display: none !important;";
    private String menuDest = "display: none !important;";
    private String previousPage = null;
    private int adultPass=0, childPass=0, infantPass=0;

    @PostConstruct
    public void init() {
        //HttpRequestHandler hrh = new HttpRequestHandler();

        availableCitiesModel = initCitiesModel(1);
        destCitiesModel = initCitiesModel(2);
        currencyModel = initCurrenciesModel();

        menuOrg = "display: none !important;";
        menuDest = "display: none !important;";

        originPort = "Vienna";
        destinationPort = "Paris";

        Calendar cald = GregorianCalendar.getInstance();

        dateFrom = cald.getTime();
        cald.add(GregorianCalendar.DAY_OF_MONTH, 30);
        dateTo = cald.getTime();
    }

    public String buttonAction() {
        return "flights.xhtml?faces-redirect=true";
    }

    public DefaultMenuModel initCitiesModel(int id) {
        DefaultMenuModel model = new DefaultMenuModel();
        List<String> cities = Arrays.asList("Vienna", "Berlin", "Paris", "Rom", "Moscow", "London", "Bern");
        Map<String, DefaultSubMenu> subMenuMap = new HashMap<>();

        Arrays.sort(cities.toArray());
        cities.stream().forEach((c) -> {
            String key = c.substring(0, 1);
            DefaultSubMenu submenu = new DefaultSubMenu(key);
            DefaultMenuItem item = new DefaultMenuItem(c);
            item.setCommand("#{mCon.itemClick('" + c + "'," + id + ")}");

            if (!subMenuMap.containsKey(key)) {
                submenu.addElement(item);
                subMenuMap.put(key, submenu);
            } else {
                subMenuMap.get(key).addElement(item);
            }
        });

        subMenuMap.values().stream().forEach((sb) -> {
            model.addElement(sb);
        });

        return model;
    }

    public void itemClick(String item, int id) {
        switch (id) {
            case 1:
                originPort = item;
                FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("mainForm:orgButton");
                RequestContext.getCurrentInstance().update("mainForm:orgButton");
                break;
            case 2:
                destinationPort = item;
                FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("mainForm:destButton");
                RequestContext.getCurrentInstance().update("mainForm:destButton");
                break;
            case 5:
                currency = item;
                RequestContext.getCurrentInstance().update("mainForm:curr");
                break;
            default: System.out.println("id ...");
                break;
        }
    }

    public DefaultMenuModel initCurrenciesModel() {
        DefaultMenuModel model = new DefaultMenuModel();
        DefaultMenuItem item = new DefaultMenuItem("€");
        DefaultMenuItem item2 = new DefaultMenuItem("$");
        DefaultMenuItem item3 = new DefaultMenuItem("rub");
        item.setCommand("#{mCon.itemClick('€',5)}");
        item2.setCommand("#{mCon.itemClick('$',5)}");
        item3.setCommand("#{mCon.itemClick('rub',5)}");
        model.addElement(item);
        model.addElement(item2);
        model.addElement(item3);
        currency = "€";
        return model;
    }

    public void showMenu(int id) {
        if (id == 1) {
            menuOrg = "display: block !important;";
        } else if (id == 2) {
            menuDest = "display: block !important";
        }
    }

    public void addPassenger(int id) {
        switch (id) {
            case 1: adultPass++;
                break;
            case 2: childPass++;
                break;
            case 3: infantPass++;
                break;
            default: System.out.println("id " + id+" not found");
                break;
        }
    }
    
    public void removePassenger(int id) {
        switch (id) {
            case 1: if(adultPass>0) adultPass--;
                break;
            case 2: if(childPass>0) childPass--;
                break;
            case 3: if(infantPass>0) infantPass--;
                break;
            default: System.out.println("id "+id+" not found");
                break;
        }
    }

    public void checkPageReload() {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        String id = viewRoot.getViewId();
        if (previousPage != null && (previousPage.equals(id))) {
            menuOrg = "display: none !important";
            menuDest = "display: none !important";
        }
        previousPage = id;
    }

    public MenuModel getAvailableCitiesModel() {
        return availableCitiesModel;
    }

    public void setAvailableCitiesModel(MenuModel availableCitiesModel) {
        this.availableCitiesModel = availableCitiesModel;
    }

    public String getOriginPort() {
        return originPort;
    }

    public void setOriginPort(String originPort) {
        this.originPort = originPort;
    }

    public String getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(String destinationPort) {
        this.destinationPort = destinationPort;
    }

    public Boolean getOneWayOrNot() {
        return oneWayOrNot;
    }

    public void setOneWayOrNot(Boolean oneWayOrNot) {
        this.oneWayOrNot = oneWayOrNot;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public MenuModel getCurrencyModel() {
        return currencyModel;
    }

    public void setCurrencyModel(MenuModel currencyModel) {
        this.currencyModel = currencyModel;
    }

    public MenuModel getDestCitiesModel() {
        return destCitiesModel;
    }

    public void setDestCitiesModel(MenuModel destCitiesModel) {
        this.destCitiesModel = destCitiesModel;
    }

    public String getMenuOrg() {
        return menuOrg;
    }

    public void setMenuOrg(String menuOrg) {
        this.menuOrg = menuOrg;
    }

    public String getMenuDest() {
        return menuDest;
    }

    public void setMenuDest(String menuDest) {
        this.menuDest = menuDest;
    }

    public int getAdultPass() {
        return adultPass;
    }

    public void setAdultPass(int adultPass) {
        this.adultPass = adultPass;
    }

    public int getChildPass() {
        return childPass;
    }

    public void setChildPass(int childPass) {
        this.childPass = childPass;
    }

    public int getInfantPass() {
        return infantPass;
    }

    public void setInfantPass(int infantPass) {
        this.infantPass = infantPass;
    }
}
