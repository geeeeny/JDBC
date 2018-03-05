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
			GoodInfo goodInfo = view.getGoodInfo(); //�߰��� ���� ������ ����ڷκ��� �Է¹޾� GoodInfo ��ü ����
			
			//���̺� �� �߰��ϱ�
			//�⺻Ű �ߺ��� �Ͼ �� �ֱ� ������ ������ ����ִ� ������ �˻��� �� insert �Ѵ�.
			GoodInfo check = dao.getGoodInfo(goodInfo.getCode()); //�̹� ��ϵ� �ڵ��̸� 1�� ����, ������ null ����
			if(check == null) {	
				int rowNum = dao.insert(goodInfo);
				if(rowNum==1) {
					System.out.println(goodInfo.getCode()+" ��ǰ�� �߰��߽��ϴ�.");
				}
			}else {
				System.out.println(goodInfo.getCode() + " �ڵ� �ߺ��Դϴ�.");
			}
			/* insert
			psmt.setString(1, goodInfo.getCode());
			psmt.setString(2, goodInfo.getName());
			psmt.setInt(3, goodInfo.getPrice());
			psmt.setString(4, goodInfo.getMaker());
			
			int rowNum = psmt.executeUpdate(); // ���� ����. �߰��� ���� ������ ����
			System.out.println(rowNum+"���� �߰��Ǿ����ϴ�.");
			*/
			
			
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		ConnectionProvider.close();
	}

}
