package client.impl;

import client.dao.data.Gender;
import client.dao.repository.GenderRepository;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import util.Messages;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class GenderImpl {

    Messages messages = new Messages();
    @Inject
    GenderRepository genderRepository;

    public JsonObject genderListAll() {
        try {
            List<Gender> genderList = genderRepository.genderListAll();
            if (!genderList.isEmpty()) {
                return new JsonObject().put("response", genderList);
            } else {
                return messages.messageListEmpty();
            }
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject findGenderById(JsonObject data) {
        try {
            Long genderId = data.getLong("genderId");
            if (genderId > 0) {
                Gender gender = genderRepository.genderFindById(genderId);
                if (gender.idGender > 0) {
                    return new JsonObject().put("response", gender);
                } else {
                    return messages.messageListEmpty();
                }
            } else {
                return messages.messageDataInput();
            }
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject createGender(JsonObject data) {
        try {
            if (!data.isEmpty()) {
                Gender gender = new Gender();
                gender.description = data.getString("description");
                gender.dateCreate = new Date();
                gender.userCreate = data.getInteger("userCreate");
                genderRepository.genderSave(gender);
                return new JsonObject().put("response", "Género registrado correctamente");
            } else {
                return messages.messageListEmpty();
            }
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject updateGender(JsonObject data) {
        try {
            if (!data.isEmpty()) {
                Gender gender = new Gender();
                gender.idGender = data.getLong("idGender");
                gender.description = data.getString("description");
                gender.dateUpdate = new Date();
                gender.userUpdate = data.getInteger("userCreate");
                genderRepository.genderSave(gender);
                return new JsonObject().put("response", "Género actualizado correctamente");
            } else {
                return messages.messageListEmpty();
            }
        } catch (Exception e) {
            return messages.messageError();
        }
    }

    public JsonObject deleteGender(JsonObject data){
        try{
            Long idGender = data.getLong("idGender");
            if(idGender > 0){
                if(genderRepository.genderDelete(idGender) > 0){
                    return new JsonObject().put("response", "Género eliminado correctamente");
                }else{
                    return messages.messageListEmpty();
                }
            }else{
                return messages.messageDataInput();
            }
        } catch (Exception e) {
            return messages.messageError();
        }
    }
}
