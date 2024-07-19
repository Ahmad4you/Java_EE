/**
 * 
 */
package com.home.batch01;

import java.util.StringTokenizer;

import com.home.model.User;
import com.home.model.Zugangsdaten;

import jakarta.batch.api.chunk.ItemProcessor;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;


/**
 * 
 * @author Ahmad Alrefai
 */
@Named
@Dependent
public class UserProcessor implements ItemProcessor {

    @Override
    public User processItem(Object line) {
        User user = new User();
        Zugangsdaten zugangsdaten = new Zugangsdaten();

        StringTokenizer tokens = new StringTokenizer((String) line, ",");
//        user.setId(Integer.parseInt(tokens.nextToken()));
        tokens.nextToken();
        user.setFirstName(tokens.nextToken());
        user.setLastName(tokens.nextToken());
//        zugangsdaten.setEmail(tokens.nextToken());
//        user.setZugangsdaten(zugangsdaten);
//        zugangsdaten.setUser(user);
        
        return user;
    }
}