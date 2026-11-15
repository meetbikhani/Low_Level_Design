import services.*;

public class filesystemDemo {
    public static void main(String[] args) {
        filesystem fileSystem = new filesystem();

        shell shell = new shell(fileSystem);

        shell.start();
    }

}
