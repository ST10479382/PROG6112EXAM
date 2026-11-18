package com.mycompany.question2;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// ==================== INTERFACE ====================
interface IProductCalculations {
double GetVat();
double GetCommission();
double GetTotal();
}

// ==================== CALCULATION CLASS ====================
class ProductCalculations implements IProductCalculations {

private double price;

public ProductCalculations(double price) {
this.price = price;
}

@Override
public double GetVat() {
return price * 0.15; // 15% VAT
}

@Override
public double GetCommission() {
return price * 0.05; // 5% Commission
}

@Override
public double GetTotal() {
return price + GetVat() + GetCommission();
}
}

// ==================== MAIN CLASS ====================
public class Question2 extends JFrame {

private JTextField txtCustomerName;
private JComboBox<String> cmbProduct;
private JTextField txtPrice;
private JButton btnProcess;

public Question2() {
setTitle("Product App");
setSize(420, 280);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);
setLayout(null);

// ===== MENU BAR =====
JMenuBar menuBar = new JMenuBar();

// File menu
JMenu menuFile = new JMenu("File");
JMenuItem itemExit = new JMenuItem("Exit");
itemExit.addActionListener(e -> System.exit(0));
menuFile.add(itemExit);

// Tools menu
JMenu menuTools = new JMenu("Tools");
JMenuItem itemProcess = new JMenuItem("Process");
JMenuItem itemClear = new JMenuItem("Clear");

itemProcess.addActionListener(e -> processSale());
itemClear.addActionListener(e -> clearFields());

menuTools.add(itemProcess);
menuTools.add(itemClear);

menuBar.add(menuFile);
menuBar.add(menuTools);
setJMenuBar(menuBar);

// ===== FORM COMPONENTS =====
JLabel lblCustomer = new JLabel("Customer Name:");
lblCustomer.setBounds(30, 30, 120, 25);
add(lblCustomer);

txtCustomerName = new JTextField();
txtCustomerName.setBounds(160, 30, 200, 25);
add(txtCustomerName);

JLabel lblProduct = new JLabel("Select Product:");
lblProduct.setBounds(30, 70, 120, 25);
add(lblProduct);

String[] products = {
"Cozy Nights Monarch",
"Restonic Barbados",
"Sealy Phuket"
};
cmbProduct = new JComboBox<>(products);
cmbProduct.setBounds(160, 70, 200, 25);
add(cmbProduct);

JLabel lblPrice = new JLabel("Enter Price:");
lblPrice.setBounds(30, 110, 120, 25);
add(lblPrice);

txtPrice = new JTextField();
txtPrice.setBounds(160, 110, 200, 25);
add(txtPrice);

btnProcess = new JButton("PROCESS");
btnProcess.setBounds(110, 160, 180, 35);
btnProcess.addActionListener(e -> processSale());
add(btnProcess);
}

// ===== PROCESS LOGIC =====
private void processSale() {
try {
String customerName = txtCustomerName.getText().trim();
String productName = (String) cmbProduct.getSelectedItem();
String priceText = txtPrice.getText().trim();

if (customerName.isEmpty() || priceText.isEmpty()) {
JOptionPane.showMessageDialog(this,
"Please enter Customer Name and Price.",
"Input Error",
JOptionPane.ERROR_MESSAGE);
return;
}

double price = Double.parseDouble(priceText);

ProductCalculations calc = new ProductCalculations(price);
double vat = calc.GetVat();
double commission = calc.GetCommission();
double total = calc.GetTotal();

String message = "Customer Name: " + customerName + "\n"
+ "Product: " + productName + "\n"
+ "VAT: R " + vat + "\n"
+ "COMMISSION: R " + commission + "\n"
+ "TOTAL: R " + total;

JOptionPane.showMessageDialog(this, message, "Message",
JOptionPane.INFORMATION_MESSAGE);

saveToFile(customerName, productName, vat, commission, total);

} catch (NumberFormatException ex) {
JOptionPane.showMessageDialog(this,
"Please enter a valid number for Price.",
"Input Error",
JOptionPane.ERROR_MESSAGE);
}
}

// ===== SAVE TO data.txt =====
private void saveToFile(String customer, String product,
double vat, double commission, double total) {
try (PrintWriter writer = new PrintWriter(new FileWriter("data.txt", true))) {
writer.println("DATA LOG");
writer.println("******************************");
writer.println("Customer Name: " + customer);
writer.println("Product: " + product);
writer.println("VAT: R " + vat);
writer.println("COMMISSION: R " + commission);
writer.println("TOTAL: R " + total);
writer.println("******************************");
writer.println();
} catch (IOException e) {
JOptionPane.showMessageDialog(this,
"Error saving to file: " + e.getMessage(),
"File Error",
JOptionPane.ERROR_MESSAGE);
}
}

// ===== CLEAR FIELDS =====
private void clearFields() {
txtCustomerName.setText("");
txtPrice.setText("");
cmbProduct.setSelectedIndex(0);
}

// ===== MAIN METHOD =====
public static void main(String[] args) {
SwingUtilities.invokeLater(() -> {
new Question2().setVisible(true);
});
}
}