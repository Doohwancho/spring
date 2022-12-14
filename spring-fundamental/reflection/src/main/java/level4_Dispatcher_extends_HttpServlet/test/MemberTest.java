package level4_Dispatcher_extends_HttpServlet.test;

import level4_Dispatcher_extends_HttpServlet.domain.Member;

import java.lang.reflect.Constructor;

public class MemberTest {

    public void newInstance_테스트() {

        String typeName = Member.class.getTypeName();
        System.out.println(typeName);

        Class<?> cls = Member.class;
        try {
            Constructor<?> constructor = cls.getConstructor();
            Member member = (Member) constructor.newInstance();
            member.setId(1);
            member.setUsername("ssar");
            member.setPassword("1234");

            System.out.println(member.getUsername());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new MemberTest().newInstance_테스트();
    }
}