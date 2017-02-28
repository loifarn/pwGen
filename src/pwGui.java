import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

public class pwGui {
    public JTextPane pwDisplay;
    private JPanel pwGuiPanel;
    private JCheckBox pwNumCheck;
    private JCheckBox pwScharCheck;
    private JButton pwGenButton;
    private JTextField pwLengthField;
    private JLabel pwNumLabel;
    private JLabel pwsScharLabel;

    private static final String specialCharacters[] = {
            "@", "~", "/", "+", "-", "*", "(", "=",
            "%", "&", "$", "£", ")", "{", "}", "!", ".", ",", "?"};
    private static final String letters[] = {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z" };
    private static final int specialCharactersCount = 19;
    private static final int letterCount = 26;
    private boolean pwNum;
    private boolean pwSchar;
    private String password = "0";

    private static int randomGen() {
        int max = 9;
        int min = 0;
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public pwGui() {
        JFrame f = new JFrame();
        f.add(pwGuiPanel);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        eventManager();
    }

    private void genPassword(String length, boolean numbers, boolean sChars) {
        password = "";
        pwDisplay.setText(null);

        while (password.length() < Integer.parseInt(length)) {
            if ((int)(Math.random()*9) < 4) {
                password = password+letters[(int)(Math.random()*letterCount)];
            }
            if ((int)(Math.random()*9) < 4) {
                password = password+letters[(int)(Math.random()*letterCount)].toUpperCase();
            }
            if (numbers && (int)(Math.random()*9) < 4) {
                password = password+Integer.toString(randomGen());
            }
            if (sChars && randomGen() < 1) {
                password = password+specialCharacters[(int)(Math.random()*specialCharactersCount)];
            }
        }
        System.out.println(""+password);
        pwDisplay.setText(password);
    }

    private void eventManager() {
        pwLengthField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() >='0' && e.getKeyChar() <='9' && pwLengthField.getText().length() < 2) {
                    //Dance, dance baby.
                } else {
                    e.consume();
                }
            }
        });

        pwGenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pwNumCheck.isSelected()) { pwNum = true; }
                else { pwNum = false; }

                if (pwScharCheck.isSelected()) { pwSchar = true; }
                else { pwSchar = false; }
                if (pwLengthField.getText() != null) {
                    try {
                        genPassword(pwLengthField.getText(), pwNum, pwSchar);
                    } catch (NumberFormatException er) {
                        pwDisplay.setText("ENTER LENGTH");
                    }
                }
            }
        });
    }

}