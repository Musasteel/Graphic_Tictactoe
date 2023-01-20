import javax.swing.*;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 * @author Shaoor Baig
 * @version 1.0
 */

public class ButtonFactory {
    public static JButton createButton(String iconPath, String hoverIconPath, Boolean isSoundHover, ActionListener actionListener) {
    ImageIcon icon = new ImageIcon(iconPath);
    ImageIcon hoverIcon = new ImageIcon(hoverIconPath);
    JButton button = new JButton();
    button.setIcon(icon);
    button.setOpaque(false);
    button.setContentAreaFilled(false);
    button.setBorderPainted(false);
    button.setFocusPainted(false);
    button.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            SoundPlayer soundPlayer = new SoundPlayer();
            if (isSoundHover) {
                soundPlayer.playSound("audio/defaulthover.wav");
            }
            button.setIcon(hoverIcon);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            button.setIcon(icon);
        }
    });
    button.addActionListener(actionListener);
    return button;
    }
}