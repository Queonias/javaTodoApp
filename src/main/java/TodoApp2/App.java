/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package TodoApp2;

import controller.ProjectController;
import controller.TaskController;
import java.util.Date;
import java.util.List;
import model.Project;
import model.Task;

public class App {
   

    public static void main(String[] args) {
              
//        ProjectController projectController = new ProjectController();
//        
//        Project project = new Project();
//        project.setName("Projeto teste");
//        project.setDescription("description");
//        projectController.save(project);
//        
        
//        ProjectController projectController = new ProjectController();
//        
//        Project project = new Project();
//        project.setId(1);
//        project.setName("Novo nome do projeto");
//        project.setDescription("description");
//        projectController.update(project);
//        
//        List<Project> projects = projectController.getAll();
//        System.out.println("Total de projetos: " + projects.size());
//        
//        projectController.removeById(1);

          TaskController taskController = new TaskController();
          
          Task task = new Task();
          task.setId(5);
          task.setIdProject(2);
          task.setName("Criar as telas da aplicação");
          task.setDescription("Devem ser criadas telas para cadastro");
          task.setNotes("Sem notas");
          task.setIsCompleted(false);
          task.setDeadline(new Date());
          
//          taskController.save(task);
          
          task.setName("alterado");
          
//          taskController.update(task);

          List<Task> tasks = taskController.getAll();
          System.out.println("Total de projetos: " + tasks.size());
//        
          taskController.removeById(2);
    }
}
