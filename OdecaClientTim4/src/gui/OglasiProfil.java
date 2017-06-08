package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JList;

public class OglasiProfil {

	private JFrame frame;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OglasiProfil window = new OglasiProfil();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public OglasiProfil() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 981, 519);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel right = new JPanel();
		frame.getContentPane().add(right, BorderLayout.EAST);
		GridBagLayout gbl_right = new GridBagLayout();
		gbl_right.columnWidths = new int[]{0, 0};
		gbl_right.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_right.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_right.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		right.setLayout(gbl_right);
		
		JLabel lblAvatar = new JLabel("avatar");
		GridBagConstraints gbc_lblAvatar = new GridBagConstraints();
		gbc_lblAvatar.insets = new Insets(0, 0, 5, 0);
		gbc_lblAvatar.gridx = 0;
		gbc_lblAvatar.gridy = 0;
		right.add(lblAvatar, gbc_lblAvatar);
		
		JLabel lblUsername = new JLabel("username");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 0);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 1;
		right.add(lblUsername, gbc_lblUsername);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 2;
		right.add(comboBox, gbc_comboBox);
		
		JList<? extends E> list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 3;
		right.add(list, gbc_list);
		
		JButton btnOdjaviSe = new JButton("Odjavi se");
		GridBagConstraints gbc_btnOdjaviSe = new GridBagConstraints();
		gbc_btnOdjaviSe.gridx = 0;
		gbc_btnOdjaviSe.gridy = 4;
		right.add(btnOdjaviSe, gbc_btnOdjaviSe);
		
		JPanel mid = new JPanel();
		frame.getContentPane().add(mid, BorderLayout.CENTER);
		
		JPanel top = new JPanel();
		frame.getContentPane().add(top, BorderLayout.NORTH);
		
		JLabel lblOglasi = new JLabel("Oglasi");
		top.add(lblOglasi);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}

}
