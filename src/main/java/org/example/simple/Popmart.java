package org.example.simple;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Popmart {

    private String[] conditions = new String[3];

    public String[] traverse(Queue<String> part, int start) {
        if (start == conditions.length - 1) {
            return null;
        }
        int size = conditions.length - start;
        String[] temp = new String[size];
        for (int i = 0; i < size; i++) {
            String item = part.poll();
            if (conditions[start] == null || conditions[start].indexOf(item) == -1) {
                temp[0] = item;
                String[] tempPart = traverse(part, start + 1);
                if (tempPart == null) {
                    // 说明找不到任何一种组合形式
                    temp = null;
                } else {
                    System.arraycopy(tempPart, 0, temp, 0, size);
                }
                if (start == 0) {
                    System.out.println(temp);
                }
            } else {
                part.offer(item);
            }
        }
        return temp;
    }

    public Popmart addCondition(int position, String condition) {
        StringBuilder sb = null;
        if (conditions[position] == null) {
            sb = new StringBuilder();
        } else {
            sb = new StringBuilder(conditions[position]);
        }
        if (sb.length() > 0) {
            sb.append(";");
        }
        sb.append(condition);
        conditions[position] = sb.toString();
        return this;
    }

    public static void main(String[] args) {
        Popmart popmart = new Popmart();
        Queue<String> product = new LinkedBlockingQueue<>();

        for (String k : new String[]{"胡迪", "巴斯光年", "翠丝"}) {
            product.offer(k);
        }
        popmart.addCondition(1, "巴斯光年").addCondition(2, "胡迪");
        popmart.traverse(product, 0);
    }
}
