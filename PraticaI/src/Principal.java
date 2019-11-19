import bd.daos.*;
import bd.dbos.*;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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
import java.awt.Font;
import java.awt.List;

public class Principal {

	JFrame frame;
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
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JTextField txtEndereco;
	private JTextField txtUsuario;
	private JButton btnSalvar;
	JButton btnCancelar;
	
	public Principal(int cod) {
		codigo = cod;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try
		{
		  funcAtual = new Funcionario(Funcionarios.getFuncionarioByCod(codigo));
		}
		catch(Exception ex) {} // n vai dar erro pois o codigo foi eu que passei
		
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if(alterou)
				{
					int choice = JOptionPane.showOptionDialog(null, 
						      "Deseja sair e salvar?", 
						      "Sair", 
						      JOptionPane.YES_NO_CANCEL_OPTION, 
						      JOptionPane.QUESTION_MESSAGE, 
						      null, null, null);

						  if (choice == JOptionPane.YES_OPTION)
						  {
							  btnSalvar.doClick();
						  }
						  else if(choice == JOptionPane.NO_OPTION)
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
				// ABRIR FORM DE MANUTENÇÃO DE ENTIDADES
				ManutEntidades formME = new ManutEntidades();
				formME.setVisible(true);
			}
		});
		frame.setBounds(100, 100, 482, 341);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Nome:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(55, 53, 48, 32);
		frame.getContentPane().add(label);
		
		JLabel label_2 = new JLabel("E-mail:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(50, 127, 53, 23);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Telefone:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(37, 93, 73, 23);
		frame.getContentPane().add(label_3);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNome.setEnabled(true);
				txtEmail.setEnabled(true);
				txtTelefone.setEnabled(true);
				txtEndereco.setEnabled(true);
				txtUsuario.setEnabled(true);
				btnSalvar.setEnabled(true);
				alterou = true;
			}
		});
		btnAlterar.setForeground(Color.BLACK);
		btnAlterar.setBounds(96, 224, 85, 32);
		frame.getContentPane().add(btnAlterar);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(286, 224, 85, 32);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtNome.getText().equals("") ||
				txtEmail.getText().equals("") ||
				txtTelefone.getText().equals("") ||
				txtEndereco.getText().equals("") ||
				txtUsuario.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Não deixe nenhum campo em branco!");
					btnCancelar.doClick();
				}
				else
				{
					try
					{
						funcAtual.setEndereco(txtEndereco.getText());
						funcAtual.setUsuario(txtUsuario.getText());
						funcAtual.setEmail(txtEmail.getText());
						funcAtual.setNome(txtNome.getText());
						funcAtual.setTelefone(txtTelefone.getText());
						Funcionarios.alterar(funcAtual);
						alterou = false;
						JOptionPane.showMessageDialog(null,"Alteração feita com sucesso!");
					}
					catch(Exception ex) {/*fazer*/}
				}
				btnSalvar.setEnabled(false);
				txtNome.setEnabled(false);
				txtEmail.setEnabled(false);
				txtTelefone.setEnabled(false);
				txtEndereco.setEnabled(false);
				txtUsuario.setEnabled(false);
			}
		});
		frame.getContentPane().add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEmail.setText(funcAtual.getEmail());
				txtNome.setText(funcAtual.getNome());
				txtEndereco.setText(funcAtual.getEndereco());
				txtUsuario.setText(funcAtual.getUsuario());
				txtTelefone.setText(funcAtual.getTelefone());
				btnSalvar.setEnabled(false);
				txtNome.setEnabled(false);
				txtEmail.setEnabled(false);
				txtTelefone.setEnabled(false);
				txtEndereco.setEnabled(false);
				txtUsuario.setEnabled(false);
			}
		});
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setBounds(191, 224, 85, 32);
		frame.getContentPane().add(btnCancelar);
		
		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setBounds(119, 62, 298, 23);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		txtNome.setText(funcAtual.getNome());
		
		txtEmail = new JTextField();
		txtEmail.setEnabled(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(119, 129, 298, 23);
		frame.getContentPane().add(txtEmail);
		txtEmail.setText(funcAtual.getEmail());
		
		txtTelefone = new JTextField();
		txtTelefone.setEnabled(false);
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(119, 95, 298, 23);
		frame.getContentPane().add(txtTelefone);
		txtTelefone.setText(funcAtual.getTelefone());
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEndereo.setBounds(30, 161, 73, 23);
		frame.getContentPane().add(lblEndereo);
		
		txtEndereco = new JTextField();
		txtEndereco.setEnabled(false);
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(119, 161, 298, 23);
		frame.getContentPane().add(txtEndereco);
		txtEndereco.setText(funcAtual.getEndereco());
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsurio.setBounds(43, 195, 60, 23);
		frame.getContentPane().add(lblUsurio);
		
		txtUsuario = new JTextField();
		txtUsuario.setEnabled(false);
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(119, 194, 200, 23);
		frame.getContentPane().add(txtUsuario);
		txtUsuario.setText(funcAtual.getUsuario());
		
		JButton btnTrocarSenha = new JButton("Trocar senha");
		btnTrocarSenha.setForeground(Color.BLACK);
		btnTrocarSenha.setBounds(322, 189, 95, 32);
		frame.getContentPane().add(btnTrocarSenha);
		
		JLabel lblHelpa = new JLabel(" \u00C1rea do funcion\u00E1rio");
		lblHelpa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHelpa.setBounds(159, 21, 190, 37);
		frame.getContentPane().add(lblHelpa);
		/*JMenu menManutencao;
		menManutencao = new JMenu("MANUTEN\u00C7\u00C3O");
		menManutencao.setBounds(55, 270, 131, 22);
		frame.getContentPane().add(menManutencao);
		menManutencao.setHorizontalAlignment(SwingConstants.CENTER);
		*/
		JMenuBar menuBar = new JMenuBar();
		JMenu ManutMenu = new JMenu("MANUTENÇÃO");
        JMenu RelatMenu = new JMenu("CONSULTAS");
        menuBar.add(ManutMenu);
        menuBar.add(RelatMenu);
        
        JMenuItem ManutFunc = new JMenuItem("Funcionários");
        ManutFunc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// abrir form de manutencao de funcionarios
			}
		});
        JMenuItem ManutEnt = new JMenuItem("Entidades");
        ManutEnt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// abrir form de manutencao de entidades
			}
		});
        JMenuItem ManutDoa = new JMenuItem("Doadores");
        ManutDoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// abrir form de manutencao de doadores
			}
		});
        
        JMenuItem RelatEnt = new JMenuItem("Entidades");
        RelatEnt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// abrir form de relatorio de entidades
			}
		});
        JMenuItem RelatDoa = new JMenuItem("Doadores");
        RelatDoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// abrir form de relatorio de entidades
			}
		});
         
        ManutMenu.add(ManutFunc);
        ManutMenu.add(ManutEnt);
        ManutMenu.add(ManutDoa);
        
        RelatMenu.add(RelatEnt);
        RelatMenu.add(RelatDoa);
        
        
        menuBar.setBounds(100, 260, 200, 40);
        frame.getContentPane().add(menuBar);

		/*JMenuItem miManutEntidades = new JMenuItem("ENTIDADES");
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
				// ABRIR FORM DE MANUTENÇÃO DE FUNCIONÁRIOS
				ManutFuncs formMF = new ManutFuncs();
				formMF.setVisible(true);
			}
		});
		menManutencao.add(miManutFuncs);
		
		JMenu menConsultas = new JMenu("CONSULTAS");
		menConsultas.setBounds(273, 270, 131, 22);
		frame.getContentPane().add(menConsultas);
		
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
			// ABRIR FORM P/ CONSULTA FUNCIONÁRIOS
				ConsultaFuncs formCF = new ConsultaFuncs();
				formCF.setVisible(true);
			}
		});
		menConsultas.add(miConsuFuncs);*/
		
		JLabel lblNewLabel = new JLabel("HELPA!");
		lblNewLabel.setBounds(216, 11, 46, 14);
		frame.getContentPane().add(lblNewLabel);
	}
}
