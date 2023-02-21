package com.musclebeach.common.util.CompositeQuery;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class jdbcUtil_CompositeQuery_ClassSchedule {
    public static String get_aCondition_For_myDB(String columnName, String value) {

        String aCondition = null;

        if ("time_id".equals(columnName) || "class_id".equals(columnName)) // 用於其他
            aCondition = columnName + "=" + value;


        return "a." + aCondition + " ";
    }

    public static String get_WhereCondition(Map<String, String[]> map) {
        Set<String> keys = map.keySet();
        StringBuffer whereCondition = new StringBuffer();
        int count = 0;
        for (String key : keys) {
            String value = map.get(key)[0];
            if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
                count++;
                String aCondition = get_aCondition_For_myDB(key, value.trim());

                if (count == 1)
                    whereCondition.append(" where " + aCondition);
                else
                    whereCondition.append(" and " + aCondition);

                System.out.println("有送出查詢資料的欄位數count = " + count);
            }
        }

        return whereCondition.toString();
    }

    public static void main(String argv[]) {

        // 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
        Map<String, String[]> map = new TreeMap<String, String[]>();
        map.put("class_id", new String[]{"1"});


        String finalSQL = "SELECT a.class_id,a.time_id,a.start_time,a.end_time,b.mem_id from  class_schedule AS a JOIN class_order AS b on a.class_id=b.class_id"
                + jdbcUtil_CompositeQuery_ClassSchedule.get_WhereCondition(map)
                + "order by time_id";
        System.out.println("●●finalSQL = " + finalSQL);

    }
}
