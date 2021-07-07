import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;

public class ParaqitjeGrafike {

	private Lojtar l1, l2;
	private Gur[][] gur = new Gur[8][18];
	private boolean luanLojtari1 = true;
	private int numriLevizjeve = 0, numriGureveNeLevizjen1;
	private JLabel pike1 = new JLabel("0 pikë"), pike2 = new JLabel("0 pikë"),
			mesazh = new JLabel();
	private JButton b1 = new JButton(), b2 = new JButton();

	public ParaqitjeGrafike(JFrame frame) {

		UIManager.put("OptionPane.background", Color.white);
		UIManager.put("Panel.background", Color.white);
		UIManager.put("Button.focus",
				new ColorUIResource(new Color(0, 0, 0, 0)));
		UIManager.put("Button.background", Color.WHITE);
		UIManager.put("Button.background", Color.WHITE);

		String emriLojtarit1 = JOptionPane.showInputDialog(null,
				"Vendos emrin: ", "Lojtari 1", JOptionPane.PLAIN_MESSAGE);
		String emriLojtarit2 = JOptionPane.showInputDialog(null,
				"Vendos emrin: ", "Lojtari 2", JOptionPane.PLAIN_MESSAGE);

		l1 = new Lojtar(emriLojtarit1);
		l2 = new Lojtar(emriLojtarit2);

		String[] lojtaret = { l1.getEmri(), l2.getEmri() };

		String lojtari = l1.getEmri();
		try {
			lojtari = JOptionPane.showInputDialog(null, "Përcakto lojtarin",
					"Kush do të bëjë lëvizjen e parë?",
					JOptionPane.PLAIN_MESSAGE, null, lojtaret, emriLojtarit1)
					.toString();
		} catch (Exception e) {
		}
		if (lojtari.equals(l2.getEmri())) {
			numriLevizjeve = 2;
			luanLojtari1 = false;
		}

		JPanel panelGuresh = krijoGur(frame);

		JPanel panelKryesor = new JPanel();
		panelKryesor.setBorder(new LineBorder(Color.WHITE, 40));

		panelKryesor.add(panelGuresh);

		JPanel panelMesazhesh = new JPanel(new GridLayout(2, 2, 15, 15));
		panelMesazhesh.setBorder(new LineBorder(Color.WHITE, 50));

		JPanel panelMbajtesMesazhesh = new JPanel(new FlowLayout(
				FlowLayout.LEFT));

		Font font = new Font("Sans", Font.PLAIN, 20);

		b1.setFont(font);
		b2.setFont(font);
		b1.setText(l1.getEmri());
		b2.setText(l2.getEmri());
		pike1.setFont(font);
		pike2.setFont(font);
		mesazh.setFont(font);

		b1.setBorder(null);
		b1.setBackground(Color.CYAN);
		b2.setBorder(null);
		b2.setBackground(Color.WHITE);

		if (lojtari.equals(l2.getEmri())) {
			b1.setBackground(Color.WHITE);
			b2.setBackground(Color.CYAN);
		}

		b1.setPreferredSize(new Dimension(100, 30));
		panelMesazhesh.add(b1);
		panelMesazhesh.add(pike1);
		panelMesazhesh.add(b2);
		panelMesazhesh.add(pike2);

		frame.add(panelKryesor, BorderLayout.NORTH);
		panelMbajtesMesazhesh.add(panelMesazhesh);
		panelMbajtesMesazhesh.add(mesazh);

		frame.add(panelMbajtesMesazhesh, BorderLayout.SOUTH);

	}

	public int gjejNumrinEGureve(int rreshti) {
		int sasia = 0;
		if (rreshti < 0 || rreshti > gur.length - 1) {
			return 0;
		}
		for (int i = 0; i < gur.length; i++) {
			if (!gur[rreshti][i].getNgjyra().equals(Color.WHITE)) {
				sasia++;
			}
		}
		return sasia;
	}

