package hei.server.service;

import hei.server.model.Group;
import hei.server.repository.GroupRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

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

    public Group updateGroup(Group group) {
        Group oldGroup = groupRepository.getById(group.getId());
        if (Objects.equals(oldGroup.getName(), group.getName())) {
            return oldGroup;
        }
        return groupRepository.updateGroup(group);
    }

    public String deleteGroupById(Integer id) {
        if (groupRepository.getById(id).getId() == null) {
            return "Group not exist";
        }
        groupRepository.deleteGroupById(id);
        return "Delete group successfully";
    }
}
