package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import bd.dbos.*;
import bd.core.MeuResultSet;
import bd.daos.*;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class ManutEntidades extends JFrame {

	private JFrame frame;
	private JTextField txtNome;
	private JTextField txtCodigo;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JTextField txtUsuario;
	private JTextField txtTelefone;
	private JTextField txtCnpj;
	private JButton btnProcurar;
	private JButton btnNovo;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnCancelar;
	private JButton btnSalvar;
	private JPanel pnlManutencao;
	private JTextField txtSite;
	private JTable tblEntidades;
	private Situacao situacaoAtual;
	private JTextField txtNcsd1;
	private JTextField txtNcsd2;
	private JTextField txtNcsd3;
	private JTextField txtNcsd4;
	private JTextField txtNcsd5;
	private JPanel pnlNecessidades;
	
	public ManutEntidades() 
	{
		initialize();
	}

	
	private void initialize()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 416);
		//frame.getContentPane().setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frame.getContentPane());
		frame.getContentPane().setLayout(null);
		
		situacaoAtual = Situacao.NAVEGANDO;
		atualizarTela();
		frame.getContentPane().setLayout(null);
	
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		tabbedPane.setBounds(5, 5, 681, 374);
		tabbedPane.setToolTipText("CADASTRO");
		tabbedPane.setSelectedIndex(0);
		frame.getContentPane().add(tabbedPane);
		
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
						int codProc = Integer.parseInt(txtCodigo.getText());
						atualizarTela();
						if(codProc != Integer.parseInt(txtCodigo.getText()))
							JOptionPane.showMessageDialog(null,"Não foi possível achar a entidade com esse código!");
					}
					else
						JOptionPane.showMessageDialog(null,"Escreva um código válido no campo de código!");
					txtCodigo.setEnabled(false);
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
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsurio.setBounds(21, 265, 69, 32);
		pnlManutencao.add(lblUsurio);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEndereo.setBounds(10, 228, 72, 32);
		pnlManutencao.add(lblEndereo);
		
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
		txtEndereco.setBounds(90, 228, 355, 30);
		pnlManutencao.add(txtEndereco);
		
		txtUsuario = new JTextField();
		txtUsuario.setEnabled(false);
		txtUsuario.setText((String) null);
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(90, 265, 179, 30);
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
				setTxtNecessidades(false);
				txtNcsd1.setEnabled(true);
				btnSalvar.enable(true);
			}
		});
		menuBar_1.add(btnNovo);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {// apertar botao de editar
				situacaoAtual = Situacao.EDITANDO;
				setTxt(true);
				txtCodigo.setEnabled(false);
				btnSalvar.setEnabled(true);
			}
		});
		menuBar_1.add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // ao clicar no botao de excluir
				try 
				{
					Entidades.excluir(Integer.parseInt(txtCodigo.getText()));
					atualizarTela();
				}
				catch(Exception er)
				{
					JOptionPane.showMessageDialog(null,"Não foi possível excluir essa entidade!");
				}
			}
		});
		menuBar_1.add(btnExcluir);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizarTela();
				setTxt(false);
				txtCodigo.setEnabled(false);
			}
		});
		menuBar_1.add(btnCancelar);
		
		JLabel label_14 = new JLabel("|");
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		menuBar_1.add(label_14);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {// ao apertar botao de salvar
			if( !txtCodigo.getText().equals("")  ||
				!txtNome.getText().equals("")    ||
				!txtEmail.getText().equals("")   ||
				!txtCnpj.getText().equals("")    ||
				!txtEndereco.getText().equals("")||
				!txtTelefone.getText().equals("")||
				!txtUsuario.getText().equals("") ||
				!txtNcsd1.getText().equals("")    )
				{
					switch(situacaoAtual)
					{
						case EDITANDO:
						try
						{
							Entidade antiga = Entidades.getEntidadeByCod(Integer.parseInt(txtCodigo.getText()));
							Entidade entidade = null;
							try {
								entidade = new Entidade(Integer.parseInt(txtCodigo.getText()), txtNome.getText(),
								txtCnpj.getText(), txtEndereco.getText(), txtEmail.getText(), txtTelefone.getText(), txtUsuario.getText(),antiga.getSenha(), 
								antiga.getVisualizacoes(), antiga.getDescricao(), antiga.getImagem(), txtSite.getText());
							}
							catch(Exception ex) {throw new Exception();}
							Entidades.alterar(entidade);
						}
						catch(Exception ex) 
						{
							JOptionPane.showMessageDialog(null,"Não foi possível editar a entidade com os novos dados inseridos. Alteração cancelada!");
						}
						break;
						
						case INCLUINDO:		
						try
						{
							Entidade entidade = null;
							try {
								entidade = new Entidade(Integer.parseInt(txtCodigo.getText()), txtNome.getText(), txtCnpj.getText(),
								txtEndereco.getText(), txtEmail.getText(),txtTelefone.getText(), txtUsuario.getText(), txtNome.getText()+"123", 0,
								"", "", txtSite.getText());
							}
							catch(Exception ex)
							{throw new Exception();}
							Entidades.incluir(entidade);
							
							int qtd = 1;
							String produtos[] = new String[qtd];
							produtos[0] = txtNcsd1.getText();
							Entidades.inserirNecessidades(entidade.getCodigo(), produtos);
						}
						catch(Exception ex) 
						{
							JOptionPane.showMessageDialog(null,"Não foi possível incluir essa entidade. Inclusão cancelada!");
						}
					
						case NAVEGANDO:
						setTxt(false);
						btnSalvar.setEnabled(false);
					}
				}
				else
					JOptionPane.showMessageDialog(null,"Não deixe campos em branco! Alteração cancelada!");
				situacaoAtual = Situacao.NAVEGANDO;
				txtNcsd1.setEnabled(false);
				atualizarTela();
				btnSalvar.setEnabled(false);
			}
		});
		menuBar_1.add(btnSalvar);
		
		JLabel lblNecessidades = new JLabel("Necessidades:");
		lblNecessidades.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNecessidades.setBounds(466, 67, 131, 32);
		pnlManutencao.add(lblNecessidades);
		
		JLabel lblSite = new JLabel("Site:");
		lblSite.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSite.setBounds(48, 301, 32, 32);
		pnlManutencao.add(lblSite);
		
		txtSite = new JTextField();
		txtSite.setText((String) null);
		txtSite.setEnabled(false);
		txtSite.setColumns(10);
		txtSite.setBounds(90, 304, 367, 30);
		pnlManutencao.add(txtSite);
		
		pnlNecessidades = new JPanel();
		pnlNecessidades.setBounds(466, 110, 191, 211);
		pnlManutencao.add(pnlNecessidades);
		pnlNecessidades.setLayout(new GridLayout(5, 1, 0, 0));
		
		txtNcsd1 = new JTextField();
		txtNcsd1.setEnabled(false);
		txtNcsd1.setColumns(10);
		pnlNecessidades.add(txtNcsd1);
		
		txtNcsd2 = new JTextField();
		txtNcsd2.setEnabled(false);
		txtNcsd2.setColumns(10);
		pnlNecessidades.add(txtNcsd2);
		
		txtNcsd3 = new JTextField();
		txtNcsd3.setEnabled(false);
		txtNcsd3.setColumns(10);
		pnlNecessidades.add(txtNcsd3);
		
		txtNcsd4 = new JTextField();
		txtNcsd4.setEnabled(false);
		txtNcsd4.setColumns(10);
		pnlNecessidades.add(txtNcsd4);
		
		txtNcsd5 = new JTextField();
		txtNcsd5.setEnabled(false);
		txtNcsd5.setColumns(10);
		pnlNecessidades.add(txtNcsd5);
		
		JPanel pnlRelatorio = new JPanel();
		tabbedPane.addTab("Relatório", null, pnlRelatorio, null);
		pnlRelatorio.setLayout(null);
		
		tblEntidades = new JTable();
		tblEntidades.setBounds(10, 392, 656, -362);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 676, 21);
		pnlRelatorio.add(menuBar);
		
		JLabel lblAquiTemosTodas = new JLabel("Aqui, temos todas as entidades cadastradas ranqueadas pela quantidade de doa\u00E7\u00F5es que receberam:");
		lblAquiTemosTodas.setForeground(Color.GRAY);
		menuBar.add(lblAquiTemosTodas);
		
		DefaultTableModel model = null;
		try 
		{
			MeuResultSet dados = Entidades.getEntidadesVisu();
			 model = new DefaultTableModel(new Object[][] {},
			new String[] {
				"C\u00F3digo", "Nome", "Email", "CNPJ", "Telefone", "Endere\u00E7o", "Usu\u00E1rio", "Visualiza\u00E7\u00F5es", "Descri\u00E7ao", "Site"
			});
			while(dados.next())
			{
				model.addRow(new Object[] {dados.getInt(1)+"", dados.getString(2), dados.getString(3), dados.getString(4), dados.getString(5), dados.getString(6), dados.getString(7), dados.getString(8), dados.getString(9), dados.getString(10), dados.getString(11)});
			}
		}
		catch(Exception ex) 
		{
			System.out.print(ex.getMessage());
		}
		tblEntidades.setModel(model);   
		pnlRelatorio.add(tblEntidades);	
		
		frame.setVisible(true);
	}
		
	private void atualizarTela() 
	{
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
		txtUsuario.setText(aEntidade.getUsuario());
		
		// exibir necessidades
		try {
			
			for (Component c : pnlNecessidades.getComponents()) 
			{
				if (c instanceof JTextField) 
			    { 
					((JTextField)c).setVisible(false);
					((JTextField)c).setEnabled(false);
			    }
			}
			
			MeuResultSet result = Entidades.getNecessidades(aEntidade.getCodigo());
			result.last();
			int qtd = result.getRow();

			int x = 0;
			for (Component c : pnlNecessidades.getComponents()) 
			{
				if(x<qtd)
				if (c instanceof JTextField) 
			    { 
					((JTextField)c).setVisible(true);
					x++;
			    }
			}
			
			result.first();
			for (Component c : pnlNecessidades.getComponents()) 
			{
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
		txtUsuario.setText("");
	}
	
	private void setTxt(boolean modo)
	{
		txtNcsd1.setEnabled(false);
		txtCodigo.setEnabled(modo);
		txtNome.setEnabled(modo);
		txtEmail.setEnabled(modo);
		txtEndereco.setEnabled(modo);
		txtEmail.setEnabled(modo);
		txtUsuario.setEnabled(modo);
		txtCnpj.setEnabled(modo);
		txtTelefone.setEnabled(modo);
	}
	
	private void setTxtNecessidades(boolean modo)
	{
		for (Component c : pnlNecessidades.getComponents()) 
		{
			if (c instanceof JTextField) 
		    { 
				((JTextField)c).setVisible(false);
		    }
		}
	}
}
