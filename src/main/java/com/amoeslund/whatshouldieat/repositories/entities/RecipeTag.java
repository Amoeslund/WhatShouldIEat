package com.amoeslund.whatshouldieat.repositories.entities;


import com.amoeslund.whatshouldieat.helpers.StringExtensions;


public class RecipeTag {

    private String tag;

    public RecipeTag() {

    }

    public RecipeTag(String tag) {
        this.tag = StringExtensions.toTitleCase(tag);
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = StringExtensions.toTitleCase(tag);
    }


    @Override
    public String toString() {
        return tag;
    }

    public boolean tagEquals(String s) {
        return this.getTag().equalsIgnoreCase(s);
    }
}
