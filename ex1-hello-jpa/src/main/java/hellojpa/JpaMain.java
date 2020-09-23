package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // Persistence unit name을 넘긴다.dd
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // Entity Manager를 꺼내고 (connection을 물고 온다)
        EntityManager em = emf.createEntityManager();
        // Transaction 얻어서 시작
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // 실제 코드를 작성
        try {
            Member member1 = new Member();
            member1.setUsername("A");
            Member member2 = new Member();
            member2.setUsername("B");
            Member member3 = new Member();
            member3.setUsername("C");
            System.out.println("===============");
            em.persist(member1); // 1, 51
            em.persist(member2); // MEM
            em.persist(member3); // MEM
            System.out.println("member1.id = " + member1.getId());
            System.out.println("member2.id = " + member2.getId());
            System.out.println("member3.id = " + member3.getId());
            System.out.println("===============");
            // 커밋
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        // 어플리케이션 끝나면 클로즈
        emf.close();
    }
}
