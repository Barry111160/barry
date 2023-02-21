package com.musclebeach.articleImg.model;

import java.util.ArrayList;
import java.util.List;

public class ArticleImgUtil {
    public static String getInsertSql(List<ArticleImgVO> list) {
        Integer size = list.size();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            sb.append("(?, ?)");
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        List<ArticleImgVO> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(new ArticleImgVO());
        }
        System.out.println(getInsertSql(list));
    }
}
