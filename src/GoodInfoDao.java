import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GoodInfoDao {
	private Connection conn;
	
	public GoodInfoDao() {
		conn = ConnectionProvider.getConenction();
	}
	/*	
	public GoodInfoDao(Connection conn) {
		super();
		this.conn = conn;
	}
	*/
	public int getCount() throws Exception{
		String sql = "select count(*) total from goodsInfo";
		try (
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);		
		){	
			rs.next(); //그룹함수이므로 무조건 하나의 행이 나옴
			//return rs.getInt(1); //첫 번째 값 리턴. 가독성 떨어지므로 인덱스로 받아오는 방법은 비권장
			return rs.getInt("total");
		}
	}
	
	/*검색하여 start~end 번째의 행 리스트 얻기*/
	public List<GoodInfo> getGoodInfoList(int start, int end) throws Exception{
		List<GoodInfo> list = new ArrayList<>();
		String sql = "select * from (" + 
				"	select row_number() over(order by code) as seq, code, name, price, maker" + 
				"	from goodsinfo)" + 
				"where seq between ? and ?";
		
		try (PreparedStatement psmt = conn.prepareStatement(sql)) { 
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			try (ResultSet rs = psmt.executeQuery()) { 
				while(rs.next()) {
					 GoodInfo goodInfo = map(rs);
					 list.add(goodInfo);
				}
			} 
		} 
		
		return list;
	}
	
	/*검색하여 전체 행의 리스트 얻기*/
	public List<GoodInfo> getGoodInfoList() throws Exception{
		List<GoodInfo> list = new ArrayList<>();
		String sql = "select * from goodsinfo";
		
		try (
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);		
		) { 
			while(rs.next()) {
				 GoodInfo goodInfo = map(rs);
				 list.add(goodInfo);
			}
		} 
		
		return list;
	}
	
	/*검색하여 하나의 행 얻기, 코드 중복검사에 활용*/
	public GoodInfo getGoodInfo(String code) throws Exception{
		GoodInfo goodInfo = null;
		String sql = "select * from goodsInfo "+"where code='"+code+"'";
		try (
				Statement stmt = (Statement) conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);		
		) { 
			if(rs.next()) {
				goodInfo = map(rs);
			}
		} 
		
		return goodInfo;
	}
	
	/*행 추가하기*/
	public int insert(GoodInfo goodInfo) throws Exception {
		String sql = "insert into goodsinfo (code, name, price, maker)" + "values(?,?,?,?)";
		try(PreparedStatement psmt = conn.prepareStatement(sql))
		{	
			psmt.setString(1, goodInfo.getCode());
			psmt.setString(2, goodInfo.getName());
			psmt.setInt(3, goodInfo.getPrice());
			psmt.setString(4, goodInfo.getMaker());
			
			return psmt.executeUpdate(); // 쿼리 실행. 추가된 행의 개수를 리턴
		}
	}
	
	/*행 수정하기*/
	public int update(GoodInfo goodInfo) throws Exception {
		String sql = "update goodsinfo set " + 
					 "name=?," + 
					 "price=?, " + 
					 "maker=? " + 
					 "where code=?";
		try(PreparedStatement psmt = conn.prepareStatement(sql))
		{	
			psmt.setString(1, goodInfo.getName());
			psmt.setInt(2, goodInfo.getPrice());
			psmt.setString(3, goodInfo.getMaker());
			psmt.setString(4, goodInfo.getCode());
			
			return psmt.executeUpdate(); // 쿼리 실행. 업데이트된 행의 개수를 리턴
		}
	}
	
	/*행 삭제하기*/
	public int delete(String code) throws Exception{
		//DB에서 code의 타입이 CHAR(5)로 되어있으므로 다섯자리가 아닌 코드를 삭제할 수 없는 문제를 해결
		code = String.format("%-5s", code); 
		String sql = "delete from goodsinfo " + "where code=?";
		try(PreparedStatement psmt = conn.prepareStatement(sql))
		{	
			psmt.setString(1, code);
			
			return psmt.executeUpdate(); // 쿼리 실행. 삭제된 행의 개수를 리턴
		}
	}
	
	/*검색 결과로 나온 행에서 값 추출하여 goodInfo 객체에 담기*/
	public GoodInfo map(ResultSet rs) throws Exception{
		GoodInfo goodInfo = new GoodInfo();
		goodInfo = new GoodInfo();
		goodInfo.setCode(rs.getString("code"));
		goodInfo.setName(rs.getString("name"));
		goodInfo.setPrice(rs.getInt("price"));
		goodInfo.setMaker(rs.getString("maker"));
		return goodInfo;
	}
}
