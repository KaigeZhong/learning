package com.learning.lombok;

import com.learning.lombok.domain.BeanWithLombokNotNull;

public class App {
    public static void main(String[] args) {
        //test NonNull
        BeanWithLombokNotNull beanWithLombokNotNull = new BeanWithLombokNotNull();
//        beanWithLombokNotNull.setName(null); //will throw null Exception
        beanWithLombokNotNull.updateName(null);
    }
}
