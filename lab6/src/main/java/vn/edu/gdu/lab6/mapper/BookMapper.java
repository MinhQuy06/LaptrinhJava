package vn.edu.gdu.lab6.mapper;
import vn.edu.gdu.lab6.dto.BookCreateDto;
import vn.edu.gdu.lab6.dto.BookResponseDto;
import vn.edu.gdu.lab6.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookResponseDto toResponseDto(Book book);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Book toEntity(BookCreateDto createDto);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "internalNotes", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(BookCreateDto dto, @MappingTarget Book entity);
}