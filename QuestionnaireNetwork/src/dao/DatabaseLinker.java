package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库操作
 */
public class DatabaseLinker {
    private String dbName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String databaseLocation = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Questionnaire";
    String userName = "***";
    String password = "********";
	private Connection connection;
	
	/**
	 * linker数据库连接对象的初始化
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public DatabaseLinker() throws SQLException, ClassNotFoundException{
		Class.forName(dbName);
		connection = DriverManager.getConnection
				(databaseLocation ,userName,password);
	}
	
	/**
	 * 关闭对数据库的链接
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		connection.close();
	}
	
	/**
	 * 传递database查询语句来查询数据库，并返回一个resultset
	 * @param selectStatement 要执行查询的SQL语句
	 * @return
	 * @throws SQLException
	 */
	public ResultSet selectDatabase(String selectStatement) throws SQLException{
		Statement statement = (Statement) connection.createStatement();
		ResultSet resultSet = statement.executeQuery(selectStatement);
		return resultSet;		
		
	}
	
	/**
	 * 传递database更新语句来更新数据库
	 * @param updateStatement  要执行更新的SQL语句
	 * @return 
	 * @throws SQLException
	 */
	public int update(String updateStatement) throws SQLException  {
		if(updateStatement == "-1"){
			System.out.println("输入有误！");
			return 0;
		}
		Statement statement = (Statement)connection.createStatement();
		return statement.executeUpdate(updateStatement);
	}
	public int updateDatabase(String updateStatement) throws SQLException  {
		ResultSet rs=null;
		long id = 0;
		if(updateStatement == "-1"){
			System.out.println("输入有误！");
			return -1;
		}
		Statement statement = (Statement)connection.createStatement();
	    statement.executeUpdate(updateStatement);
	    rs = statement.getGeneratedKeys();
	    while(rs.next()){
	        id=rs.getLong(1);
	       }
	    return (int) id;
	}
    // SQL更新
    public int executeUpdateSQL(String sql, Object... args) {
        PreparedStatement pstmt = null;
        int counts = 0;
        try {
            pstmt = connection.prepareStatement(sql);
            for (int i = 1; i <= args.length; i++) {
                pstmt.setObject(i, args[i - 1]);
            }
            counts = pstmt.executeUpdate();
            return counts;
        } catch (SQLException e) {
            System.out.println("idNo重复!");
        } finally {
        	try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return 0;
    }

}
