package cn.sdut.test.test08.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.sdut.test.test08.dao.BookDao;
import cn.sdut.test.test08.model.BookModel;
import cn.sdut.test.test08.util.MysqlUtil;

public class BookView extends JInternalFrame {
	private JTable bookTable;

	private MysqlUtil mysqlUtil = new MysqlUtil();
	private BookDao bookDao = new BookDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookView frame = new BookView();
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
	public BookView() {
		setIconifiable(true);
		setClosable(true);
		setTitle("图书信息");
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("图书借阅排行");
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(68)
							.addComponent(lblNewLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(46)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addComponent(lblNewLabel)
					.addGap(30)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(72, Short.MAX_VALUE))
		);
		
		bookTable = new JTable();
		bookTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u4E66\u540D", "\u4F5C\u8005"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bookTable.getColumnModel().getColumn(0).setResizable(false);
		bookTable.getColumnModel().getColumn(1).setResizable(false);
		bookTable.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(bookTable);
		getContentPane().setLayout(groupLayout);
		
		fillTable(new BookModel());
	}
	
	
	private void fillTable(BookModel bookModel) {
		DefaultTableModel dftm = (DefaultTableModel) bookTable.getModel();
		dftm.setRowCount(0);
		
		Connection con = null;
		try {
			con = mysqlUtil.getCon();
			ResultSet rs = bookDao.list(con, bookModel);
			
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
		}finally {
			try {
				mysqlUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
