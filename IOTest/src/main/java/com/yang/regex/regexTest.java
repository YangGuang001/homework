package com.yang.regex;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by yz on 2017/6/28.
 */
public class regexTest {
    public static void main(String[] args) {
        List<Button> buttons = new ArrayList<Button>();
        Button button1 = new Button();
        button1.setName("button1");
        button1.setType("click");
        Button button2 = new Button();
        button2.setName("button2");
        button2.setType("click");
        Button button3 = new Button();
        button3.setName("button3");
        button3.setType("click");
        Button button4 = new Button();
        button4.setName("button4");
        button4.setType("click");
        Button button5 = new Button();
        button5.setName("button5");
        button5.setType("click");
        Button button6 = new Button();
        button6.setName("button6");
        button6.setType("click");
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        String obj = JSON.toJSONString(buttons);
        System.out.println(obj);

        String json = "[{\"name\":\"button1\",\"type\":\"click\"},{\"name\":\"button2\",\"type\":\"click\"},{\"name\":\"button3\",\"type\":\"click\"},{\"name\":\"button4\",\"type\":\"click\"}]";

        List<Button> list = (List<Button>) JSON.parseObject(json, new TypeReference<List<Button>>(){});
        System.out.println(list.toString());
    }


    public void TestRegex(){
        String email = "824901900@qq.com";

        String regEx = "[a-zA-Z0-9]{1,}@[a-zA-Z0-9]{1,}\\.[a-zA-Z]{1,}";

        if (email.matches(regEx)){
            System.out.println("This is a email!!!");
        } else {
            System.out.println("This isn't a email!!!");
        }
    }
}

class Button{
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String type;
    private Button sub_button;

    private Set<Button> set = new TreeSet<Button>();
    public boolean setSub_button(Button[] buttons){
//        for (Button button : buttons){
//            this.set.add(button);
//        }
        for (int i=0; i < buttons.length; i++){
            this.set.add(buttons[i]);
        }
        return true;
    }

    public boolean add(Button button){
        this.set.add(button);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Button)) return false;

        Button button = (Button) o;

        if (!getName().equals(button.getName())) return false;
        if (!getType().equals(button.getType())) return false;
        if (sub_button != null ? !sub_button.equals(button.sub_button) : button.sub_button != null) return false;
        return set != null ? set.equals(button.set) : button.set == null;
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getType().hashCode();
        result = 31 * result + (sub_button != null ? sub_button.hashCode() : 0);
        result = 31 * result + (set != null ? set.hashCode() : 0);
        return result;
    }
}
