package swing;

import java.awt.*;
import javax.swing.*;

// Helper class that extends JFrame to include master configurations for clarity and simplicty purposes
public class Frame extends JFrame {
    public static Dimension srnDim = Toolkit.getDefaultToolkit().getScreenSize();   // screen dimensions
    public static Dimension frameMinDim = new Dimension(srnDim.width/2, srnDim.height - 100);
    public static Dimension subPanelDimension = new Dimension(srnDim.width/3, srnDim.height);
    public static ImageIcon HoMM_logo = new ImageIcon("./img/logo.png");    // HoMM logo

    public static JPanel loadDefault(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setMinimumSize(Frame.frameMinDim);  // sets window size to screan size
        frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH); // starts window as maximized
        frame.setLocationRelativeTo(null);

        return (JPanel) frame.getContentPane();
    }

    public static void addEmptySpace(JPanel panel) {
        panel.add(new JLabel(""));
    }

    public static JPanel createButtonsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1,20,20));
        panel.setMaximumSize(Frame.subPanelDimension);
        Frame.addEmptySpace(panel);
        return panel;
    }

    public static String html(String string) {
        return "<html>" + string + "</html>";
    }

    public static String html(String string, String htmlTag) {
        return "<html><" + htmlTag + ">" + string + "</" + htmlTag + "></html>";
    }
}