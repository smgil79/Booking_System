
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
 * 예약확인 화면
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
	 * 화면구성
	 * 이름과 전화번호를 입력받아 일치하는 정보 검색
	 */
	public Check_info() {
		this.setTitle("예약확인");
		gb = new GridBagLayout();
    	setLayout(gb);
    	gbc = new GridBagConstraints();
    	gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
		
		JLabel lName = new JLabel("이름");
		JPanel pName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tfName = new JTextField(14);
		pName.add(tfName);
		gbAdd(lName,1,1,1,1);
        gbAdd(pName,2,1,3,1); // 이름입력
		

		JLabel lPhon = new JLabel("전화번호");
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
        gbAdd(pPhon, 2, 2, 3, 1); //전화번호 입력
        
        gbAdd(new JLabel("체크인 : "),1,3,1,1);
        sDay = new JLabel();
        gbAdd(sDay,2,3,2,1);
        
        gbAdd(new JLabel("체크아웃 : "),1,5,1,1);
		eDay = new JLabel("");
		gbAdd(eDay,2,5,1,1);
		
		gbAdd(new JLabel("인원수 : "),1,6,1,1);
		member = new JLabel();
		gbAdd(member,2,6,1,1);
		
		gbAdd(new JLabel("상품종류 : "),1,7,1,1);
		packNum = new JLabel();
		gbAdd(packNum,2,7,1,1);
		
		gbAdd(new JLabel("결제금액 : "),1,8,1,1);
		price = new JLabel();
		gbAdd(price,2,8,1,1);					//정보 표시
        
        JPanel pButton = new JPanel();
        btOk= new JButton("확인");
        btCancel = new JButton("취소");
        pButton.add(btOk);
        pButton.add(btCancel);
        gbAdd(pButton, 0, 10, 4, 1);
        
        btOk.addActionListener(this);
        btCancel.addActionListener(this);//버튼 리스너
        
		setSize(300,350);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}//Check_info
	
	/**
	 * 화면구성
	 * @param n  이전화면에서 입력한 이름
	 * @param ph 이전화면에서 입력한 전화번호
	 * @param s  이전화면에서 입력한 체크인 날짜
	 * @param e  이전화면에서 입력한 체크아웃 날짜
	 * @param p  이전화면에서 입력한 페키지종류
	 * @param m  이전화면에서 입력한 인원수
	 * @param d  이전화면에서 입력한 날짜에 대한 체류일수
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
			if (bInfo.geteName()==null)JOptionPane.showMessageDialog(this, "조회결과가 없습니다."); //일치하는 데이터가 없을떄
			else {
				sDay.setText(bInfo.getStartDay());
				eDay.setText(bInfo.getEndDay());
				member.setText(String.valueOf(bInfo.getMember()));
				packNum.setText(bInfo.getPackNum());
				price.setText(String.valueOf(bInfo.getPrice()+"원"));
			}//else
		}//if
		else if (e.getSource()==btCancel) {this.dispose();}
	}

}//class
