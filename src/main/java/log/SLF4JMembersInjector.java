package log;

import com.google.inject.MembersInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class SLF4JMembersInjector<T> implements MembersInjector<T> {

    private final Field field;
    private final Logger logger;

    SLF4JMembersInjector(Field field) {

        this.field = field;
        this.logger = LoggerFactory.getLogger(field.getDeclaringClass());
        field.setAccessible(true);
    }

    @Override
    public void injectMembers(Object o) {
        try {
            field.set(o, logger);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
