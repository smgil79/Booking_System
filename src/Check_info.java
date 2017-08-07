
import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * ����Ȯ�� ȭ��
 * @author Shin
 *
 */
public class Check_info extends JFrame implements ActionListener{
	DB db = new DB();
	JPanel pan;
	JLabel sDay,eDay,packNum,member,price;
	JTextField tfName,tfPh1,tfPh2,tfPh3;
	JButton btOk,btCancel;
	
	GridBagLayout gb;
    GridBagConstraints gbc;
	
	/**
	 * ȭ�鱸��
	 * �̸��� ��ȭ��ȣ�� �Է¹޾� ��ġ�ϴ� ���� �˻�
	 */
	public Check_info() {
		this.setTitle("����Ȯ��");
		gb = new GridBagLayout();
    	setLayout(gb);
    	gbc = new GridBagConstraints();
    	gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
		
		JLabel lName = new JLabel("�̸�");
		JPanel pName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tfName = new JTextField(14);
		pName.add(tfName);
		gbAdd(lName,1,1,1,1);
        gbAdd(pName,2,1,3,1); // �̸��Է�
		

		JLabel lPhon = new JLabel("��ȭ��ȣ");
		JPanel pPhon = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tfPh1 = new JTextField(3);
        tfPh2 = new JTextField(4);
        tfPh3 = new JTextField(4);
        pPhon.add(tfPh1);
        pPhon.add(new JLabel("-"));
        pPhon.add(tfPh2);
        pPhon.add(new JLabel("-"));
        pPhon.add(tfPh3);
        gbAdd(lPhon, 1, 2, 1, 1);
        gbAdd(pPhon, 2, 2, 3, 1); //��ȭ��ȣ �Է�
        
        gbAdd(new JLabel("üũ�� : "),1,3,1,1);
        sDay = new JLabel();
        gbAdd(sDay,2,3,2,1);
        
        gbAdd(new JLabel("üũ�ƿ� : "),1,5,1,1);
		eDay = new JLabel("");
		gbAdd(eDay,2,5,1,1);
		
		gbAdd(new JLabel("�ο��� : "),1,6,1,1);
		member = new JLabel();
		gbAdd(member,2,6,1,1);
		
		gbAdd(new JLabel("��ǰ���� : "),1,7,1,1);
		packNum = new JLabel();
		gbAdd(packNum,2,7,1,1);
		
		gbAdd(new JLabel("�����ݾ� : "),1,8,1,1);
		price = new JLabel();
		gbAdd(price,2,8,1,1);					//���� ǥ��
        
        JPanel pButton = new JPanel();
        btOk= new JButton("Ȯ��");
        btCancel = new JButton("���");
        pButton.add(btOk);
        pButton.add(btCancel);
        gbAdd(pButton, 0, 10, 4, 1);
        
        btOk.addActionListener(this);
        btCancel.addActionListener(this);//��ư ������
        
		setSize(300,350);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}//Check_info
	
	/**
	 * ȭ�鱸��
	 * @param n  ����ȭ�鿡�� �Է��� �̸�
	 * @param ph ����ȭ�鿡�� �Է��� ��ȭ��ȣ
	 * @param s  ����ȭ�鿡�� �Է��� üũ�� ��¥
	 * @param e  ����ȭ�鿡�� �Է��� üũ�ƿ� ��¥
	 * @param p  ����ȭ�鿡�� �Է��� ��Ű������
	 * @param m  ����ȭ�鿡�� �Է��� �ο���
	 * @param d  ����ȭ�鿡�� �Է��� ��¥�� ���� ü���ϼ�
	 */
	private void gbAdd(JComponent c, int x, int y, int w, int h){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gb.setConstraints(c, gbc);
        gbc.insets = new Insets(1, 1, 1, 1);
        add(c, gbc);
    }//gbAdd
	
	public static void main (String args[]) {
		new Check_info();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String phNum = tfPh1.getText()+"-"+tfPh2.getText()+"-"+tfPh3.getText();
		if(e.getSource()==btOk) {
			Booked_info bInfo = new Booked_info();
			bInfo = db.printOne(tfName.getText(), phNum);
			if (bInfo.geteName()==null)JOptionPane.showMessageDialog(this, "��ȸ����� �����ϴ�."); //��ġ�ϴ� �����Ͱ� ������
			else {
				sDay.setText(bInfo.getStartDay());
				eDay.setText(bInfo.getEndDay());
				member.setText(String.valueOf(bInfo.getMember()));
				packNum.setText(bInfo.getPackNum());
				price.setText(String.valueOf(bInfo.getPrice()+"��"));
			}//else
		}//if
		else if (e.getSource()==btCancel) {this.dispose();}
	}

}//class
