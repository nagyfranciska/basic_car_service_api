package log;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import org.slf4j.Logger;

import java.lang.reflect.Field;

public class SLF4JTypeListener implements TypeListener {

    @Override
    public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {

        Class<?> cls = typeLiteral.getRawType();

        for (Field field : cls.getDeclaredFields()) {
            if (field.getType() == Logger.class
                    && field.isAnnotationPresent(Log.class)) {
                typeEncounter.register(new SLF4JMembersInjector<I>(field));
            }
        }
    }
}
