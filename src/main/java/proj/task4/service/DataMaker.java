package proj.task4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proj.task4.model.Model;
import proj.task4.service.DataCheck.DataCheckDate;
import proj.task4.service.DataCheck.DataCheckFio;
import proj.task4.service.DataCheck.DataCheckType;
import proj.task4.service.DataCheck.DataCheckable;
import proj.task4.service.DataReader.DataReader;
import proj.task4.service.DataWriter.DataWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class DataMaker {
    // Строки прочитанные
    private List<Model> models;
    // Компонента чтения данных
    private final DataReader dataReader;

    private final DataWriter dataWriter;
    //Компоненты проверки данных
    List<DataCheckable> component = new ArrayList<>();


    @Autowired
    public DataMaker(DataReader dataReader
            ,  DataCheckFio dataCheckFio
            ,  DataCheckType dataCheckType
            ,  DataCheckDate dataCheckDate
            , DataWriter dateWriter) {

        this.dataReader = dataReader;
        this.dataWriter = dateWriter;

        this.component.add(dataCheckFio);
        this.component.add(dataCheckType);
        this.component.add(dataCheckDate);


    }

    public  void make() throws IOException {
        // Получим путь(по условию задачи) для загрузки файлов(его можно разными способами задавать)
        String strPath = dataReader.getPath();
        // Читаем данные из файлов
        models = dataReader.readFromFiles(strPath);

        // Выполняем проверку данных
        // Проверяем ФИО(меняем на первые заглавные буквы)
        // Проверяем тип меняем на "other: ", если не  web, mobile
        // Проверяем дату Если пустая, записываем в файл


        for (DataCheckable comp: component) {
            models = comp.check(models);
        }

        //    System.out.println("=========Запись в БД =========================");
        dataWriter.writeDb(models);
    }
}
