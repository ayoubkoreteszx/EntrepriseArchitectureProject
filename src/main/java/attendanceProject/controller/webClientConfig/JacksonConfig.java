package attendanceProject.controller.webClientConfig;
import attendanceProject.domain.Faculty;
import attendanceProject.domain.Student;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        TypeResolverBuilder<?> typer = new StdTypeResolverBuilder()
                .init(JsonTypeInfo.Id.NAME, null)
                .inclusion(JsonTypeInfo.As.PROPERTY)
                .typeProperty("type");

        objectMapper.setDefaultTyping(typer);

        SimpleModule module = new SimpleModule();
        module.registerSubtypes(new NamedType(Student.class, "student"));
        module.registerSubtypes(new NamedType(Faculty.class, "faculty"));

        objectMapper.registerModule(module);
        return objectMapper;
    }
}