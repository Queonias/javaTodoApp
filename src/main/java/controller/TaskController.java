/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.Task;
import view.ConnectionFactory;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author queones
 */
public class TaskController {

    public void save(Task task) {
        String sql = "INSERT INTO tasks ("
                + "idProjects, "
                + "name, "
                + "description, "
                + "completed, "
                + "notes, "
                + "deadline, "
                + "updatedAt, "
                + "createdAt) values (?,?,?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement statement = null;

        try {
             //Estabelecendo a conexão com o banco de dados
            conn = ConnectionFactory.getConnection();
            
            // Preparando a query
            statement = conn.prepareStatement(sql);
            
            //Setando os valores do statement
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            
            // Executando a query
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar a tarefa" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }

    }

    public void update(Task task) {
        String sql = "UPDATE tasks SET "
                + "idProjects = ?, "
                + "name = ?, "
                + "description = ?, "
                + "completed = ?, "
                + "notes = ?, "
                + "deadline = ?, "
                + "updatedAt = ?, "
                + "createdAt = ? "
                + "WHERE idtasks = ?";

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            //Estabelecendo a conexão com o banco de dados
            conn = ConnectionFactory.getConnection();
            
            // Preparando a query
            statement = conn.prepareStatement(sql);
            
            //Setando os valores do statement
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            // Executando a query
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar a tarefa" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }

    public void removeById(int taskId) {

        String sql = "DELETE FROM tasks WHERE idtasks = ?";

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            //Estabelecendo a conexão com o banco de dados
            conn = ConnectionFactory.getConnection();
            
            // Preparando a query
            statement = conn.prepareStatement(sql);
            statement.setInt(1, taskId);
            
            // Executando a query
            statement.execute();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar a tarefa");
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }

    }

    public List<Task> getAll() {
        String sql = "SELECT * FROM tasks";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        //Lista de tarefas que será devolvida quando a chamada do metodo acontecer
        List<Task> tasks = new ArrayList<>();

        try {
            //Estabelecendo a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            // Preparando a query
            statement = connection.prepareStatement(sql);
            
            //Valor retornado pela execução da query
            resultSet = statement.executeQuery();

            //Enquanto houverem valores a serem pecorridos no meu resultSet
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("idtasks"));
                task.setIdProject(resultSet.getInt("idProjects"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setIsCompleted(resultSet.getBoolean("completed"));
                task.setNotes(resultSet.getString("notes"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updatedAt"));

                tasks.add(task);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar a tarefa" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
//        Lista de tarefas que foi criada e carregada no banco de dados
        return tasks;
    }

}
