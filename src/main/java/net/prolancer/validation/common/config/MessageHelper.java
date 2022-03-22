package net.prolancer.validation.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
public class MessageHelper {

    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
    }

    /**
     * Get message
     * @param code
     * @return
     */
    public String get(String code) {
        return this.get(code, null);
    }

    /**
     * Get message
     * @param code
     * @param args
     * @return
     */
    public String get(String code, @Nullable Object[] args) {
        return accessor.getMessage(code, args);
    }

}
