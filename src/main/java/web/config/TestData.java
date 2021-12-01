package web.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

//import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class TestData {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public TestData(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    public void insertData() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        roleService.saveRole(new Role("ROLE_ADMIN"));
        roleService.saveRole(new Role("ROLE_USER"));

        Set<Role> roles1 = new HashSet<>();
        roles1.add(roleService.getRoleByName("ROLE_USER"));

        User bob = new User();
        bob.setUsername("USER");
        bob.setPassword("USER");
        bob.setName("USER");
        bob.setAge(25);
        bob.setEmail("USER@Mail.ru");
        bob.setRoles(roles1);
        userService.addUser(bob);

        Set<Role> roles2 = new HashSet<>();
        roles2.add(roleService.getRoleByName("ROLE_USER"));
        roles2.add(roleService.getRoleByName("ROLE_ADMIN"));

        User tom = new User();
        tom.setUsername("ADMIN");
        tom.setPassword("ADMIN");
        tom.setName("ADMIN");
        tom.setAge(35);
        tom.setEmail("ADMIN@mail.ru");
        tom.setRoles(roles2);
        userService.addUser(tom);
    }
}
