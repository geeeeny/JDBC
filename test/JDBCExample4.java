import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JDBCExample4 {

	public static void main(String[] args) throws Exception {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("상품코드 입력\n>");
		String search = scanner.nextLine();
		
		try {
			GoodInfoDao dao = new GoodInfoDao();
			GoodInfo goodInfo = dao.getGoodInfo(search);
			if(goodInfo!=null) {
				System.out.println(goodInfo);
			}else {
				System.out.println("존재하지 않는 상품입니다.");
			}
			
			List<GoodInfo> list = dao.getGoodInfoList();
			for(GoodInfo g:list) {
				System.out.println(g);
			}
			
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		ConnectionProvider.close();

	}

}
