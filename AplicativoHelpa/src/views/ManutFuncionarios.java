package views;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import bd.core.MeuResultSet;
import bd.daos.Funcionarios;
import bd.dbos.Funcionario;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManutFuncionarios extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCodigo;
	private JTable tblRelatorio;
	private JTextField txtSalario;
	private JTextField txtAgencia;
	private JTextField txtEmail;
	private JTextField txtCpf;
	private JTextField txtTelefone;
	private JTextField txtEndereco;
	private JTextField txtUsuario;
	private JTextField txtCargo;
	private JTextField txtConta;
	private JButton btnSalvar;
	private Situacao situacaoAtual;

	/**
	 * Launch the application.
	 * 
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManutFuncionarios frame = new ManutFuncionarios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManutFuncionarios() {
		situacaoAtual = Situacao.NAVEGANDO;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 591, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 574, 384);
		tabbedPane.setToolTipText("CADASTRO");
		tabbedPane.setSelectedIndex(0);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		tabbedPane.addTab("Manuten\u00E7\u00E3o", null, panel, null);
		
		JLabel label = new JLabel("C\u00F3digo:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(20, 43, 59, 32);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Nome:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(30, 74, 48, 32);
		panel.add(label_1);
		
		txtNome = new JTextField();
		txtNome.setText((String) null);
		txtNome.setEnabled(false);
		txtNome.setColumns(10);
		txtNome.setBounds(89, 77, 355, 30);
		panel.add(txtNome);
		
		JLabel label_2 = new JLabel("CPF:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(46, 141, 33, 32);
		panel.add(label_2);
		
		txtCodigo = new JTextField();
		txtCodigo.setText((String) null);
		txtCodigo.setEnabled(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(89, 43, 109, 30);
		panel.add(txtCodigo);
		
		JLabel label_3 = new JLabel("E-mail:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(28, 107, 51, 32);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Telefone:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_4.setBounds(10, 179, 69, 32);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Endere\u00E7o:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_5.setBounds(10, 213, 72, 32);
		panel.add(label_5);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(10, 1, 1, 1));
		menuBar.setBounds(0, 0, 606, 32);
		panel.add(menuBar);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCodigo.setEnabled(true);
			}
		});
		menuBar.add(btnProcurar);
		
		JLabel label_6 = new JLabel("|");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		menuBar.add(label_6);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				situacaoAtual = Situacao.INCLUINDO;
				limparTela();
				setTxt(true);
				txtCodigo.grabFocus();
				btnSalvar.enable(true);
			}
		});
		menuBar.add(btnNovo);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				situacaoAtual = Situacao.EDITANDO;
				setTxt(true);
				txtCodigo.setEnabled(false);
				btnSalvar.setEnabled(true);
			}
		});
		menuBar.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					Funcionarios.excluir(Integer.parseInt(txtCodigo.getText()));
					atualizarTela();
				}
				catch(Exception er)
				{
					JOptionPane.showMessageDialog(null,"Não foi possível excluir esse funcionário!");
				}
			}
		});
		menuBar.add(btnExcluir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarTela();
				setTxt(false);
				txtCodigo.setEnabled(false);
			}
		});
		menuBar.add(btnCancelar);
		
		JLabel label_7 = new JLabel("|");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		menuBar.add(label_7);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( !txtCodigo.getText().equals("")  ||
					!txtNome.getText().equals("")    ||
					!txtEmail.getText().equals("")   ||
					!txtCpf.getText().equals("")     ||
					!txtTelefone.getText().equals("")||
					!txtCargo.getText().equals("")   ||
					!txtConta.getText().equals("")   ||
					!txtAgencia.getText().equals("") ||
					!txtSalario.getText().equals("") ||
					!txtEndereco.getText().equals("")||
					!txtUsuario.getText().equals("")  )
						{
							switch(situacaoAtual)
							{
								case EDITANDO:
								try
								{
									Funcionario antigo = Funcionarios.getFuncionarioByCod(Integer.parseInt(txtCodigo.getText()));
									Funcionario funcionario = null;
									try {
										funcionario = new Funcionario(Integer.parseInt(txtCodigo.getText()), txtNome.getText(),txtCpf.getText(), txtEmail.getText(),
										Float.parseFloat(txtSalario.getText()), txtTelefone.getText(), txtCargo.getText(), txtConta.getText(), txtAgencia.getText(), txtEndereco.getText(),
										txtUsuario.getText(), antigo.getSenha());
									}
									catch(Exception ex) {throw new Exception();}
									Funcionarios.alterar(funcionario);
								}
								catch(Exception ex) 
								{
									JOptionPane.showMessageDialog(null,"Não foi possível editar o funcionario com os novos dados inseridos. Alteração cancelada!");
								}
								break;
								
								case INCLUINDO:		
								try
								{
									Funcionario funcionario = null;
									try {
										funcionario = new Funcionario(Integer.parseInt(txtCodigo.getText()), txtNome.getText(),txtCpf.getText(), txtEmail.getText(),
												Float.parseFloat(txtSalario.getText()), txtTelefone.getText(), txtCargo.getText(), txtConta.getText(), txtAgencia.getText(), txtEndereco.getText(),
												txtUsuario.getText(), txtNome.getText()+"123");
									}
									catch(Exception ex)
									{throw new Exception();}
									Funcionarios.incluir(funcionario);
								}
								catch(Exception ex) 
								{
									JOptionPane.showMessageDialog(null,"Não foi possível incluir esse funcionários. Inclusão cancelada!");
								}
							
								case NAVEGANDO:
								setTxt(false);
								btnSalvar.setEnabled(false);
							}
						}
						else
							JOptionPane.showMessageDialog(null,"Não deixe campos em branco! Alteração cancelada!");
						situacaoAtual = Situacao.NAVEGANDO;
						atualizarTela();
						btnSalvar.setEnabled(false);
			}
		});
		btnSalvar.setEnabled(false);
		menuBar.add(btnSalvar);
		
		JLabel lblUsuario = new JLabel("Usu\u00E1rio:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsuario.setBounds(20, 246, 70, 32);
		panel.add(lblUsuario);
		
		JLabel lblSalrio = new JLabel("Sal\u00E1rio:");
		lblSalrio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSalrio.setBounds(348, 280, 70, 32);
		panel.add(lblSalrio);
		
		txtSalario = new JTextField();
		txtSalario.setText((String) null);
		txtSalario.setEnabled(false);
		txtSalario.setColumns(10);
		txtSalario.setBounds(407, 283, 150, 30);
		panel.add(txtSalario);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCargo.setBounds(30, 280, 70, 32);
		panel.add(lblCargo);
		
		JLabel lblConta = new JLabel("Conta:");
		lblConta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblConta.setBounds(30, 315, 70, 32);
		panel.add(lblConta);
		
		txtAgencia = new JTextField();
		txtAgencia.setText((String) null);
		txtAgencia.setEnabled(false);
		txtAgencia.setColumns(10);
		txtAgencia.setBounds(407, 318, 96, 30);
		panel.add(txtAgencia);
		
		JLabel lblAgncia = new JLabel("Ag\u00EAncia:");
		lblAgncia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAgncia.setBounds(338, 315, 70, 32);
		panel.add(lblAgncia);
		
		txtEmail = new JTextField();
		txtEmail.setText((String) null);
		txtEmail.setEnabled(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(89, 110, 355, 30);
		panel.add(txtEmail);
		
		txtCpf = new JTextField();
		txtCpf.setText((String) null);
		txtCpf.setEnabled(false);
		txtCpf.setColumns(10);
		txtCpf.setBounds(89, 144, 240, 30);
		panel.add(txtCpf);
		
		txtTelefone = new JTextField();
		txtTelefone.setText((String) null);
		txtTelefone.setEnabled(false);
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(89, 182, 240, 30);
		panel.add(txtTelefone);
		
		txtEndereco = new JTextField();
		txtEndereco.setText((String) null);
		txtEndereco.setEnabled(false);
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(89, 216, 355, 30);
		panel.add(txtEndereco);
		
		txtUsuario = new JTextField();
		txtUsuario.setText((String) null);
		txtUsuario.setEnabled(false);
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(89, 249, 240, 30);
		panel.add(txtUsuario);
		
		txtCargo = new JTextField();
		txtCargo.setText((String) null);
		txtCargo.setEnabled(false);
		txtCargo.setColumns(10);
		txtCargo.setBounds(89, 283, 240, 30);
		panel.add(txtCargo);
		
		txtConta = new JTextField();
		txtConta.setText((String) null);
		txtConta.setEnabled(false);
		txtConta.setColumns(10);
		txtConta.setBounds(89, 318, 150, 30);
		panel.add(txtConta);
		
		JLabel lblManutenoDeFuncionrios = new JLabel("Manuten\u00E7\u00E3o de Funcion\u00E1rios");
		lblManutenoDeFuncionrios.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblManutenoDeFuncionrios.setBounds(260, 43, 281, 22);
		panel.add(lblManutenoDeFuncionrios);
		
		JPanel pnlRelatorio = new JPanel();
		pnlRelatorio.setLayout(null);
		tabbedPane.addTab("Relat\u00F3rio", null, pnlRelatorio, null);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(0, 0, 596, 21);
		pnlRelatorio.add(menuBar_1);
		
		tblRelatorio = new JTable();
		tblRelatorio.setBounds(10, 298, 451, -268);
		
		DefaultTableModel model = null;
		try 
		{
			MeuResultSet dados = Funcionarios.getFuncionarios();
			 model = new DefaultTableModel(new Object[][] {},
			new String[] {
				"C\u00F3digo 1", "Nome 2", "CPF 3", "Email 4", "Endere\u00E7o 10",  "Telefone 6",  "Usu\u00E1rio 11", "Cargo 7", "Sal\u00E1rio 5", "Conta 8", "Agencia 9"
			}); 
			while(dados.next())
			{
				model.addRow(new Object[] {dados.getInt(1)+"", dados.getString(2), dados.getString(3), dados.getString(4), dados.getString(10), dados.getString(6), dados.getString(11), dados.getString(7), dados.getString(5)+"", dados.getString(8), dados.getString(9)});
			}
		}
		catch(Exception ex) 
		{
			System.out.print(ex.getMessage());
		}
		tblRelatorio.setModel(model);   
		
		pnlRelatorio.add(tblRelatorio);
		this.setVisible(true);
	}
	
	private void atualizarTela() 
	{
		Funcionario oFuncionario = null;
		try 
		{
			oFuncionario = Funcionarios.getFuncionarioByCod(Integer.parseInt(txtCodigo.getText()));
		}
		catch(Exception ex) 
		{
			try 
			{
				oFuncionario = Funcionarios.getPrimeiroRegistro();
			} 
			catch (Exception e) {} // nunca vai dar erro pois a tabela nunca estará vazia 
		}
		txtNome.setText(oFuncionario.getNome());
		txtEmail.setText(oFuncionario.getEmail());
		txtCpf.setText(oFuncionario.getCpf());
		txtEndereco.setText(oFuncionario.getEndereco());
		txtTelefone.setText(oFuncionario.getTelefone());
		txtUsuario.setText(oFuncionario.getUsuario());
		txtSalario.setText(oFuncionario.getSalario()+"");
		txtCargo.setText(oFuncionario.getCargo());
		txtConta.setText(oFuncionario.getConta());
		txtAgencia.setText(oFuncionario.getAgencia());
	}

	private void limparTela() 
	{
		txtCodigo.setText("");
		txtNome.setText("");
		txtCpf.setText("");
		txtEmail.setText("");
		txtSalario.setText("");
		txtTelefone.setText("");
		txtCargo.setText("");
		txtConta.setText("");
		txtAgencia.setText("");
		txtEndereco.setText("");
		txtUsuario.setText("");
	}
	
	private void setTxt(boolean modo)
	{
		txtCodigo.setEnabled(modo);
		txtNome.setEnabled(modo);
		txtCpf.setEnabled(modo);
		txtEmail.setEnabled(modo);
		txtSalario.setEnabled(modo);
		txtTelefone.setEnabled(modo);
		txtCargo.setEnabled(modo);
		txtConta.setEnabled(modo);
		txtAgencia.setEnabled(modo);
		txtEndereco.setEnabled(modo);
		txtUsuario.setEnabled(modo);
	}
}
