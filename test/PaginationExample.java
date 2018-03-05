import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class PaginationExample {
	static final int PER_PAGE = 5;

	public static void main(String[] args) {

		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try	{
			View view = View.getInstance();
			GoodInfoDao dao = new GoodInfoDao();
			
			int total = dao.getCount();
			int totalPage = (int)Math.ceil((double)total/PER_PAGE);
			
			int page = 1; //첫 번째 페이지부터 일단 보여준 후 새로운 페이지를 입력받아 이동
			while(true) {
				if(page>=1 && page<=totalPage) {
					int start = (page-1)*PER_PAGE+1;
					int end = start+PER_PAGE-1;
					
					List<GoodInfo> list = dao.getGoodInfoList(start, end);
					view.printPage(list, start, page, totalPage, total);
				}else if(page == -1) {
					System.out.println("종료합니다.");
					break;
				}else {
					System.out.println("잘못된 페이지 번호입니다.");
				}
				page = view.getInt("페이지: ");
			}
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		ConnectionProvider.close();
	}

}
