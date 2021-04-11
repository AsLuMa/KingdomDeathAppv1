package com.example.kingdomdeath;

import android.widget.CheckBox;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class AttributeValues {

    private String nameOfAttribute;
    private String attributeValue;
    private Boolean FA;
    private Boolean token;

    //TODO check if we need the String nameOfAttribute for anything

    public AttributeValues(String attributeValue, Boolean FA, Boolean token){
        this.nameOfAttribute = nameOfAttribute;
        this.attributeValue = attributeValue;
        this.FA = FA;
        this.token = token;
    }

    public String getNameOfAttribute() {
        return nameOfAttribute;
    }

    public void setNameOfAttribute(String nameOfAttribute) {
        this.nameOfAttribute = nameOfAttribute;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public Boolean getFA() {
        return FA;
    }

    public void setFA(Boolean FA) {
        this.FA = FA;
    }

    public Boolean getToken() {
        return token;
    }

    public void setToken(Boolean token) {
        this.token = token;
    }
}
