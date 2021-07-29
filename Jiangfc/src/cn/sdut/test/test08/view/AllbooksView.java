package cn.sdut.test.test08.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.sdut.test.test08.dao.AllBooksDao;
import cn.sdut.test.test08.dao.CardDao;
import cn.sdut.test.test08.dao.Card_BookDao;
import cn.sdut.test.test08.model.BookModel;
import cn.sdut.test.test08.model.CardModel;
import cn.sdut.test.test08.model.Card_Book;
import cn.sdut.test.test08.util.MysqlUtil;
import cn.sdut.test.test08.util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AllbooksView extends JInternalFrame {
	private JTextField s_bookname;
	private JTextField cardIdText;
	private JTable bookTable;

	private MysqlUtil mysqlUtil = new MysqlUtil();
	private AllBooksDao allBooksDao = new AllBooksDao();
	private CardDao cardDao = new CardDao();
	private Card_BookDao card_BookDao = new Card_BookDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllbooksView frame = new AllbooksView();
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
	public AllbooksView() {
		setIconifiable(true);
		setClosable(true);
		setTitle("借书还书处");
		setBounds(100, 100, 450, 364);

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblNewLabel = new JLabel("书名");

		s_bookname = new JTextField();
		s_bookname.setColumns(10);

		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookSerchActionPerformed(e);
			}
		});

		JButton btnNewButton_1 = new JButton("借阅");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookBorrowActionPerformed(e);
			}
		});

		JButton btnNewButton_2 = new JButton("归还");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnBookActionPerformed(e);
			}
		});

		JLabel lblNewLabel_2 = new JLabel("借阅人卡号");

		cardIdText = new JTextField();
		cardIdText.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(99).addComponent(btnNewButton_1)
						.addPreferredGap(ComponentPlacement.RELATED, 107, Short.MAX_VALUE).addComponent(btnNewButton_2)
						.addGap(102))
				.addGroup(groupLayout.createSequentialGroup().addGap(44).addGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addComponent(lblNewLabel).addGap(39)
								.addComponent(s_bookname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(80).addComponent(btnNewButton))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addComponent(lblNewLabel_2).addGap(29)
										.addComponent(cardIdText, GroupLayout.PREFERRED_SIZE, 158,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(62, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(24)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
								.addComponent(s_bookname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
						.addGap(30)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
						.addGap(31)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_2)
								.addComponent(cardIdText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton_2)
								.addComponent(btnNewButton_1))
						.addGap(14)));

		bookTable = new JTable();
		bookTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\u7F16\u53F7", "\u4E66\u540D", "\u4F5C\u8005" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTable.getColumnModel().getColumn(0).setResizable(false);
		bookTable.getColumnModel().getColumn(1).setResizable(false);
		bookTable.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(bookTable);
		getContentPane().setLayout(groupLayout);

		this.fillTable(new BookModel());

	}

	protected void returnBookActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String s_bookname = this.s_bookname.getText();
		String s_cardid = this.cardIdText.getText();
		if (StringUtil.isEmpty(s_bookname) || StringUtil.isEmpty(s_cardid)) {
			JOptionPane.showConfirmDialog(null, "请输入书名和借阅卡id");
		} else {
			BookModel bookModel = new BookModel(s_bookname);
			Connection con = null;
			try {
				con = mysqlUtil.getCon();
				ResultSet rs = allBooksDao.list(con, bookModel);
				while (rs.next()) {
					if ("0".equals(rs.getString(4))) {
						JOptionPane.showConfirmDialog(null, "该书籍已经归还");
					} else {
						int n = allBooksDao.update1(con, bookModel);
						if (n == 1) {
							JOptionPane.showConfirmDialog(null, "归还成功");
						}
					}
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

	private void bookBorrowActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String s_bookname = this.s_bookname.getText();
		String s_cardid = this.cardIdText.getText();

		if (StringUtil.isEmpty(s_bookname) || StringUtil.isEmpty(s_cardid)) {
			JOptionPane.showConfirmDialog(null, "请输入书名和借阅卡id");
		} else {
			BookModel bookModel = new BookModel(s_bookname);
			CardModel cardModel = new CardModel(s_cardid);
			Connection con = null;
			try {
				con = mysqlUtil.getCon();
				ResultSet rs = allBooksDao.list(con, bookModel);
				ResultSet rs1 = cardDao.list(con, cardModel);
				while (rs.next()) {
					if ("1".equals(rs.getString(4))) {
						JOptionPane.showConfirmDialog(null, "该书籍已被借阅");
					} else {
						int n = allBooksDao.update(con, bookModel);
						allBooksDao.plus(con, bookModel);
						if (n == 1) {
							JOptionPane.showConfirmDialog(null, "借阅成功");
							while (rs1.next()) {
								Card_Book card_Book = new Card_Book(rs1.getString(2), rs1.getString(3), rs.getString(2), rs.getString(3));
								card_BookDao.add(con, card_Book);
							}
						}
					}
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

	private void bookSerchActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String s_bookname = this.s_bookname.getText();
		BookModel bookModel = new BookModel();
		bookModel.setBookname(s_bookname);
		this.fillTable(bookModel);
	}

	private void fillTable(BookModel bookModel) {
		DefaultTableModel dftm = (DefaultTableModel) bookTable.getModel();
		dftm.setRowCount(0);

		Connection con = null;
		try {
			con = mysqlUtil.getCon();
			ResultSet rs = allBooksDao.list(con, bookModel);

			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("id"));
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
