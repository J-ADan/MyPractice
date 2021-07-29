package cn.sdut.test.test08.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import cn.sdut.test.test08.dao.CardDao;
import cn.sdut.test.test08.model.CardModel;
import cn.sdut.test.test08.util.MysqlUtil;
import cn.sdut.test.test08.util.StringUtil;

public class CardView extends JInternalFrame {
	private JTextField cardIdText;
	private JTextField cardNameText;
	
	private MysqlUtil mysqlUtil = new MysqlUtil();
	private CardDao cardDao = new CardDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardView frame = new CardView();
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
	public CardView() {
		setIconifiable(true);
		setTitle("Card");
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("卡片ID");
		
		JLabel lblNewLabel_1 = new JLabel("持卡人姓名");
		
		cardIdText = new JTextField();
		cardIdText.setColumns(10);
		
		cardNameText = new JTextField();
		cardNameText.setColumns(10);
		
		JButton btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardAddActionPerformed(e);
			}
		});
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerform(e);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(48)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1)))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap(90, Short.MAX_VALUE)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(cardIdText)
								.addComponent(cardNameText, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(82)
							.addComponent(btnNewButton_1)))
					.addContainerGap(116, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(54)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cardIdText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(cardNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton))
					.addContainerGap(71, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
	
	
	private void cardAddActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String cardid = this.cardIdText.getText();
		String cardname = this.cardNameText.getText();
		
		if (StringUtil.isEmpty(cardid) || StringUtil.isEmpty(cardname)) {
			JOptionPane.showConfirmDialog(null, "两者均不为空");
		}
		
		CardModel cardmodel = new CardModel(cardid, cardname);
		
		Connection con = null;
		try {
			con = mysqlUtil.getCon();
			int n = cardDao.add(con, cardmodel);
			if (n == 1) {
				JOptionPane.showConfirmDialog(null, "添加成功");
				resetValue();
			}else {
				JOptionPane.showConfirmDialog(null, "添加失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null, "添加失败");
		}finally {
			try {
				mysqlUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void resetValueActionPerform(ActionEvent evt) {
		// TODO Auto-generated method stub
		resetValue();
	}

	/*
	 * 重置表单
	 */
	
	private void resetValue() {
		this.cardIdText.setText("");
		this.cardNameText.setText("");
	}
}
