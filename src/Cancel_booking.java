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
 * 예약 취소 화면
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
     * 화면구성
     * 입력한 이름과 전화번호가 일치하는 정보를 삭제
     */
    public Cancel_booking(){
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
		//pName.add(lName);
		pName.add(tfName);
		gbAdd(lName,0,0,1,1);
        gbAdd(pName,1,0,3,1);//이름 입력

		JLabel lPhon = new JLabel("전화번호");
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
        gbAdd(pPhon, 1, 3, 3, 1);//전화번호 이름
        
        JPanel pButton = new JPanel();
        btOk= new JButton("확인");
        btCancel = new JButton("취소");
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
     * 레이아웃을 구성하는 컴포넌트 좌표 및 할당크기를 결정하는 함수
     * @param c add할 컴포넌트
     * @param x	컴포넌트 x좌표
     * @param y 컴포넌트 y좌표
     * @param w 컴포넌트에 할당할 너비
     * @param h 컴포넌트에 할당할 높이
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
     * 입력한 정보를 바탕을 받아서 취소 
     */
    private void DeleteInfo() {
    	DB db = new DB();
    	String phNum = tfPh1.getText()+"-"+tfPh2.getText()+"-"+tfPh3.getText();
    	boolean ok = db.deleteInfo(tfName.getText(), phNum);
    	if(ok) JOptionPane.showMessageDialog(this, "취소완료");
    	else JOptionPane.showMessageDialog(this, "조회결과가 없습니다.");
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