	public int qelizaEPareBosh(int rreshti) {
		if (rreshti < 0 || rreshti > gur.length - 1) {
			return 0;
		}
		int n = 6;
		if (rreshti == 0 || rreshti == 7) {
			n = 18;
		}
		for (int j = 0; j < n; j++) {
			if (gur[rreshti][j].getNgjyra().equals(Color.WHITE)) {
				return j;
			}
		}
		return -1;
	}

	private JPanel krijoGur(JFrame frame) {
		JPanel panelGuresh = new JPanel(new GridLayout(8, 18, 5, 5));

		for (int i = 0; i < gur.length; i++) {
			for (int j = 0; j < gur[i].length; j++) {
				if (i == 0 || (j == 0 && i != gur.length - 1)) {
					if (j < 6) {
						gur[i][j] = new Gur(Color.RED, i, j);
						Gur g = gur[i][j];
						gur[i][j].addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (luanLojtari1) {
									int numriHapave = 1;
									if (numriLevizjeve % 4 == 1) {
										numriHapave = numriGureveNeLevizjen1;
									}
									int x = g.getKoordinataX() + numriHapave;
									int numriGureve = numriGureveNeLevizjen1;
									if (x >= 7) {
										x = 7;
										numriHapave = 1;
										numriGureveNeLevizjen1 = 1;
									}
									else{
										numriGureveNeLevizjen1 = gjejNumrinEGureve(x);
									}

									int y = qelizaEPareBosh(x);
									if (y == -1) {
										numriGureveNeLevizjen1 = numriGureve;
										JOptionPane
												.showMessageDialog(
														null,
														"Levizja eshte e pamundur! Leviz nje gur tjeter!",
														"Gabim",
														JOptionPane.PLAIN_MESSAGE);
									} else {
										l1.levizGur(g, gur[x][y], gur);
										if (numriLevizjeve % 4 == 1) {
											luanLojtari1 = false;
											b1.setBackground(Color.WHITE);
											b2.setBackground(Color.CYAN);
										}
										numriLevizjeve++;
									}
									panelGuresh.removeAll();
									for (int i = 0; i < gur.length; i++) {
										for (int j = 0; j < gur[i].length; j++) {
											panelGuresh.add(gur[i][j]);
										}
									}
									pike1.setText(llogaritPike(Color.RED) + " pikë");

									if(lojaKaPerfunduar()){
										for (int i = 0; i < gur.length; i++) {
											for (int j = 0; j < gur[i].length; j++) {
												gur[i][j].setEnabled(false);
												if(gur[i][j].getNgjyra().equals(Color.BLACK) ||
														gur[i][j].getNgjyra().equals(Color.RED)){
													gur[i][j].removeActionListener(gur[i][j].getActionListeners()[0]);
												}
											}
										}
										String emri = l1.getEmri();
										if(llogaritPike(Color.BLACK) > llogaritPike(Color.RED)){
											emri = l2.getEmri();
										}
										mesazh.setText("Loja perfundoi! Fituesi është " + emri + ".");
										if(llogaritPike(Color.BLACK) == llogaritPike(Color.RED)){
											mesazh.setText("Loja perfundoi pa fitues.");
										}
									}

									frame.setVisible(true);
								}
							}
						});
					} else {
						gur[i][j] = new Gur(i, j);
					}
				} else if (i == gur.length - 1 || j == 5) {
					if (j < 6) {
						gur[i][j] = new Gur(Color.BLACK, i, j);
						Gur g = gur[i][j];
						gur[i][j].addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (!luanLojtari1) {
									int numriHapave = 1;
									if (numriLevizjeve % 4 == 3) {
										numriHapave = numriGureveNeLevizjen1;
									}

									int x = g.getKoordinataX() - numriHapave;
									int numriGureve = numriGureveNeLevizjen1;

									if (x <= 0) {
										x = 0;
										numriHapave = 1;
										numriGureveNeLevizjen1 = 1;
									}
									else{
										numriGureveNeLevizjen1 = gjejNumrinEGureve(x);
									}
									int y = qelizaEPareBosh(x);
									if (y == -1) {
										numriGureveNeLevizjen1 = numriGureve;
										JOptionPane
												.showMessageDialog(null,
														"Levizja eshte e pamundur! Leviz nje gur tjeter!");
									} else {
										l2.levizGur(g, gur[x][y], gur);
										if (numriLevizjeve % 4 == 3) {
											luanLojtari1 = true;
											b1.setBackground(Color.CYAN);
											b2.setBackground(Color.WHITE);
										}
										numriLevizjeve++;
									}
									panelGuresh.removeAll();
									for (int i = 0; i < gur.length; i++) {
										for (int j = 0; j < gur[i].length; j++) {
											panelGuresh.add(gur[i][j]);
										}
									}
									pike2.setText(llogaritPike(Color.BLACK) + " pikë");

									if(lojaKaPerfunduar()){
										for (int i = 0; i < gur.length; i++) {
											for (int j = 0; j < gur[i].length; j++) {
												gur[i][j].setEnabled(false);
												if(gur[i][j].getNgjyra().equals(Color.BLACK) ||
														gur[i][j].getNgjyra().equals(Color.RED)){
													gur[i][j].removeActionListener(gur[i][j].getActionListeners()[0]);
												}
											}
										}
										String emri = l1.getEmri();
										if(llogaritPike(Color.BLACK) > llogaritPike(Color.RED)){
											emri = l2.getEmri();
										}
										mesazh.setText("Loja perfundoi! Fituesi është " + emri + ".");
										if(llogaritPike(Color.BLACK) == llogaritPike(Color.RED)){
											mesazh.setText("Loja perfundoi pa fitues.");
										}
									}

									frame.setVisible(true);
								}
							}
						});
					} else {
						gur[i][j] = new Gur(i, j);
					}
				} else if (j > 5) {
					gur[i][j] = new Gur(i, j);
					gur[i][j].setBorder(null);
					gur[i][j].setEnabled(false);
				} else {
					gur[i][j] = new Gur(i, j);
					gur[i][j].setEnabled(false);
				}
				panelGuresh.add(gur[i][j]);

			}
		}
		return panelGuresh;

	}

	public boolean lojaKaPerfunduar(){
		int sasiaTeZi = 0, sasiaTeKuq = 0;
		for(int i = 0; i < gur.length; i++){
			for(int j = 0; j < gur.length; j++){
				if(gur[i][j].getNgjyra().equals(Color.BLACK)){
					sasiaTeZi++;
				}
				else if(gur[i][j].getNgjyra().equals(Color.RED)){
					sasiaTeKuq++;
				}
				if(sasiaTeZi == 12 && sasiaTeKuq == 0){
					for(; j < gur.length; j++){
						if(gur[i][j].getNgjyra().equals(Color.RED)){
							return false;
						}
					}
					return true;
				}
				else if(sasiaTeZi != 0 && sasiaTeKuq != 0){
					return false;
				}
			}
		}
		return false;
	}

	public int llogaritPike(Color ngjyra) {
		int sasiaPikeve = 0;
		for(int i = 0; i < gur.length; i++){
			for(int j = 0; j < gur.length; j++){
				if(gur[i][j].getNgjyra().equals(ngjyra)){
					if(ngjyra.equals(Color.BLACK)){
						if(i == 0){
							sasiaPikeve += 5;
						}
						else if(i == 1){
							sasiaPikeve += 3;
						}
						else if(i == 2){
							sasiaPikeve += 2;
						}
						else if(i == 3){
							sasiaPikeve += 1;
						}
					}
					else{
						if(i == 7){
							sasiaPikeve += 5;
						}
						else if(i == 6){
							sasiaPikeve += 3;
						}
						else if(i == 5){
							sasiaPikeve += 2;
						}
						else if(i == 4){
							sasiaPikeve += 1;
						}
					}
				}
			}
		}
		return sasiaPikeve;
	}

}
