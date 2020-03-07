package wiki.neoul.pado.service;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wiki.neoul.pado.domain.wiki.WikiModificationVo;

import java.util.Objects;

@Getter
@NoArgsConstructor
public class WikiModifyRequest implements Validatable {

    private String wikiId;

    private String title;

    private String content;

    @Builder
    public WikiModifyRequest(String wikiId, String title, String content) {
        this.wikiId = wikiId;
        this.title = title;
        this.content = content;
    }

    @Override
    public void assertValidation() throws InvalidParameterException {
        try {
            Objects.requireNonNull(wikiId);
            Objects.requireNonNull(title);
            Objects.requireNonNull(content);
        } catch (NullPointerException npe) {
            throw new InvalidParameterException("Modification Parameter is wrong");
        }
    }

    public WikiModificationVo toValueObject() {
        return WikiModificationVo
                .builder()
                .title(title)
                .content(content)
                .build();
    }
}
