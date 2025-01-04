package util;

import io.vertx.core.json.JsonObject;

public class Messages {

    public JsonObject messageError() {
        return new JsonObject().put("error", "Ha sucedido un error al procesar la información - Comuniquese con el administrador del sistema");
    }

    public JsonObject messageListEmpty(){
        return new JsonObject().put("message", "No se cuenta con registro de datos");
    }

    public JsonObject messageDataInput(){
        return new JsonObject().put("message", "No se puede realizar la búsqueda con la información proporcionada");
    }
}
