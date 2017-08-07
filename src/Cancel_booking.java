import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.Box;
import java.awt.Button;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 * ���� ��� ȭ��
 * @author Shin
 *
 */
public class Cancel_booking extends JFrame implements ActionListener{
	DB db = new DB();
	JPanel pan;
	JLabel sDay,eDay,packNum;
	JTextField tfName,tfPh1,tfPh2,tfPh3;
	JButton btOk,btCancel;
	
	GridBagLayout gb;
    GridBagConstraints gbc;
    
    /**
     * ȭ�鱸��
     * �Է��� �̸��� ��ȭ��ȣ�� ��ġ�ϴ� ������ ����
     */
    public Cancel_booking(){
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
		//pName.add(lName);
		pName.add(tfName);
		gbAdd(lName,0,0,1,1);
        gbAdd(pName,1,0,3,1);//�̸� �Է�

		JLabel lPhon = new JLabel("��ȭ��ȣ");
		JPanel pPhon = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tfPh1 = new JTextField(3);
        tfPh2 = new JTextField(4);
        tfPh3 = new JTextField(4);
        //pPhon.add(lPhon);
        pPhon.add(tfPh1);
        pPhon.add(new JLabel("-"));
        pPhon.add(tfPh2);
        pPhon.add(new JLabel("-"));
        pPhon.add(tfPh3);
        gbAdd(lPhon, 0, 3, 1, 1);
        gbAdd(pPhon, 1, 3, 3, 1);//��ȭ��ȣ �̸�
        
        JPanel pButton = new JPanel();
        btOk= new JButton("Ȯ��");
        btCancel = new JButton("���");
        pButton.add(btOk);
        pButton.add(btCancel);
        gbAdd(pButton, 0, 10, 4, 1);
        
        btOk.addActionListener(this);
        btCancel.addActionListener(this);
        
        
		setSize(300,180);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//Cancel_booking
    
    /**
     * ���̾ƿ��� �����ϴ� ������Ʈ ��ǥ �� �Ҵ�ũ�⸦ �����ϴ� �Լ�
     * @param c add�� ������Ʈ
     * @param x	������Ʈ x��ǥ
     * @param y ������Ʈ y��ǥ
     * @param w ������Ʈ�� �Ҵ��� �ʺ�
     * @param h ������Ʈ�� �Ҵ��� ����
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
    
    
    /**
     * �Է��� ������ ������ �޾Ƽ� ��� 
     */
    private void DeleteInfo() {
    	DB db = new DB();
    	String phNum = tfPh1.getText()+"-"+tfPh2.getText()+"-"+tfPh3.getText();
    	boolean ok = db.deleteInfo(tfName.getText(), phNum);
    	if(ok) JOptionPane.showMessageDialog(this, "��ҿϷ�");
    	else JOptionPane.showMessageDialog(this, "��ȸ����� �����ϴ�.");
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btOk) {
			DeleteInfo();
			dispose();
		}
		else if (e.getSource()==btCancel) {this.dispose();}
	}
	
}
