import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * db연동 클래스
 * @author Shin
 *
 */
public class DB {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	Booked_info bInfo;

	/**
	 * db 연결
	 */
	public DB() {
		String dburl = "jdbc:oracle:thin:@192.168.10.100:1521:orcl";
		String username = "smg";
		String password = "1234";

		try {
			con = DriverManager.getConnection(dburl, username, password);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("연결실패");
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
			System.out.println("테이블 만들기 실패");
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
	 * 데이터베이스 입력
	 * @param bInfo 입력한 값을 불러오기 위한 클래스 변수
	 * @return 입력이 재대로 되면 true, 되지않으면 false 반환
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
	 * 데이터베이스 삭제
	 * @param name 삭제할 튜플 검색을위한 변수 (이름)
	 * @param pNum 삭제할 튜플 검색을위한 변수 (전화번호)
	 * @return 삭제가 재대로 되면 true, 되지않으면 false 반환
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
	 * 데이터베이스 검색
	 * @param name 튜플 검색을 위한 변수(이름)
	 * @param pNum 튜플 검색을 위한 변수(전화번호)
	 * @return 검색한 결과의 튜플값들을 set하고 반환
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
	 * 저장되어있는 정보들을 확인하는 간단한 함수
	 * 결과를 콘솔창에 간단히 뿌려준다
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
				
				if (eName!=null) System.out.println(eName+"  "+phNum+"  "+member+"명  "+startDay+"  "+endDay+"  "+packNum+"  "+payType+"  "+price+"원");
				else System.out.println("조회결과 없음");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
}
