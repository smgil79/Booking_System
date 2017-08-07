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
 * 결제화면
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
	 * 화면구성
	 * @param n  이전화면에서 입력한 이름
	 * @param ph 이전화면에서 입력한 전화번호
	 * @param s  이전화면에서 입력한 체크인 날짜
	 * @param e  이전화면에서 입력한 체크아웃 날짜
	 * @param p  이전화면에서 입력한 페키지종류
	 * @param m  이전화면에서 입력한 인원수
	 * @param d  이전화면에서 입력한 날짜에 대한 체류일수
	 */
	public Purchase(String n,String ph, String s, String e, String p,String m, int d){
		this.setTitle("예약확인 및 결제");
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
        
        gbAdd(new JLabel("이름 : "),0,0,1,1);
        gbAdd(new JLabel(name),1,0,1,1);
        gbAdd(new JLabel("전화번호 : "),0,1,1,1);
        gbAdd(new JLabel(phNum),1,1,1,1);
        gbAdd(new JLabel("체크인 : "+start),0,2,1,1);
		gbAdd(new JLabel("체크아웃 : "+end),0,3,1,1);
		gbAdd(new JLabel("상품종류 : "+pack),0,4,1,1);
		gbAdd(new JLabel("인원수 : "+member),0,5,1,1);
		gbAdd(new JLabel("총금액: "+price+"원"),0,6,1,1); //이전화면에서 입력한 정보 확인
        
		JLabel lPay = new JLabel("결제수단");
        JPanel pPay= new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbPay1 = new JRadioButton("무통장입금");
        rbPay2 = new JRadioButton("신용카드");
        rbPay3 = new JRadioButton("계좌이체");
        ButtonGroup payType = new ButtonGroup();
        payType.add(rbPay1);
        payType.add(rbPay2);
        payType.add(rbPay3);
        pPay.add(rbPay1);
        pPay.add(rbPay2);
        pPay.add(rbPay3);
        gbAdd(lPay, 0,7,1,1);
        gbAdd(pPay,1,7,3,1);//결제수단선택
        
        JLabel lac = new JLabel("카드번호");
        JPanel pAcc= new JPanel(new FlowLayout(FlowLayout.LEFT));
        account = new JTextField(20);
        account.setEnabled(false);
        pAcc.add(account);
        gbAdd(lac,0,9,1,1);
        gbAdd(pAcc,1,9,3,1);//신용카드를 선택하면 활성화
        
        
        JPanel pButton = new JPanel();
        btOk= new JButton("확인");
        btCancel = new JButton("취소");
        pButton.add(btOk);
        pButton.add(btCancel);
        gbAdd(pButton, 0, 10, 4, 1);
        
        rbPay1.addItemListener(new SelectItemListener());
        rbPay2.addItemListener(new SelectItemListener());
        rbPay3.addItemListener(new SelectItemListener());//라디오버튼 리스너

        btOk.addActionListener(this);
        btCancel.addActionListener(this); // 버튼리스너
        
        setSize(500,400);
        setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}//Purchase
	
	/**
	 * 라디오 버튼 선택에 따라 카드번호 입력창이 활성화 or 비활성화
	 * */
	class SelectItemListener implements ItemListener{
    	public void itemStateChanged(ItemEvent e) {
    		AbstractButton sel = (AbstractButton)e.getItemSelectable();
    		if(e.getStateChange()==ItemEvent.SELECTED) {
    			if(sel.getText().equals("신용카드"))account.setEnabled(true);
    			else if (sel.getText().equals("무통장입금")) account.setEnabled(false);
    			else if (sel.getText().equals("계좌이체")) account.setEnabled(false);
    		}//if
    	}//itemStateChanged
    }//SelectItemListener
	
	/**
	 * 입력한 정보를 바탕으로 총 금액 계산
	 * @param d 체류일수
	 * @param m 인원수
	 * @param s 패키지 종류
	 * @return 계산된 금액을 반환
	 */
	public int priceCal(int d, int m,String s) {
		int p=0;
		if(member<3) {p = p + d*100000;}
        else if (member<5) {p = p + d*180000;}
        else {p = p + d*(180000 + (10000*m));}
		
		if(s=="패키지1 (1인 40,000원)") {p = p+m*40000;}
		else if (s=="패키지2 (1인 48,000원)") {p = p+m*48000;}
		else if (s=="패키지3 (1인 50,000원)") {p = p+m*50000;}
		else if (s=="패키지4 (1인 80,000원)") {p = p+m*80000;}
		
		return p;
	}//priceCal
	
	/**
	 * 입력된 정보를 db에 입력
	 */
	private void InsertInfo() {
    	Booked_info bIn = getData();
    	DB db = new DB();
    	
    	boolean ok = db.insert(bIn);
    	
    	if(ok)JOptionPane.showMessageDialog(this, "예약완료");      
    	else JOptionPane.showMessageDialog(this, "입력 오류");
    }//InsertInfo
    
    /**
     * 입력한 정보를 db에 삽입 하기 위해 setting
     * @return setting된 클래스 반환
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
