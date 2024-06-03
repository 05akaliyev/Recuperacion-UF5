// CashView.java
package view;

import javax.swing.JDialog;

import main.Shop;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CashView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField cashField;
	private Object shop;
	
	/**
	 * Create the dialog
	 */
	public CashView(Shop shop, ShopView shopView) {
		setTitle("Cash view");
		setBounds(100, 100, 400, 250);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cash shop:");
		lblNewLabel.setBounds(50, 50, 80, 20);
		getContentPane().add(lblNewLabel);
		
		cashField = new JTextField();
		cashField.setBounds(100, 100, 200, 50);
		cashField.setEditable(false);
		cashField.setText(shop.showCash());
		getContentPane().add(cashField);
		cashField.setColumns(10);
		//TODO block main frame

		/*
		 * this.addWindowListener(new WindowAdapter() { public void
		 * windowClosed(WindowEvent e) { shopView.unlockButtons(); } });
		 */
	}
}
