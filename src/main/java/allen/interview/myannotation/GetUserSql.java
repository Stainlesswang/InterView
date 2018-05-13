package allen.interview.myannotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GetUserSql {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        User u1 = new User();
        User u2 = new User();
        u1.setId(1);
        u2.setName("forezp");
        genSql(u2);
        genSql(u1);
    }
    private static void genSql(User user) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class c = user.getClass();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * from ");
        if (c.isAnnotationPresent(Table.class)) {
            Table table = (Table) c.getAnnotation(Table.class);
            String tableName = table.value();
            stringBuilder.append(tableName).append(" where 1=1 and ");
        }
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            String columnName;
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                columnName = column.value();
            } else {
                continue;
            }
            //获取方法名字  此处获取 get**();
            String fieldName = field.getName();
            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method method = c.getMethod(getMethodName);
            //执行某个类的 获取到的method方法，并且返回来一个Object对象就是获取到的值
            Object fieldValue = method.invoke(user);
            if (fieldValue == null || ((fieldValue instanceof Integer) && (Integer) fieldValue == 0)) {
                continue;
            }
            if (fieldValue instanceof Integer) {
                stringBuilder.append(columnName + "=" + fieldValue);
            }
            if (fieldValue instanceof String) {
                stringBuilder.append(columnName + "=" + "'" + fieldValue + "'");
            }

        }
        System.out.println(stringBuilder.toString());

    }
}
