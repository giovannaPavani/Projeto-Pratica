import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.dbos.*;
import bd.core.MeuResultSet;
import bd.daos.*;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JList;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ManutEntidades extends JFrame {

	private JPanel contentPane;
	
	//Funcionario funcAtual;
	boolean alterou = false;
    int ondeIncluir = 0;        // global --> acess�vel na classe toda
    int indice = 0;

    String nomeArquivoLeitores, nomeArquivoLivros;  // armazenar� o nome dos arquivospara que salvemos 
                                                   // os dados alterados nos arquivos certos
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManutEntidades frame = new ManutEntidades();
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
    
    private int posicaoAtual;
    private Situacao situacaoAtual;
	private JTextField txtAgencia;
	private JTextField txtNome;
	private JTextField txtCodigo;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JTextField txtUsuario;
	private JTextField txtTelefone;
	private JTextField txtCnpj;
	private JTextField txtConta;
	private JButton btnProcurar;
	private JButton btnNovo;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnCancelar;
	private JButton btnSalvar;
	private JPanel pnlManutencao;
	private JTextField txtNecessidade;
	private JTable table;
	private JTextField txtSite;
	
    public ManutEntidades()
    {
    	initialize();
    }
    
	public void initialize() {
		situacaoAtual = Situacao.NAVEGANDO;
		// se n tiver nd no banco - if(Entidades.getEntidades())
		posicaoAtual = 1;
		txtCodigo.setText("1"); // colocar posicaoAtual
		atualizarTela();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		tabbedPane.setBounds(5, 5, 681, 410);
		tabbedPane.setToolTipText("CADASTRO");
		tabbedPane.setSelectedIndex(0);
		contentPane.add(tabbedPane);
		
		pnlManutencao = new JPanel();
		pnlManutencao.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				// AO ENTRAR NA ABA CADASTRO
			}
		});
		
		tabbedPane.addTab("Manuten\u00E7\u00E3o", null, pnlManutencao, null);
		pnlManutencao.setLayout(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCdigo.setBounds(21, 43, 59, 32);
		pnlManutencao.add(lblCdigo);
		
		txtAgencia = new JTextField();
		txtAgencia.setEnabled(false);
		txtAgencia.setText((String) null);
		txtAgencia.setColumns(10);
		txtAgencia.setBounds(347, 279, 109, 30);
		pnlManutencao.add(txtAgencia);
		
		JLabel label_3 = new JLabel("Nome:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(31, 83, 48, 32);
		pnlManutencao.add(label_3);
		
		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setText((String) null);
		txtNome.setColumns(10);
		txtNome.setBounds(90, 80, 355, 30);
		pnlManutencao.add(txtNome);
		
		JLabel label_4 = new JLabel("CNPJ:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_4.setBounds(32, 115, 48, 32);
		pnlManutencao.add(label_4);
		
		txtCodigo = new JTextField();
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()== KeyEvent.VK_ENTER) // apertou enter e ta procurando
				{
					if(txtCodigo.getText() != "")
					{
						try
						{
							int cod = Integer.parseInt(txtCodigo.getText());
				            Entidade entidade = Entidades.getEntidadeByCod(cod);
				            txtNome.setText(entidade.getNome());
				            txtEmail.setText(entidade.getEmail());
				            txtCnpj.setText(entidade.getCnpj());
				            txtEndereco.setText(entidade.getEndereco());
				            txtConta.setText(entidade.getConta());
				            txtAgencia.setText(entidade.getAgencia());
				            txtUsuario.setText(entidade.getUsuario());
				            txtTelefone.setText(entidade.getTelefone());
						}
						catch(Exception ex) { JOptionPane.showMessageDialog(null,"N�o foi poss�vel achar a entidade com esse c�digo!"); }
					}
				}
			}
		});
		txtCodigo.setEnabled(false);
		txtCodigo.setText((String) null);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(90, 43, 109, 30);
		pnlManutencao.add(txtCodigo);
		
		JLabel label_5 = new JLabel("E-mail:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_5.setBounds(21, 150, 59, 32);
		pnlManutencao.add(label_5);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTelefone.setBounds(10, 191, 69, 32);
		pnlManutencao.add(lblTelefone);
		
		JLabel lblAgncia = new JLabel("Ag\u00EAncia:");
		lblAgncia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAgncia.setBounds(280, 275, 69, 32);
		pnlManutencao.add(lblAgncia);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsurio.setBounds(21, 318, 69, 32);
		pnlManutencao.add(lblUsurio);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEndereo.setBounds(10, 236, 72, 32);
		pnlManutencao.add(lblEndereo);
		
		JLabel label_11 = new JLabel("Conta:");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_11.setBounds(31, 275, 48, 32);
		pnlManutencao.add(label_11);
		
		txtEmail = new JTextField();
		txtEmail.setEnabled(false);
		txtEmail.setText((String) null);
		txtEmail.setColumns(10);
		txtEmail.setBounds(90, 150, 355, 30);
		pnlManutencao.add(txtEmail);
		
		txtEndereco = new JTextField();
		txtEndereco.setEnabled(false);
		txtEndereco.setText((String) null);
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(90, 236, 355, 30);
		pnlManutencao.add(txtEndereco);
		
		txtUsuario = new JTextField();
		txtUsuario.setEnabled(false);
		txtUsuario.setText((String) null);
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(90, 318, 179, 30);
		pnlManutencao.add(txtUsuario);
		
		txtTelefone = new JTextField();
		txtTelefone.setEnabled(false);
		txtTelefone.setText((String) null);
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(90, 191, 179, 30);
		pnlManutencao.add(txtTelefone);
		
		txtCnpj = new JTextField();
		txtCnpj.setEnabled(false);
		txtCnpj.setText((String) null);
		txtCnpj.setColumns(10);
		txtCnpj.setBounds(90, 114, 179, 30);
		pnlManutencao.add(txtCnpj);
		
		txtConta = new JTextField();
		txtConta.setEnabled(false);
		txtConta.setText((String) null);
		txtConta.setColumns(10);
		txtConta.setBounds(90, 275, 179, 30);
		pnlManutencao.add(txtConta);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setMargin(new Insets(10, 1, 1, 1));
		menuBar_1.setBounds(0, 0, 676, 32);
		pnlManutencao.add(menuBar_1);
		
		btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtCodigo.setEnabled(true);
			}
		});
		menuBar_1.add(btnProcurar);
		
		JLabel label_13 = new JLabel("|");
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 15));
		menuBar_1.add(label_13);
		
		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				situacaoAtual = Situacao.INCLUINDO;
				limparTela();
				txtCodigo.enable(true);
				txtCodigo.grabFocus();
				btnSalvar.enable(true);
			}
		});
		menuBar_1.add(btnNovo);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {// apertar botao de editar
				limparTela();
				txtCodigo.setEnabled(false);
				txtNome.setEnabled(true);
				txtEmail.setEnabled(true);
				txtEndereco.setEnabled(true);
				txtEmail.setEnabled(true);
				txtConta.setEnabled(true);
				txtAgencia.setEnabled(true);
				txtUsuario.setEnabled(true);
				txtCnpj.setEnabled(true);
				txtTelefone.setEnabled(true);
			}
		});
		menuBar_1.add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // ao clicar no botao de excluir
				situacaoAtual = Situacao.EXCLUINDO;
				try {
					Entidades.excluir(Integer.parseInt(txtCodigo.getText()));
				}
				catch(Exception er)
				{
					JOptionPane.showMessageDialog(null,"N�o foi poss�vel excluir essa entidade!");
					atualizarTela();
				}
			}
		});
		menuBar_1.add(btnExcluir);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizarTela();
				situacaoAtual = Situacao.NAVEGANDO;
			}
		});
		menuBar_1.add(btnCancelar);
		
		JLabel label_14 = new JLabel("|");
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		menuBar_1.add(label_14);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// ao apertar botao de salvar
				if(situacaoAtual == Situacao.EDITANDO)
				{
					try
					{
						Entidade entidade = new Entidade(Integer.parseInt(txtCodigo.getText()), txtNome.getText(),
						txtEmail.getText(), txtCnpj.getText(),txtConta.getText(), txtAgencia.getText(),
						txtEndereco.getText(), txtUsuario.getText(),"", txtTelefone.getText(), 0, "", txtSite.getText());
						Entidades.alterar(entidade);
					}
					catch(Exception ex) 
					{
						JOptionPane.showMessageDialog(null,"N�o foi poss�vel editar essa entidade!");
						atualizarTela();
					}
					
				}
			}
		});
		menuBar_1.add(btnSalvar);
		
		txtNecessidade = new JTextField();
		txtNecessidade.setBounds(475, 69, 179, 240);
		pnlManutencao.add(txtNecessidade);
		txtNecessidade.setColumns(10);
		
		JLabel lblNecessidades = new JLabel("Necessidades:");
		lblNecessidades.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNecessidades.setBounds(475, 40, 131, 32);
		pnlManutencao.add(lblNecessidades);
		
		JLabel lblSite = new JLabel("Site:");
		lblSite.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSite.setBounds(305, 320, 69, 32);
		pnlManutencao.add(lblSite);
		
		txtSite = new JTextField();
		txtSite.setText((String) null);
		txtSite.setEnabled(false);
		txtSite.setColumns(10);
		txtSite.setBounds(347, 320, 179, 30);
		pnlManutencao.add(txtSite);
		//lstNecessidade.setModel(model);
		//lstNecessidade.setListData(vetor); // vetor = pegar as coisas que identidade de codigo x precisa
		
		JPanel pnlConsulta = new JPanel();
		tabbedPane.addTab("Consulta", null, pnlConsulta, null);
		pnlConsulta.setLayout(null);
		
		pnlConsulta.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				DefaultTableModel model = null;
				try {
					MeuResultSet dados = Entidades.getEntidades();
					 model = new DefaultTableModel(new Object[][] {},
					new String[] {
						"C\u00F3digo", "Nome", "Email", "CNPJ", "Telefone", "Conta", "Ag\u00EAncia", "Endere\u00E7o", "Usu\u00E1rio", "Visualiza\u00E7\u00F5es", "Descri\u00E7ao", "Site"
					});
						for(int i =0; dados.next(); i++)
						{
							Entidade entidade = Entidades.getEntidadeById(i);
							Object[] linha = new Object[12];
							linha[0]= entidade.getCodigo();
							linha[1]= entidade.getNome();
							linha[2]= entidade.getEmail();
							linha[3]= entidade.getCnpj();
							linha[4]= entidade.getTelefone();
							linha[5]= entidade.getConta();
							linha[6]= entidade.getAgencia();
							linha[7]= entidade.getEndereco();
							linha[8]= entidade.getUsuario();
							linha[9]= entidade.getVisualizacoes();
							linha[10]= entidade.getDescricao();
							linha[11]= entidade.getSite();
							
							model.addRow(linha);
						}
					}
					 catch(Exception ex) {System.out.print(ex.getMessage());}
				
				table = new JTable();
				table.setModel(model);
				table.setBounds(678, 369, -679, -370);
				pnlConsulta.add(table);
			}
		});
	}
	
	private void atualizarTela() {
		//if(!asEntidades.estaVazio()) // yo no sei se ta certa essa logica pro maligno
		Entidade aEntidade;
		try 
		{
			aEntidade = Entidades.getEntidadeByCod(Integer.parseInt(txtCodigo.getText()));
			txtNome.setText(aEntidade.getNome());
			txtEmail.setText(aEntidade.getEmail());
			txtCnpj.setText(aEntidade.getCnpj());
			txtEndereco.setText(aEntidade.getEndereco());
			txtTelefone.setText(aEntidade.getTelefone());
			txtConta.setText(aEntidade.getConta());
			txtAgencia.setText(aEntidade.getAgencia());
			txtUsuario.setText(aEntidade.getUsuario());
		}
		catch(Exception ex) 
		{
			// fzr catch
		}
	}
	
	private void limparTela() 
	{
		txtCodigo.setText("");
		txtNome.setText("");
		txtEmail.setText("");
		txtCnpj.setText("");
		txtEndereco.setText("");
		txtTelefone.setText("");
		txtConta.setText("");
		txtAgencia.setText("");
		txtUsuario.setText("");
	}
	
}