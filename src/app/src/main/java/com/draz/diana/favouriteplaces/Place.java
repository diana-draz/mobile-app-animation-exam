package com.draz.diana.favouriteplaces;

public class Place {
    public int Id;
    public String Name;
    public String Description;
    public boolean IsVisited;

    public Place(int id, String name, String description, boolean isVisited) {
        Id = id;
        Name = name;
        Description = description;
        IsVisited = isVisited;
    }
}
