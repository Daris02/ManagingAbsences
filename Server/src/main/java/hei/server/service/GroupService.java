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
        return groupRepository.findAll();
    }

    public Group findGroupById(Integer id) {
        if (groupRepository.getReferenceById(id).getId() == null) {
            return null;
        }
        return groupRepository.getReferenceById(id);
    }

    public Group addNewGroup(Group group) {
        List<Group> groups = groupRepository.findAll();
        Integer lastId = groups.size()+1;
        Group new_group = new Group(lastId, group.getName());
        return groupRepository.save(new_group);
    }

    public Group updateGroup(Group group) {
        Group oldGroup = groupRepository.getReferenceById(group.getId());
        if (group.getName() != null &&  !Objects.equals(oldGroup.getName(), group.getName())) {
            return groupRepository.updateGroupById(group);
        }
        return oldGroup;
    }

    public String deleteGroupById(Integer id) {
        if (groupRepository.getReferenceById(id).getId() == null) {
            return "Group not exist";
        }
        groupRepository.deleteById(id);
        return "Delete group successfully";
    }
}
