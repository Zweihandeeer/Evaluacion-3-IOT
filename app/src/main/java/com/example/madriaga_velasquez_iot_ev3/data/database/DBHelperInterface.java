package com.example.madriaga_velasquez_iot_ev3.data.database;

import com.example.madriaga_velasquez_iot_ev3.domain.model.Evento;
import com.example.madriaga_velasquez_iot_ev3.domain.model.User;

import java.util.ArrayList;

public interface DBHelperInterface {
    Boolean insertData(User user);
    Boolean insertDataEventos(Evento evento);
    Boolean checkusername(String username);
    Boolean checkusernamepassword(String username, String password);
    Boolean deleteuser(String username);
    Boolean updatepassworduser(String username, String password);
    ArrayList<String> getAllRegistros();
}
