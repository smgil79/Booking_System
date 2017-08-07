import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 * ����ȭ��
 * @author Shin Man Gil
 * */
public class Main extends JFrame implements ActionListener{
	
	JPanel pbt,pm; // ��ư���� �г� / �����г�
	JButton btInsert,btCheck,btDelete,btPrintAll,btExit;
	/**
	 * ���� ȭ�鱸��
	 * */
	public Main() {
		super("���� ���α׷� ");
		DB db = new DB();
		
		pm = new JPanel();
		JLabel mLabel = new JLabel("������ ��ư�� Ŭ���ϼ���.");
		pm.add(mLabel);
		add(pm,BorderLayout.NORTH);
		
		pbt = new JPanel();
		btInsert = new JButton("�����ϱ�");
		pbt.add(btInsert);
		
		btCheck = new JButton("����Ȯ��");
		pbt.add(btCheck);
		
		btDelete = new JButton("�������");
		pbt.add(btDelete);
		
		btPrintAll = new JButton("��ü����");
		pbt.add(btPrintAll);
		
		btExit = new JButton("������");
		pbt.add(btExit);
		add(pbt,BorderLayout.SOUTH);
		
		btInsert.addActionListener(this); 
		btCheck.addActionListener(this);
		btDelete.addActionListener(this);
		btPrintAll.addActionListener(this);
		btExit.addActionListener(this); 	//��ư ������
		
		setSize(500,200);
		setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//Main
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

	/**
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		  if(e.getSource() == btInsert ){new Booking();}
		  else if (e.getSource()==btCheck) {new Check_info();}
		  else if (e.getSource()==btDelete) {new Cancel_booking();}
		  else if (e.getSource()==btPrintAll) {
			  DB db = new DB();
			  db.printAll();}
		  else if (e.getSource() == btExit) {this.dispose();}
	}//actionPerforme

}