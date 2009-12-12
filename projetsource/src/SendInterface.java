import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interface d'envoie des messages pour l'utilisateur
 * 
 * @author Thomas
 */
public class SendInterface extends javax.swing.JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	PhysicalLayer pl;

	/** Creates new form SendInterface */
	public SendInterface(PhysicalLayer pl) {
		this.pl = pl;
		initComponents();
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jTextField2 = new javax.swing.JTextField();
		jTextField3 = new javax.swing.JTextField();
		id1 = new javax.swing.JTextField();
		val1 = new javax.swing.JTextField();
		id2 = new javax.swing.JTextField();
		val2 = new javax.swing.JTextField();
		id3 = new javax.swing.JTextField();
		val3 = new javax.swing.JTextField();
		id4 = new javax.swing.JTextField();
		val4 = new javax.swing.JTextField();
		val6 = new javax.swing.JTextField();
		val5 = new javax.swing.JTextField();
		id6 = new javax.swing.JTextField();
		id5 = new javax.swing.JTextField();
		val8 = new javax.swing.JTextField();
		val7 = new javax.swing.JTextField();
		id8 = new javax.swing.JTextField();
		id7 = new javax.swing.JTextField();
		val10 = new javax.swing.JTextField();
		val9 = new javax.swing.JTextField();
		id10 = new javax.swing.JTextField();
		id9 = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextPane1 = new javax.swing.JTextPane();
		jButton1 = new javax.swing.JButton();
		jButton1.addActionListener(this);
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setText("Source :");

		jLabel2.setText("Destinataire :");

		jLabel3.setText("Nombre d'options :");

		jLabel4.setText("Options :");

		jLabel5.setText("Donnees :");

		jTextField1.setText("100.100.100.100");
		jTextField1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}
		});

		jTextField2.setText("101.101.101.101");
		jTextField2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField2ActionPerformed(evt);
			}
		});

		jTextField3.setText("0");
		jTextField3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField3ActionPerformed(evt);
			}
		});

		id1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				id1ActionPerformed(evt);
			}
		});

		val1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				val1ActionPerformed(evt);
			}
		});

		id2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				id2ActionPerformed(evt);
			}
		});

		val2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				val2ActionPerformed(evt);
			}
		});

		id3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				id3ActionPerformed(evt);
			}
		});

		val3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				val3ActionPerformed(evt);
			}
		});

		id4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				id4ActionPerformed(evt);
			}
		});

		val4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				val4ActionPerformed(evt);
			}
		});

		val6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				val6ActionPerformed(evt);
			}
		});

		val5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				val5ActionPerformed(evt);
			}
		});

		id6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				id6ActionPerformed(evt);
			}
		});

		id5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				id5ActionPerformed(evt);
			}
		});

		val8.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				val8ActionPerformed(evt);
			}
		});

		val7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				val7ActionPerformed(evt);
			}
		});

		id8.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				id8ActionPerformed(evt);
			}
		});

		id7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				id7ActionPerformed(evt);
			}
		});

		val10.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				val10ActionPerformed(evt);
			}
		});

		val9.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				val9ActionPerformed(evt);
			}
		});

		id10.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				id10ActionPerformed(evt);
			}
		});

		id9.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				id9ActionPerformed(evt);
			}
		});

		jScrollPane1.setViewportView(jTextPane1);

		jButton1.setText("Envoyer");

		jLabel6.setText("Id");

		jLabel7.setText("Id");

		jLabel8.setText("Valeur");

		jLabel9.setText("Valeur");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
								layout.createSequentialGroup().addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
												layout.createSequentialGroup().addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18,
														18, 18).addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(
												layout.createSequentialGroup().addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18,
														18, 18).addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(
												layout.createSequentialGroup().addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18,
														18, 18).addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))).addGap(358, 358, 358))
								.addGroup(
										layout.createSequentialGroup().addGroup(
												layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
														layout.createSequentialGroup().addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18, 18).addGroup(
																		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(id9,
																				javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(id7,
																						javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(
																						javax.swing.GroupLayout.Alignment.LEADING,
																						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(id5,
																								javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(id3,
																										javax.swing.GroupLayout.Alignment.TRAILING).addComponent(id1,
																										javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
																										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel6))))).addGap(6, 6, 6).addGroup(
																		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
																				layout.createSequentialGroup().addComponent(val9, javax.swing.GroupLayout.PREFERRED_SIZE, 169,
																						javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(id10, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)).addGroup(
																				layout.createSequentialGroup().addComponent(val7, javax.swing.GroupLayout.PREFERRED_SIZE, 169,
																						javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(id8, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)).addGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				layout.createSequentialGroup().addGroup(
																						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel8).addComponent(val1,
																								javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(val3,
																								javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(val5,
																								javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
																						javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
																						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(id6,
																								javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE).addComponent(id4,
																								javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE).addComponent(id2,
																								javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(
																								jLabel7)))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
																		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(val2,
																				javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(val4,
																				javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(val6,
																				javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(val10,
																				javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(val8,
																				javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel9))).addGroup(
														layout.createSequentialGroup().addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18, 18).addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))
														.addGroup(
																layout.createSequentialGroup().addComponent(jButton1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 576,
																		Short.MAX_VALUE))).addGap(69, 69, 69)))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel3).addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(27, 27, 27).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel6).addComponent(jLabel7).addComponent(jLabel8).addComponent(jLabel9))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(id1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel4).addComponent(val1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(id2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(val2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(id3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(val3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(id4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(val4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(id5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(val5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(id6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(val6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(id7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(val7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(id8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(val8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(id9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(val9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(id10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(val10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(16, 16, 16).addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
										layout.createSequentialGroup().addComponent(jLabel5).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE).addComponent(
												jButton1)).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap()));

		pack();
	}// </editor-fold>

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void id1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void val1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void id2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void val2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void id3ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void val3ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void id4ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void val4ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void val6ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void val5ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void id6ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void id5ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void val8ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void val7ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void id8ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void id7ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void val10ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void val9ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void id10ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void id9ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	/*
	 * public static void main(String args[]) {
	 * java.awt.EventQueue.invokeLater(new Runnable() { public void run() { new
	 * SendInterface(pl).setVisible(true); } }); }
	 */

	// Variables declaration - do not modify
	private javax.swing.JTextField id1;

	private javax.swing.JTextField id10;

	private javax.swing.JTextField id2;

	private javax.swing.JTextField id3;

	private javax.swing.JTextField id4;

	private javax.swing.JTextField id5;

	private javax.swing.JTextField id6;

	private javax.swing.JTextField id7;

	private javax.swing.JTextField id8;

	private javax.swing.JTextField id9;

	private javax.swing.JButton jButton1;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel2;

	private javax.swing.JLabel jLabel3;

	private javax.swing.JLabel jLabel4;

	private javax.swing.JLabel jLabel5;

	private javax.swing.JLabel jLabel6;

	private javax.swing.JLabel jLabel7;

	private javax.swing.JLabel jLabel8;

	private javax.swing.JLabel jLabel9;

	private javax.swing.JScrollPane jScrollPane1;

	private javax.swing.JTextField jTextField1;

	private javax.swing.JTextField jTextField2;

	private javax.swing.JTextField jTextField3;

	private javax.swing.JTextPane jTextPane1;

	private javax.swing.JTextField val1;

	private javax.swing.JTextField val10;

	private javax.swing.JTextField val2;

	private javax.swing.JTextField val3;

	private javax.swing.JTextField val4;

	private javax.swing.JTextField val5;

	private javax.swing.JTextField val6;

	private javax.swing.JTextField val7;

	private javax.swing.JTextField val8;

	private javax.swing.JTextField val9;

	// End of variables declaration
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Envoyer")) {
			String DEST = jTextField1.getText();
			String FROM = jTextField2.getText();
			int NBOPTIONS = 0;
			try{
			NBOPTIONS = Integer.parseInt(jTextField3.getText());
			} catch (NumberFormatException exc){
				System.out.println("*** Attention ce ce n'est pas un entier ! ***");
			}
			String[] OPTIONS = new String[NBOPTIONS];
			if (NBOPTIONS > 0) {
				int n = val1.getText().length();
				byte octet = (byte) (n);
				char octetchar = (char) (octet & 0xff);
				char indiceOption = (char) ((byte) (0 & 0xff));
				try{
				indiceOption = (char) ((byte) (Integer.parseInt(id1.getText())) & 0xff);
				} catch (NumberFormatException exc){
					System.out.println("*** Attention ce ce n'est pas un entier ! ***");
				}
				OPTIONS[0] = indiceOption + "" + octetchar + val1.getText();
			}
			if (NBOPTIONS > 1) {
				int n = val2.getText().length();
				byte octet = (byte) (n);
				char octetchar = (char) (octet & 0xff);
				char indiceOption = (char) ((byte) (0 & 0xff));
				try{
				indiceOption = (char) ((byte) (Integer.parseInt(id2.getText())) & 0xff);
				} catch (NumberFormatException exc){
					System.out.println("*** Attention ce ce n'est pas un entier ! ***");
				}
				OPTIONS[1] = indiceOption + "" + octetchar + val2.getText();
			}
			if (NBOPTIONS > 2) {
				int n = val3.getText().length();
				byte octet = (byte) (n);
				char octetchar = (char) (octet & 0xff);
				char indiceOption = (char) ((byte) (0 & 0xff));
				try{
				indiceOption = (char) ((byte) (Integer.parseInt(id3.getText())) & 0xff);
				} catch (NumberFormatException exc){
					System.out.println("*** Attention ce ce n'est pas un entier ! ***");
				}
				OPTIONS[2] = indiceOption + "" + octetchar + val3.getText();
			}
			if (NBOPTIONS > 3) {
				int n = val4.getText().length();
				byte octet = (byte) (n);
				char octetchar = (char) (octet & 0xff);
				char indiceOption = (char) ((byte) (0 & 0xff));
				try{
				indiceOption = (char) ((byte) (Integer.parseInt(id4.getText())) & 0xff);
				} catch (NumberFormatException exc){
					System.out.println("*** Attention ce ce n'est pas un entier ! ***");
				}
				OPTIONS[3] = indiceOption + "" + octetchar + val4.getText();
			}
			if (NBOPTIONS > 4) {
				int n = val5.getText().length();
				byte octet = (byte) (n);
				char octetchar = (char) (octet & 0xff);
				char indiceOption = (char) ((byte) (0 & 0xff));
				try{
				indiceOption = (char) ((byte) (Integer.parseInt(id5.getText())) & 0xff);
				} catch (NumberFormatException exc){
					System.out.println("*** Attention ce ce n'est pas un entier ! ***");
				}
				OPTIONS[4] = indiceOption + "" + octetchar + val5.getText();
			}
			if (NBOPTIONS > 5) {
				int n = val6.getText().length();
				byte octet = (byte) (n);
				char octetchar = (char) (octet & 0xff);
				char indiceOption = (char) ((byte) (0 & 0xff));
				try{
				indiceOption = (char) ((byte) (Integer.parseInt(id6.getText())) & 0xff);
				} catch (NumberFormatException exc){
					System.out.println("*** Attention ce ce n'est pas un entier ! ***");
				}
				OPTIONS[5] = indiceOption + "" + octetchar + val6.getText();
			}
			if (NBOPTIONS > 6) {
				int n = val7.getText().length();
				byte octet = (byte) (n);
				char octetchar = (char) (octet & 0xff);
				char indiceOption = (char) ((byte) (0 & 0xff));
				try{
				indiceOption = (char) ((byte) (Integer.parseInt(id7.getText())) & 0xff);
				} catch (NumberFormatException exc){
					System.out.println("*** Attention ce ce n'est pas un entier ! ***");
				}
				OPTIONS[6] = indiceOption + "" + octetchar + val7.getText();
			}
			if (NBOPTIONS > 7) {
				int n = val8.getText().length();
				byte octet = (byte) (n);
				char octetchar = (char) (octet & 0xff);
				char indiceOption = (char) ((byte) (0 & 0xff));
				try{
				indiceOption = (char) ((byte) (Integer.parseInt(id8.getText())) & 0xff);
				} catch (NumberFormatException exc){
					System.out.println("*** Attention ce ce n'est pas un entier ! ***");
				}
				OPTIONS[7] = indiceOption + "" + octetchar + val8.getText();
			}
			if (NBOPTIONS > 8) {
				int n = val9.getText().length();
				byte octet = (byte) (n);
				char octetchar = (char) (octet & 0xff);
				char indiceOption = (char) ((byte) (0 & 0xff));
				try{
				indiceOption = (char) ((byte) (Integer.parseInt(id9.getText())) & 0xff);
				} catch (NumberFormatException exc){
					System.out.println("*** Attention ce ce n'est pas un entier ! ***");
				}
				OPTIONS[8] = indiceOption + "" + octetchar + val9.getText();
			}
			if (NBOPTIONS > 9) {
				int n = val10.getText().length();
				byte octet = (byte) (n);
				char octetchar = (char) (octet & 0xff);
				char indiceOption = (char) ((byte) (0 & 0xff));
				try{
				indiceOption = (char) ((byte) (Integer.parseInt(id10.getText())) & 0xff);
				} catch (NumberFormatException exc){
					System.out.println("*** Attention ce ce n'est pas un entier ! ***");
				}
				OPTIONS[9] = indiceOption + "" + octetchar + val10.getText();
			}
			String DATA = jTextPane1.getText();
			String msg = CreationMsg.buildMsg(FROM, DEST, NBOPTIONS, OPTIONS, DATA);
			Message mess = new Message(msg);
			if (mess.good) {
				System.out.print("ENVOI : ");
				mess.print();
				pl.send(mess);
			}
		}

	}
}
