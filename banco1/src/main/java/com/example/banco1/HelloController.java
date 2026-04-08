package com.example.banco1;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class HelloController {

    private final String USUARIO_CORRECTO = "admin";
    private final String PASSWORD_CORRECTA = "1234";


    private double saldo = 0.0;


    @FXML private VBox panelLogin;
    @FXML private VBox panelBanco;
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblMensajeLogin;

    @FXML private Label lblSaldo;
    @FXML private Label lblMensajeBanco;
    @FXML private TextField txtMonto;

    @FXML
    public void initialize() {

        panelLogin.setVisible(true);
        panelLogin.setManaged(true);

        panelBanco.setVisible(false);
        panelBanco.setManaged(false);
    }


    @FXML
    protected void onLoginClick() {
        String usuario = txtUsuario.getText();
        String password = txtPassword.getText();


        if (usuario.equals(USUARIO_CORRECTO) && password.equals(PASSWORD_CORRECTA)) {
            panelLogin.setVisible(false);
            panelLogin.setManaged(false);

            panelBanco.setVisible(true);
            panelBanco.setManaged(true);

            txtUsuario.clear();
            txtPassword.clear();
            lblMensajeLogin.setText("");

            lblMensajeBanco.setStyle("-fx-text-fill: #2c3e50;");
            lblMensajeBanco.setText("Bienvenido al sistema");
        } else {
            lblMensajeLogin.setText("Usuario o contraseña incorrectos. Intenta otra vez");
        }
    }

    @FXML
    protected void onDepositarClick() {
        try {

            double monto = Double.parseDouble(txtMonto.getText());
            if (monto > 0) {
                saldo += monto;
                lblMensajeBanco.setStyle("-fx-text-fill: #27ae60;"); // Letra verde
                lblMensajeBanco.setText("Deposito exitoso de $" + String.format("%.2f", monto));
                txtMonto.clear();
                actualizarSaldo();
            } else {
                mostrarErrorBanco("El monto debe ser mayor a 0");
            }
        } catch (NumberFormatException e) {
            mostrarErrorBanco("ingresa un numero valido");
        }
    }

    @FXML
    protected void onRetirarClick() {
        try {
            double monto = Double.parseDouble(txtMonto.getText());
            if (monto > 0 && monto <= saldo) {
                saldo -= monto;
                lblMensajeBanco.setStyle("-fx-text-fill: #27ae60;");
                lblMensajeBanco.setText("Retiro exitoso de $" + String.format("%.2f", monto));
                txtMonto.clear();
                actualizarSaldo();
            } else if (monto > saldo) {
                mostrarErrorBanco("Fondos insuficientes");
            } else {
                mostrarErrorBanco("El monto debe ser mayor a 0.");
            }
        } catch (NumberFormatException e) {
            mostrarErrorBanco("ingresa un numero valido ");
        }
    }

    @FXML
    protected void onConsultarClick() {
        actualizarSaldo();
        lblMensajeBanco.setStyle("-fx-text-fill: #2980b9;");
        lblMensajeBanco.setText("Consulta realizada correctamente");
    }

    @FXML
    protected void onSalirClick() {
        Platform.exit();
    }


    private void actualizarSaldo() {
        lblSaldo.setText("Saldo actual: $" + String.format("%.2f", saldo));
    }

    private void mostrarErrorBanco(String mensaje) {
        lblMensajeBanco.setStyle("-fx-text-fill: #e74c3c;"); // Letra roja
        lblMensajeBanco.setText(mensaje);
    }
}