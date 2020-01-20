import java.awt.BorderLayout;

import javax.swing.*;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Window.Type;
import javax.swing.GroupLayout.Alignment;

//Currency Converter by Kalvin Kao

public class ConverterGUI extends Functions {
	private static JTextField txtOne, txtTwo;
	static String Currency[] = { "Canadian Dollar", "United States Dollar", "Pound Sterling", "Chinese Yuan",
			"Bitcoin" };
	static int conFrom = 0;
	static int conTo = 1;
	static double money1 = 1.00;
	static double money2;
	static double convertedResult = 0;

	public static void main(String[] args) {
		// Creating the Frame
		JFrame frameConverter = new JFrame("CCFrame");
		frameConverter.setResizable(false);
		frameConverter.setVisible(true);
		frameConverter.setTitle("Currency Converter by Kalvin Kao");
		frameConverter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameConverter.setSize(523, 518);
		frameConverter.getContentPane().setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(20, 0, 463, 68);
		frameConverter.getContentPane().add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel lblConvertingFrom = new JLabel("CONVERTING FROM");
		lblConvertingFrom.setHorizontalAlignment(SwingConstants.CENTER);
		lblConvertingFrom.setForeground(Color.BLACK);
		lblConvertingFrom.setFont(new Font("Arial", Font.BOLD, 18));
		panel_2.add(lblConvertingFrom);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 104, 463, 68);
		frameConverter.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		// TEXT ONE
		txtOne = new JTextField();
		txtOne.setBounds(0, 0, 231, 59);
		panel_1.add(txtOne);
		txtOne.setText("1.00");
		txtOne.setHorizontalAlignment(SwingConstants.CENTER);
		txtOne.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtOne.setColumns(10);

		JComboBox convertingFrom = new JComboBox();
		convertingFrom.setBounds(232, 0, 231, 59);
		panel_1.add(convertingFrom);

		JLabel lblConvertingTo = new JLabel("CONVERTING TO");
		lblConvertingTo.setBounds(0, 183, 507, 60);
		frameConverter.getContentPane().add(lblConvertingTo);
		lblConvertingTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblConvertingTo.setForeground(Color.BLACK);
		lblConvertingTo.setFont(new Font("Arial", Font.BOLD, 18));

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(20, 276, 463, 68);
		frameConverter.getContentPane().add(panel);

		JComboBox convertingTo = new JComboBox();
		convertingTo.setBounds(232, 0, 231, 59);
		panel.add(convertingTo);

		// Iterating through the array for currency
		for (int i = 0; i < Currency.length; i++) {
			convertingTo.addItem(Currency[i]);
			convertingFrom.addItem(Currency[i]);
		}

		// Selected index to USD
		convertingTo.setSelectedIndex(1);

		// TEXT TWO
		txtTwo = new JTextField();
		txtTwo.setEditable(false);
		txtTwo.setBounds(0, 0, 231, 59);
		panel.add(txtTwo);
		txtTwo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTwo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTwo.setText("$1.00");
		txtTwo.setColumns(10);

		// Convert button
		JButton convertBTN = new JButton("CONVERT");
		convertBTN.setBounds(20, 355, 463, 124);
		frameConverter.getContentPane().add(convertBTN);
		convertBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				money1 = Double.parseDouble(txtOne.getText());
				conFrom = convertingFrom.getSelectedIndex();
				conTo = convertingTo.getSelectedIndex();
				try {
					convertedResult = Functions.convertTo(conFrom, conTo, money1);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				txtTwo.setText(Functions.finalCurrencySign + String.format("%.2f", convertedResult));

			}
		});
		convertBTN.setFont(new Font("Arial", Font.BOLD, 30));
	}
}
