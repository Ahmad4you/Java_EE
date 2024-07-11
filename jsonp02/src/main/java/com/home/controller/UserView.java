/**
 * 
 */
package com.home.controller;

import java.io.Serializable;

import com.home.entity.User;
import jakarta.json.JsonStructure;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonPointer;
import jakarta.json.JsonValue;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;


/**
 * 
 * @author Ahmad Alrefai
 */


@ViewScoped
@Named
public class UserView implements Serializable {

    private static final long serialVersionUID = -7076717337997780675L;
	private static final JsonBuilderFactory BUILDERFACTORY = Json.createBuilderFactory(null);
    private final Jsonb jsonbBuilder = JsonbBuilder.create();

    private String fromArray;
    private String fromStructure;
    private String fromUser;
    private String fromJpointer;

    public void loadUserJson() {
        loadFromArray();
        loadFromStructure();
        loadFromUser();
    }

    private void loadFromArray() {
        JsonArray array = BUILDERFACTORY.createArrayBuilder()
                .add(BUILDERFACTORY.createObjectBuilder()
                        .add("name", "User1")
                        .add("email", "user1@outlook.de"))
                .add(BUILDERFACTORY.createObjectBuilder()
                        .add("name", "User2")
                        .add("email", "user2@outlook.de"))
                .add(BUILDERFACTORY.createObjectBuilder()
                        .add("name", "User3")
                        .add("email", "user3@outlook.de"))
                .build();
        fromArray = jsonbBuilder.toJson(array);
    }

    private void loadFromStructure() {
        JsonStructure structure = BUILDERFACTORY.createObjectBuilder()
                .add("name", "User1")
                .add("email", "user1@outlook.de")
                .add("profiles", BUILDERFACTORY.createArrayBuilder()
                        .add(BUILDERFACTORY.createObjectBuilder()
                                .add("id", "1")
                                .add("name", "Profile1"))
                        .add(BUILDERFACTORY.createObjectBuilder()
                                .add("id", "2")
                                .add("name", "Profile2")))
                .build();
        fromStructure = jsonbBuilder.toJson(structure);

        JsonPointer pointer = Json.createPointer("/profiles");
        JsonValue value = pointer.getValue(structure);
        fromJpointer = value.toString();
    }

    private void loadFromUser() {
        User user = new User("Ahmad", "ahmad343@outlook.de", new Integer[]{1, 2, 3});
        fromUser = jsonbBuilder.toJson(user);
    }

    public String getFromArray() {
        return fromArray;
    }

    public void setFromArray(String fromArray) {
        this.fromArray = fromArray;
    }

    public String getFromStructure() {
        return fromStructure;
    }

    public void setFromStructure(String fromStructure) {
        this.fromStructure = fromStructure;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getFromJpointer() {
        return fromJpointer;
    }

    public void setFromJpointer(String fromJpointer) {
        this.fromJpointer = fromJpointer;
    }
}

