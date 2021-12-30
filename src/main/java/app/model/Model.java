package app.model;

import app.entities.User1;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private static Model instance = new Model();

    private List<User1> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new ArrayList<>();
    }

    public void add(User1 user) {
        model.add(user);
    }

    public List<String> list() {
//        return model.stream().map(User::getName).collect(Collectors.toList());
        List<String> names = new ArrayList<>();
        for (User1 user : model) {
            names.add(user.getName());
        }
        return names;
    }
}
