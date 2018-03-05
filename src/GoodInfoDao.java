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
			rs.next(); //�׷��Լ��̹Ƿ� ������ �ϳ��� ���� ����
			//return rs.getInt(1); //ù ��° �� ����. ������ �������Ƿ� �ε����� �޾ƿ��� ����� �����
			return rs.getInt("total");
		}
	}
	
	/*�˻��Ͽ� start~end ��°�� �� ����Ʈ ���*/
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
	
	/*�˻��Ͽ� ��ü ���� ����Ʈ ���*/
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
	
	/*�˻��Ͽ� �ϳ��� �� ���, �ڵ� �ߺ��˻翡 Ȱ��*/
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
	
	/*�� �߰��ϱ�*/
	public int insert(GoodInfo goodInfo) throws Exception {
		String sql = "insert into goodsinfo (code, name, price, maker)" + "values(?,?,?,?)";
		try(PreparedStatement psmt = conn.prepareStatement(sql))
		{	
			psmt.setString(1, goodInfo.getCode());
			psmt.setString(2, goodInfo.getName());
			psmt.setInt(3, goodInfo.getPrice());
			psmt.setString(4, goodInfo.getMaker());
			
			return psmt.executeUpdate(); // ���� ����. �߰��� ���� ������ ����
		}
	}
	
	/*�� �����ϱ�*/
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
			
			return psmt.executeUpdate(); // ���� ����. ������Ʈ�� ���� ������ ����
		}
	}
	
	/*�� �����ϱ�*/
	public int delete(String code) throws Exception{
		//DB���� code�� Ÿ���� CHAR(5)�� �Ǿ������Ƿ� �ټ��ڸ��� �ƴ� �ڵ带 ������ �� ���� ������ �ذ�
		code = String.format("%-5s", code); 
		String sql = "delete from goodsinfo " + "where code=?";
		try(PreparedStatement psmt = conn.prepareStatement(sql))
		{	
			psmt.setString(1, code);
			
			return psmt.executeUpdate(); // ���� ����. ������ ���� ������ ����
		}
	}
	
	/*�˻� ����� ���� �࿡�� �� �����Ͽ� goodInfo ��ü�� ���*/
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
