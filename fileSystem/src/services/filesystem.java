package services;

import java.util.Collection;

import entities.file;
import entities.folder;
import entities.node;

public class filesystem {
    private folder root;
    private folder currentDirectory;

    public filesystem(){
        root = new folder("", null,false);
        currentDirectory = root;
    }

    //mkidr
    public void mkdir(String name){
        folder newFolder = new folder(name, currentDirectory, false);
        currentDirectory.addChild(newFolder);
    }
    
    //cd
    public void cd(String name){
        if(currentDirectory.contains(name) && !currentDirectory.isItFile()){
            currentDirectory = (folder) currentDirectory.getChild(name);
        }
        else{
       System.out.println("directory not found");     
        }
    }
    //ls
    public void ls(){
        Collection<node> children = currentDirectory.getChildren();

        for (node child : children) {
            System.out.println(child.getName());
        }
    }
    //pwd
    public void pwd(){
        StringBuilder path = new StringBuilder();

        folder current = currentDirectory;

        while (current != null && current.getParent() != null) {

            path.insert(0, "/" + current.getName());

            current = current.getParent();
        }

        if(path.length() == 0){
            System.out.println("/");
         }else{
            System.out.println(path.toString());
         }
    }
    //touch
    public void touch(String name){
        file newFile = new file(name, currentDirectory, true);
        currentDirectory.addChild(newFile);
    }
}