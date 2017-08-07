import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * db���� Ŭ����
 * @author Shin
 *
 */
public class DB {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	Booked_info bInfo;

	/**
	 * db ����
	 */
	public DB() {
		String dburl = "jdbc:oracle:thin:@192.168.10.100:1521:orcl";
		String username = "smg";
		String password = "1234";

		try {
			con = DriverManager.getConnection(dburl, username, password);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�������");
		}
//		this.init();
	}//DB

/*	private void init() {			
		try {
			String dr0 = "DROP TABLE user2";
			ps = con.prepareStatement(dr0);
			ps.executeUpdate();
			dr0 = "PURGE RECYCLEBIN";		
			ps = con.prepareStatement(dr0);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���̺� ����� ����");
		}
	}
*/
/*	public void createTable(String tabName) {
		try {
			String dr1 = "CREATE TABLE "+tabName+"( eName VARCHAR2(15),\r\n" + 
					"  ePhoneNum VARCHAR2(13) PRIMARY KEY )";		
			ps = con.prepareStatement(dr1);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}*/
	
	/**
	 * �����ͺ��̽� �Է�
	 * @param bInfo �Է��� ���� �ҷ����� ���� Ŭ���� ����
	 * @return �Է��� ���� �Ǹ� true, ���������� false ��ȯ
	 */
	public boolean insert(Booked_info bInfo) {	
		boolean ok = false;
		try {
			String dr2 = "INSERT INTO BOOKED_INFO VALUES('" 
					      +bInfo.geteName() + "','"
					      +bInfo.getPhNum()+"','"
					      +bInfo.getMember()+"','"
					      +bInfo.getStartDay()+"','"
					      +bInfo.getEndDay()+"','"
					      +bInfo.getPackNum()+"','"
					      +bInfo.getPayType()+"','"
					      +bInfo.getPrice()+"')";
			ps = con.prepareStatement(dr2);
			int r = ps.executeUpdate();	
			if(r>0)ok=true;
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ok;
	}//insert
	
	/**
	 * �����ͺ��̽� ����
	 * @param name ������ Ʃ�� �˻������� ���� (�̸�)
	 * @param pNum ������ Ʃ�� �˻������� ���� (��ȭ��ȣ)
	 * @return ������ ���� �Ǹ� true, ���������� false ��ȯ
	 */
	public boolean deleteInfo(String name, String pNum) {
		boolean ok = false;
		try {
			String dr5="DELETE FROM BOOKED_INFO WHERE eName = '"+name+"' AND phNum = '"+pNum+"'";
			ps = con.prepareStatement(dr5);
			int r = ps.executeUpdate();	
			if(r>0)ok=true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ok;
	}//deleteInfo
	
	/**
	 * �����ͺ��̽� �˻�
	 * @param name Ʃ�� �˻��� ���� ����(�̸�)
	 * @param pNum Ʃ�� �˻��� ���� ����(��ȭ��ȣ)
	 * @return �˻��� ����� Ʃ�ð����� set�ϰ� ��ȯ
	 */
	public Booked_info printOne(String name, String pNum) {
		Booked_info bInfo = new Booked_info();
		try {
			String dr4="SELECT * FROM BOOKED_INFO WHERE eName = '"+name+"' AND phNum = '"+pNum+"'";
			ps = con.prepareStatement(dr4);
			rs = ps.executeQuery();
			while(rs.next()) {
				String eName = rs.getString("eName");
				String phNum = rs.getString("phNum");
				int member = rs.getInt("memberNum");
				String startDay = rs.getString("sDay");
				String endDay = rs.getString("eDay");
				String packNum = rs.getString("packNum");
				String payType = rs.getString("payType");
				int price = rs.getInt("price");
				
				bInfo.seteName(eName);
				bInfo.setPhNum(phNum);
				bInfo.setMember(member);
				bInfo.setStartDay(startDay);
				bInfo.setEndDay(endDay);
				bInfo.setPackNum(packNum);
				bInfo.setPayType(payType);
				bInfo.setPrice(price);
			}//while
		}//try
		catch(Exception e) {
		}
		return bInfo;
	}//printOne

	/**
	 * ����Ǿ��ִ� �������� Ȯ���ϴ� ������ �Լ�
	 * ����� �ܼ�â�� ������ �ѷ��ش�
	 */
	public void printAll() {
		try {
			String dr3 = "SELECT * FROM BOOKED_INFO";
			ps = con.prepareStatement(dr3);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String eName = rs.getString("eName");
				String phNum = rs.getString("phNum");
				int member = rs.getInt("memberNum");
				String startDay = rs.getString("sDay");
				String endDay = rs.getString("eDay");
				String packNum = rs.getString("packNum");
				String payType = rs.getString("payType");
				int price = rs.getInt("price");
				
				if (eName!=null) System.out.println(eName+"  "+phNum+"  "+member+"��  "+startDay+"  "+endDay+"  "+packNum+"  "+payType+"  "+price+"��");
				else System.out.println("��ȸ��� ����");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
}
