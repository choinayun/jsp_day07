package testBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DBPKG.DBConnect;

public class TestDAO {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	public TestDAO() {
		try {
			con = DBConnect.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<TestDTO> list(int start, int end) {
		System.out.println("start : " + start);
		
		if(start == 0) {
			start = 1;
		}
		
		ArrayList<TestDTO> li = new ArrayList<TestDTO>();
		// String sql = "select * from paging";
		// String sql = "select * from paging order by num desc"; 
									// 최신 글이 제일 위에 보이게 정렬
		String sql = "select B.* from(select rownum rn, A.* from" +
			    "(select * from paging order by num desc)A)B " +
                "where rn between ? and ?";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			rs = ps.executeQuery();

			while (rs.next()) {
				TestDTO dto = new TestDTO();
				dto.setCount(rs.getInt("count"));
				dto.setNum(rs.getInt("num"));
				dto.setPdate(rs.getString("pdate"));
				dto.setTitle(rs.getString("title"));

				li.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return li;
	}

	public void insert(TestDTO dto) { 
		String sql = 
				"insert into paging(num, title, pdate, count)" + 
				" values(test_num.nextval, ?, sysdate, 0)";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void count(String cnt) {
		String sql =
				"update paging set count=count+1 where num=" + cnt;
		
		try {
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getTotalPage() { 
		String sql = "select count(*) from paging";
		int cnt = 0;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt(1); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public PageCount pagingNum (int start) {
		if(start == 0) {
			start = 1; 
		}
		
		PageCount pc = new PageCount();
		int pageNum = 3; // 한 페이지당 보여질 글 갯수
		int totalPage = getTotalPage(); // 글의 총 갯수 얻어옴 
		
		int allPage=0; 
		allPage = totalPage / pageNum ;
		// 10개의 페이지를 3개로 나누게 되면 3페이지는 보장되기때문에 
		// 나머지가 0이 아닌 경우엔 페이지를 +1 해줘야한다 
		if(totalPage % pageNum != 0) {
			allPage += 1; 
		}
		
		pc.setTotEndPage(allPage);
		pc.setStartPage( (start-1) * pageNum + 1 );
		pc.setEndPage( pageNum * start );
		
		return pc;
	}
}











