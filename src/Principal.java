import bd.daos.*;
import bd.dbos.*;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Principal {

	JFrame frame;
	private JTextField txtTelefone;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtEmail;
	//variaveis globais
	Funcionario funcAtual;
	boolean alterou = false;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	int codigo;
	
	public Principal(int cod) {
		initialize();
		codigo = cod;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try
		{
		  funcAtual = new Funcionario(Funcionarios.getFuncionario(codigo));
		}
		catch(Exception ex) {} // n vai dar erro pois o codigo foi eu que passei
		
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if(alterou)
				{
					int choice = JOptionPane.showOptionDialog(null, 
						      "Deseja sair sem salvar?", 
						      "Sair", 
						      JOptionPane.YES_NO_OPTION, 
						      JOptionPane.QUESTION_MESSAGE, 
						      null, null, null);

						  if (choice == JOptionPane.YES_OPTION)
						  {
							  System.exit(0);
						  }	  
				}
				else
					System.exit(0);
			}
		});
		frame.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// ABRIR FORM DE MANUTEN��O DE ENTIDADES
				ManutEntidades formME = new ManutEntidades();
				formME.setVisible(true);
			}
		});
		frame.setBounds(100, 100, 526, 365);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(10, 10));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JMenu menManutencao = new JMenu("MANUTEN\u00C7\u00C3O");
		menManutencao.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(menManutencao);
		
		JMenuItem miManutEntidades = new JMenuItem("ENTIDADES");
		miManutEntidades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		menManutencao.add(miManutEntidades);
		
		JMenuItem miManutFuncs = new JMenuItem("FUNCION\u00C1RIOS");
		miManutFuncs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// ABRIR FORM DE MANUTEN��O DE FUNCION�RIOS
				ManutFuncs formMF = new ManutFuncs();
				formMF.setVisible(true);
			}
		});
		menManutencao.add(miManutFuncs);
		
		JMenu menConsultas = new JMenu("CONSULTAS");
		panel.add(menConsultas);
		
		JMenuItem miConsuEntidades = new JMenuItem("ENTIDADES");
		miConsuEntidades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// ABRIR P/ CONSULTA ENTIDADES
				ConsultaEntidades formCE = new ConsultaEntidades();
				formCE.setVisible(true);
			}
		});
		menConsultas.add(miConsuEntidades);
		
		JMenuItem miConsuDoadores = new JMenuItem("DOADORES");
		miConsuDoadores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// ABRIR P/ CONSULTA DOAD
				ConsultaDoadores formCD = new ConsultaDoadores();
				formCD.setVisible(true);
			}
		});
		menConsultas.add(miConsuDoadores);
		
		JMenuItem miConsuFuncs = new JMenuItem("DOA\u00C7\u00D5ES");
		miConsuFuncs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			// ABRIR FORM P/ CONSULTA FUNCION�RIOS
				ConsultaFuncs formCF = new ConsultaFuncs();
				formCF.setVisible(true);
			}
		});
		menConsultas.add(miConsuFuncs);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 2, 0, 2));
		
		JLabel lblNome = new JLabel("Nome:");
		panel_1.add(lblNome);
		lblNome.setSize(50, 50);
		
		txtNome = new JTextField();
		txtNome.setEnabled(false);
		panel_1.add(txtNome);
		txtNome.setColumns(10);
		txtNome.setText(funcAtual.getNome());
		
		JLabel lblCPF = new JLabel("CPF:");
		panel_1.add(lblCPF);
		lblCPF.setSize(50,50);
		
		txtCPF = new JTextField();
		txtCPF.setEnabled(false);
		panel_1.add(txtCPF);
		txtCPF.setColumns(10);
		txtCPF.setText(funcAtual.getCpf());
		
		JLabel lblEmail = new JLabel("E-mail:");
		panel_1.add(lblEmail);
		lblEmail.setSize(50,50);
		
		txtEmail = new JTextField();
		txtEmail.setEnabled(false);
		panel_1.add(txtEmail);
		txtEmail.setColumns(10);
		txtEmail.setText(funcAtual.getEmail());
		
		JLabel label = new JLabel("E-mail:");
		panel_1.add(label);
		
		txtTelefone = new JTextField();
		txtTelefone.setEnabled(false);
		panel_1.add(txtTelefone);
		txtTelefone.setColumns(10);
		txtTelefone.setText(funcAtual.getTelefone());
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNome.setEnabled(true);
				txtEmail.setEnabled(true);
				txtTelefone.setEnabled(true);
				txtCPF.setEnabled(true);
				alterou = true;
			}
		});
		btnAlterar.setForeground(new Color(0, 0, 0));
		panel_1.add(btnAlterar);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtNome.getText() == "" ||
				txtEmail.getText() == "" ||
				txtTelefone.getText()== "" ||
				txtCPF.getText()== "")
				{
					JOptionPane.showMessageDialog(null,"N�o deixe nenhum campo em branco!");
				txtNome.requestFocus();
				txtEmail.requestFocus();
				txtTelefone.requestFocus();
				txtCPF.requestFocus();
				}
				else
				{
					try
					{
						funcAtual.setCpf(txtCPF.getText());
						funcAtual.setEmail(txtEmail.getText());
						funcAtual.setNome(txtNome.getText());
						funcAtual.setTelefone(txtTelefone.getText());
						Funcionarios.alterar(funcAtual);
						alterou = false;
					}
					catch(Exception ex) {/*fazer*/}
				}
			}
		});
		panel_1.add(btnNewButton);
	}
}
