import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class JDBCExample8 {

	public static void main(String[] args) {

		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try{
			View view = View.getInstance();
			GoodInfoDao dao = new GoodInfoDao();
			
			//��� ���� �� ���
			List<GoodInfo> list = dao.getGoodInfoList();
			view.printList(list);
			
			//������ ��ǰ ����
			String code = view.getString("������ ��ǰ �ڵ�: ");
			int result = dao.delete(code);
			if(result==1) {
				System.out.println("���� �Ϸ�");
			}else { //������ ���� ������(�ڵ带 �߸��Է�) 0�� ���Ϲ���
				System.out.println("���� ����: �������� �ʴ� ��ǰ �ڵ��Դϴ�.");
			}
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		ConnectionProvider.close();
	}

}
