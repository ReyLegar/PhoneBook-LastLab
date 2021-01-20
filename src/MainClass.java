import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class MainClass {
    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });

    }
}
