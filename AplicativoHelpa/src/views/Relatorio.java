package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import bd.core.MeuResultSet;
import bd.daos.Doacoes;
import javax.swing.JMenuBar;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;

public class Relatorio extends JFrame {

	private JPanel contentPane;
	private JTable tblDoacoes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Relatorio frame = new Relatorio();
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
	public Relatorio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 48, 554, 298);
		contentPane.add(panel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 554, 30);
		panel.add(menuBar);
		
		JLabel lblTodasAsDoaes = new JLabel("   Todas as doa\u00E7\u00F5es feitas pela plataforma web do Helpa!");
		menuBar.add(lblTodasAsDoaes);
		
		tblDoacoes = new JTable();
		tblDoacoes.setEnabled(false);
		tblDoacoes.setBounds(10, 289, 536, -241);
		// id, produto, quantidade, nomeEntidade, data, entregue
		DefaultTableModel model = null;
		try 
		{
			MeuResultSet dados = Doacoes.getDoacoes();
			model = new DefaultTableModel(new Object[][] {},
			new String[] {
				"Identificação", "Produto", "Quantidade", "Entidade", "Data", "Entregue?"
			});
			
			while(dados.next())
			{
				model.addRow(new Object[] {dados.getInt(1)+"", dados.getString(2), dados.getString(3), dados.getString(4), dados.getDate(5)+"", dados.getString(6)});
			}
			
			tblDoacoes.setModel(model); 
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
			System.out.print(ex.getMessage());
		}
		panel.add(tblDoacoes);
		
		JLabel lblRelatrioDeDoaes = new JLabel("RELAT\u00D3RIO DE DOA\u00C7\u00D5ES");
		lblRelatrioDeDoaes.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblRelatrioDeDoaes.setBounds(172, 11, 260, 26);
		contentPane.add(lblRelatrioDeDoaes);
	}
}
