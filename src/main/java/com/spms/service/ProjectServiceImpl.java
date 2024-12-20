package com.spms.service;

import com.spms.model.Chat;
import com.spms.model.Project;
import com.spms.model.User;
import com.spms.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @Override
    public Project createProject(Project project, User user) throws Exception {

        Project createdProject = new Project();

        createdProject.setOwner(user);
        createdProject.setTags(project.getTags());
        createdProject.setName(project.getName());
        createdProject.setCategory(project.getCategory());
        createdProject.setDescription(project.getDescription());
        createdProject.getTeam().add(user);

        Project savedProject = projectRepo.save(createdProject);

        Chat chat = new Chat();
        chat.setProject(savedProject);

        Chat projectChat = chatService.createChat(chat);
        savedProject.setChat(projectChat);

        return savedProject;
    }

    @Override
    public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {

        List<Project> projects = projectRepo.findByTeamContainingOrOwner(user, user);

        if ( category != null )
            projects = projects.stream().filter(
                    project -> project.getCategory().equals(category))
                    .toList();

        if (tag != null)
            projects = projects.stream().filter(
                    project -> project.getTags().contains(tag))
                    .toList();

        return projects;
    }

    @Override
    public Project getProjectById(Long projectId) throws Exception {
        Optional<Project> optionalProject = projectRepo.findById(projectId);

        if (optionalProject.isEmpty())
            throw new Exception("Project not found with Id = " + projectId);

        return optionalProject.get();
    }

    @Override
    public void deleteProject(Long projectId, Long userId) throws Exception {

        getProjectById(projectId);

        projectRepo.deleteById(projectId);

    }
//
//    @Override
//    public Project updateProject(Project updateProject, Long id) throws Exception {
//        return null;
//    }
//
//    @Override
//    public void addUserToProject(Long projectId, Long userId) throws Exception {
//
//    }
//
//    @Override
//    public void removeUserFromProject(Long projectId, Long userId) throws Exception {
//
//    }
//
//    @Override
//    public Chat getChatByProjectId(Long projectId) throws Exception {
//        return null;
//    }
}



