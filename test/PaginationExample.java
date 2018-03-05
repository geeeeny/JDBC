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
			
			int page = 1; //ù ��° ���������� �ϴ� ������ �� ���ο� �������� �Է¹޾� �̵�
			while(true) {
				if(page>=1 && page<=totalPage) {
					int start = (page-1)*PER_PAGE+1;
					int end = start+PER_PAGE-1;
					
					List<GoodInfo> list = dao.getGoodInfoList(start, end);
					view.printPage(list, start, page, totalPage, total);
				}else if(page == -1) {
					System.out.println("�����մϴ�.");
					break;
				}else {
					System.out.println("�߸��� ������ ��ȣ�Դϴ�.");
				}
				page = view.getInt("������: ");
			}
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		ConnectionProvider.close();
	}

}
