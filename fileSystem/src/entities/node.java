package entities;

public abstract class node{
    private String name;
    private folder parent;
    private Boolean isFile;

    public node(String name, folder parent, Boolean isFile){
        this.name=name;
        this.parent=parent;
        this.isFile=isFile;
    }

    public String getName(){
        return this.name;
    }

    public folder getParent(){
        return this.parent;
    }

    public Boolean isItFile(){
        return this.isFile;
    }
}