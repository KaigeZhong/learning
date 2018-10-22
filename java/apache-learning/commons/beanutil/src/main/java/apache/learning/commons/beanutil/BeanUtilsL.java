package apache.learning.commons.beanutil;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class BeanUtilsL {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        People people = new People(1, "1");
        People people1 = new People();
        BeanUtils.copyProperties(people1, people);
        System.out.println(people1.name);
    }

    public static class People {
        private int id;
        private String name;

        public People() {

        }

        public People(int id, String name) {
            this.id = id;
            this.name = name;
        }
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
