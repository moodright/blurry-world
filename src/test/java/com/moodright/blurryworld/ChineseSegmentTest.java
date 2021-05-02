package com.moodright.blurryworld;

import com.alibaba.fastjson.JSON;
import com.moodright.blurryworld.utils.ChineseSegmentUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @author moodright
 * @date 2021/5/2
 */
@SpringBootTest
public class ChineseSegmentTest {

    @Autowired
    ChineseSegmentUtil chineseSegmentUtil;

    @Test
    public void segmentTest() {
        String postContent = "# Spring Framwork Study 01\n" +
                "\n" +
                "Category: Spring\n" +
                "Date Created: Dec 8, 2020 12:54 PM\n" +
                "\n" +
                "## 一、依赖注入环境 (Dependency Injection)\n" +
                "\n" +
                "- 构造器注入\n" +
                "- Setter方法注入\n" +
                "- c-namespace p-namespace注入\n" +
                "\n" +
                "依赖：bean对象的创建依赖于容器\n" +
                "\n" +
                "注入：bean对象中的所有属性由容器来注入\n" +
                "\n" +
                "基于XML的bean配置文件\n" +
                "\n" +
                "```xml\n" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "    xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n" +
                "        https://www.springframework.org/schema/beans/spring-beans.xsd\">\n" +
                "\n" +
                "    <bean id=\"...\" class=\"...\">  \n" +
                "        <!-- collaborators and configuration for this bean go here -->\n" +
                "    </bean>\n" +
                "\n" +
                "    <bean id=\"...\" class=\"...\">\n" +
                "        <!-- collaborators and configuration for this bean go here -->\n" +
                "    </bean>\n" +
                "\n" +
                "    <!-- more bean definitions go here -->\n" +
                "\n" +
                "</beans>\n" +
                "```\n" +
                "\n" +
                "p-namespace头文件约束\n" +
                "\n" +
                "```xml\n" +
                "xmlns:p=\"http://www.springframework.org/schema/p\"\n" +
                "```\n" +
                "\n" +
                "c-namespace头文件约束\n" +
                "\n" +
                "```xml\n" +
                "xmlns:c=\"http://www.springframework.org/schema/c\"\n" +
                "```\n" +
                "\n" +
                "### 测试用例\n" +
                "\n" +
                "POJO \\ Entity 类 Student.java\n" +
                "\n" +
                "```java\n" +
                "package com.moodright.pojo;\n" +
                "\n" +
                "import java.util.*;\n" +
                "\n" +
                "/**\n" +
                " * @author moodright\n" +
                " * @date 2020/12/8\n" +
                " */\n" +
                "public class Student {\n" +
                "    private String name;\n" +
                "    private Address address;\n" +
                "    private String[] books;\n" +
                "    private List<String> hobbies;\n" +
                "    private Map<String,String> card;\n" +
                "    private Set<String> games;\n" +
                "    private String wife;\n" +
                "    private Properties info;\n" +
                "\n" +
                "    public void setName(String name) {\n" +
                "        this.name = name;\n" +
                "    }\n" +
                "\n" +
                "    public void setAddress(Address address) {\n" +
                "        this.address = address;\n" +
                "    }\n" +
                "\n" +
                "    public void setBooks(String[] books) {\n" +
                "        this.books = books;\n" +
                "    }\n" +
                "\n" +
                "    public void setHobbies(List<String> hobbies) {\n" +
                "        this.hobbies = hobbies;\n" +
                "    }\n" +
                "\n" +
                "    public void setCard(Map<String, String> card) {\n" +
                "        this.card = card;\n" +
                "    }\n" +
                "\n" +
                "    public void setGames(Set<String> games) {\n" +
                "        this.games = games;\n" +
                "    }\n" +
                "\n" +
                "    public void setWife(String wife) {\n" +
                "        this.wife = wife;\n" +
                "    }\n" +
                "\n" +
                "    public void setInfo(Properties info) {\n" +
                "        this.info = info;\n" +
                "    }\n" +
                "\n" +
                "    public String getName() {\n" +
                "        return name;\n" +
                "    }\n" +
                "\n" +
                "    public Address getAddress() {\n" +
                "        return address;\n" +
                "    }\n" +
                "\n" +
                "    public String[] getBooks() {\n" +
                "        return books;\n" +
                "    }\n" +
                "\n" +
                "    public List<String> getHobbies() {\n" +
                "        return hobbies;\n" +
                "    }\n" +
                "\n" +
                "    public Map<String, String> getCard() {\n" +
                "        return card;\n" +
                "    }\n" +
                "\n" +
                "    public Set<String> getGames() {\n" +
                "        return games;\n" +
                "    }\n" +
                "\n" +
                "    public String getWife() {\n" +
                "        return wife;\n" +
                "    }\n" +
                "\n" +
                "    public Properties getInfo() {\n" +
                "        return info;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public String toString() {\n" +
                "        return \"Student{\" +\n" +
                "                \"name='\" + name + '\\'' +\n" +
                "                \", address=\" + address +\n" +
                "                \", books=\" + Arrays.toString(books) +\n" +
                "                \", hobbies=\" + hobbies +\n" +
                "                \", card=\" + card +\n" +
                "                \", games=\" + games +\n" +
                "                \", wife='\" + wife + '\\'' +\n" +
                "                \", info=\" + info +\n" +
                "                '}';\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "实体类配置文件 beans.xml\n" +
                "\n" +
                "```xml\n" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n" +
                "        https://www.springframework.org/schema/beans/spring-beans.xsd\">\n" +
                "\n" +
                "    <bean id=\"address\" class=\"com.moodright.pojo.Address\">\n" +
                "        <property name=\"address\" value=\"地幔\" />\n" +
                "    </bean>\n" +
                "\n" +
                "    <bean id=\"student\" class=\"com.moodright.pojo.Student\">\n" +
                "        <!--字段值注入-->\n" +
                "        <property name=\"name\" value=\"新杰\"/>\n" +
                "        <!-- 引用注入 -->\n" +
                "        <property name=\"address\" ref=\"address\" />\n" +
                "        <!-- 数组注入 -->\n" +
                "        <property name=\"books\">\n" +
                "            <array>\n" +
                "                <value>平凡的世界</value>\n" +
                "                <value>亲爱的三毛</value>\n" +
                "                <value>送你一匹马</value>\n" +
                "            </array>\n" +
                "        </property>\n" +
                "        <!-- List注入 -->\n" +
                "        <property name=\"hobbies\">\n" +
                "            <list>\n" +
                "                <value>听歌</value>\n" +
                "                <value>跑步</value>\n" +
                "                <value>吃饭</value>\n" +
                "            </list>\n" +
                "        </property>\n" +
                "        <!-- Map注入 -->\n" +
                "        <property name=\"card\">\n" +
                "            <map>\n" +
                "                <entry key=\"身份证\" value=\"165198419191\" />\n" +
                "                <entry key=\"校园卡\" value=\"56165165165165\" />\n" +
                "            </map>\n" +
                "        </property>\n" +
                "        <!-- Set注入 -->\n" +
                "        <property name=\"games\">\n" +
                "            <set>\n" +
                "                <value>R6S</value>\n" +
                "                <value>Apex</value>\n" +
                "                <value>PUBG</value>\n" +
                "            </set>\n" +
                "        </property>\n" +
                "        <!-- null注入 -->\n" +
                "        <property name=\"wife\">\n" +
                "            <null />\n" +
                "        </property>\n" +
                "        <!-- Property注入 -->\n" +
                "        <property name=\"info\">\n" +
                "            <props>\n" +
                "                <prop key=\"学号\">171003600207</prop>\n" +
                "                <prop key=\"姓名\">新杰</prop>\n" +
                "                <prop key=\"性别\">男</prop>\n" +
                "            </props>\n" +
                "        </property>\n" +
                "    </bean>\n" +
                "</beans>\n" +
                "```\n" +
                "\n" +
                "c-namespace 和 p-namespace 测试代码\n" +
                "\n" +
                "userbean.xml\n" +
                "\n" +
                "```xml\n" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "       xmlns:p=\"http://www.springframework.org/schema/p\"\n" +
                "       xmlns:c=\"http://www.springframework.org/schema/c\"\n" +
                "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n" +
                "        https://www.springframework.org/schema/beans/spring-beans.xsd\">\n" +
                "\n" +
                "    <bean id=\"user\" class=\"com.moodright.pojo.User\" p:name=\"新杰\" p:age=\"18\" />\n" +
                "\n" +
                "    <bean id=\"user2\" class=\"com.moodright.pojo.User\" c:name=\"俞新杰\" c:age=\"19\" />\n" +
                "\n" +
                "</beans>\n" +
                "```\n" +
                "\n" +
                "---\n" +
                "\n" +
                "## 二、Bean的作用域(Bean Scope)\n" +
                "\n" +
                "- singleton\n" +
                "- prototype\n" +
                "- request\n" +
                "- session\n" +
                "- application\n" +
                "- websocket\n" +
                "\n" +
                "singleton ：spring的默认模式，容器中只有一个对象\n" +
                "\n" +
                "prototype ：每次从容器中get的时候，都会产生一个新的对象\n" +
                "\n" +
                "request、session、application、websocket只在web开发中会使用到\n" +
                "\n" +
                "```xml\n" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "       xmlns:p=\"http://www.springframework.org/schema/p\"\n" +
                "       xmlns:c=\"http://www.springframework.org/schema/c\"\n" +
                "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n" +
                "        https://www.springframework.org/schema/beans/spring-beans.xsd\">\n" +
                "\n" +
                "    <bean id=\"user\" class=\"com.moodright.pojo.User\" p:name=\"新杰\" p:age=\"18\" scope=\"singleton\" />\n" +
                "\n" +
                "    <bean id=\"user2\" class=\"com.moodright.pojo.User\" c:name=\"俞新杰\" c:age=\"19\" scope=\"prototype\" />\n" +
                "\n" +
                "</beans>\n" +
                "```\n" +
                "\n" +
                "---\n" +
                "\n" +
                "## Bean的自动装配\n" +
                "\n" +
                "1. 自动装配是Spring满足Bean依赖的一种方式\n" +
                "2. Spring会在上下文中自动寻找，并自动给Bean装配属性\n" +
                "\n" +
                "在Srping中有三种装配方式\n" +
                "\n" +
                "- 基于XML的显示配置\n" +
                "- 基于Java的显示配置\n" +
                "- **隐式的自动装配Bean**\n" +
                "\n" +
                "### 1. byName自动装配\n" +
                "\n" +
                "byName：<bean>元素的属性，会自动在容器上下文中查找，和自己对象中设置属性的setXXX方法后面的值（即XXX）对应的bean id\n" +
                "\n" +
                "```xml\n" +
                "<bean id=\"person\" class=\"com.moodright.pojo.Person\" autowire=\"byName\" />\n" +
                "```\n" +
                "\n" +
                "2. byType自动装配\n" +
                "\n" +
                "byType：<bean>元素的属性，会自动在容器上下文中查找，和自己对象属性类型相同的bean （需要保证此类型全局为一）\n" +
                "\n" +
                "```xml\n" +
                "<bean id=\"person\" class=\"com.moodright.pojo.Person\" autowire=\"byType\" />\n" +
                "```\n" +
                "\n" +
                "结论：\n" +
                "\n" +
                "- 在使用byName进行自动装配的时候，需要保证所有注入为依赖的bean的id唯一，且id要和自动注入属性的setXXX方法的值一致，即 id与XXX应相等\n" +
                "- 在使用byType进行自动装配的时候，需要保证所有注入为依赖的bean的class唯一，且class要和自动注入属性的类型一致\n" +
                "\n" +
                "### 测试用例\n" +
                "\n" +
                "POJO \\ Entity 实体类  [Person.java](http://person.java)、Cat.java、Dog.java\n" +
                "\n" +
                "```java\n" +
                "package com.moodright.pojo;\n" +
                "\n" +
                "/**\n" +
                " * @author moodright\n" +
                " * @date 2020/12/8\n" +
                " */\n" +
                "public class Person {\n" +
                "    private String name;\n" +
                "    private Cat cat;\n" +
                "    private Dog dog;\n" +
                "\n" +
                "    public void setName(String name) {\n" +
                "        this.name = name;\n" +
                "    }\n" +
                "\n" +
                "    public void setCat(Cat cat) {\n" +
                "        this.cat = cat;\n" +
                "    }\n" +
                "\n" +
                "    public void setDog(Dog dog) {\n" +
                "        this.dog = dog;\n" +
                "    }\n" +
                "\n" +
                "    public String getName() {\n" +
                "        return name;\n" +
                "    }\n" +
                "\n" +
                "    public Cat getCat() {\n" +
                "        return cat;\n" +
                "    }\n" +
                "\n" +
                "    public Dog getDog() {\n" +
                "        return dog;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public String toString() {\n" +
                "        return \"Person{\" +\n" +
                "                \"name='\" + name + '\\'' +\n" +
                "                \", cat=\" + cat +\n" +
                "                \", dog=\" + dog +\n" +
                "                '}';\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "package com.moodright.pojo;\n" +
                "\n" +
                "/**\n" +
                " * @author moodright\n" +
                " * @date 2020/12/8\n" +
                " */\n" +
                "public class Dog {\n" +
                "    public void shout() {\n" +
                "        System.out.println(\"wang~\");\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "package com.moodright.pojo;\n" +
                "\n" +
                "/**\n" +
                " * @author moodright\n" +
                " * @date 2020/12/8\n" +
                " */\n" +
                "public class Cat {\n" +
                "    public void shout() {\n" +
                "        System.out.println(\"meow~\");\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "XML配置文件 beans.xml\n" +
                "\n" +
                "```xml\n" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n" +
                "        https://www.springframework.org/schema/beans/spring-beans.xsd\">\n" +
                "\n" +
                "    <bean id=\"cat\" class=\"com.moodright.pojo.Cat\" />\n" +
                "    <bean id=\"dog\" class=\"com.moodright.pojo.Dog\" />\n" +
                "\n" +
                "    <bean id=\"person\" class=\"com.moodright.pojo.Person\" autowire=\"byName\" >\n" +
                "        <property name=\"name\" value=\"Moonlight\"/>\n" +
                "    </bean>\n" +
                "</beans>\n" +
                "\n" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n" +
                "        https://www.springframework.org/schema/beans/spring-beans.xsd\">\n" +
                "\n" +
                "    <bean id=\"cat\" class=\"com.moodright.pojo.Cat\" />\n" +
                "    <bean id=\"dog\" class=\"com.moodright.pojo.Dog\" />\n" +
                "\n" +
                "    <bean id=\"person\" class=\"com.moodright.pojo.Person\" autowire=\"byType\" >\n" +
                "        <property name=\"name\" value=\"Moonlight\"/>\n" +
                "    </bean>\n" +
                "</beans>\n" +
                "```\n" +
                "\n" +
                "测试类 MyTest.java\n" +
                "\n" +
                "```java\n" +
                "import com.moodright.pojo.Person;\n" +
                "import org.junit.Test;\n" +
                "import org.springframework.context.ApplicationContext;\n" +
                "import org.springframework.context.support.ClassPathXmlApplicationContext;\n" +
                "\n" +
                "/**\n" +
                " * @author moodright\n" +
                " * @date 2020/12/8\n" +
                " */\n" +
                "public class MyTest {\n" +
                "    @Test\n" +
                "    public void myTest1() {\n" +
                "        ApplicationContext ctx = new ClassPathXmlApplicationContext(\"beans.xml\");\n" +
                "        Person person = ctx.getBean(\"person\", Person.class);\n" +
                "        person.getCat().shout();\n" +
                "        person.getDog().shout();\n" +
                "        System.out.println(person);\n" +
                "\n" +
                "        /* 测试输出:\n" +
                "         meow~\n" +
                "         wang~\n" +
                "         Person{name='Moonlight', cat=com.moodright.pojo.Cat@28ac3dc3, dog=com.moodright.pojo.Dog@32eebfca}\n" +
                "         */\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "---\n" +
                "\n" +
                "## 三、通过注解实现自动装配\n" +
                "\n" +
                "- @Autowired   默认通过类型实现自动装配\n" +
                "- @Qualifier   如果@Autowired不能找到唯一的应被注入的依赖，可以通过此注解的value值指定bean id实现自动装配\n" +
                "- @Nullable   可以标记在字段、方法参数上，表示此字段/方法参数可以为空\n" +
                "- @Resource   javax.annotation.Resource 默认通过bean id 实现自动装配\n" +
                "\n" +
                "在XML文件中配置约束\n" +
                "\n" +
                "```xml\n" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "    xmlns:context=\"http://www.springframework.org/schema/context\"\n" +
                "    xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n" +
                "        https://www.springframework.org/schema/beans/spring-beans.xsd\n" +
                "        http://www.springframework.org/schema/context\n" +
                "        https://www.springframework.org/schema/context/spring-context.xsd\">\n" +
                "\n" +
                "\t\t<!-- 开启注解的支持 --> \n" +
                "\t\t<context:annotation-config/>\n" +
                "\n" +
                "</beans>\n" +
                "```\n" +
                "\n" +
                "### 测试用例\n" +
                "\n" +
                "XML 配置文件 beans.xml\n" +
                "\n" +
                "```xml\n" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "       xmlns:context=\"http://www.springframework.org/schema/context\"\n" +
                "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n" +
                "        https://www.springframework.org/schema/beans/spring-beans.xsd\n" +
                "        http://www.springframework.org/schema/context\n" +
                "        https://www.springframework.org/schema/context/spring-context.xsd\">\n" +
                "\n" +
                "        <context:annotation-config/>\n" +
                "\n" +
                "        <bean id=\"111\" class=\"com.moodright.pojo.Cat\" />\n" +
                "        <bean id=\"444\" class=\"com.moodright.pojo.Cat\" />\n" +
                "        <bean id=\"222\" class=\"com.moodright.pojo.Dog\" />\n" +
                "        <bean id=\"333\" class=\"com.moodright.pojo.Dog\" />\n" +
                "\n" +
                "        <bean id=\"person\" class=\"com.moodright.pojo.Person\">\n" +
                "            <property name=\"name\" value=\"Moonlight\"/>\n" +
                "        </bean>\n" +
                "</beans>\n" +
                "```\n" +
                "\n" +
                "Entity实体类 Person.java\n" +
                "\n" +
                "```java\n" +
                "package com.moodright.pojo;\n" +
                "\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.beans.factory.annotation.Qualifier;\n" +
                "\n" +
                "import javax.annotation.Resource;\n" +
                "\n" +
                "/**\n" +
                " * @author moodright\n" +
                " * @date 2020/12/8\n" +
                " */\n" +
                "public class Person {\n" +
                "    private String name;\n" +
                "\n" +
                "    @Autowired\n" +
                "    @Qualifier(value = \"111\")\n" +
                "    private Cat cat;\n" +
                "\n" +
                "    @Resource(name = \"333\")\n" +
                "    private Dog dog;\n" +
                "\n" +
                "    public Person() {\n" +
                "    }\n" +
                "\n" +
                "    public void setName(String name) {\n" +
                "        this.name = name;\n" +
                "    }\n" +
                "    public String getName() {\n" +
                "        return name;\n" +
                "    }\n" +
                "\n" +
                "    public Cat getCat() {\n" +
                "        return cat;\n" +
                "    }\n" +
                "\n" +
                "    public Dog getDog() {\n" +
                "        return dog;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public String toString() {\n" +
                "        return \"Person{\" +\n" +
                "                \"name='\" + name + '\\'' +\n" +
                "                \", cat=\" + cat +\n" +
                "                \", dog=\" + dog +\n" +
                "                '}';\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "测试类 MyTest.java\n" +
                "\n" +
                "```java\n" +
                "import com.moodright.pojo.Person;\n" +
                "import org.junit.Test;\n" +
                "import org.springframework.context.ApplicationContext;\n" +
                "import org.springframework.context.support.ClassPathXmlApplicationContext;\n" +
                "\n" +
                "/**\n" +
                " * @author moodright\n" +
                " * @date 2020/12/8\n" +
                " */\n" +
                "public class MyTest {\n" +
                "    @Test\n" +
                "    public void myTest1() {\n" +
                "        ApplicationContext ctx = new ClassPathXmlApplicationContext(\"beans.xml\");\n" +
                "        Person person = ctx.getBean(\"person\", Person.class);\n" +
                "        person.getCat().shout();\n" +
                "        person.getDog().shout();\n" +
                "        System.out.println(person);\n" +
                "\n" +
                "        /* 测试输出:\n" +
                "         meow~\n" +
                "         wang~\n" +
                "         Person{name='Moonlight', cat=com.moodright.pojo.Cat@28ac3dc3, dog=com.moodright.pojo.Dog@32eebfca}\n" +
                "         */\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "---";
        Map<String, Map<Integer, Integer>> segment = chineseSegmentUtil.segment(2011, postContent);
        for (Map.Entry<String, Map<Integer, Integer>> entry : segment.entrySet()) {
            System.out.println("Key = " + entry.getKey());
//            for ( Map.Entry<Integer, Integer> entry1 : entry.getValue().entrySet()) {
//                System.out.println("Value = ( subKey = " + entry1.getKey() + " subValue = " + entry1.getValue() + " )");
//            }
            String str = JSON.toJSONString(entry.getValue());
            System.out.println(str);
        }
    }
}
