package cn.sdut.test.test08.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.sdut.test.test08.dao.Card_BookDao;
import cn.sdut.test.test08.model.BookModel;
import cn.sdut.test.test08.model.Card_Book;
import cn.sdut.test.test08.util.MysqlUtil;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class addCardView extends JInternalFrame {
	private JTable cardTable;
	private JTextField cardIdText;
	
	private MysqlUtil mysqlUtil = new MysqlUtil();
	private Card_BookDao card_BookDao = new Card_BookDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addCardView frame = new addCardView();
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
	public addCardView() {
		setIconifiable(true);
		setClosable(true);
		setTitle("卡片借阅信息");
		setBounds(100, 100, 450, 300);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("卡号");
		
		cardIdText = new JTextField();
		cardIdText.setColumns(10);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardSerchActionPerformed(e);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(69)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(36)
							.addComponent(lblNewLabel)
							.addGap(41)
							.addComponent(cardIdText, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton)))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(cardIdText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addGap(68))
		);
		
		cardTable = new JTable();
		cardTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u4E66\u540D", "\u4F5C\u8005"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		cardTable.getColumnModel().getColumn(0).setResizable(false);
		cardTable.getColumnModel().getColumn(1).setResizable(false);
		scrollPane.setViewportView(cardTable);
		getContentPane().setLayout(groupLayout);

	}

	private void cardSerchActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s_cardid = this.cardIdText.getText();
		Card_Book card_Book = new Card_Book();
		card_Book.setCardid(s_cardid);
		this.fillTable(card_Book);
	}
	
	private void fillTable(Card_Book card_Book) {
		DefaultTableModel dftm = (DefaultTableModel) cardTable.getModel();
		dftm.setRowCount(0);

		Connection con = null;
		try {
			con = mysqlUtil.getCon();
			ResultSet rs = card_BookDao.cardlist(con, card_Book);

			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("bookname"));
				v.add(rs.getString("bookwriter"));
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
