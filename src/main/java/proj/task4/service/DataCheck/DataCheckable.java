package proj.task4.service.DataCheck;

import proj.task4.model.Model;

import java.io.IOException;
import java.util.List;

public interface DataCheckable {
    List<Model> check(List<Model> mods) throws IOException;
}
