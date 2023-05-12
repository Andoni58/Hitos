package Hito2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

    public class Ventana_principal extends JFrame implements ActionListener {
        // FRAME
        private JFrame frame1;
        private JComboBox<String> opciones;
        private JTextArea textos;

        public Ventana_principal() {
            JPanel mipanel = new JPanel();
            frame1 = new JFrame("Ficheros");
            frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            opciones = new JComboBox<>(new String[] {"Selecciona un documento", "Python.txt", "Java.txt", "C++.txt"});
            opciones.addActionListener(this);

            JButton borrar = new JButton("Borrar");
            borrar.addActionListener(e -> textos.setText(""));
            mipanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
            mipanel.add(opciones);
            mipanel.add(borrar);
            frame1.add(mipanel, BorderLayout.WEST);

            JPanel panel2 = new JPanel();
            panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
            textos = new JTextArea();
            textos.setEditable(false);
            textos.setPreferredSize(new Dimension(500, 500));
            panel2.add(textos);

            textos.setBorder(BorderFactory.createTitledBorder("texto"));
            JScrollPane scroll = new JScrollPane(textos, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            frame1.add(scroll);
            textos.setLineWrap(true);

            frame1.add(panel2, BorderLayout.EAST);
            JPanel cerrar = new JPanel();
            JButton botonCerrar = new JButton("Close");
            cerrar.add(botonCerrar);
            botonCerrar.addActionListener(this);
            frame1.add(cerrar, BorderLayout.SOUTH);
            frame1.pack();


            frame1.setVisible(true);
        }

        public void actionPerformed(ActionEvent c) {
            if (c.getActionCommand().equals("comboBoxChanged")) {
                String select = "C:/Variedad/Desktop/Hito2" + File.separator + opciones.getSelectedItem().toString();
                String src = opciones.getSelectedItem().toString();

                if (!src.equals("Selecciona un documento")) {
                    try {
                        textos.setText("");
                        File file = new File(select);
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        String leer;
                        while ((leer = reader.readLine()) != null) {
                            textos.append(leer + "\n");
                        }
                    } catch (FileNotFoundException b) {
                        textos.setText("");
                        JOptionPane.showMessageDialog(null, "Documento no encontrado");
                    } catch (IOException b) {
                        textos.setText("");
                        JOptionPane.showMessageDialog(null, "No se puede leer el documento");
                    }
                } else {
                    textos.setText("");
                }
            } else {
                frame1.dispose();
            }
        }
    }
