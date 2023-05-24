import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tower Defense Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Tworzenie JLabel z tłem
        JLabel backgroundLabel = new JLabel();
        ImageIcon backgroundImage = new ImageIcon("path/to/background.jpg");
        backgroundLabel.setIcon(backgroundImage);

        // Ustalenie rozmiaru i położenia JLabel tak, aby wypełniał całe tło
        backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        // Dodanie JLabel do JFrame
        frame.getContentPane().add(backgroundLabel);

        frame.setVisible(true);
    }
}
