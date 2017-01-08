package br.unifor.lixo;//package br.unifor.dao;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//
//import br.unifor.bean.Hobbie;
//import br.unifor.bean.Message;
//import br.unifor.bean.Profile;
//
//public class ProfileDAO {
//	
//	public ProfileDAO() {}
//	
//	public Profile userLogin(String login, String password) throws SQLException {
//		
//		String sql = "select * from user where lg_user = '"+login+"' and pass_user = '"+password+"'";
//		
//		ResultSet rs = ConnectionDAO.getConexao().createStatement().executeQuery(sql);
//		
//		if (rs.next()) {
//			Profile profile = new Profile();
//			
//			profile.setId(rs.getString("id_user"));
//			profile.setName(rs.getString("nm_user"));
//			profile.setLogin(rs.getString("lg_user"));
//			profile.setSex(rs.getString("sex_user"));
//			profile.setPassword(rs.getString("pass_user"));
//			profile.setImagePath(rs.getString("img_user"));
//			profile.setFriendsList(this.getFriends(profile.getId()));
//			profile.setHobbies(this.getHobbies(profile.getId()));
//			profile.setMessages(this.getMessages(profile.getId()));
//			
//			ConnectionDAO.closeConnection();
//			
//			return profile;
//		}
//		
//		ConnectionDAO.closeConnection();
//		
//		return null;
//	}
//	
//	public ArrayList<Profile> search(String login) throws SQLException {
//		String sql = "select * from user where lg_user ilike '"+login+"'";
//		ArrayList<Profile> profiles = new ArrayList<Profile>();
//		
//		ResultSet rs = ConnectionDAO.getConexao().createStatement().executeQuery(sql);
//		
//		while (rs.next()) {
//			Profile profile = new Profile();
//			
//			profile.setId(rs.getString("id_user"));
//			profile.setName(rs.getString("nm_user"));
//			profile.setLogin(rs.getString("lg_user"));
//			profile.setSex(rs.getString("sex_user"));
//			//profile.setPassword(rs.getString("pass_user"));
//			profile.setImagePath(rs.getString("img_user"));
//			//profile.setFriendsList(this.getFriends(id));
//			profile.setHobbies(this.getHobbies(profile.getId()));
//			//profile.setMessages(this.getMessages(profile.getId()));
//			
//			
//			profiles.add(profile);
//		}
//		
//		return profiles;
//	}
//	
//	public ArrayList<Profile> getFriends(String id) throws SQLException {
//		ArrayList<Profile> friends = new ArrayList<Profile>();
//		
//		String sql = "select * from user where id_user in (select id_friend from friend where id_user = '"+id+"')";
//		
//		ResultSet rs = ConnectionDAO.getConexao().createStatement().executeQuery(sql);
//		
//		while (rs.next()) {
//			Profile profile = new Profile();
//			
//			profile.setId(rs.getString("id_user"));
//			profile.setName(rs.getString("nm_user"));
//			profile.setLogin(rs.getString("lg_user"));
//			profile.setSex(rs.getString("sex_user"));
//			//profile.setPassword(rs.getString("pass_user"));
//			profile.setImagePath(rs.getString("img_user"));
//			//profile.setFriendsList(this.getFriends(id));
//			profile.setHobbies(this.getHobbies(profile.getId()));
//			//profile.setMessages(this.getMessages(profile.getId()));
//			
//			friends.add(profile);
//		}
//		
//		return friends;
//	}
//	
//	public ArrayList<Hobbie> getHobbies(String id) throws SQLException {
//		ArrayList<Hobbie> hobbies = new ArrayList<Hobbie>();
//		String sql;
//		
//		if (!id.equalsIgnoreCase("-1")) sql = "select * from hobbies where id_hobbies in (select id_hobbies from userhobbies where id_user = '"+id+"')";
//		else sql = "select * from hobbies";
//		
//		ResultSet rs = ConnectionDAO.getConexao().createStatement().executeQuery(sql);
//		
//		while (rs.next()) {
//			Hobbie hobbie = new Hobbie();
//			
//			hobbie.setId(rs.getString("id_hobbies"));
//			hobbie.setName(rs.getString("nm_hobbies"));
//			hobbie.setDescription(rs.getString("des_hobbies"));
//			
//			hobbies.add(hobbie);
//		}
//		
//		return hobbies;
//	}
//	
//	public ArrayList<Hobbie> getHobbies(ArrayList<String> hobbiesID, String userID) throws SQLException {
//		String sql;
//		Statement stm = ConnectionDAO.getConexao().createStatement();
//		
//		for (int i = 0 ; i < hobbiesID.size() ; i++) {
//			sql = "insert into userhobbies values ('"+userID+"', '"+hobbiesID.get(i)+"')";
//			stm.executeUpdate(sql);
//		}
//		
//		return this.getHobbies(userID);
//	}
//	
//	public Profile getUserProfile (String id) throws SQLException {
//		String sql = "select * from user where id_user = '"+id+"'";
//		
//		ResultSet rs = ConnectionDAO.getConexao().createStatement().executeQuery(sql);
//		
//		if (rs.next()) {
//			Profile profile = new Profile();
//			
//			profile.setId(rs.getString("id_user"));
//			profile.setName(rs.getString("nm_user"));
//			profile.setLogin(rs.getString("lg_user"));
//			profile.setSex(rs.getString("sex_user"));
//			//profile.setPassword(rs.getString("pass_user"));
//			profile.setImagePath(rs.getString("img_user"));
//			//profile.setFriendsList(this.getFriends(id));
//			profile.setHobbies(this.getHobbies(profile.getId()));
//			//profile.setMessages(this.getMessages(profile.getId()));
//			
//			return profile;
//		}
//		
//		return null;
//	}
//	
//	public ArrayList<Message> getMessages(String id) throws SQLException {
//		ArrayList<Message> messages = new ArrayList<Message>();
//		
//		String sql = "select * from message where id_user = '"+id+"'";
//		
//		System.out.println("SQL: "+sql);
//		
//		ResultSet rs = ConnectionDAO.getConexao().createStatement().executeQuery(sql);
//		
//		while (rs.next()) {
//			Message message = new Message();
//			
//			message.setId(rs.getString("id_mes"));
//			message.setTitle(rs.getString("title_mes"));
//			
//			String remitter = rs.getString("remitter_mes");
//			Profile rem = this.getUserProfile(remitter);
//			
//			System.out.println("REMITTER id: "+remitter);
//			System.out.println("REMITTER name: "+rem.getLogin());
//			
//			message.setRemitter(rem);
//			message.setBody(rs.getString("body_mes"));
//			message.setDate(rs.getDate("dt_mes"));
//			
//			messages.add(message);
//		}
//		
//		return messages;
//	}
//	
//	public String getUserID(String login) throws SQLException {
//		
//		String sql = "select id_user from user where lg_user = '"+login+"'";
//		
//		ResultSet rs = ConnectionDAO.getConexao().createStatement().executeQuery(sql);
//		
//		if (rs.next()) {
//			return rs.getString("id_user");
//		}
//		
//		return null;
//	}
//	
//	public boolean insertUser(Profile profile) throws SQLException {
//		String sql = "select lg_user from user where lg_user = '"+profile.getLogin()+"'";
//		
//		Statement stm = ConnectionDAO.getConexao().createStatement();
//		
//		ResultSet rs = stm.executeQuery(sql);
//		
//		if (rs.next()) {
//			return false;
//		}
//		
//		
//		{
//			sql = "insert into user (nm_user, lg_user, pass_user, sex_user, img_user) values (?,?,?,?,?)";
//			
//			PreparedStatement pstm = ConnectionDAO.getConexao().prepareStatement(sql);
//			pstm.setString(1, profile.getName());
//			pstm.setString(2, profile.getLogin());
//			pstm.setString(3, profile.getPassword());
//			pstm.setString(4, profile.getSex());
//			pstm.setString(5, profile.getImagePath());
//			
//			pstm.executeUpdate();
//			
//			ConnectionDAO.closeConnection();
//			
//			return true;
//		}
//	}
//	
//	public void addFriends(String[] friends, String idUser) throws SQLException {
//		
//		String sql;
//		Statement stm = ConnectionDAO.getConexao().createStatement();
//		
//		for ( int i = 0 ; i < friends.length ; i++) {
//			sql = "insert into friend (id_user, id_friend) values ('"+idUser+"','"+friends[i]+"')";
//			stm.executeUpdate(sql);
//		}
//		
//		ConnectionDAO.closeConnection();
//	}
//	
//	public void updateProfile(Profile profile) throws SQLException {
//		String sql = "update user set lg_user = '"+profile.getLogin()+"', nm_user = '"+profile.getName()+"', pass_user = '"+profile.getPassword()+"', sex_user = '"+profile.getSex()+"', img_user = '"+profile.getImagePath()+"' where id_user = '"+profile.getId()+"'";
//		
//		Statement stm = ConnectionDAO.getConexao().createStatement();
//		stm.executeUpdate(sql);
//		
//		ConnectionDAO.closeConnection();
//	}
//	
//	public Profile findFriend(String idUser, String idFriend) throws SQLException {
//		String sql = "select * from user as u, friend as f where f.id_user = '"+idUser+"' and f.id_friend = '"+idFriend+"' and u.id_user = '"+idFriend+"'";
//		
//		System.out.println("SQL: "+sql);
//		
//		ResultSet rs = ConnectionDAO.getConexao().createStatement().executeQuery(sql);
//		
//		Profile profile = null;
//		if (rs.next()) {
//			profile = new Profile();
//			
//			profile.setId(rs.getString("id_user"));
//			profile.setName(rs.getString("nm_user"));
//			profile.setLogin(rs.getString("lg_user"));
//			profile.setSex(rs.getString("sex_user"));
//			profile.setPassword(rs.getString("pass_user"));
//			profile.setImagePath(rs.getString("img_user"));
//			profile.setFriendsList(this.getFriends(profile.getId()));
//			profile.setHobbies(this.getHobbies(profile.getId()));
//			profile.setMessages(this.getMessages(profile.getId()));
//		}
//		
//		ConnectionDAO.closeConnection();
//		
//		return profile;
//	}
//	
//	public ArrayList<Profile> findFriends(String idUser) throws SQLException {
//		ArrayList<Profile> profiles = new ArrayList<Profile>();
//		
//		String sql = "select * from user as u, friend as f where u.id_user = '"+idUser+"' and f.id_user = '"+idUser+"'";
//		
//		ResultSet rs = ConnectionDAO.getConexao().createStatement().executeQuery(sql);
//		
//		
//		while (rs.next()) {
//			Profile profile = new Profile();
//			
//			profile.setName(rs.getString("name"));
//			profile.setLogin(rs.getString("login"));
//			profile.setSex(rs.getString("sex"));
//			
//			profiles.add(profile);
//		}
//		
//		ConnectionDAO.closeConnection();
//		
//		return profiles;
//	}
//	
//	public ArrayList<Profile> profileList (String id) throws SQLException {
//		ArrayList<Profile> profileList = new ArrayList<Profile>();
//		String sql = "select * from user where id_user <> '"+id+"'";
//		
//		Statement stm = ConnectionDAO.getConexao().createStatement();
//		ResultSet rs = stm.executeQuery(sql);
//		
//		while (rs.next()) {
//			Profile profile = new Profile();
//			
//			profile.setId(rs.getString("id_user"));
//			profile.setName(rs.getString("nm_user"));
//			profile.setLogin(rs.getString("lg_user"));
//			profile.setSex(rs.getString("sex_user"));
//			//profile.setPassword(rs.getString("pass_user"));
//			profile.setImagePath(rs.getString("img_user"));
//			//profile.setFriendsList(this.getFriends(id));
//			profile.setHobbies(this.getHobbies(profile.getId()));
//			//profile.setMessages(this.getMessages(profile.getId()));
//			
//			profileList.add(profile);
//		}
//		
//		return profileList;
//	}
//	
//	public void sendMessage(Message message, String[] receivers) throws SQLException {
//		
//		String sql;
//		Statement stm = ConnectionDAO.getConexao().createStatement();
//		
//		for (int i = 0 ; i < receivers.length ; i++) {
//			sql = "insert into message (title_mes, body_mes, remitter_mes, dt_mes, id_user) values ('"+message.getTitle()+"','"+message.getBody()+"','"+message.getRemitter().getId()+"','"+new Timestamp(message.getDate().getTime())+"','"+receivers[i]+"')";
//			stm.executeUpdate(sql);
//		}
//		
//		
//		
//	}
//	
//	public void removeFriends(String[] friendsID, String idUser) throws SQLException {
//		
//		String sql;
//		Statement stm = ConnectionDAO.getConexao().createStatement();
//		
//		for (int i = 0 ; i < friendsID.length ; i++) {
//			sql = "delete from friend where id_user = "+idUser+" and id_friend = '"+friendsID[i]+"'";
//			stm.executeUpdate(sql);
//		}
//		
//	}
//	
//	public ArrayList<Profile> filterUser(String filter) throws SQLException {
//		ArrayList<Profile> filtereds = new ArrayList<Profile>();
//		String sql = "select * from user where lg_user like '%"+filter+"%'";
//		
//		Statement stm = ConnectionDAO.getConexao().createStatement();
//		ResultSet rs = stm.executeQuery(sql);
//		
//		while (rs.next()) {
//			Profile profile = new Profile();
//			
//			profile.setId(rs.getString("id_user"));
//			profile.setName(rs.getString("nm_user"));
//			profile.setLogin(rs.getString("lg_user"));
//			profile.setSex(rs.getString("sex_user"));
//			//profile.setPassword(rs.getString("pass_user"));
//			profile.setImagePath(rs.getString("img_user"));
//			//profile.setFriendsList(this.getFriends(id));
//			profile.setHobbies(this.getHobbies(profile.getId()));
//			//profile.setMessages(this.getMessages(profile.getId()));
//			
//			filtereds.add(profile);
//		}
//		
//		return filtereds;
//	}
//	
//	public ArrayList<Profile> filterFriends(String filter, String idUser) throws SQLException {
//		ArrayList<Profile> filtereds = new ArrayList<Profile>();
//		String sql = "select * from user u where id_user in (select id_friend from friend where id_user = '"+idUser+"') and u.lg_user like '%"+filter+"%'";
//		
//		Statement stm = ConnectionDAO.getConexao().createStatement();
//		ResultSet rs = stm.executeQuery(sql);
//		
//		while (rs.next()) {
//			Profile profile = new Profile();
//			
//			profile.setId(rs.getString("id_user"));
//			profile.setName(rs.getString("nm_user"));
//			profile.setLogin(rs.getString("lg_user"));
//			profile.setSex(rs.getString("sex_user"));
//			//profile.setPassword(rs.getString("pass_user"));
//			profile.setImagePath(rs.getString("img_user"));
//			//profile.setFriendsList(this.getFriends(id));
//			profile.setHobbies(this.getHobbies(profile.getId()));
//			//profile.setMessages(this.getMessages(profile.getId()));
//			
//			filtereds.add(profile);
//		}
//		
//		return filtereds;
//	}
//	
//	public void removeMessage(String idMes) throws SQLException {
//		String sql = "delete from message where id_mes = '"+idMes+"'";
//		
//		System.out.println("removeMessage: "+sql);
//		
//		Statement stm = ConnectionDAO.getConexao().createStatement();
//		stm.executeUpdate(sql);
//	}
//}
