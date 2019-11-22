package views;
import bd.daos.*;
import bd.dbos.*;
import javax.swing.JFrame;
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
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

public class PrincipalFunc extends JFrame {
	
	private JFrame frame;
	private JPanel contentPane;
	Funcionario funcionarioLog;
	boolean alterou = false;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalFunc frame = new PrincipalFunc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	int codigo;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JTextField txtEndereco;
	private JTextField txtUsuario;
	private JButton btnSalvar;
	JButton btnCancelar;
	
	public PrincipalFunc(int cod) {
		codigo = cod;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try
		{
		  funcionarioLog = new Funcionario(Funcionarios.getFuncionarioByCod(codigo));
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
		frame.setBounds(100, 100, 485, 361);
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
				setTxts(true);
				btnSalvar.setEnabled(true);
				alterou = true;
			}
		});
		btnAlterar.setForeground(Color.BLACK);
		btnAlterar.setBounds(100, 229, 85, 32);
		frame.getContentPane().add(btnAlterar);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(290, 229, 85, 32);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtNome.getText().equals("") ||
				txtEmail.getText().equals("") ||
				txtTelefone.getText().equals("") ||
				txtEndereco.getText().equals("") ||
				txtUsuario.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Não deixe nenhum campo em branco!");
				}
				else
				{
					try
					{
						funcionarioLog.setEndereco(txtEndereco.getText());
						funcionarioLog.setUsuario(txtUsuario.getText());
						funcionarioLog.setEmail(txtEmail.getText());
						funcionarioLog.setNome(txtNome.getText());
						funcionarioLog.setTelefone(txtTelefone.getText());
						Funcionarios.alterar(funcionarioLog);
						alterou = false;
						JOptionPane.showMessageDialog(null,"Alteração feita com sucesso!");
					}
					catch(Exception ex) 
					{
						JOptionPane.showMessageDialog(null,ex.getMessage() + "Alteração cancelada");
					}
				}
				btnCancelar.doClick();
			}
		});
		frame.getContentPane().add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEmail.setText(funcionarioLog.getEmail());
				txtNome.setText(funcionarioLog.getNome());
				txtEndereco.setText(funcionarioLog.getEndereco());
				txtUsuario.setText(funcionarioLog.getUsuario());
				txtTelefone.setText(funcionarioLog.getTelefone());

				setTxts(false);
			}
		});
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setBounds(195, 229, 85, 32);
		frame.getContentPane().add(btnCancelar);
		
		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setBounds(119, 62, 298, 23);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		txtNome.setText(funcionarioLog.getNome());
		
		txtEmail = new JTextField();
		txtEmail.setEnabled(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(119, 129, 298, 23);
		frame.getContentPane().add(txtEmail);
		txtEmail.setText(funcionarioLog.getEmail());
		
		txtTelefone = new JTextField();
		txtTelefone.setEnabled(false);
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(119, 95, 298, 23);
		frame.getContentPane().add(txtTelefone);
		txtTelefone.setText(funcionarioLog.getTelefone());
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEndereo.setBounds(30, 161, 73, 23);
		frame.getContentPane().add(lblEndereo);
		
		txtEndereco = new JTextField();
		txtEndereco.setEnabled(false);
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(119, 161, 298, 23);
		frame.getContentPane().add(txtEndereco);
		txtEndereco.setText(funcionarioLog.getEndereco());
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsurio.setBounds(43, 195, 60, 23);
		frame.getContentPane().add(lblUsurio);
		
		txtUsuario = new JTextField();
		txtUsuario.setEnabled(false);
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(119, 194, 200, 23);
		frame.getContentPane().add(txtUsuario);
		txtUsuario.setText(funcionarioLog.getUsuario());
		
		JButton btnTrocarSenha = new JButton("Trocar senha");
		btnTrocarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrocarSenha formTS = new TrocarSenha(funcionarioLog);
				formTS.setVisible(true);
			}
		});
		btnTrocarSenha.setForeground(Color.BLACK);
		btnTrocarSenha.setBounds(322, 189, 95, 32);
		frame.getContentPane().add(btnTrocarSenha);
		
		JLabel lblHelpa = new JLabel(" \u00C1rea do funcion\u00E1rio");
		lblHelpa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHelpa.setBounds(159, 21, 190, 37);
		frame.getContentPane().add(lblHelpa);
		JMenu menManutencao;
		menManutencao = new JMenu("MANUTEN\u00C7\u00C3O");
		menManutencao.setBounds(170, 290, 131, 22);
		frame.getContentPane().add(menManutencao);
		menManutencao.setHorizontalAlignment(SwingConstants.CENTER);
		

		JMenuItem miManutEntidades = new JMenuItem("ENTIDADES");
		miManutEntidades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// manunetncao ENTIDADES
				ManutEntidades formME = new ManutEntidades();
				formME.setVisible(true);
			}
		});
		menManutencao.add(miManutEntidades);
		
		JMenuItem miManutDoadores = new JMenuItem("DOADORES");
		menManutencao.add(miManutDoadores);
		
		
		JMenuItem miManutFuncs = new JMenuItem("FUNCION\u00C1RIOS");
		if(!funcionarioLog.getCargo().equals("chefe de manutenção"))
		{
			miManutFuncs.setEnabled(false);
		}
		miManutFuncs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// ABRIR FORM DE MANUTENÇÃO DE FUNCIONÁRIOS
				if(funcionarioLog.getCargo().equals("chefe de manutenção")) // acho q nem precisa
				{
					//ManutFuncionarios formMF = new ManutFuncs();
					//formMF.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(null,"Você não tem permissão para acessar essa camada de manutenção");
			}
		});
		menManutencao.add(miManutFuncs);
		
		JLabel lblNewLabel = new JLabel("HELPA!");
		lblNewLabel.setBounds(216, 11, 46, 14);
		frame.getContentPane().add(lblNewLabel);
	}
	
	private void setTxts(boolean modo)
	{
		txtNome.setEnabled(modo); 
		txtEmail.setEnabled(modo);
		txtTelefone.setEnabled(modo);
		txtEndereco.setEnabled(modo);
		txtUsuario.setEnabled(modo);
	}
}