package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldFetchTasksLists() throws Exception {
        //Given
        Task task = new Task(1L, "test task", "test to do");
        Task task2 = new Task(2L, "test task2", "learn Java");

        TaskDto taskDto = new TaskDto(1L, "test task", "test to do");
        TaskDto taskDto2 = new TaskDto(2L, "test task2", "learn Java");

        List<TaskDto> taskListDto = Arrays.asList(taskDto, taskDto2);

        List<Task> taskList = Arrays.asList(task, task2);

        when(service.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskListDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].id" , is(1)))
                .andExpect(jsonPath("$.[0].title", is("test task")))
                .andExpect(jsonPath("$.[0].content", is("test to do")))
                .andExpect(jsonPath("$.[1].id", is(2)))
                .andExpect(jsonPath("$.[1].title", is("test task2")))
                .andExpect(jsonPath("$.[1].content", is("learn Java")));
    }

    @Test
    public void shouldFetchEmptyTasksLists() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();

        when(service.getAllTasks()).thenReturn(new ArrayList<>());
        when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(taskDtoList);

        //When &Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchTask() throws Exception {
        //given
        Task task = new Task(1L, "test task", "test to do");

        TaskDto taskDto = new TaskDto(1L, "test task", "test to do");

        when(service.getTask(1L)).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test task")))
                .andExpect(jsonPath("$.content", is("test to do")));
    }

    @Test
    public void testDeleteTask() throws Exception {
        //Given
        Task task = new Task(1L, "test task", "test to do");

        when(service.getTask(task.getId())).thenReturn(Optional.of(task));
        doNothing().when(service).deleteTask(task.getId());

        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void testUpdateTask() throws Exception {
            //Given
            Task task = new Task(1L, "test task", "test to do");

            TaskDto taskDto = new TaskDto(1L, "test task", "test to do");

            when(taskMapper.mapToTask(taskDto)).thenReturn(task);
            when(service.saveTask(task)).thenReturn(task);
            when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

            Gson gson = new Gson();
            String jsonContent = gson.toJson(taskDto);

            //When & Then
            mockMvc.perform(put("/v1/task/updateTask?taskId=1").contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding(("UTF-8"))
                    .content(jsonContent))
                    .andExpect(status().is(200));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        Task task = new Task(1L, "test task", "test to do");

        TaskDto taskDto = new TaskDto(1L, "test task", "test to do");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(service.saveTask(any(Task.class))).thenReturn(task);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(("UTF-8"))
                .content(jsonContent))
                .andExpect(status().is(200));
    }
}
