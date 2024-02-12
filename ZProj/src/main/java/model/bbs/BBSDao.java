package model.bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jakarta.servlet.ServletContext;
import model.MemberDTO;
import service.DaoService;

public class BBSDao implements DaoService<BBSDto>{
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;
		
	public BBSDao(ServletContext context) {
		try {
			//server.xml에 등록한 Resource 값을 찾아서 DB 연결
			Context ctx = new InitialContext();
			DataSource source = (DataSource)ctx.lookup("java:comp/env/ictuser");
			conn = source.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}////

	@Override
	public void close() {
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		}
		catch(SQLException e) {}
	}////

	@Override
	public List<BBSDto> selectList(Map map, String bbstype) {
		List<BBSDto> items = new Vector<>();
		boolean bbstypeflag = false; 
		String sql="SELECT b.*,name FROM bbs b JOIN member m ON b.id=m.id ";
		if(bbstype.equals("PYTHON") || bbstype.equals("HTML") || bbstype.equals("CSS") || bbstype.equals("JAVA")) {
			sql += "WHERE BBS_NAME = ?";
			bbstypeflag = true;
		}
		sql+= " ORDER BY no DESC";
		try {
			psmt = conn.prepareStatement(sql);
			if(bbstypeflag) {
				psmt.setString(1, bbstype);
			}
			rs=psmt.executeQuery();
			while(rs.next()) {
				BBSDto dto = new BBSDto();
				dto.setContent(rs.getString(4));
				dto.setHitCount(rs.getString(5));
				dto.setId(rs.getString(2));
				dto.setNo(rs.getString(1));
				dto.setPostDate(rs.getDate(6));
				dto.setTitle(rs.getString(3));
				dto.setBbsName(rs.getString(7));
				dto.setName(rs.getString(8));
				items.add(dto);
			}
		}catch(SQLException e) {e.printStackTrace();}
		return items;
	}
	
