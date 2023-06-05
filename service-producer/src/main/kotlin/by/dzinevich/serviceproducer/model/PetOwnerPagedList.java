package by.dzinevich.serviceproducer.model;

import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PetOwnerPagedList extends PageImpl<PetOwnerDto> {
    public PetOwnerPagedList(List<PetOwnerDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public PetOwnerPagedList(List<PetOwnerDto> content) {
        super(content);
    }
}
