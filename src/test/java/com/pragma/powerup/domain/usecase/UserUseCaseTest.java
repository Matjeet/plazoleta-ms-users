package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.exception.ParamaterNotValidException;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    private User user;
    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;
    @BeforeEach
    void setUp(){
    }

    @Test
    void saveUserSuccess() {
        user = new User();
        user.setRoleId(1);
        user.setName("Mateo");
        user.setLastName("Velásquez");
        user.setDocumentId("1000919492");
        user.setPhoneNumber("+573226749122");
        user.setBirthDate(LocalDate.of(2002,11,29));
        user.setEmail("matius29002@gmail.com");
        user.setPassword("1234");
        user.setRoleId(1);

        Mockito.doNothing().when(userPersistencePort).saveUser(user);

        userUseCase.saveUser(user);

        Mockito.verify(userPersistencePort,Mockito.times(1)).saveUser(user);
    }

    @Test
    void saveUserFailed(){
        user = new User();
        user.setRoleId(1);
        user.setName("Mateo");
        user.setLastName("Velásquez");
        user.setDocumentId("1000919492");
        user.setPhoneNumber("3226749122");
        user.setBirthDate(LocalDate.of(2002,11,29));
        user.setEmail("matius29002@gmail.com");
        user.setPassword("1234");
        user.setRoleId(1);

        ParamaterNotValidException exception = assertThrows(ParamaterNotValidException.class, () -> {
            userUseCase.saveUser(user);
        }, "No exception was made");

        assertNull(exception.getMessage());
    }
}