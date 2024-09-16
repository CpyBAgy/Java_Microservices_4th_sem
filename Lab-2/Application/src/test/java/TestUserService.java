import com.CpyBAgy.javarush.DTO.UserDTO;
import com.CpyBAgy.javarush.Services.IUserService;
import com.CpyBAgy.javarush.Services.UserService;
import com.CpyBAgy.javarush.DAO.CatDAO;
import com.CpyBAgy.javarush.DAO.IDAO;
import com.CpyBAgy.javarush.DAO.UserDAO;
import com.CpyBAgy.javarush.Entities.Cat;
import com.CpyBAgy.javarush.Entities.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class TestUserService {
    @Test
    public void createOwner_OwnerCreatedAndHasValidInfo() {
        User user = new User();
        user.setId(1);
        user.setName("Vanyusha");
        user.setBirthday(new Date(2004, Calendar.MAY, 18));
        user.setCats(new ArrayList<>());

        Cat cat = new Cat();
        cat.setId(1);
        cat.setName("Barsik");
        cat.setBreed("Dvoroviy");
        cat.setBirthday(new Date(2020, Calendar.APRIL, 13));

        user.addCat(cat);
        cat.setUser(user);

        IDAO<User, Integer> dao = Mockito.mock(UserDAO.class);
        Mockito.when(dao.create(Mockito.isA(User.class))).thenReturn(1);
        Mockito.when(dao.findById(Mockito.isA(Integer.class))).thenReturn(Optional.of(user));
        Mockito.doNothing().when(dao).update(Mockito.isA(User.class));
        Mockito.doNothing().when(dao).delete(Mockito.isA(User.class));

        IDAO<Cat, Integer> catDao = Mockito.mock(CatDAO.class);
        Mockito.when(catDao.create(Mockito.isA(Cat.class))).thenReturn(1);
        Mockito.when(catDao.findById(Mockito.isA(Integer.class))).thenReturn(Optional.of(cat));
        Mockito.doNothing().when(catDao).update(Mockito.isA(Cat.class));
        Mockito.doNothing().when(catDao).delete(Mockito.isA(Cat.class));
        
        IUserService service = new UserService(dao, catDao);
        
        Integer id = service.createUser(user.getName(), user.getBirthday());
        UserDTO userDTO = service.getUserById(id);

        Assertions.assertEquals(id, 1);
        Assertions.assertEquals(user.getName(), userDTO.name());
        Assertions.assertEquals(user.getBirthday(), userDTO.birthday());
    }
}
