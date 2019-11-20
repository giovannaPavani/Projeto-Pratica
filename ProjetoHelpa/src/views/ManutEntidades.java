package views;
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
import java.awt.Component;

import javax.swing.JList;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Array;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class ManutEntidades extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
    /*
	public static void main(String[] args) {
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
	}

	/**
	 * Create the frame.
	 */
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
	private JTable table;
	private JTextField txtSite;
	private JTable tblEntidades;
	private Situacao situacaoAtual;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPanel pnlNecessidades;
	private JComboBox cbxNecessidade;
	
	public ManutEntidades() 
	{
		initialize();
	}
	
	private void initialize()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		situacaoAtual = Situacao.NAVEGANDO;
		atualizarTela();
		contentPane.setLayout(null);
	
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		tabbedPane.setBounds(5, 5, 681, 429);
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
					if(!txtCodigo.getText().equals(""))
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
						catch(Exception ex) { JOptionPane.showMessageDialog(null,"Não foi possível achar a entidade com esse código!"); }
					}
					else
						JOptionPane.showMessageDialog(null,"Escreva um código válido no campo de código!");
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
				setTxt(true);
				txtCodigo.grabFocus();
				btnSalvar.enable(true);
			}
		});
		menuBar_1.add(btnNovo);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {// apertar botao de editar
				situacaoAtual = Situacao.EDITANDO;
				limparTela();
				setTxt(true);
				txtCodigo.setEnabled(false);
				cbxNecessidades.setEnabled(true);
			}
		});
		menuBar_1.add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // ao clicar no botao de excluir

					try 
					{
						Entidades.excluir(Integer.parseInt(txtCodigo.getText()));
					}
					catch(Exception er)
					{
						JOptionPane.showMessageDialog(null,"Não foi possível excluir essa entidade!");
						atualizarTela();
					}
				}
		});
		menuBar_1.add(btnExcluir);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizarTela();
				txtCodigo.setEnabled(false);
			}
		});
		menuBar_1.add(btnCancelar);
		
		JLabel label_14 = new JLabel("|");
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		menuBar_1.add(label_14);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {// ao apertar botao de salvar
			if( !txtCodigo.getText().equals("")  ||
				!txtNome.getText().equals("")    ||
				!txtEmail.getText().equals("")   ||
				!txtCnpj.getText().equals("")    ||
				!txtEndereco.getText().equals("")||
				!txtTelefone.getText().equals("")||
				!txtConta.getText().equals("")   ||
				!txtAgencia.getText().equals("") ||
				!txtUsuario.getText().equals(""))
				{
					try {
						Entidade entidade = new Entidade(Integer.parseInt(txtCodigo.getText()), txtNome.getText(),
						txtEmail.getText(), txtCnpj.getText(),txtConta.getText(), txtAgencia.getText(),
						txtEndereco.getText(), txtUsuario.getText(),"", txtTelefone.getText(), 0, "", txtSite.getText());
						
						switch(situacaoAtual)
						{
							case EDITANDO:
							{
								try
								{
									Entidades.alterar(entidade);
								}
								catch(Exception ex) 
								{
									JOptionPane.showMessageDialog(null,"Não foi possível editar essa entidade!");
									atualizarTela();
								}
							}
							break;
							
							case INCLUINDO:
							{
								
								try
								{
									Entidades.incluir(entidade);
								}
								catch(Exception ex) 
								{
									JOptionPane.showMessageDialog(null,"Não foi possível editar essa entidade!");
									atualizarTela();
								}
							}
							
							case NAVEGANDO:
								setTxt(false);
						}
					}
					catch(Exception ex) 
					{
						JOptionPane.showMessageDialog(null,"Entidade inválida. Alteração cancelada!");
					}
					atualizarTela();
				}
				else
					JOptionPane.showMessageDialog(null,"Não deixe campos em branco! Alteração cancelada!");
				situacaoAtual = Situacao.NAVEGANDO;
			}
		});
		menuBar_1.add(btnSalvar);
		
		JLabel lblNecessidades = new JLabel("Necessidades:");
		lblNecessidades.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNecessidades.setBounds(468, 43, 131, 32);
		pnlManutencao.add(lblNecessidades);
		
		JLabel lblSite = new JLabel("Site:");
		lblSite.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSite.setBounds(47, 357, 32, 32);
		pnlManutencao.add(lblSite);
		
		txtSite = new JTextField();
		txtSite.setText((String) null);
		txtSite.setEnabled(false);
		txtSite.setColumns(10);
		txtSite.setBounds(89, 360, 367, 30);
		pnlManutencao.add(txtSite);
		
		cbxNecessidade = new JComboBox();
		cbxNecessidade.setEnabled(false);
		cbxNecessidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (Component c : pnlNecessidades.getComponents()) 
				{
					if (c instanceof JTextField) 
				    { 
						((JTextField)c).setVisible(false);
				    }
				}
				
				int qtd = Integer.parseInt(cbxNecessidade.getSelectedItem().toString());
				int x = 0;
				
				for (Component c : pnlNecessidades.getComponents()) {
					if(x<qtd)
					if (c instanceof JTextField) 
				    { 
						((JTextField)c).setVisible(true);
						x++;
				    }
				}
			}
		});
		cbxNecessidade.setBounds(466, 72, 41, 30);
		pnlManutencao.add(cbxNecessidade);
		
		JLabel lblItems = new JLabel("Item(s)");
		lblItems.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblItems.setBounds(517, 69, 131, 32);
		pnlManutencao.add(lblItems);
		
		pnlNecessidades = new JPanel();
		pnlNecessidades.setBounds(466, 110, 191, 211);
		pnlManutencao.add(pnlNecessidades);
		pnlNecessidades.setLayout(new GridLayout(5, 1, 0, 0));
		
		textField = new JTextField();
		textField.setColumns(10);
		pnlNecessidades.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		pnlNecessidades.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		pnlNecessidades.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		pnlNecessidades.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		pnlNecessidades.add(textField_4);
		
		
		//lstNecessidade.setModel(model);
		//lstNecessidade.setListData(vetor); // vetor = pegar as coisas que identidade de codigo x precisa
		
		JPanel pnlConsulta = new JPanel();
		tabbedPane.addTab("Relatório", null, pnlConsulta, null);
		pnlConsulta.setLayout(null);
		
		tblEntidades = new JTable();
		tblEntidades.setBounds(10, 362, 656, -332);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 676, 21);
		pnlConsulta.add(menuBar);
		
		JLabel lblAquiTemosTodas = new JLabel("Aqui, temos todas as entidades cadastradas ranqueadas pela quantidade de doa\u00E7\u00F5es para elas feitas");
		menuBar.add(lblAquiTemosTodas);
		
		pnlConsulta.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				DefaultTableModel model = null;
				try {
					MeuResultSet dados = Entidades.getEntidadesVisu();
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
							linha[11]= entidade.getImagem();
							
							model.addRow(linha);
						}
					}
					catch(Exception ex) {System.out.print(ex.getMessage());}
				
				tblEntidades.setModel(model);
				pnlConsulta.add(tblEntidades);
			}
		});
	}
	
	private void atualizarTela() {
		Entidade aEntidade = null;
		try 
		{
			aEntidade = Entidades.getEntidadeByCod(Integer.parseInt(txtCodigo.getText()));
		}
		catch(Exception ex) 
		{
			try 
			{
				aEntidade = Entidades.getPrimeiroRegistro();
			} 
			catch (Exception e) {} // nunca vai dar erro pois a tabela nunca estará vazia 
		}
		txtNome.setText(aEntidade.getNome());
		txtEmail.setText(aEntidade.getEmail());
		txtCnpj.setText(aEntidade.getCnpj());
		txtEndereco.setText(aEntidade.getEndereco());
		txtTelefone.setText(aEntidade.getTelefone());
		txtConta.setText(aEntidade.getConta());
		txtAgencia.setText(aEntidade.getAgencia());
		txtUsuario.setText(aEntidade.getUsuario());
		// exibir necessidades

		try {
			
			for (Component c : pnlNecessidades.getComponents()) 
			{
				if (c instanceof JTextField) 
			    { 
					((JTextField)c).setVisible(false);
			    }
			}
			
			MeuResultSet result = Entidades.getNecessidades(aEntidade.getCodigo());
			result.last();
			int qtd = result.getRow();

			int x = 0;
			for (Component c : pnlNecessidades.getComponents()) {
				if(x<qtd)
				if (c instanceof JTextField) 
			    { 
					((JTextField)c).setVisible(true);
					x++;
			    }
			}
			
			result.first();
			for (Component c : pnlNecessidades.getComponents()) {
			    if (c instanceof JTextField) 
			    { 
			    	try 
			    	{
			    		((JTextField)c).setText(result.getObject("Produto").toString());
			    		result.next();
			    	}
			    	catch(Exception ex)
			    	{
			    		((JTextField)c).setText("");
			    	}
			    }
			}
		}
		catch(Exception ex)
		{} // n vai dar erro pois o codigo foi passado por mim
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
	
	private void setTxt(boolean modo)
	{
		txtCodigo.setEnabled(modo);
		txtNome.setEnabled(modo);
		txtEmail.setEnabled(modo);
		txtEndereco.setEnabled(modo);
		txtEmail.setEnabled(modo);
		txtConta.setEnabled(modo);
		txtAgencia.setEnabled(modo);
		txtUsuario.setEnabled(modo);
		txtCnpj.setEnabled(modo);
		txtTelefone.setEnabled(modo);
	}
}
