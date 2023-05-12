package Hito4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class VentanaPrincipal extends JFrame implements ActionListener {
    private JFrame frame;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JButton boton;
    private JComboBox<String> opciones;
    private JLabel fotos;
    private JTextField texto;
    private JCheckBox check;
    private ImageIcon foto;

    VentanaPrincipal() {
        // frame
        frame = new JFrame("Hito 4");
        frame.setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(null, "¡Adios!");
                dispose();
            }
        });

        // paneles
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();

        // aplicacion
        opciones = new JComboBox<>();
        opciones.setPreferredSize(new Dimension(100, 25));
        opciones.setMaximumSize(new Dimension(200, opciones.getPreferredSize().height));

        opciones.addItem("foto1");
        opciones.addItem("foto2");
        opciones.addItem("foto3");
        opciones.addActionListener(this);

        check = new JCheckBox("Save your comment");
        check.setSelected(true);

        texto = new JTextField(10);

        foto= new ImageIcon();
        fotos = new JLabel(foto);
        boton = new JButton("SAVE");
        boton.addActionListener(this);
        panel1.setLayout(new BorderLayout());

        // add
        frame.add(panel1);

        panel1.add(panel2, BorderLayout.EAST);
        panel1.add(panel3, BorderLayout.SOUTH);
        panel1.add(opciones, BorderLayout.NORTH);
        panel1.add(fotos, BorderLayout.CENTER);

        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel2.add(check);
        panel2.add(texto);

        panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel3.add(boton);

        frame.setVisible(true);
    }

    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == boton) {
            String imagen = (String) opciones.getSelectedItem();
            try {
                FileWriter leer = new FileWriter("src/main/java/Hito4/coment/" + imagen + ".txt", true);
                BufferedWriter escribir = new BufferedWriter(leer);
                if (check.isSelected()) {
                    escribir.write(imagen + ": " + texto.getText());
                } else {
                    escribir.write(imagen);
                }
                escribir.close();
                leer.close();

            } catch (IOException ex) {
                System.err.println("No se puede escribir en el archivo: " + ex.getMessage());
            }
        }
        if (e.getSource() == opciones) {
            String opcion = (String) opciones.getSelectedItem();
            switch (opcion) {
                case "foto1":
                    foto = new ImageIcon("src/main/java/Hito4/Fotos/foto1.jpg");
                    break;
                case "foto2":
                    foto = new ImageIcon("src/main/java/Hito4/Fotos/foto2.jpg");
                    break;
                case "foto3":
                    foto = new ImageIcon("src/main/java/Hito4/Fotos/foto3.jpg");
                    break;
            }
            Image img = foto.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
            foto = new ImageIcon(img);
            fotos.setIcon(foto);
        }
    }
}