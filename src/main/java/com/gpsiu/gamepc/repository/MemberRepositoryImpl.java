package com.gpsiu.gamepc.repository;

import com.gpsiu.gamepc.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepositoryImpl implements MemberRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public Optional<Member> findById(Long id) {
        try {
            return Optional.ofNullable(em.find(Member.class, id));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        TypedQuery<Member> query = em.createQuery("select m from Member m where username = :username", Member.class);
        try {
            return Optional.ofNullable(
                    query.setParameter("username", username)
                            .getSingleResult()
            );
        } catch (NoResultException e) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Member> findByName(String name) {
        TypedQuery<Member> query = em.createQuery("select m from Member m where name = :name", Member.class);
        try {
            return Optional.ofNullable(
                    query.setParameter("name", name)
                            .getSingleResult()
            );
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}