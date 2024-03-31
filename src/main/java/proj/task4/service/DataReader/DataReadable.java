package proj.task4.service.DataReader;



import proj.task4.model.Model;

import java.io.IOException;
import java.util.List;


public interface DataReadable {
    //  Получить данные для записи в БД
    List<Model> readFromFiles(String strPath) throws IOException;
}

