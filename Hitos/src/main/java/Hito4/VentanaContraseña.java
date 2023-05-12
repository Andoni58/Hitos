package Hito4;

import javax.swing.*;
public class VentanaContraseña {
    VentanaContraseña(){
        // contraseña
        String contraseña= "damocles";

        // panel para ingresar
        JOptionPane panel = new JOptionPane("Input password");
        String contraseñaIngresada = JOptionPane.showInputDialog("Ingrese su contraseña:");

        // if para valorar si la contraseña es correcta
        if (contraseña.equals(contraseñaIngresada)){
           VentanaPrincipal vp = new VentanaPrincipal();
        } else {
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
            System.exit(0);
        }
    }
}