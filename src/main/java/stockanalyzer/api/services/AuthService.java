package stockanalyzer.api.services;

import stockanalyzer.api.models.User;

public interface AuthService {

    void signup(User user);

    String signin(User user);
}