	public List<BBSDto> selectList(Map map, int no) {
		List<BBSDto> items = new Vector<>();
		String sql="SELECT b.*,name FROM bbs b JOIN member m ON b.id=m.id ORDER BY no DESC Fetch FIRST ? ROWS ONLY";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, no);
			rs=psmt.executeQuery();
			while(rs.next()) {
				BBSDto dto = new BBSDto();
				dto.setContent(rs.getString(4));
				dto.setHitCount(rs.getString(5));
				dto.setId(rs.getString(2));
				dto.setNo(rs.getString(1));
				dto.setPostDate(rs.getDate(6));
				dto.setTitle(rs.getString(3));
				dto.setBbsName(rs.getString(7));
				dto.setName(rs.getString(8));
				items.add(dto);
			}
		}catch(SQLException e) {e.printStackTrace();}
		return items;
	}
	

	@Override
	public BBSDto selectOne(String... params) {
		return null;
	}

	@Override
	public int getTotalRecordCount(Map map) {
		return 0;
	}

	@Override
	public int insert(BBSDto dto) {
		return 0;
	}

	@Override
	public int update(BBSDto dto) {
		return 0;
	}

	@Override
	public int delete(BBSDto dto) {
		return 0;
	}
	
	public String quarter_bbs() {
		String bbsname = "";
		
		return bbsname;
	}
	
	public boolean isMember(String username, String password) {
		String sql = "Select COUNT(*) FROM member WHERE id=? and pwd=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, username);
			psmt.setString(2, password);
			rs = psmt.executeQuery();
			rs.next();
			if(rs.getInt(1)==0) return false;
		}
		catch(SQLException e) {e.printStackTrace();return false;}
		return true;
	}/////
	
	//비밀번호 확인
	public boolean passwordchk(String pwd, String pwd2) {
		if(pwd.equals(pwd2)) return true;
		return false;
	}
	
	public boolean iduse(String username) { //아이디를 체크함으로써 1이면 해당 아이디 존재, 0이면 미존재
		String sql = "Select COUNT(*) FROM member WHERE id=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, username);
			rs = psmt.executeQuery();
			rs.next();
			if(rs.getInt(1)==0) return true; //해당 아이디 사용 가능
		}
		catch(SQLException e) {e.printStackTrace();return false;}
		return false;
	}
	
	public String writesuccess(String username, String title, String content, String bbsname) {
		String msg="";
		String sql = "INSERT INTO BBS(NO, ID, TITLE, CONTENT, POSTDATE,BBS_NAME) values(SEQ_BBS.NEXTVAL,?,?,?,SYSDATE,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, username);
			psmt.setString(2, title);
			psmt.setString(3, content);
			psmt.setString(4, bbsname);
			System.out.println(psmt.executeUpdate()+"행이 수정되었숩니다.");
			msg = "등록 완료";
		}catch(SQLException e) {
			e.printStackTrace();
			msg = "등록 실패";
			}
		return msg;
	}
	
	public String signUp(String username, String password, String password2,String name, String gender, String grade, String self, String inters,String email) {
		String msg;
		String signupsql;
		if(!iduse(username)) {
			msg = "이미 존재하는 아이디입니다.";
		}
		else if(!passwordchk(password, password2)) {
			msg = "비밀번호가 일치하지 않습니다.";
		}
		else {
			signupsql = "INSERT INTO Member(ID, PWD, NAME, GENDER, EDUCATION, SELFINTRODUCE, INTERS,EMAIL) values(?,?,?,?,?,?,?,?)";
			try {
				psmt = conn.prepareStatement(signupsql);
				psmt.setString(1, username);
				psmt.setString(2, password);
				psmt.setString(3, name);
				psmt.setString(4, gender);
				psmt.setString(5, grade);
				psmt.setString(6, self);
				psmt.setString(7, inters);
				psmt.setString(8, email);
				
				psmt.executeUpdate();
				msg ="회원가입을 축하드립니다.";
			}catch(SQLException e) {
				msg="업데이트 오류";
			}
		}
		return msg;
	}

	public String dataout(String colname, String id) {
		String val="";
		String sql="SELECT ? FROM MEMBER where ID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(2, id);
			if("pwd".equals(colname)) {
				psmt.setString(1, "PWD");
			}else if("name".equals(colname)) {
				psmt.setString(1, "NAME");				
			}else if("GENDER".equals(colname)) {
				psmt.setString(1, "GENDER");								
			}else if("EDUCATION".equals(colname)) {
				psmt.setString(1, "EDUCATION");								
			}else if("SELFINTRODUCE".equals(colname)) {
				psmt.setString(1, "SELFINTRODUCE");				
			}else if("EMAIL".equals(colname)) {
				psmt.setString(1, "EMAIL");
			}
		}catch(SQLException e) {
			
		}
		return val;
	}
	
	public boolean bbsDelete(String no) {
		String sql = "DELETE ";
		return true;
	}
	
	public BBSDto bbsdata(String no) {
		BBSDto bto = null;
		try {
			String sql = "SELECT b.*, m.name FROM bbs b JOIN member m ON b.id = m.id WHERE b.No = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			if(rs.next()) {
				bto = new BBSDto();
				bto.setNo(rs.getString(1));
				bto.setId(rs.getString(2));
				bto.setTitle(rs.getString(3));
				bto.setContent(rs.getString(4));
				bto.setHitCount(rs.getString(5));
				bto.setPostDate(rs.getDate(6));
				bto.setName(rs.getString(8));
				bto.setBbsName(rs.getString(7));
			}
		}catch(SQLException e) {e.printStackTrace();}
		return bto;
	}
	
	public int likedata(String no, String id) {
		String sql = "SELECT * FROM likes WHERE no =? and id=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			psmt.setString(2, id);
			rs = psmt.executeQuery();
			rs.next();
			if(rs.getInt(1)==0) return 0;
			return 1;
		}catch(SQLException e) {e.printStackTrace();return 0;}
	}
	
	public MemberDTO mydata(String id) {
		MemberDTO mto = null;
		try {
			String sql = "SELECT * FROM member WHERE ID = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				mto = new MemberDTO();
				mto.setId(rs.getString(1));
				mto.setPwd(rs.getString(2));
				mto.setName(rs.getString(3));
				mto.setGender(rs.getString(4));
				mto.setEducation(rs.getString(6));
				mto.setSelfintroduce(rs.getString(7));
				mto.setEmail(rs.getString(10));
				
				String interests = rs.getString(5);
				if(interests != null) {
					String[] interestArray = interests.split(",");
					mto.setInters(interestArray);
				}else {
					mto.setInters(new String[0]);				
				}
			}
		}catch(SQLException e) {e.printStackTrace();}
		return mto;
	} 
	
	public String bbsupdate(String no, String title, String content) {
		String sql = "UPDATE bbs SET title =?, content=? WHERE NO = ?";
		try {
			System.out.println(no+title+content);
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setString(3, no);
			psmt.executeUpdate();
			int updaterow = psmt.executeUpdate();
			if(updaterow == 1) {
				return "수정완료";
			}else {
				return "수정실패";
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return "수정실패";
		}
	}
	
	public boolean setpwd(String cpwd, String id) throws SQLException {
		String sql="UPDATE member SET PWD = ?, UPDATEINFO = DEFAULT WHERE ID = ?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, cpwd);
		psmt.setString(2, id);
		psmt.executeUpdate();
		int updaterow = psmt.executeUpdate();
		if(updaterow == 1) {
			return true;
		}else {
			return false;
		}
	}
	
	public String updatemyinfo(String username, String password,String name, String gender, String grade, String self, String inters, String email){
		String sql = "UPDATE member SET ";
		List<Object> params = new ArrayList<>();
		List<String> updateFields = new ArrayList<>();

		MemberDTO mto = mydata(username);		
		if(password != null && !password.equals(mto.getPwd())) {
			updateFields.add("pwd=?");
		    params.add(password);
		}
		if(name != null && !name.equals(mto.getName())) {
			updateFields.add("name=?");
		    params.add(name);
		}
		if(!gender.equals(mto.getGender())) {
			updateFields.add("gender=?");
		    params.add(gender);
		}
		if(!grade.equals(mto.getEducation())) {
			updateFields.add("education=?");
		    params.add(grade);
		}
		if(!self.equals(mto.getSelfintroduce())) {
			updateFields.add("selfintroduce=?");
		    params.add(self);
		}
		if(!inters.equals(mto.getInters())) {
			updateFields.add("inters=?");
		    params.add(inters);
		}
		if(!email.equals(mto.getEmail())) {
			updateFields.add("email=?");
		    params.add(email);
		}
		sql += String.join(", ", updateFields);
		sql += ", UPDATEINFO = default WHERE id=?";

		params.add(username);
		String msg="";
		try {			
			psmt = conn.prepareStatement(sql);
			for (int i = 0; i < params.size(); i++) {
			    psmt.setObject(i + 1, params.get(i));
			}
			System.out.println(psmt.executeUpdate()+"행이 수정되었숩니다.");
			msg ="정보를 수정했습니다.";
		}catch(SQLException e) {
			e.printStackTrace();
			msg = "정보 수정 실패";
			System.out.println(msg);
		}
		return msg;
	}

	public boolean deleteBBSData(String no) {
		System.out.println("Deleting data with ID: " + no);
        String sql = "DELETE FROM bbs WHERE no = ?";
        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, no);

            int updaterow = psmt.executeUpdate();
    		if(updaterow == 1) {
    			System.out.println("Data deleted successfully with ID: " + no);
    			return true;
    		}else {
    			return false;
    		}
        } catch (SQLException e) {e.printStackTrace(); 
        System.out.println("Error deleting data: " + e.getMessage());
        return false;} 
	}
	
	public String likebbscontrol(String no, String id, int type) {
		String sql ="";
		String msg = "";
		if(type == 1) {
			sql = "INSERT INTO LIKES (NO, ID,LIKEDATE) VALUES (?, ?,SYSDATE)";
		}else {
			sql = "DELETE FROM LIKES WHERE NO = ? AND ID = ?";
		}
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, no);
			psmt.setString(2, id);
			int updaterow = psmt.executeUpdate();
			if(updaterow == 1) {
    			System.out.println("Like bbsname successfully with No: " + no);
    			msg = type==1? "좋아요" : "좋아요 취소";
    		}else {
    			msg = "오류 발생";
    		}
			return msg;
		}catch (SQLException e) {e.printStackTrace(); return "오류 발생";}
	}
}
