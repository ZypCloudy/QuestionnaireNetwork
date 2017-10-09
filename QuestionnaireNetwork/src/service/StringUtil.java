package service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import dao.SpellOperateSql;

public class StringUtil {
	/*public static void main(String[] args) {
		System.out.println(StringUtil.firstCharToUp(str)); 
	}*/
	public static String firstCharToUp(String str){
		char ch[]=str.toCharArray();
		char ch1=Character.toUpperCase(ch[0]);
		ch[0]=ch1;
		String s=new String(ch);
		return s;
	}
	
	public Object getObj(HttpServletRequest request, String operation, String entityName){
		Object object = getObjByName(entityName);
		return setObject(request, operation, object);
	}
	
	public Object getObjByName(String name){
		try {
			Class obj = Class.forName("entity."+name);
			Object object = obj.newInstance();
			return object;
		} catch (Exception e) {
			System.out.println("erro1:无此对象");
			e.printStackTrace();
		}
		System.out.println("erro2:无此对象");
		return null;
	}
	
	public Object setObject(HttpServletRequest request, String operation, Object object){
		Class<?> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();  
		Method[] methods=clazz.getMethods();
		String className = object.getClass().getName();
		for (Field field : fields) {
			String setter="set"+ firstCharToUp(field.getName());
			String value = request.getParameter(operation + "_" + field.getName());
			for(Method method:methods){
				String methodName=method.getName();
				if(methodName.equals(setter)){
						try {
							Object args = getCheckedInput(field.getType().getSimpleName(),(String)value);
							//通过反射对属性进行赋值
							method.invoke(object, args);
						} catch (Exception e) {
							
							e.printStackTrace();
						}
						break;
					}
				}
		}
		return object;
	}
	// 获取经过校验的输入
	 	public static Object getCheckedInput(String type,String input) {
	 		Object obj = null;
	 		try {
	 			if(type.equalsIgnoreCase("int")){
	 				obj = Integer.parseInt(input);
	 			}else if (type.equalsIgnoreCase("String")) {
	 				obj = input;
	 			} else if (type.equalsIgnoreCase("Integer")) {
	 				obj = Integer.valueOf(input);
	 			} else if (type.equalsIgnoreCase("Boolean")) {
	 				obj = input.equalsIgnoreCase("1") ? true : false;
	 			} else if (type.equalsIgnoreCase("double")) {
	 				obj = Double.valueOf(input);
	 			}
	 		} catch (Exception e) {
	 			return null;
	 		}
	 		return obj;
	 	}

	
	public Object getIdObject(HttpServletRequest request, String entityName, String operation){
		Object object = getObjByName(entityName);
		return setNo(request, object, operation);
	}
	
	public Object setNo(HttpServletRequest request, Object object, String operation){
		String setter="setNo";
		Class<?> clazz = object.getClass();
		Method[] methods=clazz.getMethods();
		String value = request.getParameter(operation + "_no");
		for(Method method:methods){
			String methodName=method.getName();
			if(methodName.equals(setter)){
					try {
						method.invoke(object, (String)value);
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					break;
			}
				
		}
		return object;
	} 
}
