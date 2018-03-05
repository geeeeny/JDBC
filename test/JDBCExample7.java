import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class JDBCExample7 {

	public static void main(String[] args) {

		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try{
			View view = View.getInstance();
			GoodInfoDao dao = new GoodInfoDao();
			
			//목록 추출 및 출력
			List<GoodInfo> list = dao.getGoodInfoList();
			view.printList(list);
			
			//수정할 상품 선택
			String code = view.getString("수정 상품 코드: ");
			GoodInfo goodInfo = dao.getGoodInfo(code);
			if(goodInfo==null) {
				System.out.println("상품 코드를 잘못입력했습니다.");
				return;
			}
			
			//수정값 입력
			GoodInfo goodInfo2 = view.getGoodInfo(goodInfo);
			
			//DB 수정
			dao.update(goodInfo2);
			
			System.out.println("수정 완료");
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		ConnectionProvider.close();
	}

}
