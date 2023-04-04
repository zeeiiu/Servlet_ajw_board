package edu.global.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import edu.global.board.vo.BoardVO;

public class BDao {

	private DataSource dataSource;

	public BDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BoardVO contentView(String strID) {
		BoardVO board = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;  //PreparedStatement Statement보다 성능적으로 좋은것,, 그래서 물음표도 제공
		ResultSet resultSet = null;

		try {
			String query = "select * from mvc_board where bid = ?";

			connection = dataSource.getConnection(); 
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, Integer.valueOf(strID));
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				int bid = resultSet.getInt("bid");

				String bname = resultSet.getString("bname");
				String btitle = resultSet.getString("btitle");
				String bcontent = resultSet.getString("bcontent");

				Timestamp bdate = resultSet.getTimestamp("bdate");

				int bhit = resultSet.getInt("bhit");
				int bgroup = resultSet.getInt("bgroup");
				int bstep = resultSet.getInt("bstep");
				int bindent = resultSet.getInt("bindent");

				board = new BoardVO(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (resultSet != null)
					resultSet.close();

				if (preparedStatement != null)
					preparedStatement.close();

				if (connection != null)
					connection.close();

			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return board;
	}

	public List<BoardVO> list() {
		List<BoardVO> boards = new ArrayList<BoardVO>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try { // 댓글이 그룹별로 묶이기 때문에 by bgroup으로 묶어주고 그 그룹 내에서 step으로 정렬한다.  
			String query = "select * from mvc_board order by bgroup desc, bstep asc";

			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				int bid = resultSet.getInt("bid");

				String bname = resultSet.getString("bname");
				String btitle = resultSet.getString("btitle");
				String bcontent = resultSet.getString("bcontent");

				Timestamp bdate = resultSet.getTimestamp("bdate");

				int bhit = resultSet.getInt("bhit");
				int bgroup = resultSet.getInt("bgroup");
				int bstep = resultSet.getInt("bstep");
				int bindent = resultSet.getInt("bindent");

				BoardVO vo = new BoardVO(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);
				boards.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (resultSet != null)
					resultSet.close();

				if (preparedStatement != null)
					preparedStatement.close();

				if (connection != null)
					connection.close();

			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return boards;

	}

	public int modify(String bid, String bname, String btitle, String bcontent) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int rn = 0;

		try {
			String query = "update mvc_board set bname = ?, btitle = ?, bcontent = ? where bid = ?";

			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, bname);
			preparedStatement.setString(2, btitle);
			preparedStatement.setString(3, bcontent);
			preparedStatement.setInt(4, Integer.valueOf(bid));

			rn = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {

				if (preparedStatement != null)
					preparedStatement.close();

				if (connection != null)
					connection.close();

			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return rn;
	}
	public int delete(String bid) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int rn = 0;

		try {
			String query = "delete mvc_board where bid = ?";

			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);


			preparedStatement.setInt(1, Integer.valueOf(bid));

			rn = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {

				if (preparedStatement != null)
					preparedStatement.close();

				if (connection != null)
					connection.close();

			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return rn;
	}
	public int write (String bname, String btitle, String bcontent) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int rn = 0;

		try {
			String query = "insert into mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0 )";

			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);


			preparedStatement.setString(1, bname );   // 첫 번째 ?에 들어가는 건 bname
			preparedStatement.setString(2, btitle );
			preparedStatement.setString(3, bcontent );

			rn = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {

				if (preparedStatement != null)
					preparedStatement.close();

				if (connection != null)
					connection.close();

			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return rn;
	}
	public BoardVO reply_view(String strID) {
		BoardVO board = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;  //PreparedStatement Statement보다 성능적으로 좋은것,, 그래서 물음표도 제공
		ResultSet resultSet = null;

		try {
			String query = "select * from mvc_board where bid = ?";

			connection = dataSource.getConnection(); 
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, Integer.valueOf(strID));
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				int bid = resultSet.getInt("bid");

				String bname = resultSet.getString("bname");
				String btitle = resultSet.getString("btitle");
				String bcontent = resultSet.getString("bcontent");

				Timestamp bdate = resultSet.getTimestamp("bdate");

				int bhit = resultSet.getInt("bhit");
				int bgroup = resultSet.getInt("bgroup");
				int bstep = resultSet.getInt("bstep");
				int bindent = resultSet.getInt("bindent");

				board = new BoardVO(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (resultSet != null)
					resultSet.close();

				if (preparedStatement != null)
					preparedStatement.close();

				if (connection != null)
					connection.close();

			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return board;
	} 
	public int reply(String bid, String bname, String btitle, String bcontent, 
			           String bgroup, String bstep, String bindent) {

		replyShape(bgroup, bstep); // 댓글의 댓글은 플러스 1 해줘야하니깐

		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int rn = 0;

		try {
			String query = "insert into mvc_board (bId, bName, bTitle, bContent, bGroup, bStep, bIndent) values (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";

			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, bname);
			preparedStatement.setString(2, btitle);
			preparedStatement.setString(3, bcontent);
			preparedStatement.setInt(4, Integer.parseInt(bgroup)); // parseInt means valueOf. Just this own is older version. 
			preparedStatement.setInt(5, Integer.parseInt(bstep) + 1); // 자기꺼
			preparedStatement.setInt(6, Integer.parseInt(bindent) + 1); // 자기꺼

			rn = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {

				if (preparedStatement != null)
					preparedStatement.close();

				if (connection != null)
					connection.close();

			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return rn;
	}
	public int replyShape(String strGroup, String strStep) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int rn = 0;
		

		try {  // 자기가 들어갈 자리를 마련하기 위해서 밑으로 싹 밀어버림.  
			String query = "update mvc_board set bStep = bStep + 1 where bGroup = ? and bStep > ?"; 

			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);


			 preparedStatement.setInt(1, Integer.parseInt(strGroup));
	         preparedStatement.setInt(2, Integer.parseInt(strStep));
	         


			rn = preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {

				if (preparedStatement != null)
					preparedStatement.close();

				if (connection != null)
					connection.close();

			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return rn;
	}
}
