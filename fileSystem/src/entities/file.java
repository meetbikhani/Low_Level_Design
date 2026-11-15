package entities;

public class file extends node{

    private String content;
    
    public file(String name, folder parent, Boolean isFile) {
        super(name, parent,isFile);
        this.content="";
    }

    public String read() {
        return content;
    }

    public void write(String content) {
        this.content = content;
    }
}