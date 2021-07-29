package cn.sdut.test.test08.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import cn.sdut.test.test08.dao.Card_BookDao;
import cn.sdut.test.test08.model.Card_Book;
import cn.sdut.test.test08.util.MysqlUtil;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class addBookView extends JInternalFrame {
	private JTextField bookNameText;
	private JTable bookTable;

	
	private MysqlUtil mysqlUtil = new MysqlUtil();
	private Card_BookDao card_BookDao = new Card_BookDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addBookView frame = new addBookView();
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
	public addBookView() {
		setIconifiable(true);
		setClosable(true);
		setTitle("图书借阅记录");
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("书名");
		
		bookNameText = new JTextField();
		bookNameText.setColumns(10);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookSerchActionPerformed(e);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(46)
							.addComponent(lblNewLabel)
							.addGap(48)
							.addComponent(bookNameText, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(btnNewButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(88)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(76, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(bookNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(31)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(50, Short.MAX_VALUE))
		);
		
		bookTable = new JTable();
		bookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5361\u53F7", "\u6301\u5361\u4EBA\u59D3\u540D"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTable.getColumnModel().getColumn(0).setResizable(false);
		bookTable.getColumnModel().getColumn(1).setResizable(false);
		scrollPane.setViewportView(bookTable);
		getContentPane().setLayout(groupLayout);

	}

	private void bookSerchActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s_bookname = this.bookNameText.getText();
		Card_Book card_Book = new Card_Book();
		card_Book.setBookname(s_bookname);
		this.fillTable(card_Book);
	}

	private void fillTable(Card_Book card_Book) {
		// TODO Auto-generated method stub
		DefaultTableModel dftm = (DefaultTableModel) bookTable.getModel();
		dftm.setRowCount(0);

		Connection con = null;
		try {
			con = mysqlUtil.getCon();
			ResultSet rs = card_BookDao.booklist(con, card_Book);

			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("cardid"));
				v.add(rs.getString("cardname"));
				dftm.addRow(v);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				mysqlUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
