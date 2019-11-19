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
import java.awt.SystemColor;
import javax.swing.UIManager;

public class PrincipalEntidade extends JFrame {

	private JPanel contentPane;
	private int codigo;
	Entidade entiAtual;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_2;
	private JTextField textField_5;
	private JTextField textField_11;
	private JTextField textField_10;
	private JTextField textField_12;
	private JTextField textField_13;

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
		setBounds(100, 100, 578, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		//tabbedPane.setSelectedIndex(0);
		tabbedPane.setBounds(0, 0, 562, 408);
		contentPane.add(tabbedPane);
		
		JPanel pnlInfoInt = new JPanel();
		pnlInfoInt.setLayout(null);
		tabbedPane.addTab("Informa\u00E7\u00F5es Internas", null, pnlInfoInt, null);
		
		textField = new JTextField();
		textField.setText((String) null);
		textField.setEnabled(false);
		textField.setColumns(10);
		textField.setBounds(348, 201, 72, 30);
		pnlInfoInt.add(textField);
		
		JLabel label_1 = new JLabel("Nome:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(31, 43, 48, 32);
		pnlInfoInt.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setText((String) null);
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		textField_1.setBounds(88, 45, 400, 30);
		pnlInfoInt.add(textField_1);
		
		JLabel label_2 = new JLabel("CNPJ:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(36, 86, 43, 32);
		pnlInfoInt.add(label_2);
		
		JLabel label_3 = new JLabel("E-mail:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(31, 121, 51, 32);
		pnlInfoInt.add(label_3);
		
		JLabel lblTelefone = new JLabel("Telefone: ");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTelefone.setBounds(272, 86, 72, 32);
		pnlInfoInt.add(lblTelefone);
		
		JLabel label_5 = new JLabel("Ag\u00EAncia:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_5.setBounds(279, 201, 69, 32);
		pnlInfoInt.add(label_5);
		
		JLabel label_7 = new JLabel("Endere\u00E7o:");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_7.setBounds(10, 161, 72, 32);
		pnlInfoInt.add(label_7);
		
		JLabel label_8 = new JLabel("Conta:");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_8.setBounds(30, 201, 48, 32);
		pnlInfoInt.add(label_8);
		
		textField_3 = new JTextField();
		textField_3.setText((String) null);
		textField_3.setEnabled(false);
		textField_3.setColumns(10);
		textField_3.setBounds(88, 124, 400, 30);
		pnlInfoInt.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setText((String) null);
		textField_4.setEnabled(false);
		textField_4.setColumns(10);
		textField_4.setBounds(88, 163, 400, 30);
		pnlInfoInt.add(textField_4);
		
		textField_6 = new JTextField();
		textField_6.setText((String) null);
		textField_6.setEnabled(false);
		textField_6.setColumns(10);
		textField_6.setBounds(343, 87, 179, 30);
		pnlInfoInt.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setText((String) null);
		textField_7.setEnabled(false);
		textField_7.setColumns(10);
		textField_7.setBounds(88, 86, 179, 30);
		pnlInfoInt.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setText((String) null);
		textField_8.setEnabled(false);
		textField_8.setColumns(10);
		textField_8.setBounds(88, 204, 179, 30);
		pnlInfoInt.add(textField_8);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(10, 1, 1, 1));
		menuBar.setBounds(0, 0, 676, 32);
		pnlInfoInt.add(menuBar);
		
		JButton btnAlterar = new JButton("ALTERAR");
		btnAlterar.setFont(UIManager.getFont("ToolTip.font"));
		menuBar.add(btnAlterar);
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.setFont(UIManager.getFont("ToolTip.font"));
		menuBar.add(btnSalvar);
		
		JLabel lblCasoQueiraAlterar = new JLabel("   Caso queira alterar algo, clique no bot\u00E3o [Alterar] e, ap\u00F3s digitar, clique em [Salvar]!");
		lblCasoQueiraAlterar.setForeground(Color.GRAY);
		menuBar.add(lblCasoQueiraAlterar);
		
		JLabel lblEssasInformaesApenas = new JLabel("Essas informa\u00E7\u00F5es apenas n\u00F3s da Helpa! e voc\u00EA, dono da Entidade, t\u00EAm acesso! ");
		lblEssasInformaesApenas.setForeground(SystemColor.inactiveCaptionText);
		lblEssasInformaesApenas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEssasInformaesApenas.setBounds(21, 348, 556, 32);
		pnlInfoInt.add(lblEssasInformaesApenas);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsurio.setBounds(138, 242, 66, 32);
		pnlInfoInt.add(lblUsurio);
		
		textField_10 = new JTextField();
		textField_10.setText((String) null);
		textField_10.setEnabled(false);
		textField_10.setColumns(10);
		textField_10.setBounds(210, 245, 179, 30);
		pnlInfoInt.add(textField_10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSenha.setBounds(138, 281, 66, 32);
		pnlInfoInt.add(lblSenha);
		
		textField_12 = new JTextField();
		textField_12.setText((String) null);
		textField_12.setEnabled(false);
		textField_12.setColumns(10);
		textField_12.setBounds(210, 284, 179, 30);
		pnlInfoInt.add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setText((String) null);
		textField_13.setEnabled(false);
		textField_13.setColumns(10);
		textField_13.setBounds(210, 319, 179, 30);
		pnlInfoInt.add(textField_13);
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar Senha:");
		lblConfirmarSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblConfirmarSenha.setBounds(77, 316, 127, 32);
		pnlInfoInt.add(lblConfirmarSenha);
		
		JPanel pnlInfoPub = new JPanel();
		pnlInfoPub.setLayout(null);
		tabbedPane.addTab("Informa\u00E7\u00F5es P\u00FAblicas", null, pnlInfoPub, null);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setMargin(new Insets(10, 1, 1, 1));
		menuBar_1.setBounds(0, 0, 566, 32);
		pnlInfoPub.add(menuBar_1);
		
		JButton btnNewButton = new JButton("Alterar");
		menuBar_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Salvar");
		menuBar_1.add(btnNewButton_1);
		
		JLabel label_9 = new JLabel("   Caso queira alterar algo, clique no bot\u00E3o [Alterar] e, ap\u00F3s digitar, clique em [Salvar]!");
		label_9.setForeground(Color.GRAY);
		menuBar_1.add(label_9);
		
		JLabel lblSuasNecessidades = new JLabel("Necessidades");
		lblSuasNecessidades.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSuasNecessidades.setBounds(408, 86, 158, 32);
		pnlInfoPub.add(lblSuasNecessidades);
		
		textField_9 = new JTextField();
		textField_9.setEnabled(false);
		textField_9.setColumns(10);
		textField_9.setBounds(373, 112, 173, 257);
		pnlInfoPub.add(textField_9);
		
		textField_2 = new JTextField();
		textField_2.setText((String) null);
		textField_2.setEnabled(false);
		textField_2.setColumns(10);
		textField_2.setBounds(10, 57, 353, 30);
		pnlInfoPub.add(textField_2);
		
		JLabel label_4 = new JLabel("Site:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_4.setBounds(10, 28, 69, 32);
		pnlInfoPub.add(label_4);
		
		JLabel label_6 = new JLabel("Foto:");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_6.setBounds(10, 86, 69, 32);
		pnlInfoPub.add(label_6);
		
		textField_5 = new JTextField();
		textField_5.setText((String) null);
		textField_5.setEnabled(false);
		textField_5.setColumns(10);
		textField_5.setBounds(10, 112, 353, 30);
		pnlInfoPub.add(textField_5);
		
		JLabel lblinsiraOLink = new JLabel("(Insira o link da imagem. Ex: http://recanto(...).jpg)");
		lblinsiraOLink.setForeground(Color.GRAY);
		lblinsiraOLink.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblinsiraOLink.setBounds(10, 136, 296, 32);
		pnlInfoPub.add(lblinsiraOLink);
		
		textField_11 = new JTextField();
		textField_11.setEnabled(false);
		textField_11.setBounds(10, 186, 353, 183);
		pnlInfoPub.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDescrio.setBounds(10, 161, 101, 32);
		pnlInfoPub.add(lblDescrio);
		
		//JImagePanel img = new JImagePanel(entiAtual.getImagem());
	}
}
