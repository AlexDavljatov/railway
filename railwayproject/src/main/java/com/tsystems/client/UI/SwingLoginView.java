package com.tsystems.client.UI;

import javax.swing.*;

/**
 * Hello world!
 */
public class SwingLoginView {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Railway app");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //;
        JPanel userPanel = new JPanel();
        JPanel passPanel = new JPanel();
        userPanel.setSize(200, 100);
        userPanel.setBorder(BorderFactory.createTitledBorder("Username"));
        //JEditorPane uname = new JEditorPane();
        JTextField uname = new JTextField("Username");
        uname.setSize(100, 50);
        userPanel.add(uname);
        //userPanel.
        passPanel.setSize(200, 100);
        passPanel.setBorder(BorderFactory.createTitledBorder("Password"));
        JPasswordField pword = new JPasswordField("Password");
        pword.setSize(100, 50);
        passPanel.add(pword);
        //passPanel.
        frame.getContentPane().add(userPanel);
        frame.getContentPane().add(passPanel);
        //frame.pack(); //обтекающие размеры
        frame.setVisible(true);

        /**
         JFrame frame = new JFrame("Current IP");
         frame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
         System.exit(0);
         }
         });
         String IP = "";
         try {
         IP = InetAddress.getLocalHost().getHostAddress();
         } catch (Exception e) {
         IP = "Error finding IP";
         }
         //Создается панель,
         //которая будет содржать информацию о IP адресе
         JPanel panel = new JPanel();
         //добавление границы к панели
         panel.setBorder(BorderFactory.createTitledBorder("Current IP Address"));
         panel.add(new JLabel(" " + IP + " "));
         //Добавление панели к фрейму
         frame.getContentPane().add(panel);
         //метод рack(); сообщает Swing о том,
         //что нужно придать компонентам необходимые размеры для
         //правильного помещения их в форму.
         //Другой способ - вызвать setSize(int width, int height).
         frame.pack();
         //Для того, чтобы увидеть окно на экране
         //вы должны вызвать метод setVisible(true)
         frame.setVisible(true);
         */
    }
}
