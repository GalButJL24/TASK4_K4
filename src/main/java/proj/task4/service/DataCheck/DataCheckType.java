package proj.task4.service.DataCheck;

import org.springframework.stereotype.Component;
import proj.task4.model.Model;
import proj.task4.service.LogTransformation;

import java.util.List;

@Component
public class DataCheckType implements DataCheckable {
    // Проверяем тип приложения
    public String checkType(String applType) {
        if (applType == null)
            applType = "";
        if (!applType.equals("web") && !applType.equals("mobile"))
            return "other:" + applType;
        return applType;
    }

    // Проверяем тип (компонента логируется )
    @LogTransformation("LogComponentReader.log")
    @Override
    public List<Model> check(List<Model> mods) {
        mods.stream().peek(x-> x.setApplType(checkType(x.getApplType()))).toList();//.collect(Collectors.toList());
        return mods;
    }
}
