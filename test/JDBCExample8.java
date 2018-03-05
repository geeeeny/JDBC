import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class JDBCExample8 {

	public static void main(String[] args) {

		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try{
			View view = View.getInstance();
			GoodInfoDao dao = new GoodInfoDao();
			
			//목록 추출 및 출력
			List<GoodInfo> list = dao.getGoodInfoList();
			view.printList(list);
			
			//삭제할 상품 선택
			String code = view.getString("삭제할 상품 코드: ");
			int result = dao.delete(code);
			if(result==1) {
				System.out.println("삭제 완료");
			}else { //삭제된 행이 없으면(코드를 잘못입력) 0을 리턴받음
				System.out.println("삭제 실패: 존재하지 않는 상품 코드입니다.");
			}
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		ConnectionProvider.close();
	}

}
