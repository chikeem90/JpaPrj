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
            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            Team team = new Team();
            team.setName("teamA");
            // MEMBER에 UPDATE 날린다
            team.getMembers().add(member);
            em.persist(team);

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
