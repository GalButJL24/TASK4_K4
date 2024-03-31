package proj.task4.service.DataWriter;

import proj.task4.model.Model;

import java.util.List;

public interface DataWriteable {
    void writeDb(List<Model> mods);
}
