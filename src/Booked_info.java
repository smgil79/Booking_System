
/**
 * 정보를 입력하거나 가져가기 위한 클래스
 * @author Shin
 *
 */
public class Booked_info {

	private String eName;
	private String phNum;
	private String startDay;
	private String endDay;
	private String packNum;
	private String payType;
	private int price;
	private int member;
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public String getPhNum() {
		return phNum;
	}
	public void setPhNum(String phNum) {
		this.phNum = phNum;
	}
	
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	
	public String getPackNum() {
		return packNum;
	}
	public void setPackNum(String packNum) {
		this.packNum = packNum;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getMember() {
		return member;
	}
	public void setMember(int member) {
		this.member = member;
	}
	
	
}
