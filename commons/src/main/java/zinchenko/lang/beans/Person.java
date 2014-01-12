package zinchenko.lang.beans;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * User: zinchenko
 * Date: 04.01.14
 */
public class Person implements Comparable<Person>{

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int compareTo(Person o) {
        return new CompareToBuilder()
                .append(age, o.age).append(name, o.name)
                .toComparison();
    }

    @Override
    public String toString() {
        return "age=" + age + " | name=" + name;
    }

    public String toStringUsingBuilder() {
        return new ToStringBuilder(this)
                .append("age", age)
                .append("name", name)
                .toString();
    }


}
