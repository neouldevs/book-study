package wiki.neoul.pado.domain.wiki;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Wiki {

    @Id
    private String id = UUID.randomUUID().toString();

    private String title;

    private String content;

    @Builder
    public Wiki(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(WikiModificationVo vo) {
        this.title = vo.getTitle();
        this.content = vo.getContent();
    }
}
