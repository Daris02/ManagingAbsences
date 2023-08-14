package hei.server.controller;

import hei.server.model.Group;
import hei.server.model.Student;
import hei.server.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/groups")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

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

    @PutMapping("/{id}")
    public Group updateGroup(@PathVariable Integer id, @RequestBody Group group) {
        return groupService.updateGroup(id, group);
    }

    @DeleteMapping("/{id}")
    public String deleteGroupById(@PathVariable Integer id) {
        groupService.deleteGroupById(id);
        return "Delete group successfully";
    }
}
