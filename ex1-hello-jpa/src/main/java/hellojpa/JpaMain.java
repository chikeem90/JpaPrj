package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
            Parent parent = new Parent();
            Child child1 = new Child();
            Child child2 = new Child();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            em.remove(findParent);
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
