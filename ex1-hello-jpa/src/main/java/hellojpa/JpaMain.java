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
            // 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
//            member.changeTeam(team); //**
            em.persist(member);

            team.addMember(member);

//            team.getMembers().add(member); //**

//            em.flush();
//            em.clear();

            // 1차 캐시에 있는 걸 가지고 오기 때문에,
            // Team 객체의 members에는 아무 것도 들어 있지 않은 상태
            // 실제 DB로 SELECT 날리지 않기 때문에,,
            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            System.out.println("================");
            // 무한루프에 빠지게 된다
            System.out.println("members = " + findTeam);
            System.out.println("================");

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
