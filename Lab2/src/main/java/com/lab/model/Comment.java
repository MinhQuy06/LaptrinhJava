package com.lab.model;

public class Comment {
    private String author;
    private String text;
    public Comment(String author, String text) {
        this.author = author;
        this.text   = text;
    }
    public String getAuthor() { return author; }
    public String getText()   { return text; }
    @Override
    public String toString() { return "[" + author + "]: " + text; }
}