package it.mainPr.mapper;

import it.mainPr.dto.HeartResponseDto;
import it.mainPr.model.Heart;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-01T01:40:17+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class HeartMapperImpl implements HeartMapper {

    @Override
    public List<HeartResponseDto> heartToHeartResponseDtos(List<Heart> hearts) {
        if ( hearts == null ) {
            return null;
        }

        List<HeartResponseDto> list = new ArrayList<HeartResponseDto>( hearts.size() );
        for ( Heart heart : hearts ) {
            list.add( heartToHeartResponseDto( heart ) );
        }

        return list;
    }
}
