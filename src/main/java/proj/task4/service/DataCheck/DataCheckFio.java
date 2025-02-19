package proj.task4.service.DataCheck;

import org.springframework.stereotype.Component;
import proj.task4.model.Model;
import proj.task4.service.LogTransformation;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataCheckFio implements DataCheckable {

    // Проверяем ФИО
    public String checkFio(String fio) {
        // В ФИО может быть несколько пробелов
        String[] strArr = fio.split(" ");
        // Итоговая строка
        StringBuilder strRes = new StringBuilder();
        for (String s : strArr) {
            String strAdd = s.trim();
            // Делаем первую букву заглавной
            if (!strAdd.isEmpty())
                strRes.append(" ").append(strAdd.substring(0, 1).toUpperCase()).append(strAdd.substring(1).toLowerCase());
        }
        // удаляем добавленный первый пробел
        return strRes.toString().trim();
    }


    // Проверяем ФИО  (компонента логируется )
    @LogTransformation("LogComponentReader.log")
    @Override
    public List<Model> check(List<Model> mods) {
        mods.stream().peek(x-> x.setFio(checkFio(x.getFio()))).collect(Collectors.toList());
        return mods;
    }
}
