import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCExample6 {

	public static void main(String[] args) {

		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			View view = View.getInstance();
			GoodInfoDao dao = new GoodInfoDao();
			GoodInfo goodInfo = view.getGoodInfo(); //추가할 행의 정보를 사용자로부터 입력받아 GoodInfo 객체 생성
			
			//테이블에 행 추가하기
			//기본키 중복이 일어날 수 있기 때문에 기존에 들어있는 값인지 검사한 후 insert 한다.
			GoodInfo check = dao.getGoodInfo(goodInfo.getCode()); //이미 등록된 코드이면 1을 리턴, 없으면 null 리턴
			if(check == null) {	
				int rowNum = dao.insert(goodInfo);
				if(rowNum==1) {
					System.out.println(goodInfo.getCode()+" 상품을 추가했습니다.");
				}
			}else {
				System.out.println(goodInfo.getCode() + " 코드 중복입니다.");
			}
			/* insert
			psmt.setString(1, goodInfo.getCode());
			psmt.setString(2, goodInfo.getName());
			psmt.setInt(3, goodInfo.getPrice());
			psmt.setString(4, goodInfo.getMaker());
			
			int rowNum = psmt.executeUpdate(); // 쿼리 실행. 추가된 행의 개수를 리턴
			System.out.println(rowNum+"행이 추가되었습니다.");
			*/
			
			
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		ConnectionProvider.close();
	}

}
