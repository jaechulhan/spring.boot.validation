package net.prolancer.validation.common.contants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

    @Value( "${upload.file.path}" )
    public String uploadFilePath;

}
