import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import bd.daos.Entidades;
import bd.dbos.Entidade;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import java.awt.Insets;

public class PrincipalEntidade extends JFrame {

	private JPanel contentPane;
	private int codigo;
	Entidade entiAtual;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_10;
	private JTextField textField_9;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalEntidade frame = new PrincipalEntidade();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public PrincipalEntidade(int cod) {
		codigo = cod;
		initialize();
	}
	
	private void initialize()
	{
		try
		{
		  entiAtual = new Entidade(Entidades.getEntidadeByCod(codigo));
		}
		catch(Exception ex) {} // n vai dar erro pois o codigo foi eu que passei
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("CADASTRO");
		tabbedPane.setSelectedIndex(0);
		tabbedPane.setBounds(0, 0, 681, 410);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		tabbedPane.addTab("Informa\u00E7\u00F5es", null, panel, null);
		
		JLabel label = new JLabel("C\u00F3digo:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(21, 43, 59, 32);
		panel.add(label);
		
		textField = new JTextField();
		textField.setText((String) null);
		textField.setEnabled(false);
		textField.setColumns(10);
		textField.setBounds(347, 279, 109, 30);
		panel.add(textField);
		
		JLabel label_1 = new JLabel("Nome:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(31, 83, 48, 32);
		panel.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setText((String) null);
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		textField_1.setBounds(90, 80, 355, 30);
		panel.add(textField_1);
		
		JLabel label_2 = new JLabel("CNPJ:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(32, 115, 48, 32);
		panel.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setText((String) null);
		textField_2.setEnabled(false);
		textField_2.setColumns(10);
		textField_2.setBounds(90, 43, 109, 30);
		panel.add(textField_2);
		
		JLabel label_3 = new JLabel("E-mail:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(21, 150, 59, 32);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Telefone:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_4.setBounds(10, 191, 69, 32);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Ag\u00EAncia:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_5.setBounds(280, 275, 69, 32);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Usu\u00E1rio:");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_6.setBounds(21, 318, 69, 32);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("Endere\u00E7o:");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_7.setBounds(10, 236, 72, 32);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("Conta:");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_8.setBounds(31, 275, 48, 32);
		panel.add(label_8);
		
		textField_3 = new JTextField();
		textField_3.setText((String) null);
		textField_3.setEnabled(false);
		textField_3.setColumns(10);
		textField_3.setBounds(90, 150, 355, 30);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setText((String) null);
		textField_4.setEnabled(false);
		textField_4.setColumns(10);
		textField_4.setBounds(90, 236, 355, 30);
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setText((String) null);
		textField_5.setEnabled(false);
		textField_5.setColumns(10);
		textField_5.setBounds(90, 318, 179, 30);
		panel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setText((String) null);
		textField_6.setEnabled(false);
		textField_6.setColumns(10);
		textField_6.setBounds(90, 191, 179, 30);
		panel.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setText((String) null);
		textField_7.setEnabled(false);
		textField_7.setColumns(10);
		textField_7.setBounds(90, 114, 179, 30);
		panel.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setText((String) null);
		textField_8.setEnabled(false);
		textField_8.setColumns(10);
		textField_8.setBounds(90, 275, 179, 30);
		panel.add(textField_8);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(10, 1, 1, 1));
		menuBar.setBounds(0, 0, 676, 32);
		panel.add(menuBar);
		
		JLabel label_10 = new JLabel("Site:");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_10.setBounds(305, 320, 69, 32);
		panel.add(label_10);
		
		textField_10 = new JTextField();
		textField_10.setText((String) null);
		textField_10.setEnabled(false);
		textField_10.setColumns(10);
		textField_10.setBounds(347, 320, 179, 30);
		panel.add(textField_10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("New tab", null, panel_1, null);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setMargin(new Insets(10, 1, 1, 1));
		menuBar_1.setBounds(0, 0, 676, 32);
		panel_1.add(menuBar_1);
		
		JLabel lblSuasNecessidades = new JLabel("Suas necessidades:");
		lblSuasNecessidades.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSuasNecessidades.setBounds(10, 38, 158, 32);
		panel_1.add(lblSuasNecessidades);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(10, 68, 179, 240);
		panel_1.add(textField_9);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		tabbedPane.addTab("New tab", null, panel_2, null);
		
		JMenuBar menuBar_2 = new JMenuBar();
		menuBar_2.setMargin(new Insets(10, 1, 1, 1));
		menuBar_2.setBounds(0, 0, 676, 32);
		panel_2.add(menuBar_2);
	}
}
