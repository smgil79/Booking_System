
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
 * 예약 정보 입력화면
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
    GridBagConstraints gbc; //레이아웃을 위한 변수
 
    /**
     * 화면구성
     */
    public Booking() {
    	this.setTitle("예약하기");
    	gb = new GridBagLayout();
    	setLayout(gb);
    	gbc = new GridBagConstraints();
    	gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        JPanel pInfo = new JPanel();
        JTextArea info = new JTextArea();
        info.setSize(400,30);
        info.setText("                                                             -상품설명-\n "
        			+""
        			+"1. 숙박비 \n"
        			+"인원수 1인 or 2인 -> 2인실 \n"
        			+"3인이상 -> 4인실 (4인 초과시 인당 10,000원추가)\n"
        			+"   - 2인실 1박 : 100,000원\n"
        			+"   - 4인실 1박 : 180,000원\n"
        			+""
        			+"2. 관광상품\n"
        			+"패키지1 : 유리의성 + 테지움사파리 + 불빛정원 + 빅볼랜드 (1인 40,000원)\n"
        			+"패키지2 : 우도 왕복권 + 오설록 티스톤 + 불빛정원 + 수상레져 (1인 48,000원)\n"
        			+"패키지3 : 자동차 박물관 + 항공우주 박물관 + 카트레이싱 + 우도 왕복권  (1인 50,000원)\n"
        			+"패키지4 : 오설록 티스톤 + 카트레이싱 + 수상레져 + 빅볼랜드 (1인 80,000원)\n");
        info.setEditable(false);
        pInfo.add(info);
        gbAdd(pInfo,0, 1, 0, 1); // 상품안내
        
        JLabel lName= new JLabel("이름");
        JPanel pName = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfName = new JTextField(20);
        pName.add(tfName);
        gbAdd(lName, 1, 2, 1, 1);
        gbAdd(pName, 2, 2, 3, 1); //이름입력
        
        JLabel lPhon= new JLabel("전화번호");
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
        gbAdd(pPhon, 2, 3, 3, 1); //전화번호입력
        
        JLabel lDay = new JLabel("날짜");
        JPanel pDay = new JPanel(new FlowLayout(FlowLayout.LEFT));
        startDay = new JDateChooser();
        endDay = new JDateChooser();
        pDay.add(startDay);
        pDay.add(new JLabel(" ~ "));
        pDay.add(endDay);
        gbAdd(lDay, 1,4,1,1);
        gbAdd(pDay,2,4,3,1); //날짜입력
        
        JLabel lMember = new JLabel("인원수");
        JPanel pMember = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfMember = new JTextField(1);
        pMember.add(tfMember);
        pMember.add(new JLabel("명"));
        gbAdd(lMember,1,5,1,1);
        gbAdd(pMember,2,5,3,1); //인원수
        
        JLabel lPack= new JLabel("추가 관광상품");
        JPanel pPack = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] arrPack = {"선택하지 않음",
        					"패키지1 (1인 40,000원)",
        					"패키지2 (1인 48,000원)",
        					"패키지3 (1인 50,000원)",
        					"패키지4 (1인 80,000원)"};
        cbPack = new JComboBox(arrPack);
        pPack.add(cbPack);       
        gbAdd(lPack,1,6,1,1);
        gbAdd(pPack,2,6,3,1); //패키지 선택
        
        JPanel pButton = new JPanel();
        btOk= new JButton("확인");
        btCancel = new JButton("취소");
        pButton.add(btOk);
        pButton.add(btCancel);
        gbAdd(pButton,1, 8, 4, 1);
        
        endDay.getDateEditor().addPropertyChangeListener(new PropertyChangeListener());
        btOk.addActionListener(this);
        btCancel.addActionListener(this);	//컴포넌트 리스너
        
        setSize(500,480);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//Booking
    
    /**
     * 레이아웃을 구성하는 컴포넌트 좌표 및 할당크기를 결정하는 함수
     * @param c add할 컴포넌트
     * @param x	컴포넌트 x좌표
     * @param y 컴포넌트 y좌표
     * @param w 컴포넌트에 할당할 너비
     * @param h 컴포넌트에 할당할 높이
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
	 * 체크아웃 날짜가 체크인 날짜 앞으로 가지 못하게 하기위한 리스너
	 * */
	class PropertyChangeListener implements java.beans.PropertyChangeListener{
		public void propertyChange(PropertyChangeEvent e) {
			if("date".equals(e.getPropertyName())) {
				if(startDay.getDate().getTime()>=endDay.getDate().getTime()) {
					try {endDay.setDate(null);}
					catch(Exception ignoreOrNot){
						JOptionPane.showMessageDialog(null, "날짜를 잘못 입력하셨습니다.");
						}//catch
				}//if
			}//if
		}//propertyChange
	}//PropertyChangeListener
	
	/**
	 * 리스너를 이용해 결제창으로 넘어가게함
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
				JOptionPane.showMessageDialog(null, "입력하지 않은 칸이 있습니다.");
			}//catch
		}//if
		else if(e.getSource()==btCancel) {this.dispose();}
	}
}
