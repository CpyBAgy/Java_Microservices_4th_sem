import com.CpyBAgy.javarush.DAO.CatDAO;
import com.CpyBAgy.javarush.DAO.IDAO;
import com.CpyBAgy.javarush.DTO.CatDTO;
import com.CpyBAgy.javarush.Entities.Cat;
import com.CpyBAgy.javarush.Entities.Color;
import com.CpyBAgy.javarush.Services.CatService;
import com.CpyBAgy.javarush.Services.ICatService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class TestCatService {
    @Test
    public void createCat_catCreatedAndHasValidInfo() {
        Cat cat = new Cat();
        cat.setId(1);
        cat.setName("Barsik");
        cat.setColor(Color.black);
        cat.setBreed("Dvoroviy");
        cat.setBirthday(new Date(2020, Calendar.APRIL, 13));
        cat.setFriends(new ArrayList<>());

        IDAO<Cat, Integer> dao = Mockito.mock(CatDAO.class);
        Mockito.when(dao.create(Mockito.isA(Cat.class))).thenReturn(1);
        Mockito.when(dao.findById(Mockito.isA(Integer.class))).thenReturn(Optional.of(cat));
        Mockito.doNothing().when(dao).update(Mockito.isA(Cat.class));
        Mockito.doNothing().when(dao).delete(Mockito.isA(Cat.class));

        ICatService service = new CatService(dao);

        Integer id = service.createCat(cat.getName(), cat.getBirthday(), cat.getBreed(), cat.getColor());
        CatDTO catDTO = service.getCatById(id);

        Assertions.assertEquals(id, 1);
        Assertions.assertEquals(catDTO.name(), cat.getName());
        Assertions.assertEquals(catDTO.breed(), cat.getBreed());
        Assertions.assertEquals(catDTO.color(), cat.getColor());
        Assertions.assertEquals(catDTO.birthday(), cat.getBirthday());
    }
}
