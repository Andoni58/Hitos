package Hito1;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;

class ventana extends JFrame {
    public ventana() {
        JFrame frame = new JFrame("Try yourself");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel emptyLabel = new JLabel ("");
        frame.add(emptyLabel, BorderLayout.NORTH);
        frame.pack();
        frame.setSize(900, 600);

        //paneles
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //los dos primeros checks
        frame.add(panel1, BorderLayout.NORTH);
        JCheckBox chec1 = new JCheckBox ("Katniss", true);
        JCheckBox chec2 = new JCheckBox ("Peeta");

        //las cuatro fotos
        panel2.setLayout(new GridLayout(2,2));
        frame.add(panel2, BorderLayout.CENTER);
        ImageIcon img1 = new ImageIcon("C:/Users/ik012982i9/Desktop/flores.jpg");
        JLabel label1 = new JLabel (img1);
        ImageIcon img2 = new ImageIcon("C:/Users/ik012982i9/Desktop/flores.jpg");
        JLabel label2 = new JLabel (img2);
        ImageIcon img3 = new ImageIcon("C:/Users/ik012982i9/Desktop/flores.jpg");
        JLabel label3 = new JLabel (img3);
        ImageIcon img4 = new ImageIcon("C:/Users/ik012982i9/Desktop/flores.jpg");
        JLabel label4 = new JLabel (img4);

        //radio
        frame.add(panel3, BorderLayout.EAST);
        String [] array = {"OPT 1", "OPT 2", "OPT 3"};

        ButtonGroup gruporadios = new ButtonGroup();
        GridBagConstraints posicion = new GridBagConstraints();
        panel3.setLayout(new GridBagLayout());

        JRadioButton radio1 = new JRadioButton (array[0], true);
        JRadioButton radio2 = new JRadioButton (array[1]);
        JRadioButton radio3 = new JRadioButton (array[2]);
        JRadioButton [] radio = {radio1, radio2, radio3};

        for (int i = 0; i<radio.length; i++) {
            posicion.gridx = 1;
            posicion.gridy = i;
            panel3.add(radio[i], posicion);
        }

        //botones
        frame.add(panel4, BorderLayout.SOUTH);

        JButton boton1 = new JButton ("But 1");
        JButton boton2 = new JButton ("But 2");

        //add
        panel1.add(chec1);
        panel1.add(chec2);

        panel2.add(label1);
        panel2.add(label2);
        panel2.add(label3);
        panel2.add(label4);

        gruporadios.add(radio1);
        gruporadios.add(radio2);
        gruporadios.add(radio3);

        panel4.add(boton1);
        panel4.add(boton2);

        frame.setVisible(true);
    }
}

class main {
    public static void main(String[] args) {
        ventana v1 = new ventana();
    }
}
