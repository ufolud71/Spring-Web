package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {

    @InjectMocks
    TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        Assert.assertNotNull(task);
        Assert.assertEquals(1l, task.getId(), 0);
        Assert.assertEquals("title", task.getTitle());
        Assert.assertEquals("content", task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(2L, "task", "content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        Assert.assertNotNull(task);
        Assert.assertEquals(2l, taskDto.getId(), 0);
        Assert.assertEquals("task", taskDto.getTitle());
        Assert.assertEquals("content", taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task task = new Task(3L, "task", "content");
        List<Task> taskList = Arrays.asList(task);

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        Assert.assertNotNull(taskDtoList);
        Assert.assertEquals(1, taskDtoList.size());
        taskDtoList.forEach(taskDto -> {
            assertEquals(3L, taskDto.getId(), 0);
            assertEquals("task", taskDto.getTitle());
            assertEquals("content", taskDto.getContent());
        });
    }
}
