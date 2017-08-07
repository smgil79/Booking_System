import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 * 메인화면
 * @author Shin Man Gil
 * */
public class Main extends JFrame implements ActionListener{
	
	JPanel pbt,pm; // 버튼모음 패널 / 메인패널
	JButton btInsert,btCheck,btDelete,btPrintAll,btExit;
	/**
	 * 메인 화면구성
	 * */
	public Main() {
		super("예약 프로그램 ");
		DB db = new DB();
		
		pm = new JPanel();
		JLabel mLabel = new JLabel("수행할 버튼을 클릭하세요.");
		pm.add(mLabel);
		add(pm,BorderLayout.NORTH);
		
		pbt = new JPanel();
		btInsert = new JButton("예약하기");
		pbt.add(btInsert);
		
		btCheck = new JButton("예약확인");
		pbt.add(btCheck);
		
		btDelete = new JButton("예약취소");
		pbt.add(btDelete);
		
		btPrintAll = new JButton("전체보기");
		pbt.add(btPrintAll);
		
		btExit = new JButton("나가기");
		pbt.add(btExit);
		add(pbt,BorderLayout.SOUTH);
		
		btInsert.addActionListener(this); 
		btCheck.addActionListener(this);
		btDelete.addActionListener(this);
		btPrintAll.addActionListener(this);
		btExit.addActionListener(this); 	//버튼 리스너
		
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