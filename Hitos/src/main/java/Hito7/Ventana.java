package Hito7;

import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

public class Ventana implements ActionListener {
    JXDatePicker fecha;
    JFrame frame;
    JPanel panel1;
    JPanel panelmatrizc;
    JPanel panelfecha;
    JComboBox<ArrayList<String>> matrizC;
    JList lista;
    JLabel label;

    Fotografos fotografos;
    Imagenes imagenes;
    Conexion bd;

    public Ventana() {
        bd = new Conexion();
        fotografos = new Fotografos();
        imagenes = new Imagenes();
        fotografos.tenerNombres();

        //frame
        frame = new JFrame();
        frame.setSize(600, 800);

        // paneles
        panel1 = new JPanel(new GridLayout(2, 2));
        fecha = new JXDatePicker();
        panelmatrizc = new JPanel();
        panelmatrizc.setPreferredSize(new Dimension(350, 400));
        panelfecha = new JPanel();
        panelfecha.setPreferredSize(new Dimension(350, 400));
        fecha.setPreferredSize(new Dimension(200, 30));
        matrizC = new JComboBox(fotografos.getMatriz().toArray(new Object[0]));
        matrizC.setPreferredSize(new Dimension(200, 30));
        label = new JLabel();
        lista = new JList<>();
        Date[] fecha = new Date[]{new Date()};

        // add
        panel1.add(panelmatrizc);
        panel1.add(panelfecha);
        panel1.add(label);
        panel1.add(lista);
        panelfecha.add(this.fecha);
        panelmatrizc.add(matrizC);
        frame.add(panel1);

        this.fecha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Ventana.this.fecha.getDate()!=null){
                    fecha[0] = Ventana.this.fecha.getDate();
                }
            }
        });

        lista.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList<String> list = (JList<String>) evt.getSource();
                String fotografo = (String) matrizC.getSelectedItem();
                String extension = ".jpg";
                String url = "src/main/java/Hito6/";
                String selectedTitle = (String) list.getSelectedValue();


                if (evt.getClickCount() == 2 ) {
                    System.out.println(fecha[0]);
                    imagenes.arch = bd.tenerArchivos(fotografo);
                    bd.Mas(selectedTitle);
                    for (String a : imagenes.arch) {
                        System.out.println(a);
                        if (a.equals(selectedTitle + extension)) {
                            url = url + a;
                            ImageIcon imagen = new ImageIcon(url);
                            Image img = imagen.getImage();
                            Image newImg = img.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                            ImageIcon newIcon = new ImageIcon(newImg);
                            label.setIcon(newIcon);
                        }
                    }
                }
            }
        });

        matrizC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pMatrizC = (String) matrizC.getSelectedItem();

                if (Ventana.this.fecha.getDate() == null) {
                    DefaultListModel<String> model = new DefaultListModel<>();
                    imagenes.titu = bd.tenerTitulos(pMatrizC);
                    for (String titulo : imagenes.titu) {
                        model.addElement(titulo);
                    }
                    lista.setModel(model);
                } else {
                    DefaultListModel<String> model2 = new DefaultListModel<>();
                    System.out.println(Ventana.this.fecha.getDate());
                    imagenes.titu= bd.tener(pMatrizC, Ventana.this.fecha.getDate());
                    model2=new DefaultListModel<>();
                    for (String titulo : imagenes.titu) {
                        model2.addElement(titulo);
                    }
                    lista.setModel(model2);
                }
            }
        });
        frame.setVisible(true);
    }
    // obliga
    public void actionPerformed(ActionEvent e) {}
}