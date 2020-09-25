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

            Team team2 = new Team();
            team.setName("TeamB");
            em.persist(team2);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();
//            // 한 회원이 어느 팀에 소속되어 있나 조회하기 위해서는
//            // 멤버의 TeamId를 조회해서 그 TeamId로 또 TEAM을 조회해야함.
//            Member findMember = em.find(Member.class, member.getId());
//            Long findTeamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, findTeamId);
            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());
            // 커밋
            findMember.setTeam(team2);
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
