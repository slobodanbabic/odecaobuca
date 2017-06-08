package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import beans.OdecaTim4I;
import entities.KategorijaTim4;
import entities.KorisnikTim4;
import entities.PredmetTim4;

public class OglasiProfil {

	private JFrame frame;
	private ImageIcon avatar;
	private OdecaTim4I remoteEjb;
	private JComboBox<String> comboBoxKategorije;

	public OglasiProfil(KorisnikTim4 k) {
		initialize(k);
	}

	private void initialize(KorisnikTim4 k) {
		comboBoxKategorije = new JComboBox<>();
		try {
			remoteEjb = Remote.getRemote();
		} catch (NamingException ex) {
			String msg = ex.getClass().getName() + ":\n" + ex.getMessage();
			System.out.println(msg);
		}
		avatar = null;
		frame = new JFrame();
		frame.setBounds(100, 100, 981, 519);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel right = new JPanel();
		frame.getContentPane().add(right, BorderLayout.EAST);
		GridBagLayout gbl_right = new GridBagLayout();
		gbl_right.columnWidths = new int[] { 0, 0 };
		gbl_right.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_right.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_right.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		right.setLayout(gbl_right);

		JLabel lblAvatar = new JLabel();
		try {
			avatar = new ImageIcon(k.getAvatar());
		} catch (NullPointerException e) {
			avatar = new ImageIcon("avatar-default.png");
		}
		Image img = avatar.getImage();
		lblAvatar.setBounds(0, 0, 100, 100);
		img = img.getScaledInstance(lblAvatar.getWidth(), lblAvatar.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(img);
		lblAvatar.setIcon(icon);
		GridBagConstraints gbc_lblAvatar = new GridBagConstraints();
		gbc_lblAvatar.insets = new Insets(0, 0, 5, 0);
		gbc_lblAvatar.gridx = 0;
		gbc_lblAvatar.gridy = 0;
		right.add(lblAvatar, gbc_lblAvatar);

		JLabel lblFirstAndLastName = new JLabel(k.getIme() + " " + k.getPrezime());
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 0);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 1;
		right.add(lblFirstAndLastName, gbc_lblUsername);

		JComboBox comboBoxVelicine = new JComboBox();
		comboBoxVelicine.setVisible(true);
		GridBagConstraints gbc_comboBoxVelicine = new GridBagConstraints();
		gbc_comboBoxVelicine.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxVelicine.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxVelicine.gridx = 0;
		gbc_comboBoxVelicine.gridy = 4;
		right.add(comboBoxVelicine, gbc_comboBoxVelicine);

		JComboBox<String> comboBoxFilter = new JComboBox<>();
		comboBoxFilter.addItem("Filtriraj po velicini");
		comboBoxFilter.addItem("Filtriraj po boji");
		comboBoxFilter.addItem("Filtriraj po ceni");
		comboBoxFilter.addItem("Filtriraj po materijalu");
		comboBoxFilter.addItem("Filtriraj po marci");
		comboBoxFilter.setVisible(true);
		comboBoxFilter.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				comboBoxVelicine.setVisible(true);
				int tmp = comboBoxFilter.getSelectedIndex();
				comboBoxVelicine.removeAllItems();
				if (e.getStateChange() == ItemEvent.SELECTED) {
					switch (tmp) {
					case 0:
						List<String> velicine = remoteEjb.getSveUniqueVelicineByKategorija(
								KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString().toUpperCase()));
						for (String s : velicine) {
							comboBoxVelicine.addItem(s);
						}
						break;
					case 1:
						List<String> boje = remoteEjb.getSveUniqueBojeByKategorija(
								KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString().toUpperCase()));
						for (String s : boje) {
							comboBoxVelicine.addItem(s);
						}
						break;
					case 2:
						List<Integer> cene = remoteEjb.getSveUniqueCeneByKategorija(
								KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString().toUpperCase()));
						for (Integer i : cene) {
							comboBoxVelicine.addItem(i.toString());
						}
						break;
					case 3:
						List<String> materijal = remoteEjb.getSveUniqueMaterijalByKategorija(
								KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString().toUpperCase()));
						for (String s : materijal) {
							comboBoxVelicine.addItem(s);
						}
						break;
					case 4:
						List<String> marka = remoteEjb.getSveUniqueMarkalByKategorija(
								KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString().toUpperCase()));
						for (String s : marka) {
							comboBoxVelicine.addItem(s);
						}
						break;
					default:
						List<String> velicine1 = remoteEjb.getSveUniqueVelicineByKategorija(
								KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString().toUpperCase()));
						for (String s : velicine1) {
							comboBoxVelicine.addItem(s);
						}
						break;
					}
				}

			}
		});
		GridBagConstraints gbc_comboBoxFilter = new GridBagConstraints();
		gbc_comboBoxFilter.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxFilter.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFilter.gridx = 0;
		gbc_comboBoxFilter.gridy = 3;
		right.add(comboBoxFilter, gbc_comboBoxFilter);

		List<KategorijaTim4> kategorije = remoteEjb.getKategorije();
		for (KategorijaTim4 kategorija : kategorije) {
			comboBoxKategorije.addItem(kategorija.name());
		}
		comboBoxKategorije.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
				}
			}
		});
		GridBagConstraints gbc_comboBoxKategorije = new GridBagConstraints();
		gbc_comboBoxKategorije.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxKategorije.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxKategorije.gridx = 0;
		gbc_comboBoxKategorije.gridy = 2;
		right.add(comboBoxKategorije, gbc_comboBoxKategorije);

		JButton btnFIltriraj = new JButton("Filtriraj");
		btnFIltriraj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				int tmp = comboBoxFilter.getSelectedIndex();
				switch (tmp) {
				case 0:
					List<PredmetTim4> poVelicinama = remoteEjb.getByVelicina(
							comboBoxVelicine.getSelectedItem().toString(),
							KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString().toUpperCase()));

					for (PredmetTim4 p : poVelicinama) {
						System.out.println(p.toString());
					}
					break;
				case 1:
					List<PredmetTim4> poBojama = remoteEjb.getByBoja(comboBoxVelicine.getSelectedItem().toString(),
							KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString().toUpperCase()));
					for (PredmetTim4 p : poBojama) {
						System.out.println(p.toString());
					}
					break;
				case 2:
					List<PredmetTim4> poCeni = remoteEjb.getByCena(
							new Integer(comboBoxVelicine.getSelectedItem().toString()),
							KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString().toUpperCase()));
					for (PredmetTim4 p : poCeni) {
						System.out.println(p.toString());
					}
					break;
				case 3:
					List<PredmetTim4> poMaterijalu = remoteEjb.getByMaterijal(
							comboBoxVelicine.getSelectedItem().toString(),
							KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString().toUpperCase()));
					for (PredmetTim4 p : poMaterijalu) {
						System.out.println(p.toString());
					}
					break;

				case 4:
					List<PredmetTim4> poMarci = remoteEjb.getByMarka(comboBoxVelicine.getSelectedItem().toString(),
							KategorijaTim4.valueOf(comboBoxKategorije.getSelectedItem().toString().toUpperCase()));
					for (PredmetTim4 p : poMarci) {
						System.out.println(p.toString());
					}
					break;
				}
			}
		});
		GridBagConstraints gbc_btnFIltriraj = new GridBagConstraints();
		gbc_btnFIltriraj.insets = new Insets(0, 0, 5, 0);
		gbc_btnFIltriraj.gridx = 0;
		gbc_btnFIltriraj.gridy = 5;
		right.add(btnFIltriraj, gbc_btnFIltriraj);

		JList<String> list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 6;
		right.add(list, gbc_list);

		JButton btnOdjaviSe = new JButton("Odjavi se");
		GridBagConstraints gbc_btnOdjaviSe = new GridBagConstraints();
		gbc_btnOdjaviSe.gridx = 0;
		gbc_btnOdjaviSe.gridy = 8;
		right.add(btnOdjaviSe, gbc_btnOdjaviSe);

		JPanel mid = new JPanel();
		frame.getContentPane().add(mid, BorderLayout.CENTER);

		JPanel top = new JPanel();
		frame.getContentPane().add(top, BorderLayout.NORTH);

		JLabel lblOglasi = new JLabel("Oglasi");
		top.add(lblOglasi);
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}

}
