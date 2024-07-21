/**
 * 
 */
package com.home.datacache;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

import com.home.model.User;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;



/**
 * Die UserCacheBean würde in der Geschäftslogikschicht (Business Layer) implementiert.
 * Sie dient als Zwischenspeicher für Benutzerdaten, um wiederholte Datenbankabfragen zu reduzieren.
 * Der Server verwaltet den Lebenszyklus der Bean basierend auf den Annotationen wie @Singleton und @Startup.
 * 
 * UserCacheBean ist besonders nützlich in Szenarien, wo:
 * Benutzerdaten häufig gelesen, aber selten geändert werden.
 * Die Anwendung eine hohe Leistung bei Benutzerabfragen benötigt.
 * 
 * @author Ahmad Alrefai
 */
@Singleton
@Startup
public class UserCacheBean {

    protected Queue<User> cache = null;
    
    @PersistenceContext
    private EntityManager em;

    public UserCacheBean() {
    }

    protected void loadCache() {
    	TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
    	List<User> list = query.getResultList();

        list.forEach((user) -> {
            cache.add(user);
        });
    }

    @Lock(LockType.READ)
    public List<User> get() {
        return cache.stream().collect(Collectors.toList());
    }

    @PostConstruct
    protected void init() {
        cache = new ConcurrentLinkedQueue<>();
        loadCache();
    }

}
