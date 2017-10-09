 package dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import service.Clumn;
import service.StringUtil;
/**
 * 根据数据库操作拼SQL语句（拼SQL语句）
 */
public class SpellOperateSql {
	
	/**
	 * 数据库新增操作
	 */
	public String Save(Object object){
		String sql = "insert into [" + getObjectClassName(object) + "] values";
		sql += getValue(object);
		System.out.println("sql:" + sql);
		return sql;
	}
	
	/**
	 * 数据库修改操作
	 */
	public String Change(Object object, String ID){
		String sql = "update " + getObjectClassName(object) + " set ";
		if(getChangeString(object) == "") return "-1";
		sql += getChangeString(object) + "where no = '" + ID + "'";
		System.out.println("sql:" + sql);
		return sql;
	}
	
	/**
	 * 数据库删除操作
	 */
	public String Delete(Object object, String ID){
		String sql = "delete from " + getObjectClassName(object) + " where NO = '" + ID + "'";
		System.out.println("sql:" + sql);
		return sql;
	}
	
	/**
	 * 数据库查询操作
	 */
	public String check(Object object){
		String sql = "select * from " + getObjectClassName(object) + " where " + getCheckString(object);
		System.out.println("sql:" + sql);
		return sql;
	}
	/**
	 * 数据库查询所有数据操作
	 */
	public String getAllObjects(Object object){
		String sql = "select * from [" + getObjectClassName(object) +"]";
		System.out.println("sql:" + sql);
		return sql;
	}
	/**
	 * 判断单个字段是否符合规范
	 * @param isNumber
	 * @param isNull
	 * @param minLength
	 * @param maxLength
	 * @param value
	 * @return
	 */
	public boolean judgeField(boolean isNumber, boolean isNull, int minLength, int maxLength, String value){
		if(isNull && value == "NULL") return false;
		if(isNumber){
			for (int i = 0; i < value.length(); i++) {
				if(value.charAt(i) < '0' || value.charAt(i) > '9') return false;
			}
		}
		if(value.length() > maxLength || value.length() < minLength) return false;
		return true;
	}
	
	/**
	 * 获得类名
	 * @param object
	 * @return
	 */
	public String getObjectClassName(Object object){
		return object.getClass().getSimpleName();
	}
	
	/**
	 * 获得对象的值
	 * @param object
	 * @return
	 */
	private String getValue(Object object){
		Class<?> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Method[] methods=clazz.getMethods();
		String values = "(";
		int filedNum = fields.length;
		int count = 0;
		for (Field field : fields) {
			String getter="get"+StringUtil.firstCharToUp(field.getName());
			for(Method method:methods){
				String methodName=method.getName();
				if(methodName.equals(getter)){
					if(field.getType().getSimpleName().equals("String")){
						try {
							values += "'" + method.invoke(object) + "'";
							if(count < filedNum-1){
								values+= ",";
							}
							count++;
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					}else if(field.getType().getSimpleName().equals("int")){
						try {
							values +=  method.invoke(object);
							if(count < filedNum-1){
								values+= ",";
							}
							count++;
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					}
				}
			}
		}
		String sql =values.substring(0, 1);
	    sql +=	values.substring(3, values.length());
		return sql + ")";
	}
	
	/**
	 * 获得单个字段的值
	 * @param field
	 * @param object
	 * @param methods
	 * @return
	 */
	public String getValue(Field field, Object object, Method[] methods){
		String value = "";
		String setter="get"+StringUtil.firstCharToUp(field.getName());
		for(Method method:methods){
			String methodName=method.getName();
			if(methodName.equals(setter)){
				if(field.getType().getName()=="java.lang.String"){
					try {
						value = (String) method.invoke(object);
						if(value.equals("NULL")) value = "";
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					break;
				}
			}
		}
		return value;
	}
	
	@SuppressWarnings("unused")
	public String getChangeString(Object object){
		Class<?> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Method[] methods=clazz.getMethods();
		String values = "";
		int filedNum = fields.length - 1;
		int count = 0;
		int first = 0;
		
		for (Field field : fields) {
			Clumn clumn = field.getAnnotation(Clumn.class);
			boolean isPrimaryKey = clumn.isPrimaryKey();
			if(isPrimaryKey) continue;
			String getter="get"+StringUtil.firstCharToUp(field.getName());
			for(Method method:methods){
				String methodName=method.getName();
				if(methodName.equals(getter)){
						try {
							count++;
							String value = (String) method.invoke(object);
							if(value != null && value != ""){
								first++;
								if(first > 1) values += ",";
								values += field.getName() + "='" + value + "'";
							}
						} catch (Exception e) {
							
							e.printStackTrace();
						}
						break;
				}
			}
		}
		return values;
	}
	
	@SuppressWarnings("unused")
	public String getCheckString(Object object){
		Class<?> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Method[] methods=clazz.getMethods();
		int filedNum = fields.length;
		int count = 0;
		int first = 0;
		String values = "";
		for (Field field : fields) {
			String getter="get"+StringUtil.firstCharToUp(field.getName());
			for(Method method:methods){
				String methodName=method.getName();
				if(methodName.equals(getter)){
						try {
							String value = (String)method.invoke(object);
							count++;
							if(value != null && value != ""){
								first++;
								if(first > 1) values += " and ";
								values += field.getName() + "='" + value + "'";
							}
						} catch (Exception e) {
							
							e.printStackTrace();
						}
						break;
				}
			}
		}
		if(values == "") values = "1=1";
		return values;
	}
	
}
