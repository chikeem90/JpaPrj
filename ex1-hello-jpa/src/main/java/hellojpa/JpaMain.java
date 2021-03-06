package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        // Persistence unit name을 넘긴다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // Entity Manager를 꺼내고 (connection을 물고 온다)
        EntityManager em = emf.createEntityManager();
        // Transaction 얻어서 시작
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // 실제 코드를 작성
        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street", "10000"));
            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("족발");

            member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "10000"));
            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("=================== START ===================");
            Member findMember = em.find(Member.class, member.getId());
//            findMember.getHomeAddress().setCity("newCity");
//            Address a = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));
//
//            // 치킨 -> 한식
//            //
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("한식");

//            findMember.getAddressHistory().remove(new Address("old1", "street", "10000"));
//            findMember.getAddressHistory().add(new Address("newCity1", "street", "10000"));

            // 커밋
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        // 어플리케이션 끝나면 클로즈
        emf.close();
    }

    private static void logic(Member m1, Member m2) {
        System.out.println("m1 == m2 : " + (m1 instanceof Member));
        System.out.println("m1 == m2 : " + (m2 instanceof Member));
    }
}
