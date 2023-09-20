package hei.server.repository;

import hei.server.model.Group;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository {
    List<Group> getAll();

    Group getById(Integer id);

    Group add(Group group);

    Group updateGroup(Group group);

    void deleteGroupById(Integer id);
}
