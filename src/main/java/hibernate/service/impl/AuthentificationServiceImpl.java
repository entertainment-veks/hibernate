package hibernate.service.impl;

import hibernate.exception.AuthenticationException;
import hibernate.model.User;
import hibernate.service.AuthenticationService;
import hibernate.service.ShoppingCartService;
import hibernate.service.UserService;
import hibernate.util.SecurityUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationServiceImpl implements AuthenticationService {
    private UserService userService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public AuthentificationServiceImpl(UserService userService,
                                       ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> dirty = userService.findByEmail(email);
        if (dirty.isPresent() && dirty.get().getPassword()
                .equals(SecurityUtil.hashPassword(password, dirty.get().getSalt()))) {
            return dirty.get();
        }
        throw new AuthenticationException("Wrong email or password");
    }

    @Override
    public User register(String email, String password) {
        User current = new User();
        current.setEmail(email);
        current.setPassword(password);
        current = userService.add(current);
        shoppingCartService.registerNewShoppingCart(current);
        return current;
    }
}
