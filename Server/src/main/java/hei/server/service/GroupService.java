package hei.server.service;

import hei.server.model.Group;
import hei.server.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> findAllGroups() {
        return groupRepository.getAll();
    }

    public Group findGroupById(Integer id) {
        if (groupRepository.getById(id).getId() == null) {
            return null;
        }
        return groupRepository.getById(id);
    }

    public Group addNewGroup(Group group) {
        List<Group> groups = groupRepository.getAll();
        Integer lastId = groups.size()+1;
        Group new_group = new Group(lastId, group.getName());
        return groupRepository.add(new_group);
    }

    public Group updateGroup(Integer id, Group group) {
        Group oldGroup = groupRepository.getById(id);
        if (Objects.equals(oldGroup.getName(), group.getName())) {
            return oldGroup;
        }
        return groupRepository.updateGroup(group);
    }

    public void deleteGroupById(Integer id) {
        groupRepository.deleteGroupById(id);
    }
}
