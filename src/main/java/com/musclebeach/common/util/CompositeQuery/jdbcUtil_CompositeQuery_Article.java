/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *     所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */


package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_Article {

	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;

		if ("type_id".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("mem_name".equals(columnName) || "art_title".equals(columnName) || "art_content".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
	
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
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
		map.put("type_id", new String[] { "1" });
		map.put("action", new String[] { "getXXX" });


		String finalSQL = "SELECT * FROM gym.article a "
		        + "JOIN gym.member m ON a.mem_id = m.mem_id "
		        + jdbcUtil_CompositeQuery_Article.get_WhereCondition(map)
		        + " order by art_ltime desc";

		System.out.println("●●finalSQL = " + finalSQL);

	}
}
