import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

/**
 * ����ȭ��
 * @author Shin
 *
 */
/**
 * @author Shin
 *
 */
/**
 * @author Shin
 *
 */
public class Purchase extends JFrame implements ActionListener {
	JTextField account;
	JLabel sDay,eDay,packNum;
	JRadioButton rbPay1,rbPay2,rbPay3;
	JButton btOk,btCancel;
	
    GridBagLayout gb;
    GridBagConstraints gbc;
    
    Booked_info bInfo;
	Booking bk;
    
	String name,phNum,start,end,pay,pack;
	int member,price;

	public Purchase() {
		
	}
	
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
	public Purchase(String n,String ph, String s, String e, String p,String m, int d){
		this.setTitle("����Ȯ�� �� ����");
		gb = new GridBagLayout();
    	setLayout(gb);
    	gbc = new GridBagConstraints();
    	gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        name = n;
        phNum = ph;
        start = s;
        end = e;
        pack = p;
        member = Integer.parseInt(m);
        
        price = priceCal(d-1,member,pack);
        
        gbAdd(new JLabel("�̸� : "),0,0,1,1);
        gbAdd(new JLabel(name),1,0,1,1);
        gbAdd(new JLabel("��ȭ��ȣ : "),0,1,1,1);
        gbAdd(new JLabel(phNum),1,1,1,1);
        gbAdd(new JLabel("üũ�� : "+start),0,2,1,1);
		gbAdd(new JLabel("üũ�ƿ� : "+end),0,3,1,1);
		gbAdd(new JLabel("��ǰ���� : "+pack),0,4,1,1);
		gbAdd(new JLabel("�ο��� : "+member),0,5,1,1);
		gbAdd(new JLabel("�ѱݾ�: "+price+"��"),0,6,1,1); //����ȭ�鿡�� �Է��� ���� Ȯ��
        
		JLabel lPay = new JLabel("��������");
        JPanel pPay= new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbPay1 = new JRadioButton("�������Ա�");
        rbPay2 = new JRadioButton("�ſ�ī��");
        rbPay3 = new JRadioButton("������ü");
        ButtonGroup payType = new ButtonGroup();
        payType.add(rbPay1);
        payType.add(rbPay2);
        payType.add(rbPay3);
        pPay.add(rbPay1);
        pPay.add(rbPay2);
        pPay.add(rbPay3);
        gbAdd(lPay, 0,7,1,1);
        gbAdd(pPay,1,7,3,1);//�������ܼ���
        
        JLabel lac = new JLabel("ī���ȣ");
        JPanel pAcc= new JPanel(new FlowLayout(FlowLayout.LEFT));
        account = new JTextField(20);
        account.setEnabled(false);
        pAcc.add(account);
        gbAdd(lac,0,9,1,1);
        gbAdd(pAcc,1,9,3,1);//�ſ�ī�带 �����ϸ� Ȱ��ȭ
        
        
        JPanel pButton = new JPanel();
        btOk= new JButton("Ȯ��");
        btCancel = new JButton("���");
        pButton.add(btOk);
        pButton.add(btCancel);
        gbAdd(pButton, 0, 10, 4, 1);
        
        rbPay1.addItemListener(new SelectItemListener());
        rbPay2.addItemListener(new SelectItemListener());
        rbPay3.addItemListener(new SelectItemListener());//������ư ������

        btOk.addActionListener(this);
        btCancel.addActionListener(this); // ��ư������
        
        setSize(500,400);
        setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}//Purchase
	
	/**
	 * ���� ��ư ���ÿ� ���� ī���ȣ �Է�â�� Ȱ��ȭ or ��Ȱ��ȭ
	 * */
	class SelectItemListener implements ItemListener{
    	public void itemStateChanged(ItemEvent e) {
    		AbstractButton sel = (AbstractButton)e.getItemSelectable();
    		if(e.getStateChange()==ItemEvent.SELECTED) {
    			if(sel.getText().equals("�ſ�ī��"))account.setEnabled(true);
    			else if (sel.getText().equals("�������Ա�")) account.setEnabled(false);
    			else if (sel.getText().equals("������ü")) account.setEnabled(false);
    		}//if
    	}//itemStateChanged
    }//SelectItemListener
	
	/**
	 * �Է��� ������ �������� �� �ݾ� ���
	 * @param d ü���ϼ�
	 * @param m �ο���
	 * @param s ��Ű�� ����
	 * @return ���� �ݾ��� ��ȯ
	 */
	public int priceCal(int d, int m,String s) {
		int p=0;
		if(member<3) {p = p + d*100000;}
        else if (member<5) {p = p + d*180000;}
        else {p = p + d*(180000 + (10000*m));}
		
		if(s=="��Ű��1 (1�� 40,000��)") {p = p+m*40000;}
		else if (s=="��Ű��2 (1�� 48,000��)") {p = p+m*48000;}
		else if (s=="��Ű��3 (1�� 50,000��)") {p = p+m*50000;}
		else if (s=="��Ű��4 (1�� 80,000��)") {p = p+m*80000;}
		
		return p;
	}//priceCal
	
	/**
	 * �Էµ� ������ db�� �Է�
	 */
	private void InsertInfo() {
    	Booked_info bIn = getData();
    	DB db = new DB();
    	
    	boolean ok = db.insert(bIn);
    	
    	if(ok)JOptionPane.showMessageDialog(this, "����Ϸ�");      
    	else JOptionPane.showMessageDialog(this, "�Է� ����");
    }//InsertInfo
    
    /**
     * �Է��� ������ db�� ���� �ϱ� ���� setting
     * @return setting�� Ŭ���� ��ȯ
     */
    public Booked_info getData() {
    	Booked_info bInfo = new Booked_info();
    	pay = "";
    	if(rbPay1.isSelected())pay=rbPay1.getText();
    	else if(rbPay2.isSelected())pay=rbPay2.getText();
    	else if(rbPay3.isSelected())pay=rbPay3.getText();
    	
    	bInfo.seteName(name);
    	bInfo.setPhNum(phNum);
    	bInfo.setStartDay(start);
    	bInfo.setEndDay(end);
    	bInfo.setPackNum(pack);
    	bInfo.setPayType(pay);
    	bInfo.setPrice(price);
    	bInfo.setMember(member);
    	return bInfo;
    }//getData()
	
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
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btOk) {
			InsertInfo();
			dispose();
			}//if
		else if(e.getSource()==btCancel) {this.dispose();}
		
	}
}
