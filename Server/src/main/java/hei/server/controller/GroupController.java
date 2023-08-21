package hei.server.controller;

import hei.server.model.Group;
import hei.server.service.GroupService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path="/groups")
public class GroupController {
    private final GroupService groupService;

    @GetMapping
    public List<Group> findAllGroups() {
        return groupService.findAllGroups();
    }

    @GetMapping("/{id}")
    public Group findGroupById(@PathVariable Integer id) {
        return groupService.findGroupById(id);
    }

    @PostMapping
    public Group addNewGroup(@RequestBody Group group) {
        return groupService.addNewGroup(group);
    }

    @PutMapping
    public Group updateGroup(@RequestBody Group group) {
        return groupService.updateGroup(group);
    }

    @DeleteMapping("/{id}")
    public String deleteGroupById(@PathVariable Integer id) {
        return groupService.deleteGroupById(id);
    }
}
