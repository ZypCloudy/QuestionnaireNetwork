package service;

import java.sql.SQLException;

import dao.DatabaseLinker;
import dao.SpellOperateSql;

/**
 * 更新数据库数据操作
 */
public class UpdateData {
	private SpellOperateSql operation = new SpellOperateSql();
	private DatabaseLinker databaseLinker;
	/**
	 * 将数据存入数据库
	 */
	public int save(Object object){
		int id = 0;
		 try {
			databaseLinker = new DatabaseLinker();
			String sql = operation.Save(object)+"select @@identity as 'id'";
			id = databaseLinker.updateDatabase(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		 return id;
	}
	
	/**
	 * 删除数据库数据
	 */
	public void delete(Object object,String id){
		try {
			databaseLinker = new DatabaseLinker();
			databaseLinker.updateDatabase(operation.Delete(object,id));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改数据库数据
	 */
	public void change(Object object,String id) {
		try {
			databaseLinker = new DatabaseLinker();
			databaseLinker.updateDatabase(operation.Change(object, id));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	  // 修改
    public boolean update(String objectName, Object[] inputs, Object[] options) {
        int id = 0;
        Class<?> clazz = null;
        try {
            clazz = Class.forName("entity." + objectName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        // 修改值列表

        String sqlCenter = "";

        // 拼接更新部分SQL语句
        for (int i = 0; i < options.length; i++) {
            sqlCenter += options[i].toString() + "=?,";
            if (options[i].toString().equals("questionId")) {
                id = Integer.parseInt(inputs[i].toString());
            }
        }

        sqlCenter = sqlCenter.substring(0, sqlCenter.length() - 1);

        String sql = "update " + objectName + " set " + sqlCenter
                + " where questionId=" + id;

        // 对数据库进行操作
        int count = databaseLinker.executeUpdateSQL(sql, inputs);

        return count > 0 ? true : false;
    }
    // 修改n列
    public boolean updateById(String objectName, Object[] inputs, Object[] ids,String column) {
    	int count=0;
		try {
			databaseLinker = new DatabaseLinker();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=0;i<ids.length;i++){
			   String sql = "update [" + objectName + "] set "+ column+"Name = '" + inputs[i]
		                + "' where "+column + "Id=" + ids[i];
		        // 对数据库进行操作
				try {
						databaseLinker.update(sql);
						count++;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}
		try {
			databaseLinker.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count > 0 ? true : false;
    }
    public boolean updateOneById(String objectName, String input, String id,String column) {
    	int count=0;
		try {
			databaseLinker = new DatabaseLinker();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			   String sql = "update [" + objectName + "] set "+ column+" = '" + input
		                + "' where "+objectName + "Id=" + id;
			   System.out.print(sql);
		        // 对数据库进行操作
				try {
						databaseLinker.update(sql);
						count++;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		try {
			databaseLinker.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count > 0 ? true : false;
    }
}
