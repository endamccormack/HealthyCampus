package com.Endanger.healthycampusapp.healthycampus.app.database;

/**
 * Created by Enda on 4/7/2014.
 */
public class Tag {

    private String TagName;

    public Tag(){}
    public Tag(String tagName){
        TagName = tagName;
    }

    public void setTagName(String tagName){
        TagName = tagName;
    }

    public String getTagName(){
        return TagName;
    }
}
