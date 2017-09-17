import edu.duke.*;

public class HelloWorld {
    public void runHello () {
        FileResource res = new FileResource("GRch38dnapart.fa.txt");
        for( String line : res.lines()) {
            System.out.println(line);
        }
    }
}