package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import bd.core.MeuResultSet;
import bd.daos.Pessoas;
import bd.dbos.Pessoa;
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

public class ManutDoadores extends JFrame {

	static ManutDoadores frame;// TEM Q COLOCAR ISSO AQUI
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCodigo;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JTextField txtTelefone;
	private JTextField txtCpf;
	private JTextField txtCidade;
	JButton btnSalvar;
	private JTable tblDoadores;
	private JTextField txtUf;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new ManutDoadores();
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
	public ManutDoadores() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("CADASTRO");
		//tabbedPane.setSelectedIndex(0);
		tabbedPane.setBounds(0, 0, 476, 335);
		contentPane.add(tabbedPane);
		
		JPanel pnlManutencao = new JPanel();
		pnlManutencao.setLayout(null);
		tabbedPane.addTab("Manuten\u00E7\u00E3o", null, pnlManutencao, null);
		
		JLabel label = new JLabel("C\u00F3digo:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(21, 43, 59, 32);
		pnlManutencao.add(label);
		
		JLabel label_1 = new JLabel("Nome:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(31, 83, 48, 32);
		pnlManutencao.add(label_1);
		
		txtNome = new JTextField();
		txtNome.setText((String) null);
		txtNome.setEnabled(false);
		txtNome.setColumns(10);
		txtNome.setBounds(90, 80, 355, 30);
		pnlManutencao.add(txtNome);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCpf.setBounds(32, 147, 48, 32);
		pnlManutencao.add(lblCpf);
		
		txtCodigo = new JTextField();
		txtCodigo.setText((String) null);
		txtCodigo.setEnabled(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(90, 43, 109, 30);
		pnlManutencao.add(txtCodigo);
		
		JLabel label_3 = new JLabel("E-mail:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(21, 115, 59, 32);
		pnlManutencao.add(label_3);
		
		JLabel label_4 = new JLabel("Telefone:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_4.setBounds(10, 185, 69, 32);
		pnlManutencao.add(label_4);
		
		JLabel label_7 = new JLabel("Endere\u00E7o:");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_7.setBounds(8, 221, 72, 32);
		pnlManutencao.add(label_7);
		
		txtEmail = new JTextField();
		txtEmail.setText((String) null);
		txtEmail.setEnabled(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(90, 115, 355, 30);
		pnlManutencao.add(txtEmail);
		
		txtEndereco = new JTextField();
		txtEndereco.setText((String) null);
		txtEndereco.setEnabled(false);
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(90, 224, 355, 30);
		pnlManutencao.add(txtEndereco);
		
		txtTelefone = new JTextField();
		txtTelefone.setText((String) null);
		txtTelefone.setEnabled(false);
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(90, 185, 179, 30);
		pnlManutencao.add(txtTelefone);
		
		txtCpf = new JTextField();
		txtCpf.setText((String) null);
		txtCpf.setEnabled(false);
		txtCpf.setColumns(10);
		txtCpf.setBounds(90, 150, 179, 30);
		pnlManutencao.add(txtCpf);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(10, 1, 1, 1));
		menuBar.setBounds(0, 0, 471, 32);
		pnlManutencao.add(menuBar);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtCodigo.setEnabled(true);
			}
		});
		menuBar.add(btnProcurar);
		
		JLabel label_2 = new JLabel("|");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		menuBar.add(label_2);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					Pessoas.excluir(Integer.parseInt(txtCodigo.getText()));
					atualizarTela();
				}
				catch(Exception er)
				{
					JOptionPane.showMessageDialog(null,"Não foi possível excluir essa entidade!");
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
		
		JLabel label_6 = new JLabel("|");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		menuBar.add(label_6);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( !txtCodigo.getText().equals("")  ||
					!txtNome.getText().equals("")    ||
					!txtEmail.getText().equals("")   ||
					!txtCpf.getText().equals("")     ||
					!txtTelefone.getText().equals("")||
					!txtEndereco.getText().equals("")||
					!txtCidade.getText().equals("")  ||
					!txtUf.getText().equals("")       )
					{
						try
						{
							Pessoa antiga = Pessoas.getPessoa(Integer.parseInt(txtCodigo.getText()));
							Pessoa pessoa = null;
							try 
							{
								pessoa = new Pessoa(Integer.parseInt(txtCodigo.getText()), txtNome.getText(),
								txtEmail.getText(), txtCpf.getText(), txtEndereco.getText(), antiga.getSenha(), txtTelefone.getText(), 
								txtCidade.getText(), txtUf.getText());
							}
							catch(Exception ex) {throw new Exception();}
							Pessoas.alterar(pessoa);
						}
						catch(Exception ex) 
						{
							JOptionPane.showMessageDialog(null,"Não foi possível editar o doador com os novos dados inseridos. Alteração cancelada!");
						}
					}
					else
						JOptionPane.showMessageDialog(null,"Não deixe campos em branco! Alteração cancelada!");
					atualizarTela();
					btnSalvar.setEnabled(false);
			}
		});
		btnSalvar.setEnabled(false);
		menuBar.add(btnSalvar);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCidade.setBounds(26, 259, 54, 32);
		pnlManutencao.add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setText((String) null);
		txtCidade.setEnabled(false);
		txtCidade.setColumns(10);
		txtCidade.setBounds(90, 262, 221, 30);
		pnlManutencao.add(txtCidade);
		
		JLabel lblUf = new JLabel("UF:");
		lblUf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUf.setBounds(336, 259, 54, 32);
		pnlManutencao.add(lblUf);
		
		txtUf = new JTextField();
		txtUf.setText((String) null);
		txtUf.setEnabled(false);
		txtUf.setColumns(10);
		txtUf.setBounds(370, 262, 48, 30);
		pnlManutencao.add(txtUf);
		
		JPanel pnlRelatorio = new JPanel();
		pnlRelatorio.setLayout(null);
		tabbedPane.addTab("Relat\u00F3rio", null, pnlRelatorio, null);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(0, 0, 537, 21);
		pnlRelatorio.add(menuBar_1);
		
		JLabel lblAquiEstEm = new JLabel("   Aqui, os doadores est\u00E3o ranqueados por quantidade de vezes que realizar\u00E3o doa\u00E7\u00F5es:");
		menuBar_1.add(lblAquiEstEm);
		
		tblDoadores = new JTable();
		tblDoadores.setBounds(10, 298, 451, -268);
		pnlRelatorio.add(tblDoadores);
		
		DefaultTableModel model = null;
		try 
		{
			MeuResultSet dados = Pessoas.getPessoasDoa();
			 model = new DefaultTableModel(new Object[][] {},
			new String[] {
				"C\u00F3digo", "Nome Doador", "Vezes"
			});
			while(dados.next())
			{
				model.addRow(new Object[] {dados.getInt(1)+"", dados.getString(2), dados.getInt(3)+""});
			}
		}
		catch(Exception ex) 
		{
			System.out.print(ex.getMessage());
		}
		tblDoadores.setModel(model);   
		pnlRelatorio.add(tblDoadores);
		atualizarTela();
	}
	
	private void atualizarTela() 
	{
		Pessoa oDoador = null;
		try 
		{
			oDoador = Pessoas.getPessoa(Integer.parseInt(txtCodigo.getText()));
		}
		catch(Exception ex) 
		{
			try 
			{
				oDoador = Pessoas.getPrimeiroRegistro();
			} 
			catch (Exception e) {} // nunca vai dar erro pois a tabela nunca estará vazia 
		}
		txtNome.setText(oDoador.getNome());
		txtEmail.setText(oDoador.getEmail());
		txtCpf.setText(oDoador.getCpf());
		txtEndereco.setText(oDoador.getEndereco());
		txtTelefone.setText(oDoador.getTelefone());
		txtCidade.setText(oDoador.getCidade());
		txtUf.setText(oDoador.getUf());
	}
	
	private void setTxt(boolean modo)
	{
		txtCodigo.setEnabled(modo);
		txtNome.setEnabled(modo);
		txtEmail.setEnabled(modo);
		txtEndereco.setEnabled(modo);
		txtEmail.setEnabled(modo);
		txtCpf.setEnabled(modo);
		txtTelefone.setEnabled(modo);
		txtCidade.setEnabled(modo);
		txtUf.setEnabled(modo);
	}
}
