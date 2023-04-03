package mapper;

import com.task.manager.task.domain.Task;
import com.task.manager.task.infrastructure.dto.TaskInputDto;
import com.task.manager.task.infrastructure.dto.mapper.AutoTaskMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class TestMapper {
    TaskInputDto taskInputDto = null;
    Task task =  null;

    @BeforeEach
    void setUp(){
        taskInputDto = new TaskInputDto("task1","review something else", LocalDateTime.now());


    }


    @Test
    @Tag("shouldAddPersonTest")
    @DisplayName("shouldAddPersonTest")
    void shouldAddPersonTest() throws Exception{
        //when
        task = AutoTaskMapper.INSTANCE.mapToTaskInputDtoToTask(taskInputDto);
        //then
        assertThat(task).isNotNull();
        assertThat(task.getTitle()).isEqualTo("task1");
        assertThat(task.getDescription()).isEqualTo("review something else");


    }
}
