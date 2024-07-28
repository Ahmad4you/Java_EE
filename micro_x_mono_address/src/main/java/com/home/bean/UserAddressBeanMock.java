/**
 * 
 */
package com.home.bean;

import java.util.ArrayList;
import java.util.List;

import com.home.model.UserAddress;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;



/**
 * 
 * @author Ahmad Alrefai
 */
@Stateless
public class UserAddressBeanMock {

    @PersistenceContext
    private EntityManager em;

    private static final List<UserAddress> ADDRESS_LIST = new ArrayList<>();

    public void add(UserAddress address) {
        ADDRESS_LIST.add(address);
    }

    public void remove(UserAddress address) {
        ADDRESS_LIST.remove(address);
    }

    public void update(UserAddress address) {
        for (UserAddress u: ADDRESS_LIST) {
            if (u.getId().equals(address.getId())) {
                ADDRESS_LIST.remove(address);
                break;
            }
        }
        ADDRESS_LIST.add(address);
    }

    public UserAddress findById(Long id) {
        for (UserAddress u : ADDRESS_LIST) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    public List<UserAddress> get() {
        return ADDRESS_LIST;
    }
}
