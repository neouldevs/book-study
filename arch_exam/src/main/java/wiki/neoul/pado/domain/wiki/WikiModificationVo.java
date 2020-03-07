package wiki.neoul.pado.domain.wiki;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WikiModificationVo {

    private String title;
    private String content;

    @Builder
    public WikiModificationVo(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
