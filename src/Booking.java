
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
 * ���� ���� �Է�ȭ��
 * @author Shin
 *
 */
public class Booking  extends JFrame implements ActionListener {
	
	JTextField account;
	JTextField tfName, tfPh1,tfPh2,tfPh3,tfMember;
	JComboBox cbMember,cbPack;
	JRadioButton rbPay1,rbPay2,rbPay3;
	JButton btOk,btCancel;
	JDateChooser startDay,endDay;
	
    GridBagLayout gb;
    GridBagConstraints gbc; //���̾ƿ��� ���� ����
 
    /**
     * ȭ�鱸��
     */
    public Booking() {
    	this.setTitle("�����ϱ�");
    	gb = new GridBagLayout();
    	setLayout(gb);
    	gbc = new GridBagConstraints();
    	gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        JPanel pInfo = new JPanel();
        JTextArea info = new JTextArea();
        info.setSize(400,30);
        info.setText("                                                             -��ǰ����-\n "
        			+""
        			+"1. ���ں� \n"
        			+"�ο��� 1�� or 2�� -> 2�ν� \n"
        			+"3���̻� -> 4�ν� (4�� �ʰ��� �δ� 10,000���߰�)\n"
        			+"   - 2�ν� 1�� : 100,000��\n"
        			+"   - 4�ν� 1�� : 180,000��\n"
        			+""
        			+"2. ������ǰ\n"
        			+"��Ű��1 : �����Ǽ� + ��������ĸ� + �Һ����� + �򺼷��� (1�� 40,000��)\n"
        			+"��Ű��2 : �쵵 �պ��� + ������ Ƽ���� + �Һ����� + ������ (1�� 48,000��)\n"
        			+"��Ű��3 : �ڵ��� �ڹ��� + �װ����� �ڹ��� + īƮ���̽� + �쵵 �պ���  (1�� 50,000��)\n"
        			+"��Ű��4 : ������ Ƽ���� + īƮ���̽� + ������ + �򺼷��� (1�� 80,000��)\n");
        info.setEditable(false);
        pInfo.add(info);
        gbAdd(pInfo,0, 1, 0, 1); // ��ǰ�ȳ�
        
        JLabel lName= new JLabel("�̸�");
        JPanel pName = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfName = new JTextField(20);
        pName.add(tfName);
        gbAdd(lName, 1, 2, 1, 1);
        gbAdd(pName, 2, 2, 3, 1); //�̸��Է�
        
        JLabel lPhon= new JLabel("��ȭ��ȣ");
        JPanel pPhon = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfPh1 = new JTextField(3);
        tfPh2 = new JTextField(4);
        tfPh3 = new JTextField(4);
        pPhon.add(tfPh1);
        pPhon.add(new JLabel("-"));
        pPhon.add(tfPh2);
        pPhon.add(new JLabel("-"));
        pPhon.add(tfPh3);
        gbAdd(lPhon, 1, 3, 1, 1);
        gbAdd(pPhon, 2, 3, 3, 1); //��ȭ��ȣ�Է�
        
        JLabel lDay = new JLabel("��¥");
        JPanel pDay = new JPanel(new FlowLayout(FlowLayout.LEFT));
        startDay = new JDateChooser();
        endDay = new JDateChooser();
        pDay.add(startDay);
        pDay.add(new JLabel(" ~ "));
        pDay.add(endDay);
        gbAdd(lDay, 1,4,1,1);
        gbAdd(pDay,2,4,3,1); //��¥�Է�
        
        JLabel lMember = new JLabel("�ο���");
        JPanel pMember = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfMember = new JTextField(1);
        pMember.add(tfMember);
        pMember.add(new JLabel("��"));
        gbAdd(lMember,1,5,1,1);
        gbAdd(pMember,2,5,3,1); //�ο���
        
        JLabel lPack= new JLabel("�߰� ������ǰ");
        JPanel pPack = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] arrPack = {"�������� ����",
        					"��Ű��1 (1�� 40,000��)",
        					"��Ű��2 (1�� 48,000��)",
        					"��Ű��3 (1�� 50,000��)",
        					"��Ű��4 (1�� 80,000��)"};
        cbPack = new JComboBox(arrPack);
        pPack.add(cbPack);       
        gbAdd(lPack,1,6,1,1);
        gbAdd(pPack,2,6,3,1); //��Ű�� ����
        
        JPanel pButton = new JPanel();
        btOk= new JButton("Ȯ��");
        btCancel = new JButton("���");
        pButton.add(btOk);
        pButton.add(btCancel);
        gbAdd(pButton,1, 8, 4, 1);
        
        endDay.getDateEditor().addPropertyChangeListener(new PropertyChangeListener());
        btOk.addActionListener(this);
        btCancel.addActionListener(this);	//������Ʈ ������
        
        setSize(500,480);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//Booking
    
    /**
     * ���̾ƿ��� �����ϴ� ������Ʈ ��ǥ �� �Ҵ�ũ�⸦ �����ϴ� �Լ�
     * @param c add�� ������Ʈ
     * @param x	������Ʈ x��ǥ
     * @param y ������Ʈ y��ǥ
     * @param w ������Ʈ�� �Ҵ��� �ʺ�
     * @param h ������Ʈ�� �Ҵ��� ����
     */
    public void gbAdd(JComponent c, int x, int y, int w, int h){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;  
        gb.setConstraints(c, gbc);
        gbc.insets = new Insets(1, 1, 1, 1);
        add(c, gbc);
    }//gbAdd
   
	public static void main(String[] args) {
		new Booking();
	}
	
	/**
	 * üũ�ƿ� ��¥�� üũ�� ��¥ ������ ���� ���ϰ� �ϱ����� ������
	 * */
	class PropertyChangeListener implements java.beans.PropertyChangeListener{
		public void propertyChange(PropertyChangeEvent e) {
			if("date".equals(e.getPropertyName())) {
				if(startDay.getDate().getTime()>=endDay.getDate().getTime()) {
					try {endDay.setDate(null);}
					catch(Exception ignoreOrNot){
						JOptionPane.showMessageDialog(null, "��¥�� �߸� �Է��ϼ̽��ϴ�.");
						}//catch
				}//if
			}//if
		}//propertyChange
	}//PropertyChangeListener
	
	/**
	 * �����ʸ� �̿��� ����â���� �Ѿ����
	 *  (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btOk) {
			try{
				String name = tfName.getText();
				String phNum = tfPh1.getText()+"-"+tfPh2.getText()+"-"+tfPh3.getText();
				String sDay = (startDay.getDate().getYear()+1900)+"-"+(startDay.getDate().getMonth()+1)+"-"+startDay.getDate().getDate();
				String eDay = (endDay.getDate().getYear()+1900)+"-"+(endDay.getDate().getMonth()+1)+"-"+endDay.getDate().getDate();
				String pack = (String)cbPack.getSelectedItem();
				String member = tfMember.getText();
				long dayTime = (endDay.getDate().getTime() - startDay.getDate().getTime());
				int days = (int)dayTime/86400000+1;
				new Purchase(name, phNum, sDay, eDay, pack, member,days);
				this.dispose();}
			catch(Exception gnoreOrNot) {
				JOptionPane.showMessageDialog(null, "�Է����� ���� ĭ�� �ֽ��ϴ�.");
			}//catch
		}//if
		else if(e.getSource()==btCancel) {this.dispose();}
	}
}
