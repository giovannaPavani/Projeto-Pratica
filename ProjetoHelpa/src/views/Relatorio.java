package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bd.core.MeuResultSet;
import bd.daos.Doacoes;
import bd.daos.Entidades;

import javax.swing.JMenuBar;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

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
		setBounds(100, 100, 611, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(31, 48, 554, 340);
		contentPane.add(panel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 676, 30);
		panel.add(menuBar);
		
		tblDoacoes = new JTable();
		tblDoacoes.setBounds(10, 331, 536, -283);
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
		
		JLabel label = new JLabel("   Aqui est\u00E3o todas as doa\u00E7\u00F5es que as pessoas se comprometeram a fazer para a sua entidade");
		label.setForeground(Color.GRAY);
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(0, 34, 519, 14);
		panel.add(label);
		
		JLabel lblRelatrioDeDoaes = new JLabel("RELAT\u00D3RIO DE DOA\u00C7\u00D5ES");
		lblRelatrioDeDoaes.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRelatrioDeDoaes.setBounds(263, 11, 46, 14);
		contentPane.add(lblRelatrioDeDoaes);
	}
}
