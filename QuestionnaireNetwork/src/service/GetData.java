package service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import myTools.InputObject;
import dao.DatabaseLinker;
import dao.SpellOperateSql;

/**
 * 获取指定对象的数据
 */
public class GetData {
	private ArrayList<Object> arrayList = new ArrayList<Object>();
	private SpellOperateSql operation = new SpellOperateSql();
	private DatabaseLinker databaseLinker;
	
	//通过主键Id查找
	public ArrayList<Object> getObjById(Object object,String id){
		 arrayList.clear();
		 try {
			    databaseLinker = new DatabaseLinker();
			    String className = object.getClass().getSimpleName();
			    String sql = "select * from ["+ className + "] where " +className+"Id = "+id ;
			    //结果集(ResultSet)是数据中查询结果返回的一种对象，可以说结果集是一个存储查询结果的对象
			    ResultSet rs = databaseLinker.selectDatabase(sql);
			    process(object, rs);
				databaseLinker.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		 return arrayList;
	}
	//通过外键Id查找
	public ArrayList<Object> getObjByForeignId(Object object,String foreiginName,String foreiginId){
		 arrayList.clear();
		 try {
			    databaseLinker = new DatabaseLinker();
			    String className = object.getClass().getSimpleName();
			    String sql = "select * from ["+ className + "] where " +foreiginName+"Id = "+foreiginId ;
			    //结果集(ResultSet)是数据中查询结果返回的一种对象，可以说结果集是一个存储查询结果的对象
			    ResultSet rs = databaseLinker.selectDatabase(sql);
			    process(object, rs);
				databaseLinker.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		 return arrayList;
	}
	//通过条件查找列
	public ArrayList<Object> selectByCon(String Con,Object object,String clumnName){
		 arrayList.clear();
		 try {
			    databaseLinker = new DatabaseLinker();
			    String className = object.getClass().getSimpleName();
			    String sql = "select * from ["+className+"] where "+clumnName +" = '"+Con+"'";
			    System.out.println(sql);
			   //结果集(ResultSet)是数据中查询结果返回的一种对象，可以说结果集是一个存储查询结果的对象
			    ResultSet rs = databaseLinker.selectDatabase(sql);
			    process(object, rs);
				databaseLinker.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		 return arrayList;
	}
	//通过搜索信息相似查找
		public ArrayList<Object> selectBySearch(String search,Object object,String clumnName){
			 arrayList.clear();
			 try {
				    databaseLinker = new DatabaseLinker();
				    String className = object.getClass().getSimpleName();
				    String sql = "select * from ["+className+"] where "+clumnName +" like '"+search+"%'";
				    System.out.println(sql);
				   //结果集(ResultSet)是数据中查询结果返回的一种对象，可以说结果集是一个存储查询结果的对象
				    ResultSet rs = databaseLinker.selectDatabase(sql);
				    process(object, rs);
					databaseLinker.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			 return arrayList;
		}
	//获取所有问卷单
    public ArrayList<Object> getAllObjects(Object object){
    	arrayList.clear();
    	getObj(object);
	    return arrayList;
   }
 

    /**
     * 获取要获取数据的对象
     * @param object  指定的要查询的对象
     */
    public void getObj(Object object){
		 try {
			    databaseLinker = new DatabaseLinker();
			    //结果集(ResultSet)是数据中查询结果返回的一种对象，可以说结果集是一个存储查询结果的对象
			    String className = object.getClass().getSimpleName();
			    String sql ="select * from ["+className+"] where paperStatus=1";
			    ResultSet rs = databaseLinker.selectDatabase(sql);
			    process(object, rs);
				databaseLinker.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
    /**
     * 解析并生成对象实例
     * 通过反射完成
     * @param object 指定的要查询的对象
     * @param rs 数据库查询结果的结里集对象
     */
    @SuppressWarnings("rawtypes")
	public void process(Object object, ResultSet rs){
    	//获取传入对象的类
		Class<?> clazz = object.getClass();
		//获取该类的所有数据域
		Field[] fields = clazz.getDeclaredFields();  
		//获取该类的所有方法
		Method[] methods=clazz.getMethods();
		//获取指定类的类名
		String className = object.getClass().getName();
		try {
			while(rs.next()){
				//根据对象的全类名，指定类
				Class obj = Class.forName(className);
				//实例化对象实例
				Object object2 = obj.newInstance();
				//遍历该类的所有数据域
				for (Field field : fields) {
					//根据数据域名称获取相应的数据
					String value = rs.getString(field.getName());
					//对类的属性进行赋值
					object2 = setValue(field, value, object2, methods);
				}
				arrayList.add(object2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    /**
     * 对已经实例化的对象属性进行赋值
     * @param field 要赋值的属性
     * @param value 要赋值属性对应的数据
     * @param object 指定的对象
     * @param methods 指定对象的所有公共方法
     */
	public Object setValue(Field field, Object value, Object object,Method[] methods){
		//获取数据域的setter方法名
		String setter="set"+StringUtil.firstCharToUp(field.getName());
		//遍历该类的所有公共方法
		for(Method method:methods){
			//获取方法名称
			String methodName=method.getName();
			//比较方法名与该属性的setter方法名是否相同
			if(methodName.equals(setter)){
					try {
		 				Object args =InputObject.getCheckedInput(field.getType().getSimpleName(),(String)value);
						//通过反射对属性进行赋值
						method.invoke(object, args);
						
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					break;
				}
			}
		return object;
	} 
}
