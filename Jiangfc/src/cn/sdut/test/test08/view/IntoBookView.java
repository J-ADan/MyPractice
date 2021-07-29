package cn.sdut.test.test08.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

import cn.sdut.test.test08.dao.IntoBookDao;
import cn.sdut.test.test08.model.BookModel;
import cn.sdut.test.test08.util.MysqlUtil;
import cn.sdut.test.test08.util.StringUtil;

public class IntoBookView extends JInternalFrame {
	private JTextField bookNameText;
	private JTextField bookWriterText;
	
	private MysqlUtil mysqlUtil = new MysqlUtil();
	private IntoBookDao intoBookDao = new IntoBookDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IntoBookView frame = new IntoBookView();
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
	public IntoBookView() {
		setIconifiable(true);
		setClosable(true);
		setTitle("进书处");
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("书名");
		
		JLabel lblNewLabel_1 = new JLabel("作者");
		
		bookNameText = new JTextField();
		bookNameText.setColumns(10);
		
		bookWriterText = new JTextField();
		bookWriterText.setColumns(10);
		
		JButton btnNewButton = new JButton("进书");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookAddActionPerformed(e);
			}
		});
		
		JButton btnNewButton_1 = new JButton("重置");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1))
							.addGap(29)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(bookWriterText)
								.addComponent(bookNameText, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(111)
							.addComponent(btnNewButton)
							.addGap(80)
							.addComponent(btnNewButton_1)))
					.addContainerGap(117, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(58)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(bookNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(bookWriterText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton))
					.addGap(35))
		);
		getContentPane().setLayout(groupLayout);

	}

	private void bookAddActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String bookname = this.bookNameText.getText();
		String bookwriter = this.bookWriterText.getText();
		
		if (StringUtil.isEmpty(bookname) || StringUtil.isEmpty(bookwriter)) {
			JOptionPane.showConfirmDialog(null, "两者均不为空");
		}
		
		BookModel bookModel = new BookModel(bookname, bookwriter);
		
		Connection con = null;
		try {
			con = mysqlUtil.getCon();
			int n = intoBookDao.add(con, bookModel);
			
			if (n == 1) {
				JOptionPane.showConfirmDialog(null, "添加成功");
				resetValue();
			}else {
				JOptionPane.showConfirmDialog(null, "添加失败");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null, "添加失败");
			// TODO: handle exception
		}finally {
			try {
				mysqlUtil.closeCon(con);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	private void resetValueActionPerformed(ActionEvent evt) {
		resetValue();
	}

	private void resetValue() {
		// TODO Auto-generated method stub
		this.bookNameText.setText("");
		this.bookWriterText.setText("");
	}
}
