package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Locale;
import java.util.ResourceBundle;

@Getter
@Setter
public class LocalizationBean {
    private String language = "ru";
    private String country = "RU";

    private String name;
    private String group;
    private String variant;
    private String mainPage;
    private String startPage;
    private String x;
    private String r;
    private String y;
    private String clearAll;
    private String valueX;
    private String xNumber;
    private String xBetween;
    private String xRequired;
    private String valueR;
    private String rNumber;
    private String rBetween;
    private String rRequired;
    private String submit;
    private String result;
    private String currtime;
    private String extime;
private String YReq;

    public void localize() {
        Locale currentLocale = new Locale(language, country);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resources", currentLocale);
        name = resourceBundle.getString("name");
        variant = resourceBundle.getString("variant");
        mainPage = resourceBundle.getString("goToMain");
        startPage = resourceBundle.getString("goToStart");
        x = resourceBundle.getString("x");
        y = resourceBundle.getString("y");
        r = resourceBundle.getString("r");
        clearAll = resourceBundle.getString("clearAll");
        valueX = resourceBundle.getString("valueX");
        xNumber = resourceBundle.getString("xNumber");
        xBetween = resourceBundle.getString("xBetween");
        xRequired = resourceBundle.getString("xRequired");
        valueR = resourceBundle.getString("valueR");
        rNumber = resourceBundle.getString("rNumber");
        rBetween = resourceBundle.getString("rBetween");
        rRequired = resourceBundle.getString("rRequired");
        submit = resourceBundle.getString("Submit");
        result = resourceBundle.getString("Result");
        currtime = resourceBundle.getString("Currenttime");
        extime = resourceBundle.getString("Executetime");
        YReq = resourceBundle.getString("YReq");
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
        localize();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        localize();
    }


    public String getrNumber() {
        return rNumber;
    }

    public void setrNumber(String rNumber) {
        this.rNumber = rNumber;
    }

    public String getrBetween() {
        return rBetween;
    }

    public void setrBetween(String rBetween) {
        this.rBetween = rBetween;
    }

    public String getClearAll() {
        return clearAll;
    }

    public void setClearAll(String clearAll) {
        this.clearAll = clearAll;
    }

    public String getrRequired() {
        return rRequired;
    }

    public void setrRequired(String rRequired) {
        this.rRequired = rRequired;
    }

    public String getxNumber() {
        return xNumber;
    }

    public void setxNumber(String xNumber) {
        this.xNumber = xNumber;
    }

    public String getxBetween() {
        return xBetween;
    }

    public void setxBetween(String xBetween) {
        this.xBetween = xBetween;
    }

    public String getxRequired() {
        return xRequired;
    }

    public void setxRequired(String xRequired) {
        this.xRequired = xRequired;
    }
}
