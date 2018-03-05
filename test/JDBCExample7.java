import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class JDBCExample7 {

	public static void main(String[] args) {

		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try{
			View view = View.getInstance();
			GoodInfoDao dao = new GoodInfoDao();
			
			//��� ���� �� ���
			List<GoodInfo> list = dao.getGoodInfoList();
			view.printList(list);
			
			//������ ��ǰ ����
			String code = view.getString("���� ��ǰ �ڵ�: ");
			GoodInfo goodInfo = dao.getGoodInfo(code);
			if(goodInfo==null) {
				System.out.println("��ǰ �ڵ带 �߸��Է��߽��ϴ�.");
				return;
			}
			
			//������ �Է�
			GoodInfo goodInfo2 = view.getGoodInfo(goodInfo);
			
			//DB ����
			dao.update(goodInfo2);
			
			System.out.println("���� �Ϸ�");
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		ConnectionProvider.close();
	}

}
